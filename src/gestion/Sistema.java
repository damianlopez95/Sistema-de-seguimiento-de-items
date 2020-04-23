package gestion;

import java.util.ArrayList;
import java.util.HashMap;

import dominioproyecto.Estado;
import dominioproyecto.Item;
import dominioproyecto.Proyecto;
import dominioproyecto.TipoItem;
import dominioproyecto.Usuario;
import persistencia.Datos;

public class Sistema {

	private HashMap<String,Usuario> usuarios;
	private HashMap<String,TipoItem> tipoItems;
	private HashMap<String,Proyecto> proyectos;
	private Datos datos;
	
	public Sistema() {
		datos = new Datos();
		usuarios = new HashMap<String,Usuario>();
		tipoItems = new HashMap<String,TipoItem>();
		proyectos = new HashMap<String,Proyecto>();
		cargarDatos();
	}
	
	private void cargarDatos() {
		usuarios = datos.cargarUsuarios(usuarios);
		tipoItems = datos.cargarTipoItems(tipoItems);
		proyectos = datos.cargarProyectos(proyectos);
	}
	
	private void actUsuarios() {
		datos.actualizarUsuarios(usuarios);
	}
	
	private void actTipoItems() {
		datos.actualizarTipoItems(tipoItems);
	}
	
	private void actProyectos() {
		datos.actualizarProyectos(proyectos);
	}
	
	public ArrayList<String> getListProyectos() {
		ArrayList<String> lista = new ArrayList<String>(); 
		for (String key : proyectos.keySet()) { 
			lista.add(key);
		}
		return lista;
	}
	
	public ArrayList<String> getListUsuarios() {
		ArrayList<String> lista = new ArrayList<String>(); 
		for (String key : usuarios.keySet()) { 
			lista.add(key);
		}
		return lista;
	}
	
	public ArrayList<String> getListTipoItems() {
		ArrayList<String> lista = new ArrayList<String>(); 
		for (String key : tipoItems.keySet()) { 
			lista.add(key);
		}
		return lista;
	}
	
	//Obtener lista de nombres id de items de un proyecto 
	public ArrayList<String> getListItems(String proyecto) {
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<Item> listaItems = proyectos.get(proyecto).getItems();
		for (Item i : listaItems) {
			lista.add(i.getNombre());
		}
		return lista;
	}
	
	//Obtener lista de proyectos que integra un usuario determinado
	public ArrayList<String> getListProyUser(String usuario) {
		return usuarios.get(usuario).getProyectos();
	}
	
	public void addProyecto(String nombre, String creador) {
		proyectos.put(nombre, new Proyecto(nombre,usuarios.get(creador)));
		usuarios.get(creador).addProyecto(nombre);
		actProyectos();
		actUsuarios();
	}
	
	public void addUser(String nombre, String id, String password) {
		usuarios.put(id, new Usuario(nombre,id,password));
		actUsuarios();
	}
	
	//Para pruebas
	public void eliminarUsers() {
		usuarios.clear();
		actUsuarios();
	}
	
	public void eliminarProyectos() {
		proyectos.clear();
		actProyectos();
	}
	
	public void eliminarTI() {
		tipoItems.clear();
		actTipoItems();
	}
	//
	
	public boolean isCorrectPassword(String user, String password) {
		return usuarios.get(user).getPassword().equals(password);
	}
	
	public boolean addUserProyecto(String usuario, String proyecto) {
		if (usuarios.get(usuario).existeProyecto(proyecto)) {
			return false; //Ya existe
		} else {
			usuarios.get(usuario).addProyecto(proyecto);
			usuarios.get(usuario).addNotificacion("Has sido integrado al proyecto " + proyecto);
			proyectos.get(proyecto).agregarIntegrante(usuarios.get(usuario));
			actUsuarios();
			actProyectos();
			return true; //Agregado
		}
	}
	
	public ArrayList<String> getIntegrantesProyecto(String proyecto) {
		return  proyectos.get(proyecto).getIntegrantes();
	}
	
	public void asignarRespItem(String integrante, String proyecto, String item) {
		proyectos.get(proyecto).asignarResponsableItem(item,usuarios.get(integrante));
		usuarios.get(integrante).addNotificacion("Has sido asignado como responsable del item " + item + " para el proyecto " + proyecto);
		usuarios.get(integrante).addTipoItem(proyectos.get(proyecto).getItem(item).getTipo().getNombre());
		actUsuarios();
		actProyectos();
	}
	
	public ArrayList<String> getTiposItemUser(String user) {
		return usuarios.get(user).getTiposItem();
	}
	
	public void crearProyecto(String nombre, String creador) {
		proyectos.put(nombre, new Proyecto(nombre, usuarios.get(creador)));
		actProyectos();
	}
	
	public void crearItem(String proyecto, String nombre, String tipoitem, int prioridad) {
		proyectos.get(proyecto).agregarItem(new Item(nombre,tipoItems.get(tipoitem),prioridad,proyectos.get(proyecto).getLider()));
		actProyectos();
	}
	
	public void crearTipoItem(String nombre, String creador) {
		tipoItems.put(nombre, new TipoItem(nombre,usuarios.get(creador)));
		actTipoItems();
	}
	
	public String getRespActual(String proyecto, String item) {
		return proyectos.get(proyecto).getResponsable(item);
	}
	
	public String getCreadorTipoItem(String ti) {
		return tipoItems.get(ti).getCreador();
	}
	
	public ArrayList<String> getEstadosTipoItem(String tipoitem) {
		return tipoItems.get(tipoitem).getEstados();
	}
	
	public void agregarEstado(String tipoItem, String estado) {
		tipoItems.get(tipoItem).addEstado(new Estado(estado));
		actTipoItems();
	}
	
	public void agregarTransicion(String tipoitem, String inicial, String siguiente) {
		tipoItems.get(tipoitem).generarTransicion(inicial, siguiente);
		actTipoItems();
	}
	
	public ArrayList<String> getEstadosSig(String tipoitem, String estado) {
		return tipoItems.get(tipoitem).getEstadosSig(estado);
	}
	
	//Retorna el estado actual de un item en un proyecto
	public String getEstadoActual(String proyecto, String item) {
		return proyectos.get(proyecto).getItem(item).getEstadoActual().getNombre();
	}
	
	public boolean existeTransicion(String ti, String inicial, String siguiente) {
		return tipoItems.get(ti).getTransicionExistente(inicial,siguiente);
	}
	
	//Retorna el tipo de item de un item perteneciente a un proyecto
	public String getTipoItem(String proyecto, String item) {
		return proyectos.get(proyecto).getItem(item).getTipo().getNombre();
	}
	
	public void ejecutarTransicion(String proyecto, String item, String e1, String e2, String responsable) {
		proyectos.get(proyecto).getItem(item).cambiarEstado(e2);
		if (!proyectos.get(proyecto).getLider().getUserID().equals(responsable)) {
			String x = "Proyecto: " + proyecto + " / Item " + item + " / Responsable " + responsable + " cambia estado: " + e1 + " -> " + e2;
			usuarios.get(proyectos.get(proyecto).getLider().getUserID()).addNotificacion(x);
		}
		actProyectos();
		actUsuarios();
	}
	
	public void addNotificacionIng(String proyecto, String solicitante) {
		usuarios.get(proyectos.get(proyecto).getLider().getUserID()).addNotificacion("El usuario " + solicitante + " solicita permiso para integrarse al proyecto " + proyecto);
		actUsuarios();
	}
	
	public void finalizarEstado(String proyecto, String item) {
		proyectos.get(proyecto).finalizarEstado(item);
		actProyectos();
	}
	
	public ArrayList<String> getNotificaciones(String user) {
		ArrayList<String> aux = usuarios.get(user).getNotificaciones();
		actUsuarios(); //Así la próxima vez que se levanten los datos no siguen exisitiendo las notificaciones
		return aux;
	}
	
	public int getCargo(String proyecto, String user) {
		if (proyectos.get(proyecto).getLider().getUserID().equals(user)) {
			return 2; //líder
		} else {
			boolean aux = false;
			int nro = 0;
			ArrayList<Item> items = proyectos.get(proyecto).getItems();
			if (!items.isEmpty()) {
				while ((!aux) && (nro < items.size())) {
					if ((items.get(nro).getResponsable().getUserID().equals(user)) && (!items.get(nro).getEstadoActual().getNombre().equals("Terminado"))) {
						aux = true;
					}
					nro += 1;
				}
			}
				if (aux) {
					return 1; //Resp. de al menos un ítem
				} else {
					return 0; //Sin responsabilidades en el proyecto
				}
		}
	}
	
	//Solo en el caso de que el cargo sea "1"..
	public ArrayList<String> getItemsBajoResp(String proyecto, String user) {
		ArrayList<String> aux = new ArrayList<String>();
		for (Item i : proyectos.get(proyecto).getItems()) {
			if ((i.getResponsable().getUserID()).equals(user)) {
				aux.add(i.getNombre());
			}
		}
		return aux;
	}
	
	//Se busca el/los responsable/s en un estado y en una fecha determinada
	public ArrayList<String> getRespEnFecha(String proyecto, String item, String estado, String fecha) {
		return proyectos.get(proyecto).getItem(item).getRespFecha(estado,fecha);
	}
	
	//Registro no histórico completo. Muestra a los estados por los que pasó
	public ArrayList<String> getRegistroItems(String proyecto, String item) {
		return proyectos.get(proyecto).getItem(item).getEstados();
	}
	
	public ArrayList<String> getSecuenciaEstados(String proyecto, String item) {
		return proyectos.get(proyecto).getItem(item).getSecuenciaEstados();
	}
	
	public ArrayList<String> getRegHistorico(String proyecto, String item) {
		return proyectos.get(proyecto).getItem(item).getRegistro();
	}
	
}
package dominioproyecto;
import java.util.ArrayList;
import java.io.Serializable;

public class Proyecto implements Serializable {

	static final long serialVersionUID = 1L;

	private String nombre;
	private Usuario lider;
	private ArrayList<Item> items;
	private EquipoTrabajo equipodetrabajo;
	
	public Proyecto(String nombre, Usuario lider) {
		items = new ArrayList<Item>();
		equipodetrabajo = new EquipoTrabajo();
		equipodetrabajo.addIntegrante(lider);
		setNombre(nombre);
		setLider(lider);
	};

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getLider() {
		return lider;
	}

	public void setLider(Usuario lider) {
		this.lider = lider;
	}

	public Item getItem(String i) {
		for (Item aux : items) {
			if (aux.getNombre().equals(i)) {
				return aux; //salida segura
			}
		}
		return null; //no ocurre dado que el item elegido debe existir
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public ArrayList<String> getIntegrantes() {
		ArrayList<String> aux = new ArrayList<String>();
		for (Usuario u : equipodetrabajo.getIntegrantes()) {
			aux.add(u.getUserID());
		}
		return aux;
	}

	public void agregarItem(Item i) {
		items.add(i);
	}
	
	public void agregarIntegrante(Usuario id) {
		equipodetrabajo.addIntegrante(id);
	}
	
	public String getResponsable(String item) {
		return getItem(item).getResponsable().getUserID();
	}
	
	public void asignarResponsableItem(String item, Usuario integrante) {
		if (equipodetrabajo.getIntegrantes().contains(integrante)) {
			getItem(item).setResponsable(integrante);	//Existe ya que la lista de selección viene de equipodetrabajo
		}
	}

	public void finalizarEstado(String item) {
		getItem(item).finalizarEstado();
	}
}

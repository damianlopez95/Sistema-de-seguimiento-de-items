package dominioproyecto;
import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String userID;
	private String password;
	private ArrayList<String> notificaciones;
	
	//Experiencia del usuario
	private ArrayList<String> tipositem; //Muestra los t. de items en los cuales trabajó
	private ArrayList<String> proyectos; //Identificadores de aquellos proyectos en los que forma parte de su equipo de trabajo
	
	public Usuario(String n, String id, String pass) {
		notificaciones = new ArrayList<String>();
		proyectos = new ArrayList<String>();
		tipositem = new ArrayList<String>();
		setNombre(n);
		setUserID(id);
		setPass(pass);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPass(String pass) {
		password = pass;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setUserID(String id) {
		this.userID = id;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void addTipoItem(String ti) {
		if (!tipositem.contains(ti)) { 	//sin repetidos
			tipositem.add(ti);
		}
	}
	
	public ArrayList<String> getTiposItem() {
		return tipositem;
	}
	
	public void addNotificacion(String n) {
		notificaciones.add(n);
	}
	
	public ArrayList<String> getNotificaciones() {
		ArrayList<String> aux = new ArrayList<String>(notificaciones); //copia
		notificaciones.clear(); //se eliminan las existentes
		return aux;
	}
	
	public void addProyecto(String proyecto) {
		proyectos.add(proyecto);
	}
	
	public ArrayList<String> getProyectos() {
		return proyectos;
	}
	
	public boolean existeProyecto(String proyecto) {
		return proyectos.contains(proyecto);
	}
	
	public String toString() {
		return nombre + " " + userID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.toLowerCase().equals(other.userID.toLowerCase()))
			return false;
		return true;
	}
	
}

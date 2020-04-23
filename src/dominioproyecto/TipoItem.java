package dominioproyecto;
import java.io.Serializable;
import java.util.ArrayList;

public class TipoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private Usuario creador;
	private ArrayList<Estado> estados;
	
	public TipoItem(String nombre, Usuario creador) {
		setNombre(nombre);
		setCreador(creador);
		estados = new ArrayList<Estado>();
		addEstado(new Estado("Iniciado"));
		addEstado(new Estado("Terminado"));
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCreador() {
		return creador.getUserID();
	}
	
	public void setCreador(Usuario creador) {
		this.creador = creador;
	}
	
	public void addEstado(Estado e) {
		estados.add(e);
	}
	
	public boolean getTransicionExistente(String inicial, String siguiente) {
		return getEstado(inicial).esSig(getEstado(siguiente));
	}
	
	public Estado getEstadoIni() {
		return estados.get(0);
	}
	
	public Estado getEstado(String nombre) {
		for (Estado e : estados) {
			if (e.getNombre().equals(nombre)) {
				return e;
			}
		}
		return null;
	}
	
	public void generarTransicion(String inicial, String sig) {
		getEstado(inicial).addSig(getEstado(sig));
	}
	
	public ArrayList<String> getEstados() {
		ArrayList<String> aux = new ArrayList<String>();
		for (Estado e : estados) {
			aux.add(e.getNombre());
		}
		return aux;
	}
	
	public ArrayList<String> getEstadosSig(String estado) {
		ArrayList<String> aux = new ArrayList<String>();
		for (Estado e : getEstado(estado).getSig()) {
			aux.add(e.getNombre());
		}
		return aux;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		TipoItem other = (TipoItem) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.toLowerCase().equals(other.nombre.toLowerCase()))
			return false;
		return true;
	}

}

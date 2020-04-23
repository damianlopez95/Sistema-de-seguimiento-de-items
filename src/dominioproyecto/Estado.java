package dominioproyecto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estado implements Serializable {

	static final long serialVersionUID = 1L;
	private String nombre;
	private ArrayList<Estado> siguientes;
	
	public Estado(String nombre) {
		setNombre(nombre);
		siguientes = new ArrayList<Estado>();
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void addSig(Estado e) {
		siguientes.add(e);
	}
	
	public List<Estado> getSig() {
		return siguientes;
	}
	
	public boolean esSig(Estado sig) {
		return siguientes.contains(sig);
	}
	
	@Override
	public String toString() {
		return nombre;
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
		Estado other = (Estado) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.toLowerCase().equals(other.nombre.toLowerCase()))
			return false;
		return true;
	}
}

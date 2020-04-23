package dominioproyecto;
import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable{

	static final long serialVersionUID = 1L;
	
	private String nombre;
	private TipoItem tipo;
	private int prioridad;
	private RegistroHistorico registro;
	
	public Item(String n, TipoItem tipo, int prioridad, Usuario creador) {
		setNombre(n);
		setTipo(tipo);
		setPrioridad(prioridad);
		registro = new RegistroHistorico(tipo.getEstadoIni(),creador);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Estado getEstadoActual() {
		return registro.getEstadoActual();
	}
	
	public void setTipo(TipoItem tipo) {
		this.tipo = tipo;
	}
	
	public TipoItem getTipo() {
		return tipo;
	}
	
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
	public int getPrioridad() {
		return prioridad;
	}
	
	public Usuario getResponsable() {
		return registro.getResponsableActual();
	}

	public void setResponsable(Usuario responsable) {
		registro.setResponsable(responsable);
	}
	
	public ArrayList<String> getEstados() {
		ArrayList<String> aux = new ArrayList<String>();
		for (Estado e : registro.getEstados()) {
			aux.add(e.getNombre());
		}
		return aux;
	}
	
	public ArrayList<String> getSecuenciaEstados() {
		ArrayList<String> aux = new ArrayList<String>();
		for (Estado e : registro.getSecuenciaEstados()) {
			aux.add(e.getNombre());
		}
		return aux;
	}

	public ArrayList<String> getRegistro() {
		ArrayList<String> aux = new ArrayList<String>();
		for (RegistroEstado ri : registro.getRegistro()) {
			aux.add(ri.toString());
		}
		return aux;
	}
	
	public void cambiarEstado(String e) {
		registro.cambiarEstado(tipo.getEstado(e));
	}
	
	//Cuando el item llega al estado "Terminado" debería marcar la fecha final en el registro
	public void finalizarEstado() {
		registro.setFechaFinal();
	}
	
	public ArrayList<String> getRespFecha(String estado, String fecha) {
		return registro.getRespFecha(estado, fecha);
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
		Item other = (Item) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.toLowerCase().equals(other.nombre.toLowerCase()))
			return false;
		return true;
	}

}

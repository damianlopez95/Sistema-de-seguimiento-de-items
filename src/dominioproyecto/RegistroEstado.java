package dominioproyecto;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroEstado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario responsable;
	private Estado estado;
	private LocalDate desde;
	private LocalDate hasta;
	
	public RegistroEstado(Usuario resp, Estado e) {
		setResponsable(resp);
		setEstado(e);
		desde = LocalDate.now();
		hasta = null;
	}
	
	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void setFechaFinal() {
		hasta = LocalDate.now();
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public LocalDate getDesde() {
		return desde;
	}
	
	public Usuario getResponsable() {
		return responsable;
	}
	
	public boolean fechaValida(LocalDate fecha) {
		if (fecha.isAfter(desde) || fecha.isEqual(desde)) {
			if (hasta != null) {
				if (fecha.isBefore(hasta) || fecha.isEqual(hasta)) {
					return true;
				}
			} else { 
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String d = desde.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String h;
		if (hasta!=null) {
			h = hasta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} else {
			h = null;
		}
		return responsable.getUserID() + " es responsable del estado " + estado.getNombre()
			   + " desde " + d  + " hasta " + ((hasta!=null)? h : "la actualidad"); 
	}
}

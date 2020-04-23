package dominioproyecto;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RegistroHistorico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Estado> secuenciaEstados;
	private ArrayList<RegistroEstado> registro; 
	
	public RegistroHistorico(Estado estado, Usuario responsable) {
		secuenciaEstados = new ArrayList<Estado>();
		registro = new ArrayList<RegistroEstado>();
		addEstado(estado);
		registro.add(new RegistroEstado(responsable,estado)); 
	}
	
	public void addEstado(Estado estado) {
		secuenciaEstados.add(estado);
	}
	
	public void setFechaFinal() {
		registro.get(registro.size()-1).setFechaFinal();
	}
	
	public void setResponsable(Usuario responsable) {
		setFechaFinal();
		registro.add(new RegistroEstado(responsable,secuenciaEstados.get(secuenciaEstados.size()-1)));
	}
	
	public void cambiarEstado(Estado e) {
		addEstado(e);
		setResponsable(registro.get(registro.size()-1).getResponsable()); //sigue el mismo responsable
	}
	
	public ArrayList<Estado> getEstados() {
		ArrayList<Estado> aux = new ArrayList<Estado>();
		for (RegistroEstado reg : registro) {
			if (!aux.contains(reg.getEstado())) {
				aux.add(reg.getEstado());
			}
		}
		return aux; //Lista de estados sin repetir. No muestra la secuencia, sino el total de estados.
	}
	
	public ArrayList<Estado> getSecuenciaEstados() {
		return secuenciaEstados; //Lista con la secuencia de estados tal como sucedieron.
	}
	
	public ArrayList<RegistroEstado> getRegistro() {
		return registro; // Lista de registros con cada responsable, intervalo de fechas y estado.
	}
	
	public Estado getEstadoActual() {
		return secuenciaEstados.get(secuenciaEstados.size()-1);
	}
	
	public Usuario getResponsableActual() {
		return registro.get(registro.size()-1).getResponsable();
	}
	
	public ArrayList<String> getRespFecha(String estado,String fecha) {
		ArrayList<String> registros = new ArrayList<String>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		LocalDate date = LocalDate.parse(fecha, formatter);
		int aux = 0;
		LocalDate dateAux = registro.get(aux).getDesde();
		//Es posible que se hayan cambiado varios responsables en un día particular, entonces son varios en una misma fecha
		while ((aux<registro.size()) && ((dateAux.isBefore(date) || dateAux.isEqual(date)))) {
			if ((registro.get(aux).getEstado().getNombre()).equals(estado)) {
				if (registro.get(aux).fechaValida(date)) {
					registros.add(registro.get(aux).toString());
				}
			}
			dateAux = registro.get(aux).getDesde();
			aux += 1;
		}
		return registros;
	}
}

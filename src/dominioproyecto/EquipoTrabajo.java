package dominioproyecto;
import java.io.Serializable;
import java.util.ArrayList;

public class EquipoTrabajo implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Usuario> integrantes;
	
	public EquipoTrabajo() {
		integrantes = new ArrayList<Usuario>();
	}
	
	public void addIntegrante(Usuario nuevo) {
		integrantes.add(nuevo);
	}
	
	public ArrayList<Usuario> getIntegrantes() {
		return integrantes;
	}
	
}

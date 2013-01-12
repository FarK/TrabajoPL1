import java.util.List;
import java.util.ArrayList;

public class Consulta {
	public List<String> atributosConsulta;
	public String clase;
	public String atributoRestriccion;
	public Operador operador;
	public Atributo restriccion;

	public Consulta(){
		atributosConsulta = new ArrayList<String>();
		clase = "";
		atributoRestriccion = "";
		operador = null;
		restriccion = null;
	}

	public String toString(){
		return	"Atributos consulta: " + atributosConsulta + "\n" +
			"Clase = " + clase + "\n" +
			"Atributo restricción = " + atributoRestriccion + "\n" +
			"Operador = \"" + Operador.toString(operador) + "\"\n" +
			"Restricción = {" + restriccion + "}\n"
			;
	}
}

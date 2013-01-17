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

	public Boolean compruebaExp(Fichero fichero){
		Boolean result = false;

		for(Atributo atributo : fichero.atributos){
			if(atributo.nombre.equals(atributoRestriccion))
				result = result || compruebaExp(atributo);
		}

		return result;
	}

	private Boolean compruebaExp(Atributo atributo){
		Boolean result = false;

		if(atributo.tipo == restriccion.tipo)
			switch(operador){
				case EQ:
					switch(atributo.tipo){
						case NUM:
							result = atributo.valorNumero == restriccion.valorNumero;
						break;
						
						case TXT:
							result = atributo.valorCadena.equals(restriccion.valorCadena);
						break;
						
						case TIM:
							result = atributo.valorTiempo.equals(restriccion.valorTiempo);
						break;
					}
				break;

				case LT:
					switch(atributo.tipo){
						case NUM:
							result = atributo.valorNumero < restriccion.valorNumero;
						break;
						
						case TXT:
							result = false;
						break;
						
						case TIM:
							result = atributo.valorTiempo.before(restriccion.valorTiempo);
						break;
					}
				break;

				case GT:
					switch(atributo.tipo){
						case NUM:
							result = atributo.valorNumero > restriccion.valorNumero;
						break;
						
						case TXT:
							result = false;
						break;
						
						case TIM:
							result = atributo.valorTiempo.after(restriccion.valorTiempo);
						break;
					}
				break;

				case LE:
					switch(atributo.tipo){
						case NUM:
							result = atributo.valorNumero <= restriccion.valorNumero;
						break;
						
						case TXT:
							result = false;
						break;
						
						case TIM:
							result = atributo.valorTiempo.before(restriccion.valorTiempo) ||
							         atributo.valorTiempo.equals(restriccion.valorTiempo);
						break;
					}
				break;

				case GE:
					switch(atributo.tipo){
						case NUM:
							result = atributo.valorNumero >= restriccion.valorNumero;
						break;
						
						case TXT:
							result = false;
						break;
						
						case TIM:
							result = atributo.valorTiempo.after(restriccion.valorTiempo) ||
							         atributo.valorTiempo.equals(restriccion.valorTiempo);
						break;
					}
				break;

				default:
					return false;
			}
		else result = false;

		return result;
	}

	public List<String> ejecuta(Fichero fichero){
		List<String> result = new ArrayList<String>();

		for(String atributoC : atributosConsulta){
			if(atributoC.equals("nombre")){
				result.add(fichero.nombre);
			}
			else if(atributoC.equals("ruta")){
				result.add(fichero.ruta);
			}
			else{
				for(Atributo atributoF : fichero.atributos)
					if(atributoC.equals(atributoF.nombre))
						result.add(atributoF.toString());
			}
		}

		return result;
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

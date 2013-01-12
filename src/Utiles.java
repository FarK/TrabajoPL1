import java.util.List;
import java.util.ListIterator;

public class Utiles {
	public static Boolean compruebaAtributos(List<Tipo> aob, List<Tipo> aop, List<Atributo> atributos){
		Boolean atrCorrectos = true;

		if(aob.size() + aop.size() >= atributos.size()){
			ListIterator<Atributo> atrIt = atributos.listIterator();
			ListIterator<Tipo> aobIt = aob.listIterator();
			ListIterator<Tipo> aopIt = aop.listIterator();

			while(atrCorrectos && aobIt.hasNext()){
				atrCorrectos = atrIt.hasNext() &&
					atrIt.next().tipo == aobIt.next();
			}

			while(atrCorrectos && aopIt.hasNext() && atrIt.hasNext()){
				atrCorrectos = atrIt.next().tipo == aopIt.next();
			}
		}

		return atrCorrectos;
	}

	public static Boolean compruebaCamposConsulta(List<String> aob, List<String> aop, List<String> atributosConsulta){
		Boolean result = true;

		//Comprobamos que los atributos estan en alguna de las dos listas
		for(String atributo : atributosConsulta){
			//Evitamos el campo "nombre", porque siempre se puede consultar
			if(!atributo.equals("nombre")){
				Boolean atributoEnAob = false;
				Boolean atributoEnAop = false;

				for(String at_aob : aob)
					atributoEnAob = atributoEnAob || atributo.equals(at_aob);

				for(String at_aob : aop)
					atributoEnAop = atributoEnAop || atributo.equals(at_aob);

				result = result && (atributoEnAob || atributoEnAop);
			}
		}

		return result;
	}

	public static Boolean compruebaCampoConsulta(List<String> aob, List<String> aop, String atributoRestriccion){
		Boolean result = true;

		//Comprobamos que el atributo está en alguna de las dos listas
		//Evitamos el campo "nombre", porque siempre se puede consultar
		if(!atributoRestriccion.equals("nombre")){
			Boolean atributoEnAob = false;
			Boolean atributoEnAop = false;

			for(String at_aob : aob)
				atributoEnAob = atributoEnAob || atributoRestriccion.equals(at_aob);

			for(String at_aob : aop)
				atributoEnAop = atributoEnAop || atributoRestriccion.equals(at_aob);

			result = result && (atributoEnAob || atributoEnAop);
		}

		return result;
	}
}

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
}

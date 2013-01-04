import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ListIterator;

public class Utiles {
	//Devuelve el tipo del string que recibe como atributo
	public static Tipo getTipo(String atributo){
		Tipo tipo = null;

		try{
			Float.parseFloat(atributo);
			tipo = Tipo.NUM;
		}
		catch(NumberFormatException exc){
			DateFormat df = new SimpleDateFormat("mm:ss");
			try{
				df.parse(atributo);
				tipo = Tipo.TIM;
			}catch (ParseException e){
				tipo = Tipo.TXT;
			}
		}

		return tipo;
	}

	public static Boolean compruebaAtributos(List<Tipo> aob, List<Tipo> aop, List<String> atributos){
		Boolean atrCorrectos = true;

		if(aob.size() + aop.size() >= atributos.size()){
			ListIterator<String> atrIt = atributos.listIterator();
			ListIterator<Tipo> aobIt = aob.listIterator();
			ListIterator<Tipo> aopIt = aop.listIterator();

			while(atrCorrectos && aobIt.hasNext()){
				atrCorrectos = atrIt.hasNext() &&
					Utiles.getTipo(atrIt.next()) == aobIt.next();
			}

			while(atrCorrectos && aopIt.hasNext() && atrIt.hasNext()){
				atrCorrectos = Utiles.getTipo(atrIt.next()) == aopIt.next();
			}
		}

		return atrCorrectos;
	}
}

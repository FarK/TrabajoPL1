import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Fichero {
	public String ruta;
	public String nombre;
	public String extension;
	public List<String> atributos;

	public Fichero(){
		ruta = "";
		nombre = "";
		extension = "";
		atributos = null;
	}

	//Crea la ruta uniendo todos los elementos de la lista con "/" menos el
	//último, que lo guarda como nombre.
	public void addRuta(List<String> lRuta){
		nombre = lRuta.get(lRuta.size()-1);
		int i;
		for(i = 0 ; i < lRuta.size()-1 ; ++i)
			ruta += "/" + lRuta.get(i);
		nombre = lRuta.get(i);

	}

	//Devuelve el tipo del string que recibe como atributo
	public Tipo getTipo(String atributo){
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

	public String toString(){
		return	"Ruta:\t" + ruta + "\n" +
			"Nombre:\t" + nombre + "\n" +
			"Extensión:\t" + extension + "\n" +
			"Atributos:\t" + atributos + "\n";
	}
}

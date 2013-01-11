import java.util.List;
import java.util.ArrayList;

public class Fichero {
	public String ruta;
	public String nombre;
	public String extension;
	public List<Atributo> atributos;

	public Fichero(){
		ruta = "";
		nombre = "";
		extension = "";
		atributos = new ArrayList<Atributo>();
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

	public void addAtributo(String atributo){
		atributos.add(new Atributo(atributo));
	}

	public String toString(){
		return	"Ruta:\t" + ruta + "\n" +
			"Nombre:\t" + nombre + "\n" +
			"Extensión:\t" + extension + "\n" +
			"Atributos:\t" + atributos + "\n";
	}
}

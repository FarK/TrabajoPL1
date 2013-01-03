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

	public void addRuta(List<String> lRuta){
		nombre = lRuta.get(lRuta.size()-1);
		int i;
		for(i = 0 ; i < lRuta.size()-1 ; ++i)
			ruta += "/" + lRuta.get(i);
		nombre = lRuta.get(i);

	}

	public String toString(){
		return	"Ruta:\t" + ruta + "\n" +
			"Nombre:\t" + nombre + "\n" +
			"Extensión:\t" + extension + "\n" +
			"Atributos:\t" + atributos + "\n";
	}
}

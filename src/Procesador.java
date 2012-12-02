import java.io.*;
import antlr.*;

public class Procesador {
	public static void main(String args[]) {
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			
			Analex analex = new Analex(fis);
			Anasint anasint = new Anasint(analex);
			
			anasint.entrada();
			
		}catch(ANTLRException ae) {
			System.err.println(ae.getMessage());
		}catch(FileNotFoundException fnfe) {
			System.err.println("No se encontró el fichero");
		}
	}
}
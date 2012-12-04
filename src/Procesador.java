import java.io.*;
import antlr.*;
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;

public class Procesador {
	public static void main(String args[]) {
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			
			Analex analex = new Analex(fis);
			Anasint anasint = new Anasint(analex);
			anasint.entrada();
			AST arbol = anasint.getAST();

			ASTFrame display = new ASTFrame("Árbol", arbol);
			display.setVisible(true);
		}catch(ANTLRException ae) {
			System.err.println(ae.getMessage());
		}catch(FileNotFoundException fnfe) {
			System.err.println("No se encontró el fichero");
		}
	}
}

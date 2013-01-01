import java.io.*;
import antlr.*;
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;

public class Procesador {
	public static void main(String args[]) {
		try {
			FileInputStream fEsquemas = new FileInputStream(args[0]);
			
			LexerEsquemas lexerEsquemas = new LexerEsquemas(fEsquemas);
			ParserEsquemas parserEsquemas = new ParserEsquemas(lexerEsquemas);
			parserEsquemas.esquemas();
			AST arbol = parserEsquemas.getAST();
			EvaluadorEsquemas evaluadorEsquemas = new EvaluadorEsquemas();
			evaluadorEsquemas.entrada(arbol);

			ASTFrame display = new ASTFrame("Árbol", arbol);
			display.setVisible(true);
		}catch(ANTLRException ae) {
			System.err.println(ae.getMessage());
		}catch(FileNotFoundException fnfe) {
			System.err.println("No se encontró el fichero");
		}
	}
}

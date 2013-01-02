import java.io.*;
import antlr.*;
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;

public class Procesador {
	public static void main(String args[]) {
		try {
			//ANÁLISIS DE ESQUEMAS
			FileInputStream fEsquemas = new FileInputStream(args[0]);

			LexerEsquemas lexerEsquemas = new LexerEsquemas(fEsquemas);
			ParserEsquemas parserEsquemas = new ParserEsquemas(lexerEsquemas);
			EvaluadorEsquemas evaluadorEsquemas = new EvaluadorEsquemas();

			parserEsquemas.esquemas();
			AST arbolEsquemas = parserEsquemas.getAST();
			//evaluadorEsquemas.entrada(arbolEsquemas);

			ASTFrame displayEsquemas = new ASTFrame("Árbol Esquemas", arbolEsquemas);
			displayEsquemas.setVisible(true);

			//ANÁLISIS DE DATOS
			FileInputStream fDatos = new FileInputStream(args[1]);

			LexerDatos lexerDatos = new LexerDatos(fDatos);
			ParserDatos parserDatos = new ParserDatos(lexerDatos);
			//EvaluadorDatos evaluadorDatos = new EvaluadorDatos();

			parserDatos.datos();
			AST arbolDatos = parserDatos.getAST();
			//evaluadorDatos.datos(arbolDatos);

			ASTFrame displayDatos = new ASTFrame("Árbol Datos", arbolDatos);
			displayDatos.setVisible(true);

		}catch(ANTLRException ae) {
			System.err.println(ae.getMessage());
		}catch(FileNotFoundException fnfe) {
			System.err.println("No se encontró el fichero");
		}
	}
}

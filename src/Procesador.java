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

			parserEsquemas.esquemas();
			AST arbolEsquemas = parserEsquemas.getAST();

			//ANÁLISIS DE DATOS
			FileInputStream fDatos = new FileInputStream(args[1]);

			LexerDatos lexerDatos = new LexerDatos(fDatos);
			ParserDatos parserDatos = new ParserDatos(lexerDatos);

			parserDatos.datos();
			AST arbolDatos = parserDatos.getAST();

			//COMPROBACIÓN DE ESQUEMAS
			GeneraFicheros generaFicheros = new GeneraFicheros();
			CompruebaFicheros compruebaFicheros = new CompruebaFicheros();

			generaFicheros.datos(arbolDatos);

			Boolean esqCorrectos = true;
			for(Fichero fichero : generaFicheros.ficheros){
				esqCorrectos = compruebaFicheros.esquemas(arbolEsquemas,fichero)
					&&  esqCorrectos;
			}
			
			if(esqCorrectos)
				System.out.println("Todo correcto");
			else
				System.out.println("Error");

			//ANÁLISIS DE CONSULTAS
			FileInputStream fConsultas = new FileInputStream(args[2]);

			LexerConsultas lexerConsultas = new LexerConsultas(fConsultas);
			ParserConsultas parserConsultas = new ParserConsultas(lexerConsultas);

			parserConsultas.consultas();
			AST arbolConsultas = parserConsultas.getAST();

			//MOSTRAR ÁRBOLES
			ASTFrame displayEsquemas = new ASTFrame("Árbol Esquemas", arbolEsquemas);
			displayEsquemas.setVisible(true);

			ASTFrame displayDatos = new ASTFrame("Árbol Datos", arbolDatos);
			displayDatos.setVisible(true);

			ASTFrame displayConsultas = new ASTFrame("Árbol Consultas", arbolConsultas);
			displayConsultas.setVisible(true);

		}catch(ANTLRException ae) {
			System.err.println(ae.getMessage());
		}catch(FileNotFoundException fnfe) {
			System.err.println("No se encontró el fichero");
		}
	}
}

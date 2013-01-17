import java.io.*;

import antlr.*;
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;

import java.util.List;
import java.util.ArrayList;

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

			//COMPROBACIÓN DE FICHEROS
			GeneraFicheros generaFicheros = new GeneraFicheros();
			CompruebaFicheros compruebaFicheros = new CompruebaFicheros();

			generaFicheros.datos(arbolDatos);

			Boolean fichCorrectos = true;
			for(Fichero fichero : generaFicheros.ficheros){
				fichCorrectos = compruebaFicheros.esquemas(arbolEsquemas,fichero)
					&& fichCorrectos;
			}
			
			if(fichCorrectos)
				System.out.println("Todo los ficheros siguen los esquemas.");
			else
				System.out.println("Error semántico: algún fichero no sigue los esquemas.");

			//ANÁLISIS DE CONSULTAS
			FileInputStream fConsultas = new FileInputStream(args[2]);

			LexerConsultas lexerConsultas = new LexerConsultas(fConsultas);
			ParserConsultas parserConsultas = new ParserConsultas(lexerConsultas);

			parserConsultas.consultas();
			AST arbolConsultas = parserConsultas.getAST();

			//COMPROBACIÓN DE CONSULTAS
			GeneraConsultas generaConsultas = new GeneraConsultas();
			CompruebaCamposConsultas compruebaCamposConsultas = new CompruebaCamposConsultas();

			generaConsultas.consultas(arbolConsultas);

			Boolean consCorrectas = true;
			for(Consulta consulta : generaConsultas.consultas){
				consCorrectas = compruebaCamposConsultas.esquemas(arbolEsquemas, consulta)
					&&  consCorrectas;
			}

			if(consCorrectas)
				System.out.println("Todas las consultas son sobre campos existentes en los esquemas.");
			else
				System.out.println("Error semántico: alguna consulta es sobre un campo que no existe en los esquemas.");

			//EJECUTA CONSULTAS
			FiltraFichero filtraFichero = new FiltraFichero();

			int numConsulta = 1;
			for(Consulta consulta : generaConsultas.consultas){
				List<Fichero> ficherosFiltrados = new ArrayList<Fichero>();

				for(Fichero fichero : generaFicheros.ficheros){
					if(filtraFichero.esquemas(arbolEsquemas, consulta, fichero))
						ficherosFiltrados.add(fichero);
				}

				//System.out.println(ficherosFiltrados);
				for(Fichero fichero : ficherosFiltrados)
					System.out.println("C" + numConsulta++ + ": " + consulta.ejecuta(fichero));
			}

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

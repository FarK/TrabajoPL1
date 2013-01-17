TABAJO PL1
=========

Descripción y funcionalidades del trabajo
-----------------------------------------
La función del programa es reconocer un patrón de atributos, especificado por un esquema de clases, en los nombres (ruta incluida) de los ficheros que se le pasa como entrada; para posteriormente permitir realizar consultas sobre estos datos.

Hay tres entradas:

* *Datos.txt*: Lista de nombres de ficheros (uno por línea). En el nombre de los ficheros vienen codificados los atributos del mismo.
* *Esquemas.txt*: Define los tipos y la estructura de los atributos que se van a reconocer. Se clasificará los ficheros en clases según su extensión (lista de extensiones), y se comprobarán el orden y el tipo de sus atributos (se permiten tes tipos distintos y atributos optativos)
* *Consultas.txt*: Consultas sencillas en un lenguaje similar a SQL. Se permite consultar cualquier número de atributos. Se permite consultar el nombre y la ruta del fichero (no están contemplados en Esquema.txt como atributos, pues todos los ficheros los tienen). Se permite solo consultar por una clase y las restricciones son simples (no se permiten operadores lógicos and, or, not, etc)

Funcionamiento y flujo de ejecución
-----------------------------------
1. *Análisis de esquemas*: Análisis léxico y sintáctico de la entrada "Esquemas.txt". Se crea un árbol de sintáxis abstracta.
2. *Análisis de esquemas*: Análisis léxico y sintáctico de la entrada "Datos.txt". Se crea un árbol de sintáxis abstracta y una lista de clases "Fichero". Esta clase guarda la información relevante extraida del análisis sintáctico.
3. *Comprobación de ficheros*: Comprobación semántica de "Datos.txt". Se comprueba que los datos siguen los esquemas.
4. *Análisis de consultas*: Análisis léxico y sintáctico de la entrada "Consultas.txt". Se crea un árbol de sintáxis abstracta y una lista de clases "Consulta". Esta clase guarda la información relevante extraida del análisis sintáctico.
5. *Comprobación de consultas*: Comprobación semántica de "Consultas.txt". Se comprueba que las consultas siguen los esquemas (no se consulta ni se restringe un atributo inexistente).
6. *Ejecución de consultas*: Se muestra por pantalla el resultado de las consultas.


Dependencias de compilación de gramáticas
-----------------------------------------
	lexerDatos -> parserDatos -> generaFicheros

	                                    / compruebafichero
	                                    | compruebaCamposConsulta
	lexerEsquemas -> parserEsquemas -> <  
	                                    | filtraFichero
	                                    \ nombraAtributos
	
	lexerConsultas -> parserConsultas -> generaConsultas

Ejecución
---------
Las clases y los ficheros _\*.g_ van dentro de la carpeta _src_

El main está en la clase _Procesador_

Al main se han de pasar las entradas en este orden: "Esquemas.txt" "Datos.txt" "Consultas.txt"

/************************/
/* Analizador sintáctico /
/************************/
class CompruebaDatos extends TreeParser;
options{
	importVocab = ParserDatos;
}

datos: #(DATOS (fichero)*);

fichero: #(FICHERO ruta atributos extension);

ruta: #(RUTA (SECCION)+);

atributos: #(ATRIBUTOS (SECCION)+);

extension: #(EXTENSION SECCION);

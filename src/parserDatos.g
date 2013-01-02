/************************/
/* Analizador sintáctico /
/************************/
class ParserDatos extends Parser;
options{
	k=2;
	importVocab = LexerDatos;
	buildAST = true;
}

tokens {
	FICHERO;
	RUTA;
	ATRIBUTOS;
	EXTENSION;
}

datos: DATOS^ LLAVE_A! (fichero)* LLAVE_C!;

fichero: ruta SEPARADOR! atributos PUNTO! extension
       {#fichero = #(#[FICHERO, "FICHERO"], ##);}
     ;

ruta: BARRA! (SECCION BARRA!)* SECCION
      {#ruta = #(#[RUTA,"RUTA"], ##);}
    ;

atributos: SECCION (COMA! SECCION)*
           {#atributos = #(#[ATRIBUTOS,"ATRIBUTOS"], ##);}
         ;

extension: SECCION
           {#extension = #(#[EXTENSION,"EXTENSION"], ##);}
         ;

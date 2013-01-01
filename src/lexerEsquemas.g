/********************/
/* Analizador léxico /
/********************/
class LexerEsquemas extends Lexer;
options{
	k=1;
	charVocabulary = '\3'..'\377';
}

// Palabras reservadas
tokens {
	T_NUMERO="num";
	T_TEXTO="txt";
	T_TIEMPO="tim";
	
	ESQUEMAS="ESQUEMAS";
	DATOS="DATOS";
}

// Blancos
//TODO: Diferenciar entre los espacioes en blanco de las rutas y del resto de
//la entrada
protected SL : '\n' {newline();};
protected TAB: '\t';
BLANCO : (SL|TAB|' ') {$setType(Token.SKIP);};

// Comentarios
COMENTARIO: "#" (~'\n')* {$setType(Token.SKIP);};

// Constantes
protected DIGITO : '0'..'9';
NUMERO : (DIGITO)+('.'(DIGITO)+)?;

protected FICHERO : (~('/'|'\n'))*;
RUTA : ("/"FICHERO)+;

// Identificadores
//protected LETRA : 'A'..'Z'|'a'..'z'|á|é|í|ú|ñ|Á|É|Í|Ú|Ñ;
protected LETRA : 'A'..'Z'|'a'..'z';
IDENT: LETRA(LETRA|DIGITO)*;

//Agrupadores
LLAVE_A: '{';
LLAVE_C: '}';
CORCHETE_A: '[';
CORCHETE_C: ']';

///////////////////////////////
// Analizador léxico base
///////////////////////////////
class Analex extends Lexer;
options{
	importVocab = Anasint;
	k=2;
	charVocabulary = '\3'..'\377';
}

// Palabras reservadas
tokens {
	WHILE="while";
	IF="if";
}

// Blancos
protected NUEVA_LINEA : "\n" {newline();};
BLANCO : (' '|'\t'|NUEVA_LINEA) {$setType(Token.SKIP);};

// Comentarios
COML: "//" (~'\n')* {$setType(Token.SKIP);};
COMB: "/*" (options{greedy=false;}:(NUEVA_LINEA|.))* "*/" {$setType(Token.SKIP);};

// Constantes
protected DIGITO : '0'..'9';
NUMERO : (DIGITO)+('.'(DIGITO)+)?;
CADENA : '"' (~'"')* '"';

// Identificadores
protected LETRA : 'A'..'Z'|'a'..'z'|'_';
IDENT: LETRA(LETRA|DIGITO)*;

// Operadores
OPERADOR : '+'|'-'|'/'|'*'|'='|"=="|"!="|
         "<="|">="|'<'|'>'|"&&"|"||"|"!";

// Separadores y agrupadores
AGRUPADOR : '('|')'|'{'|'}';
SEPARADOR : ';'|',' ;

/********************/
/* Analizador léxico /
/********************/
class LexerConsultas extends Lexer;
options{
	k=2;
	charVocabulary = '\3'..'\377';
}

tokens{
	SELECT="SELECT";
	FROM="FROM";
	WHERE="WHERE";
}

// Blancos
//la entrada
protected SL : '\n' {newline();};
protected TAB: '\t';
BLANCO : (SL|TAB|' ') {$setType(Token.SKIP);};

// Comentarios
COMENTARIO: '#' (~'\n')* {$setType(Token.SKIP);};

// Identificadores
protected LETRA : 'A'..'Z'|'a'..'z';
protected DIGITO : '0'..'9';
IDENT: LETRA(LETRA|DIGITO)*;
TIEMPO: DIGITO DIGITO ':' DIGITO DIGITO;
NUMERO : (DIGITO)+('.'(DIGITO)+)?;
CADENA: '"'(~'"')*'"';

//Operadores
EQ: '=';
LT: '<';
GT: '>';
LE: "<=";
GE: ">=";

//Separadores
COMA: ',';
PYC: ';';

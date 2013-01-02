/********************/
/* Analizador léxico /
/********************/
class LexerDatos extends Lexer;
options{
	k=1;
	charVocabulary = '\3'..'\377';
}

tokens{
	DATOS="DATOS";
}

// Blancos
//la entrada
protected SL : '\n' {newline();};
BLANCO : (SL|'\t') {$setType(Token.SKIP);};

// Comentarios
COMENTARIO: '#' (~'\n')* {$setType(Token.SKIP);};

// Identificadores
SECCION: (~(
              '|'
            | ','
            | '.'
            | '/'
            | '\n'
            | '\t'
            | '#'
            | '{'
            | '}'
         ))+;
BARRA: '/';
SEPARADOR: '|';
COMA: ',';
PUNTO: '.';

//Agrupadores
LLAVE_A: '{';
LLAVE_C: '}';

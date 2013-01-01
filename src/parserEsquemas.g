/************************/
/* Analizador sintáctico /
/************************/
class ParserEsquemas extends Parser;
options{
	importVocab = LexerEsquemas;
	buildAST = true;
}

tokens{
	ENTRADA;
	ATRIBUTO;
}

entrada: esquemas
         {#entrada = #(#[ENTRADA,"ENTRADA"], ##);}
       ;

esquemas: ESQUEMAS^ LLAVE_A! (esquema)* LLAVE_C!;

esquema: IDENT^ LLAVE_A! (atributo)+ LLAVE_C!;
atributo: (CORCHETE_A!)? tipo IDENT^ (CORCHETE_C!)?;
tipo: T_NUMERO
    | T_TEXTO
    | T_TIEMPO;

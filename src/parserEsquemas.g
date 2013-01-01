/************************/
/* Analizador sintáctico /
/************************/
class ParserEsquemas extends Parser;
options{
	importVocab = LexerEsquemas;
	buildAST = true;
}

tokens{
	ATRIBUTO_OB;
	ATRIBUTO_OP;
}

esquemas: ESQUEMAS^ LLAVE_A! (esquema)* LLAVE_C!;
esquema: IDENT^ LLAVE_A! atributos LLAVE_C!;

atributos: atributos_ob atributos_op
         ;
atributos_ob: (atributo_ob)+
              {#atributos_ob = #(#[ATRIBUTO_OB, "ATRIBUTOS OBLIGATORIO"], ##);}
            ;
atributos_op: (atributo_op)*
              {#atributos_op = #(#[ATRIBUTO_OP, "ATRIBUTOS OPTATIVOS"], ##);}
            ;
atributo_ob: t:tipo IDENT
             {#atributo_ob = #(#t, ##);}
           ;
atributo_op: CORCHETE_A! t:tipo IDENT CORCHETE_C!
             {#atributo_op = #(#t, ##);}
           ;

tipo: T_NUMERO
    | T_TEXTO
    | T_TIEMPO;

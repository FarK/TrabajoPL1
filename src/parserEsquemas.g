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
esquema: IDENT^ LLAVE_A! aob:atributos_ob aop:atributos_op LLAVE_C!;

atributos_ob: (atributo_ob)+
              {#atributos_ob = #(#[ATRIBUTO_OB, "ATRIBUTOS OBLIGATORIO"], ##);}
            ;
atributos_op: (atributo_op)*
              {#atributos_op = #(#[ATRIBUTO_OP, "ATRIBUTOS OPTATIVOS"], ##);}
            ;
atributo_ob: t:tipo i:IDENT!
             {#atributo_ob = #(#t, #i);}
           ;
atributo_op: CORCHETE_A! t:tipo i:IDENT! CORCHETE_C!
             {#atributo_op = #(#t, #i);}
           ;

tipo: T_NUMERO^
    | T_TEXTO^
    | T_TIEMPO^;

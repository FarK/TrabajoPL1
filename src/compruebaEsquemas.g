/************/
/* Evaluador /
/************/
class CompruebaEsquemas extends TreeParser;
options{
	importVocab = ParserEsquemas;
}

{
	enum Tipo{NULL, NUMERO, TEXTO, TIEMPO};
}

esquemas: #(ESQUEMAS (esquema)*);

esquema: #(IDENT atributos_ob atributos_op);

atributos_ob: #(ATRIBUTO_OB (atributo)+);

atributos_op: #(ATRIBUTO_OP (atributo)+);

atributo: #(T_NUMERO IDENT)
        | #(T_TEXTO IDENT)
        | #(T_TIEMPO IDENT)
        ;

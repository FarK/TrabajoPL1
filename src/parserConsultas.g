/************************/
/* Analizador sintáctico /
/************************/
class ParserConsultas extends Parser;
options{
	k=1;
	importVocab = LexerConsultas;
	buildAST = true;
}

tokens {
	CONSULTAS;
	CONSULTA;
	ATRIBUTOS;
}

consultas: (consulta)+
           {#consultas = #(#[CONSULTAS, "CONSULTAS"], ##);}
         ;
consulta: SELECT! atributos FROM! clase WHERE! expresion PYC!
           {#consulta = #(#[CONSULTA, "CONSULTA"], ##);}
        ;

atributos: IDENT (COMA! IDENT)*
           {#atributos = #(#[ATRIBUTOS, "ATRIBUTOS"], ##);}
         ;

clase: IDENT;

expresion: i:IDENT! op:operador v:valor!
           {#expresion = #(#op, #i,#v);}
         ;

operador: EQ | LT | GT | LE | GE;
valor: CADENA | NUMERO | TIEMPO;

/************************/
/* Analizador sintáctico /
/************************/
class Anasint extends Parser;
options{
	importVocab = Analex;
	buildAST = true;
}

tokens{
	ENTRADA;
	ATRIBUTO;
}

entrada: esquemas datos
         {#entrada = #(#[ENTRADA,"ENTRADA"], ##);}
       ;

esquemas: ESQUEMAS^ LLAVE_A! (esquema)* LLAVE_C!;
datos: DATOS^ LLAVE_A! (RUTA)* LLAVE_C!;

esquema: IDENT^ LLAVE_A! (atributo)+ LLAVE_C!;
atributo: (CORCHETE_A!)? tipo IDENT (CORCHETE_C!)?
          {#atributo = #(##, ##);}
        ;
tipo: T_NUMERO
    | T_TEXTO
    | T_TIEMPO;

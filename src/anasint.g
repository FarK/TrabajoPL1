/************************/
/* Analizador sintáctico /
/************************/
class Anasint extends Parser;
options{
	importVocab = Analex;
}

entrada: esquemas datos;

esquemas: ESQUEMAS LLAVE_A (esquema)* LLAVE_C;
datos: DATOS LLAVE_A (RUTA)* LLAVE_C;

esquema: IDENT LLAVE_A (atributo)+ LLAVE_C;
atributo: (CORCHETE_A)? tipo IDENT (CORCHETE_C)?;
tipo: T_NUMERO
    | T_TEXTO
    | T_TIEMPO;

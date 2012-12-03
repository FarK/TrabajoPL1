///////////////////////////////
// Analizador sintáctico
///////////////////////////////
class Anasint extends Parser;
options{
	importVocab = Analex;
}

entrada: esquemas datos;

esquemas: ESQUEMA (SL)+ (TAB esquema)*;
datos: DATOS (SL)+ (TAB RUTA (SL)+)*;

esquema: IDENT (SL)+ (TAB TAB atributo)+;
atributo: (CORCHETE_A)? tipo IDENT (CORCHETE_C)?;
tipo: T_NUMERO
    | T_TEXTO
    | T_TIEMPO;

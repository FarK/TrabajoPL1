///////////////////////////////
// Analizador sintáctico
///////////////////////////////
class Anasint extends Parser;
options{
	importVocab = Analex;
	k=3;
}

saltos: (SL)+;
leb: (TAB)*SL;

entrada: (leb)* esquemas datos EOF;

esquemas: ESQUEMAS (leb)+ (TAB esquema)+;
datos: DATOS SL (TAB RUTA(SL TAB RUTA)*)?;

esquema: IDENT SL atributos;
atributos: TAB TAB atributo(SL TAB TAB atributo)*;
atributo: (CORCHETE_A)? tipo IDENT (CORCHETE_C)?
        | leb
        ;

tipo: t:T_NUMERO {System.out.println(t.getText());}
    | t2:T_TEXTO {System.out.println(t2.getText());}
    | t3:T_TIEMPO {System.out.println(t3.getText());}
    ;

/************/
/* Evaluador /
/************/
class EvaluadorEsquemas extends TreeParser;
options{
	importVocab = ParserEsquemas;
}

{
	enum Tipo{NULL, NUMERO, TEXTO, TIEMPO};
}

entrada: #(ENTRADA esquemas datos);

esquemas: #(ESQUEMAS (esquema)*);
datos: #(DATOS (RUTA)*);

esquema: #(i:IDENT (atributo)+)
    	{System.out.println(i.getText());}
       ;

atributo {Tipo t = Tipo.NULL;}: #(i:IDENT t=tipo)
            {
            	switch(t){
            		case NUMERO:
            			{System.out.println("Número: "+i.getText());};
            		break;

            		case TEXTO:
            			{System.out.println("Texto: "+i.getText());};
            		break;

            		case TIEMPO:
            			{System.out.println("Tiempo: "+i.getText());};
            		break;

            		case NULL:
            			{System.out.println("ERROR: No tipo");};
            		break;
            	}
            }
        ;
tipo returns [Tipo tipo = Tipo.NULL;]:
      T_NUMERO {tipo = Tipo.NUMERO;}
    | T_TEXTO {tipo = Tipo.TEXTO;}
    | T_TIEMPO {tipo = Tipo.TIEMPO;}
    ;

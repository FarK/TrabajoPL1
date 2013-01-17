header{
	import java.util.List;
	import java.util.ArrayList;
}

class CompruebaCamposConsultas extends TreeParser;
options{
	importVocab = ParserEsquemas;
}

esquemas[Consulta consulta] returns [Boolean consCorrecta = false] {Boolean b = false;}:
          #(ESQUEMAS (b=esquema[consulta]
                       {consCorrecta = consCorrecta || b;}
                     )*
           )
        ;

esquema[Consulta consulta] returns [Boolean consCorrecta = false] {List<String> aob, aop;}:
         #(e:IDENT . aob=atributos_ob aop=atributos_op)
           {
            if(e.getText().equals(consulta.clase)){
            	consCorrecta =	Utiles.compruebaCamposConsulta(aob, aop, consulta.atributosConsulta) &&
            			Utiles.compruebaCampoConsulta(aob, aop, consulta.atributoRestriccion)
            			;
            }
           }
       ;

atributos_ob returns [List<String> aob = new ArrayList<String>()] {String at;}:
              #(ATRIBUTO_OB (at=atributo {aob.add(at);} )+)
            ;

atributos_op returns [List<String> aob = new ArrayList<String>()] {String at;}:
              #(ATRIBUTO_OP (at=atributo {aob.add(at);} )+)
            ;

atributo returns [String atributo = ""]:
          #(T_NUMERO an:IDENT) {atributo = #an.getText();}
        | #(T_TEXTO atx:IDENT) {atributo = #atx.getText();}
        | #(T_TIEMPO at:IDENT) {atributo = #at.getText();}
        ;

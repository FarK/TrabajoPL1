header{
	import java.util.List;
	import java.util.ArrayList;
}

class GeneraConsultas extends TreeParser;
options{
	importVocab = ParserConsultas;
}

{public List<Consulta> consultas = new ArrayList<Consulta>();}

consultas {Consulta c;}:
           #(CONSULTAS (c=consulta
              {consultas.add(c);}
            )+)
         ;

consulta returns [Consulta c = new Consulta()]:
          #(CONSULTA atributos[c] i:IDENT expresion[c])
             {c.clase = #i.getText();}
        ;

atributos [Consulta c]:
           #(ATRIBUTOS a:IDENT {c.atributosConsulta.add(#a.getText());}
              (an:IDENT
                 {c.atributosConsulta.add(#an.getText());}
              )*
            )
         ;

expresion[Consulta consulta] {Atributo v;}:
           #(EQ i0:IDENT v=valor) {
                 consulta.atributoRestriccion = #i0.getText();
                 consulta.operador = Operador.EQ;
                 consulta.restriccion = v;
             }
         | #(LT i1:IDENT v=valor) {
                 consulta.atributoRestriccion = #i1.getText();
                 consulta.operador = Operador.LT;
                 consulta.restriccion = v;
             }
         | #(GT i2:IDENT v=valor) {
                 consulta.atributoRestriccion = #i2.getText();
                 consulta.operador = Operador.GT;
                 consulta.restriccion = v;
             }
         | #(LE i3:IDENT v=valor) {
                 consulta.atributoRestriccion = #i3.getText();
                 consulta.operador = Operador.LE;
                 consulta.restriccion = v;
             }
         | #(GE i4:IDENT v=valor) {
                 consulta.atributoRestriccion = #i4.getText();
                 consulta.operador = Operador.GE;
                 consulta.restriccion = v;
             }
         ;

valor returns [Atributo valor = new Atributo();]:
       c:CADENA {valor.setAtributo(#c.getText().replaceAll("\"", ""));}
     | n:NUMERO {valor.setAtributo(#n.getText());}
     | t:TIEMPO {valor.setAtributo(#t.getText());}
     ;

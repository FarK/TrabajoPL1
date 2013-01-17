header{
	import java.util.List;
	import java.util.ArrayList;
	import java.util.ListIterator;
}
class NombraAtributos extends TreeParser;
options{
	importVocab = ParserEsquemas;
}

esquema[Fichero fichero, Integer num_atr_ob, Integer num_atr_op]:
         #(IDENT . atributos_ob[fichero] atributos_op[fichero, num_atr_ob, num_atr_op])
       ;

atributos_ob[Fichero fichero] {Integer i = 0;}:
               #(ATRIBUTO_OB (atributo[fichero, i++])+)
            ;

atributos_op[Fichero fichero, Integer num_atr_ob, Integer num_atr_op] {Integer i = num_atr_ob;}:
               #(ATRIBUTO_OP (atributo[fichero, (i<fichero.atributos.size())? i++ : -1])+)
            ;

atributo [Fichero fichero, int i]:
          #(T_NUMERO in:IDENT)	{if(i >= 0) fichero.atributos.get(i).nombre = #in.getText();}
        | #(T_TEXTO itx:IDENT)	{if(i >= 0) fichero.atributos.get(i).nombre = #itx.getText();}
        | #(T_TIEMPO it:IDENT)	{if(i >= 0) fichero.atributos.get(i).nombre = #it.getText();}
        ;

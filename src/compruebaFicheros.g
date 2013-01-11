header{
	import java.util.List;
	import java.util.ArrayList;
	import java.util.ListIterator;
}
/************/
/* Evaluador /
/************/
class CompruebaFicheros extends TreeParser;
options{
	importVocab = ParserEsquemas;
}

esquemas[Fichero fichero] returns [Boolean esqCorrecto = false] {Boolean b = false;}:
          #(ESQUEMAS (b=esquema[fichero]
                        {esqCorrecto = esqCorrecto || b;}
                     )*)
        ;

esquema[Fichero fichero] returns [Boolean esqCorrecto = false] {Boolean ext; List<Tipo> aob,aop;}:
         #(esq:IDENT ext=extensiones[fichero.extension] aob=atributos_ob aop=atributos_op)
         {esqCorrecto = ext && Utiles.compruebaAtributos(aob, aop, fichero.atributos);}
       ;

extensiones[String extension] returns [Boolean extB = false]:
              #(EXTENSIONES (ext:IDENT
                 {extB = extB || (#ext.getText().equals(extension));}
              )+)
           ;

atributos_ob returns [List<Tipo> atributos = new ArrayList()] {Tipo tipo = Tipo.TXT;}:
               #(ATRIBUTO_OB
                (tipo=atributo
                 {atributos.add(tipo);}
                )+)
            ;

atributos_op returns [List<Tipo> atributos = new ArrayList()] {Tipo tipo = Tipo.TXT;}:
               #(ATRIBUTO_OP
                (tipo=atributo
                 {atributos.add(tipo);}
                )+)
            ;

atributo returns [Tipo tipo = Tipo.TXT;]:
          #(T_NUMERO IDENT)	{tipo = Tipo.NUM;}
        | #(T_TEXTO IDENT)	{tipo = Tipo.TXT;}
        | #(T_TIEMPO IDENT)	{tipo = Tipo.TIM;}
        ;

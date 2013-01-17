class FiltraFichero extends TreeParser;
options{
	importVocab = ParserEsquemas;
}

esquemas[Consulta consulta, Fichero fichero] returns [Boolean pasaFiltro = false] {Boolean b = false;}:
          #(ESQUEMAS (b=esquema[consulta, fichero]
             {pasaFiltro = pasaFiltro || b;}
          )*)
        ;

esquema[Consulta consulta, Fichero fichero] returns [Boolean pasaFiltro = false] {Boolean e,ob,op;}:
         #(c:IDENT e=extensiones[fichero] . .)
         {
          if(e && #c.getText().equals(consulta.clase))
	  	pasaFiltro = consulta.compruebaExp(fichero);
         }
       ;

extensiones[Fichero fichero] returns [Boolean e = false]:
              #(EXTENSIONES (ext:IDENT
                 {e = e || (#ext.getText().equals(fichero.extension));}
              )+)
           ;

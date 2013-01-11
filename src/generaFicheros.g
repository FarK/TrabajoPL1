header{
	import java.util.List;
	import java.util.ArrayList;
}

/************************/
/* Analizador sintáctico /
/************************/
class GeneraFicheros extends TreeParser;
options{
	importVocab = ParserDatos;
}

{public List<Fichero> ficheros = new ArrayList<Fichero>();}

datos {Fichero f;}: #(DATOS (
          f=fichero {ficheros.add(f);}
         )*)
     ;

fichero returns [Fichero fichero = new Fichero()] {String e; List<String> r;}:
         #(FICHERO r=ruta atributos[fichero] e=extension)
         {
            fichero.addRuta(r);
            fichero.extension = e;
         }
       ;

ruta returns [List<String> ruta = new ArrayList<String>()]:
      #(RUTA (s:SECCION
                {ruta.add(#s.getText());}
             )+)
    ;

atributos [Fichero fichero]:
           #(ATRIBUTOS (s:SECCION
                          {fichero.addAtributo(#s.getText());}
                       )+)
         ;

extension returns [String extension = ""]:
            #(EXTENSION s:SECCION) {extension = #s.getText();}
         ;

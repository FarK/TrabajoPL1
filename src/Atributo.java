import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Atributo {
	public Tipo tipo;
	public String nombre;

	public String valorCadena;
	public Double valorNumero;
	public Date valorTiempo;
	
	private DateFormat dateFormat;

	public Atributo(){
		tipo = null;

		valorCadena = null;
		valorNumero = null;
		valorTiempo = null;
		
		dateFormat = new SimpleDateFormat("mm:ss");
	}

	public Atributo(String atributo){
		this();
		this.valorCadena = atributo;

		setValorYTipo(atributo);
	}

	public void setAtributo(String atributo){
		this.valorCadena = atributo;

		setValorYTipo(atributo);
	}

	private void setValorYTipo(String atributo){
		try{
			valorNumero = Double.parseDouble(atributo);
			tipo = Tipo.NUM;
		}
		catch(NumberFormatException exc){
			try{
				valorTiempo = dateFormat.parse(atributo);
				tipo = Tipo.TIM;
			}catch (ParseException e){
				tipo = Tipo.TXT;
			}
		}
	}

	public String toString(){
		String result = "";
		
		switch(tipo){
			case NUM:
				result = valorNumero.toString();
			break;

			case TXT:
				result = valorCadena;
			break;

			case TIM:
				result = dateFormat.format(valorTiempo);
			break;
		}

		return result;
	}
}

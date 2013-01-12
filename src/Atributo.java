import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Atributo {
	public Tipo tipo;

	public String valorCadena;
	public Double valorNumero;
	public Date valorTiempo;

	public Atributo(){
		tipo = null;

		valorCadena = null;
		valorNumero = null;
		valorTiempo = null;
	}

	public Atributo(String atributo){
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
			DateFormat df = new SimpleDateFormat("mm:ss");
			try{
				valorTiempo = df.parse(atributo);
				tipo = Tipo.TIM;
			}catch (ParseException e){
				tipo = Tipo.TXT;
			}
		}
	}

	public String toString(){
		return	"Tipo = " + Tipo.toString(tipo) + "\n" +
			"Valor como cadena = " + valorCadena + "\n" +
			"Valor como numero = " + valorNumero + "\n" +
			"Valor como Tiempo = " + valorTiempo + "\n"
			;
	}
}

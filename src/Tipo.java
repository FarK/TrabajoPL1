public enum Tipo {
	NUM, TXT, TIM;
	
	public String toString(Tipo tipo){
		String ret = "";

		switch(tipo){
			case NUM:
				ret = "Número";
			break;
			
			case TXT:
				ret = "Texto";
			break;
			
			case TIM:
				ret = "Tiempo";
			break;
			
		}

		return ret;
	}
}

public enum Operador {
	EQ, LT, GT, LE, GE;

	public static String toString(Operador tipo){
		String ret = "";

		switch(tipo){
			case EQ:
				ret = "=";
			break;
			
			case LT:
				ret = "<";
			break;
			
			case GT:
				ret = ">";
			break;
			
			case LE:
				ret = "<=";
			break;
			
			case GE:
				ret = ">=";
			break;
		}

		return ret;
	}
}

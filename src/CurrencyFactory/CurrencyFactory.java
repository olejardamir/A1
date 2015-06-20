package CurrencyFactory;

public class CurrencyFactory {
public double convert(double value, String currencyType) throws Exception{
	currencyType = currencyType.toUpperCase();
	if(currencyType.equals("MXN")){return new MXN().toCAD(value);}
	else if (currencyType.equals("USD")){return new USD().toCAD(value);}
	else if (currencyType.equals("CAD")){return value;}
	else{throw new Exception("Currency type must be properly defined, only CAD, USD, and MXN are accepted!");}
}
}

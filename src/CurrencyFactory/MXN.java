package CurrencyFactory;

public class MXN implements Currency{

	public double toCAD(double value) {
		// $1.00 CAD = $10.00 MXN
		return value/10;
	}

}

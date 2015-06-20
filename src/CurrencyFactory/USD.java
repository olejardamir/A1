package CurrencyFactory;

public class USD implements Currency{

	public double toCAD(double value) {
		//$1.00 CAD = $0.50 USD
		return 2*value;
	}

}

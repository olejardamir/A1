package TESTS;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SQL.PopulateSql;
import SQL.SqlQueries;

public class Case4 {
	private String schema;
	private SqlQueries s;

		public Case4() throws Exception{
			this.schema = "test";
			this.s = new SqlQueries();
		}
		
	@Before
	public void setUp() throws Exception {
		PopulateSql p= new PopulateSql(schema);
	}

	@After
	public void tearDown() throws Exception {
		s.dropSchema(schema);
		s.closeConnection();
	}

	@Test
	public void test() throws Exception {
		//Peter Griffin withdraws $70.00 USD from account number 0123.
		s.removeFunds("0123", 70.00, "USD", schema);

		//Lois Griffin deposits $23,789.00 USD to account number 0456. 
		s.addFunds("0456", 23789.00, "USD", schema);

		//Lois Griffin transfers $23.75 CAD from account number 0456 to Peter Griffin (account number 0123).	
		s.transferFunds("0456", "0123", 23.75, "CAD", schema);

		//Account Number: 0123 Balance: $33.75 CAD
		double balance_0123 = s.getBalance("0123", schema);
		assertEquals(balance_0123, 33.75,0.0);

		//Account Number: 0456 Balance: $112,554.25 CAD
		double balance_0456 = s.getBalance("0456", schema);
		assertEquals(balance_0456, 112554.25,0.0);

	}

}

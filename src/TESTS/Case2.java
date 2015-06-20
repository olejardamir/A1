package TESTS;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SQL.PopulateSql;
import SQL.SqlQueries;

public class Case2 {
	private String schema;
	private SqlQueries s;

	public Case2() throws Exception{
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
		//Glenn Quagmire withdraws $5,000.00 MXN from account number 2001.
		s.removeFunds("2001", 5000.00, "MXN", schema);
		//Glenn Quagmire withdraws $12,500.00 USD from account number 2001. 
		s.removeFunds("2001", 12500.00, "USD", schema);
		//Glenn Quagmire deposits $300.00 CAD to account number 2001.
		s.addFunds("2001", 300.00, "CAD", schema);
		
		//EXPECTED: Account Number: 2001 Balance: $9,800 CAD
		double balance = s.getBalance("2001", schema);
		assertEquals(balance, 9800.0,0.0);	
		}

}

package TESTS;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SQL.PopulateSql;
import SQL.SqlQueries;

public class Case1 {
private String schema;
private SqlQueries s;

	public Case1() throws Exception{
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
		//INPUT: Stewie Griffin deposits $300.00 USD to account number 1234.
		s.addFunds("1234", 300.00, "USD", schema);
		
		//EXPECTED: Account Number: 1234 Balance: $700.00 CAD
		double balance = s.getBalance("1234", schema);
		assertEquals(balance, 700.0,0.0);
	}

}

package TESTS;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuites {

	public static void main(String[] args) {
		suite();
	}
	
	
	public static Test suite() {
		  TestSuite suite=new TestSuite();
		  suite.addTest(new JUnit4TestAdapter(Case1.class));
		  suite.addTest(new JUnit4TestAdapter(Case2.class));
		  suite.addTest(new JUnit4TestAdapter(Case3.class));
		  suite.addTest(new JUnit4TestAdapter(Case4.class));

		  return suite;
		}

}

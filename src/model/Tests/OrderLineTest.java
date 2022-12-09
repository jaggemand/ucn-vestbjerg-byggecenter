package model.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.OrderLine;
import model.Product;

public class OrderLineTest {
	
	OrderLine testOrderLine;

	@Before
	public void setUp() throws Exception {
		testOrderLine = new OrderLine(42, new Product("Test produkt", "1234", "Test Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getQuantityTest() {
		//TODO
	}
	@Test
	public void setQuantityTest() {
		//TODO
	}
	@Test
	public void getProductTest() {
		//TODO
	}
	@Test
	public void setProductTest() {
		//TODO
	}

}

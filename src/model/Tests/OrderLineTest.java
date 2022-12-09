package model.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.OrderLine;
import model.Product;

public class OrderLineTest {
	
	private OrderLine testOrderLine;
	private Product testProduct;

	@Before
	public void setUp() throws Exception {
		testProduct = new Product("Test produkt", "1234", "Test Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
		testOrderLine = new OrderLine(42, testProduct);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getQuantityTest() {
		//Test that the attribute called quantity contains expected value 
		assertEquals(42, testOrderLine.getQuantity());
	}
	@Test
	public void setQuantityTest() {
		//Test that the attribute called quantity is NOT changed yet
		assertNotEquals(69, testOrderLine.getQuantity());
		//Change the attribute to the new value
		testOrderLine.setQuantity(69);
		//Test that the attribute called quantity is changed to the new value
		assertEquals(69, testOrderLine.getQuantity());
	}
	@Test
	public void getProductTest() {
		//Test that the attribute called product contains expected value 
		assertEquals(testProduct, testOrderLine.getProduct());
	}
	@Test
	public void setProductTest() {
		//Create a new test product.
		Product newTestProduct = new Product("Nyt Test produkt", "1234", "New Test Description", new String[] {"New Test 1","New Test 2"}	, "1", "2", 20, 50);
		
		//Test that the attribute called product is NOT changed yet
		assertNotEquals(newTestProduct, testOrderLine.getQuantity());
		//Change the attribute to the new value
		testOrderLine.setProduct(newTestProduct);
		//Test that the attribute called product is changed to the new value
		assertEquals(newTestProduct, testOrderLine.getProduct());
	}

}

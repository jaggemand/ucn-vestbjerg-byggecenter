package model.Tests;

import model.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private Order testOrder;
	private OrderLine testOrderLine;
	private Product testProduct;

	@Before
	public void setUp() throws Exception {
		testOrder = new Order();
		testProduct = new Product("Test produkt", "1234", "Test Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
		testProduct.setSalesPrice(10);
		testOrder.addProduct(testProduct, 42);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void removeProductTest() {
		//Verify amount of orderLines in order
		assertEquals(1, testOrder.getOrderLines().size());
		//remove the product in orderline using the expected barcode
		assertEquals(true, testOrder.removeProduct("1234")); 
		//Verify that the product is removed
		assertEquals(0, testOrder.getOrderLines().size());
	}
	@Test
	public void addProductTest() {
		
		//Create a new test product.
		Product newTestProduct = new Product("Nyt Test produkt", "2345", "New Test Description", new String[] {"New Test 1","New Test 2"}	, "1", "2", 20, 50);
				
		//Verify amount of orderLines in order
		assertEquals(1, testOrder.getOrderLines().size());
		//add new product to orderline
		testOrder.addProduct(newTestProduct, 69);
		//Verify amount of orderLines in order
		assertEquals(2, testOrder.getOrderLines().size());
		
	}
	@Test
	public void getOrderLinesTest() {
		//Test that the method are able to get access to the orderlines
		assertEquals("Test produkt", testOrder.getOrderLines().get(0).getProduct().getName());
		assertEquals(42, testOrder.getOrderLines().get(0).getQuantity());
	}
	@Test
	public void setDateTest() {
		//TODO hard to implement
	}
	@Test
	public void getTotalPriceTest() {
		//Create a new test product.
		Product newTestProduct = new Product("Nyt Test produkt", "2345", "New Test Description", new String[] {"New Test 1","New Test 2"}	, "1", "2", 20, 50);
		
		//Set the sales prices prices
		newTestProduct.setSalesPrice(20);
		
		//Test the total price with one product in order
		assertEquals(420, testOrder.getTotalPrice(),0);
		
		//add the new product to the order
		testOrder.addProduct(newTestProduct, 20);
		
		//Test the total price with two products in order
		assertEquals(820, testOrder.getTotalPrice(),0);
		
		
	}
	@Test
	public void setStatusTest() {
		//TODO - Attrribute will be converted to enum?
	}
	@Test
	public void setPickupTest() {
		//TODO - Attrribute will be converted to enum?
	}
	@Test
	public void getStatusTest() {
		//TODO - Attrribute will be converted to enum?
	}
	@Test
	public void getPickupTest() {
		//TODO - Attrribute will be converted to enum?
	}
	@Test
	public void getDateTest() {
		//TODO - hard to implement
	}
	@Test
	public void getOrderNumberTest() {
		//TODO - attribute is static but never incremented
	}

}

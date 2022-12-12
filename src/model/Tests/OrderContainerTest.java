package model.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Order;
import model.OrderContainer;
import model.OrderLine;
import model.Product;
import model.ProductContainer;

public class OrderContainerTest {
	private Order testOrder;
	private OrderLine testOrderLine;
	private Product testProduct;

	@Before
	public void setUp() throws Exception {
		testOrder = new Order();
		testProduct = new Product("Test produkt", "1234", "Test Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
		testProduct.setSalesPrice(10);
		testOrder.addProduct(testProduct, 42);
		OrderContainer.getInstance().addOrder(testOrder);
	}

	@After
	public void tearDown() throws Exception {
		//Because the tests will contain usage off containers that are configured as singletons
		//We need to make the reset call after each test
		OrderContainer.getInstance().resetSingleton();
	}

	@Test
	public void resetSingletonTest() {
		//TODO - hard to implement
	}
	@Test
	public void getInstanceTest() {
		OrderContainer testContainer = OrderContainer.getInstance();
		assertEquals(testContainer, OrderContainer.getInstance());
	}
	@Test
	public void addOrderTest() {
		//Create a new test order.
		Product newTestProduct = new Product("Nyt Test produkt", "2345", "New Test Description", new String[] {"New Test 1","New Test 2"}	, "1", "2", 20, 50);
		Order newTestOrder = new Order();
		//add new test product to new test order
		newTestOrder.addProduct(newTestProduct, 69);
		
		//verify arraysize before adding another order
		assertEquals(1, OrderContainer.getInstance().getOrders().size());
		//add the test order to the container
		assertEquals(true, OrderContainer.getInstance().addOrder(newTestOrder));
		//verify arraysize after adding another order
		assertEquals(2, OrderContainer.getInstance().getOrders().size());
				
	}
	@Test
	public void removeOrderTest() {
				
		//verify arraysize before removing the order
		assertEquals(1, OrderContainer.getInstance().getOrders().size());
		//remove the order to the container
		assertEquals(true, OrderContainer.getInstance().removeOrder(testOrder));
		//verify arraysize after removing the order
		assertEquals(0, OrderContainer.getInstance().getOrders().size());
				
	}
	@Test
	public void getOrdersTest() {
		//TODO
	}

}

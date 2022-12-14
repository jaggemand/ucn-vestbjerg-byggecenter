package model.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Order;
import model.OrderContainer;
import model.Product;

public class OrderContainerTest {
	private Order testOrder;
	private Product testProduct;

	@Before
	public void setUp() throws Exception {
		testOrder = new Order(true);
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
		//Check that our container contains 1 instance
		assertEquals(1, OrderContainer.getInstance().getOrders().size());
		
		//Resets our container instance
		OrderContainer.getInstance().resetSingleton();
		//Checks our reset for orderContainer was a success
		assertEquals(0, OrderContainer.getInstance().getOrders().size());
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
		Order newTestOrder = new Order(true);
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
		//Create a new empty list
		ArrayList<Order> methodList = new ArrayList<>();
		//Test that the list is empty
		assertEquals(0, methodList.size());
		//get the list from the container and assign it to the local list
		methodList = OrderContainer.getInstance().getOrders();
		//test that the list is as expected
		assertEquals(1, methodList.size());
	}

}

package controller.Tests;

import controller.*;
import model.Order;
import model.OrderContainer;
import model.Product;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderControllerTest {

	private OrderController oController;
	private Order testOrder;
	private Product testProduct;
	
	@Before
	public void setUp() throws Exception {
		oController = new OrderController();
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
	public void addOrderTest() {
		//test to see if the specified order already exits in orderContainer
		assertNotEquals(2, OrderContainer.getInstance().getOrders().size());
		//adds new order to orderContainer through OrderController
		oController.createOrder();
		oController.addOrder();
		//test to see if the orders list in orderContainer has increased in size
		assertEquals(2, OrderContainer.getInstance().getOrders().size());
	}
	
	@Test
	public void createOrderTest() {
		//TODO
	}
	
	@Test
	public void getCurrentOrderTest() {
		//TODO
	}
	
	@Test
	public void addProductTest() {
		//TODO
	}
	
	@Test
	public void removeProductTest() {
		//TODO
	}
	
	@Test
	public void findOrderTest() {
		//TODO
	}
	
	@Test
	public void removeOrderTest() {
		//TODO
	}
	
}

package controller.Tests;

import controller.*;
import model.Order;
import model.OrderContainer;
import model.Product;
import model.ProductContainer;

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
		ProductContainer.getInstance().addProduct(testProduct);
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
		//Test if currentOrder is set
		assertEquals(null, oController.getCurrentOrder());
		//Instantiate the currentOrder variable
		oController.createOrder();
		//Test if currentOrder is set
		assertEquals(false, oController.getCurrentOrder().equals(null));
	}
	
	@Test
	public void getCurrentOrderTest() {
		//test to see if the OrderController already has an instance of order in its attributes
		assertEquals(null, oController.getCurrentOrder());
		//adds an order to current order in OrderController
		oController.createOrder();
		//test to see if the currentOrder attribute is not null after insertion of order in previous call
		assertNotEquals(null, oController.getCurrentOrder());
	}
	
	//not finished
	@Test
	public void addProductTest() {
		oController.createOrder();
		//test to see if add order results in false
		assertEquals(false, oController.removeProduct("1234"));
		//add order to OrderController attribute
		oController.getCurrentOrder().addProduct(testProduct, 1);
		//test to see if add order results in true
		assertEquals(true, oController.removeProduct("1234"));
	}
	
	@Test
	public void removeProductTest() {
		//creates order in attributes to avoid nullpointer
		oController.createOrder();
		//Tests to see if it is possible to remove a product from current order 
		assertEquals(false, oController.removeProduct("1234"));
		//adds product to current order
		oController.getCurrentOrder().addProduct(testProduct, 1);
		//test to see if it is possible to remove added product from current order
		assertEquals(true, oController.removeProduct("1234"));
	}
	
	@Test
	public void findOrderTest() {
		String id = testOrder.getOrderNumber();
		//test to see if the outputOrder returned is testOrder
		assertEquals(testOrder, oController.findOrder(id));
	}
	
	@Test
	public void removeOrderTest() {
		//adds order to attributes, and adds order to OrderContainer
		oController.createOrder();
		oController.addOrder();
		//Stores orderNumber
		int containerSize = OrderContainer.getInstance().getOrders().size();
		String id = oController.getCurrentOrder().getOrderNumber();
		//checks if it is possible to remove order
		assertEquals(true, oController.removeOrder(id));
		
		assertEquals(containerSize-1, OrderContainer.getInstance().getOrders().size());
	}
}

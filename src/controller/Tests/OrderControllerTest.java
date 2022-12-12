package controller.Tests;

import controller.*;
import model.OrderContainer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderControllerTest {

	@Before
	public void setUp() throws Exception {
		OrderController controller = new OrderController();
	}

	@After
	public void tearDown() throws Exception {
		//Because the tests will contain usage off containers that are configured as singletons
		//We need to make the reset call after each test
		OrderContainer.getInstance().resetSingleton();
	}

	@Test
	public void addOrderTest() {
		//TODO
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

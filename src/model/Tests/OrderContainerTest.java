package model.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.OrderContainer;

public class OrderContainerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		//Because the tests will contain usage off containers that are configured as singletons
		//We need to make the reset call after each test
		OrderContainer.getInstance().resetSingleton();
	}

	@Test
	public void resetSingletonTest() {
		//TODO
	}
	@Test
	public void getInstanceTest() {
		//TODO
	}
	@Test
	public void addOrderTest() {
		//TODO
	}
	@Test
	public void removeOrderTest() {
		//TODO
	}
	@Test
	public void getOrdersTest() {
		//TODO
	}

}

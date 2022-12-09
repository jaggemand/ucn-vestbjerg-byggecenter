package model.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.ProductContainer;

public class ProductContainerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		//Because the tests will contain usage off containers that are configured as singletons
		//We need to make the reset call after each test
		ProductContainer.getInstance().resetSingleton();
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
	public void addProductTest() {
		//TODO
	}
	@Test
	public void getProductsTest() {
		//TODO
	}
	@Test
	public void removeProductTest() {
		//TODO
	}

}

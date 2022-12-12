package model.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Product;
import model.ProductContainer;

public class ProductContainerTest {
	
	private Product testProduct;

	@Before
	public void setUp() throws Exception {
		testProduct = new Product("Test produkt", "1234", "Test Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
		ProductContainer.getInstance().addProduct(testProduct);
	}
	
	@After
	public void tearDown() throws Exception {
		//Because the tests will contain usage off containers that are configured as singletons
		//We need to make the reset call after each test
		ProductContainer.getInstance().resetSingleton();
	}

	@Test
	public void resetSingletonTest() {
		
		//Check if ProductContainer contains 1 instance
		assertEquals(1, ProductContainer.getInstance().getProducts().size());
		
		//Resets ProductContainer to zero
		ProductContainer.getInstance().resetSingleton();
		//Checks the reset was a success
		assertEquals(0, ProductContainer.getInstance().getProducts().size());
		
	}
	@Test
	public void getInstanceTest() {
		ProductContainer testContainer = ProductContainer.getInstance();
		assertEquals(testContainer, ProductContainer.getInstance());
	}
	@Test
	public void addProductTest() {
		//Create a new test product.
		Product newTestProduct = new Product("Nyt Test produkt", "2345", "New Test Description", new String[] {"New Test 1","New Test 2"}	, "1", "2", 20, 50);
				
		//verify arraysize before adding another product
		assertEquals(1, ProductContainer.getInstance().getProducts().size());
		//add the test product to the container
		assertEquals(true, ProductContainer.getInstance().addProduct(newTestProduct));
		//verify arraysize after adding another product
		assertEquals(2, ProductContainer.getInstance().getProducts().size());
	}
	
	@Test
	
	public void getProductsTest() {
		//test to see if the methods returns the list of products
		assertEquals("Test produkt", ProductContainer.getInstance().getProducts().get(0).getName());
	}
	
	@Test
	public void removeProductTest() {

		//verify arraysize before removing the product
		assertEquals(1, ProductContainer.getInstance().getProducts().size());
		//remove the product to the container
		assertEquals(true, ProductContainer.getInstance().removeProduct(testProduct));
		//verify arraysize after removing the product
		assertEquals(0, ProductContainer.getInstance().getProducts().size());
	}
}
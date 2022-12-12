package controller.Tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.*;
import model.Product;
import model.ProductContainer;

public class ProductControllerTest {
	private Product testProduct;
	private ProductController pController;

	@Before
	public void setUp() throws Exception {
		pController = new ProductController();
		testProduct = new Product("Test produkt", "1234", "Test Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
		testProduct.setSalesPrice(10);
		ProductContainer.getInstance().addProduct(testProduct);
	}

	@After
	public void tearDown() throws Exception {
		//Because the tests will contain usage off containers that are configured as singletons
		//We need to make the reset call after each test
		ProductContainer.getInstance().resetSingleton();
	}

	@Test
	public void createProductTest() {
		//Initialize new product
		Product methodProduct = pController.createProduct("testMethodProduct", "5678", "Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
		//see if the product has initialized correctly and returns the right name
		assertEquals("testMethodProduct", methodProduct.getName());
		//see if the productContainer size has increased
		assertEquals(2, ProductContainer.getInstance().getProducts().size());
	}
	
	@Test
	public void removeProductTest() {
		//sees if return is true when trying to remove product by barcode
		assertEquals(true, pController.removeProduct("1234"));
		//checks the length of the ProductContainer list to see if it has been shortend
		assertEquals(0, ProductContainer.getInstance().getProducts().size());
	}
	
	@Test
	public void addProductTest() {
		//Initialize new product
		Product methodProduct = new Product("testMethodProduct", "5678", "Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
		
		//Checks the variable was created with success and added to the product controller
		assertEquals(true, pController.addProduct(methodProduct));
		//Checks that one variable has been added to the product container
		assertEquals(2, ProductContainer.getInstance().getProducts().size());
	}
	
	@Test
	public void findProductTest() {
		//compares testProduct to Product instance return on findProduct with search "1234"
		assertEquals(testProduct, pController.findProduct("1234"));
	}
	
	@Test
	public void changeProductNameTest() {
		//test if productName is NOT equal to the new value
		assertNotEquals("changed name", ProductContainer.getInstance().getProducts().get(0).getName());
		//Change the name useing test method
		pController.changeProductName("changed name", testProduct);
		//verify that the value is changed
		assertEquals("changed name", ProductContainer.getInstance().getProducts().get(0).getName());
	}
	
	@Test
	public void changeProductDescriptionTest() {
		//Checks to see if the testProduct description does not match "abc"
		assertEquals(false, testProduct.getDescription().equals("acb"));
		testProduct.setDescription("abc");
		//Checks that the description change has been successful
		assertEquals(true, testProduct.getDescription().equals("abc"));
	}
	
	@Test
	public void changeProductBarcodeTest() {
		String changedValue = "2345";
		//test if productName is not equal to the new value
		assertNotEquals(changedValue, ProductContainer.getInstance().getProducts().get(0).getBarcode());
		//Change the name using test method
		pController.changeProductBarcode(changedValue, testProduct);
		//verify that the value is changed
		assertEquals(changedValue, ProductContainer.getInstance().getProducts().get(0).getBarcode());	
	}
	
	@Test
	public void changeProductCategoryTest() {
		String[] changedValue = new String[] {"Test 2", "Test 3"};
		//test if productName is not equal to the new value
		assertNotEquals(changedValue, ProductContainer.getInstance().getProducts().get(0).getCategory());
		//Change the name useing test method
		pController.changeProductCategory(changedValue, testProduct);
		//verify that the value is changed
		assertArrayEquals(changedValue, ProductContainer.getInstance().getProducts().get(0).getCategory());
	}
	
	@Test
	public void changeProductStorageLocationTest() {
		//Check that the storage location is not "abc"
		assertEquals(false, testProduct.getStorageLocation().equals("acb"));
		testProduct.setStorageLocation("abc");
		//Checks that the storage location change has been successful
		assertEquals(true, testProduct.getStorageLocation().equals("abc"));
	}
	
	@Test
	public void changeProductWarehouseLocationTest() {
		//Check that the warehouse location is not "abc"
		assertEquals(false, testProduct.getWarehouseLocation().equals("acb"));
		testProduct.setWarehouseLocation("abc");
		//Checks that the warehouse location change has been successful
		assertEquals(true, testProduct.getWarehouseLocation().equals("abc"));
	}
	
	@Test
	public void changeProductStorageAmountTest() {
		//Check that the storage amount is not 9999
		assertEquals(false, testProduct.getStorageAmount() == 9999);
		testProduct.setStorageAmount(9999);
		//Checks that the storage amount change has been successful
		assertEquals(true, testProduct.getStorageAmount() == 9999);
	}
	
	@Test
	public void changeProductWarehouseAmountTest() {
		//Check that the warehouse amount is not 9999
		assertEquals(false, testProduct.getWarehouseAmount() == 9999);
		testProduct.setWarehouseAmount(9999);
		//Checks that the warehouse amount change has been successful
		assertEquals(true, testProduct.getWarehouseAmount() == 9999);
	}

}

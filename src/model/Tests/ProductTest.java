package model.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Product;

public class ProductTest {

	Product testProduct;

	@Before
	public void setUp() throws Exception {
		testProduct = new Product("Test produkt", "1234", "Test Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
		testProduct.setCostPrice(10);
		testProduct.setSalesPrice(30);
		testProduct.setSuggestedSalesPrice(60);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getProductIDTest() {
		//Due to the nature of how this variable is implemented
		//The tests for this method will be different from the other test cases
		//All product numbers will be a composite of a static prefix and an increasing number combined
		//ex: "1000-1" OR "1000-526356"
		//All numbers are at least 5 chars long: "1000-"
		assertEquals(true, testProduct.getProductID().length() > 5);
	}
	@Test
	public void getNameTest() {
		
		//Test 
		assertEquals("Test produkt", testProduct.getName());
	}
	@Test
	public void setNameTest() {
		
		//test that the name is NOT the changed value
		assertNotEquals("Test produkt ny", testProduct.getName());
		//Change the attribute to new value
		testProduct.setName("Test produkt ny");
		//test that the new value is set
		assertEquals("Test produkt ny", testProduct.getName());
	}
	@Test
	public void getBarcodeTest() {
		
		//Test barcode returns actual barcode
		assertEquals("1234", testProduct.getBarcode());
	}
	@Test
	public void setBarcodeTest() {
		
		//Test that the new barcode is NOT the changed value
		assertNotEquals("4321", testProduct.getBarcode());
		//Test change the attritube to new value
		testProduct.setBarcode("4321");
		//Test that the new value is set
		assertEquals("4321", testProduct.getBarcode());
		
	}
	@Test
	public void getDescriptionTest() {
		
		//Test description return actual description
		assertEquals("Test Description", testProduct.getDescription());
	}
	
	@Test
	public void setDescriptionTest() {
		
		//Test the new description is NOT the changed value
		assertNotEquals("Test newDescription", testProduct.getDescription());
		// Test new value to attribute
		testProduct.setDescription("Test newDescription");
		//Test new value is assgined the attribute
		assertEquals("Test newDescription", testProduct.getDescription());
	}
	
	@Test
	public void getCategoryTest() {
		
		//Test category return actual value
		assertArrayEquals(new String[]{"Test 1","Test 2"}, testProduct.getCategory());
	}
	
	@Test
	public void setCategoryTest() {
		
		//Test changed value NOT is the same as current
		assertNotEquals(new String[]{"Test 10","Test 20"}, testProduct.getCategory());
		//Test new value to attribute
		testProduct.setCategory(new String[]{"Test 10","Test 20"});
		//Test new assgined value is a success
		assertArrayEquals(new String[]{"Test 10","Test 20"}, testProduct.getCategory());
		
	}
	@Test
	public void getStorageLocationTest() {
		//Test StorageLocation returns actual value
		assertEquals("1", testProduct.getStorageLocation());
	}
	
	@Test
	public void setStorageLocationTest() {
		//Test change value NOT is the same as current
		assertNotEquals("10", testProduct.getStorageLocation());
		//Test new value is set to attribute
		testProduct.setStorageLocation("10");
		//Test new value assgined is a succes
		assertEquals("10", testProduct.getStorageLocation());
	}
	
	@Test
	public void getWarehouseLocationTest() {
		//Test warehouseLocation returns actual value
		assertEquals("2", testProduct.getWarehouseLocation());
		
	}
	@Test
	public void setWarehouseLocationTest() {
		//Test change value NOT is the same as current
		assertNotEquals("20", testProduct.getWarehouseLocation());
		//Test new value is set to attribute
		testProduct.setWarehouseLocation("20");
		//Test new value assgined is a success
		assertEquals("20", testProduct.getWarehouseLocation());
		
	}
	@Test
	public void getStorageAmountTest() {
		//Test StorageAmount returns actual value
		assertEquals(20, testProduct.getStorageAmount());
		
		
	}
	@Test
	public void setStorageAmountTest() {
		//Test change value NOT is the same as current
		assertNotEquals(200, testProduct.getStorageAmount());
		//Test new value is set to attribute
		testProduct.setStorageAmount(200);
		//Test new value assgined is a success
		assertEquals(200, testProduct.getStorageAmount());
	}
	
	@Test
	public void getWarehouseAmountTest() {
		//Test GetWarehouseAmount returns actual value
		assertEquals(50, testProduct.getWarehouseAmount());
	}
	
	@Test
	public void setWarehouseAmountTest() {
		//Test change value NOT is the same as current
		assertNotEquals(500, testProduct.getWarehouseAmount());
		//Test new value is set to attribute
		testProduct.setWarehouseAmount(500);
		//Test new value assgined is a success
		assertEquals(500, testProduct.getWarehouseAmount());
	}
	
	@Test
	public void getSalesPriceTest() {
		//Test getSalesPrice returns actual value
		assertEquals(30.0, testProduct.getSalesPrice(), 0);
	}
	
	@Test
	public void setSalesPriceTest() {
		//Test change value is NOT the same as current
		assertNotEquals(25.0, testProduct.getSalesPrice(), 0);
		//Test new value is set to attribute
		testProduct.setSalesPrice(25.0);
		//Test new value assgined is a success
		assertEquals(25.0, testProduct.getSalesPrice(), 0);
		
	}
	
	@Test
	public void getCostPriceTest() {
		//Test getCostPrice returns actual value
		assertEquals(10.0, testProduct.getCostPrice(), 0);
	}
	
	@Test
	public void setCostPriceTest() {
		//Test change value is NOT the same as current
		assertNotEquals(15.0, testProduct.getCostPrice(), 0);
		//Test new value is set to attribute
		testProduct.setCostPrice(15.0);
		//Test new value assgined is a success
		assertEquals(15.0, testProduct.getCostPrice(), 0);
		
	}
	@Test
	public void getSuggestedSalesPriceTest() {
		//Test getSuggestedSalesPrice returns actual value
		assertEquals(60.0, testProduct.getSuggestedSalesPrice(), 0);
	}
	
	@Test
	public void setSuggestedSalesPriceTest() {
		//Test change value is NOT the same as current
		assertNotEquals(75.0, testProduct.getSuggestedSalesPrice(), 0);
		//Test new value is set to attribute
		testProduct.setSuggestedSalesPrice(75.0);
		//Test new value assgined is a success
		assertEquals(75.0, testProduct.getSuggestedSalesPrice(), 0);
	}
	
	@Test
	public void containsBarcodeOrProuductIDTest() {
		String testID = testProduct.getProductID();
		//Test containsBarcodeOrProuductID returns actual value of String barcode
		assertEquals(true, testProduct.containsBarcodeOrProuductID("1234"));
		//Test containsBarcodeOrProuductID returns actual value of String productID
		assertEquals(true, testProduct.containsBarcodeOrProuductID(testID));
		//Test that barcode value is NOT equal to actual value
		assertNotEquals(true, testProduct.containsBarcodeOrProuductID("4321"));
		//Test that productID value is NOT equal to actual value
		assertNotEquals(false, testProduct.containsBarcodeOrProuductID(testID));
	}
}
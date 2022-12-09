package model.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.OrderLine;
import model.Product;

public class ProductTest {

	Product testProduct;

	@Before
	public void setUp() throws Exception {
		testProduct = new Product("Test produkt", "1234", "Test Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getProductIDTest() {
		//TODO
	}
	@Test
	public void getNameTest() {
		//TODO
	}
	@Test
	public void setNameTest() {
		//TODO
	}
	@Test
	public void getBarcodeTest() {
		//TODO
	}
	@Test
	public void setBarcodeTest() {
		//TODO
	}
	@Test
	public void getDescriptionTest() {
		//TODO
	}
	@Test
	public void setDescriptionTest() {
		//TODO
	}
	@Test
	public void getCategoryTest() {
		//TODO
	}
	@Test
	public void setCategoryTest() {
		//TODO
	}
	@Test
	public void getStorageLocationTest() {
		//TODO
	}
	@Test
	public void setStorageLocationTest() {
		//TODO
	}
	@Test
	public void getWarehouseLocationTest() {
		//TODO
	}
	@Test
	public void setWarehouseLocationTest() {
		//TODO
	}
	@Test
	public void getStorageAmountTest() {
		//TODO
	}
	@Test
	public void setStorageAmountTest() {
		//TODO
	}
	@Test
	public void getWarehouseAmountTest() {
		//TODO
	}
	@Test
	public void setWarehouseAmountTest() {
		//TODO
	}
	@Test
	public void getSalesPriceTest() {
		//TODO
	}
	@Test
	public void setSalesPriceTest() {
		//TODO
	}
	@Test
	public void getCostPriceTest() {
		//TODO
	}
	@Test
	public void setCostPriceTest() {
		//TODO
	}
	@Test
	public void getSuggestedSalesPriceTest() {
		//TODO
	}
	@Test
	public void setSuggestedSalesPriceTest() {
		//TODO
	}
	@Test
	public void containsBarcodeOrProuductIDTest() {
		//TODO
	}
	

}

package controller.Tests;

import controller.*;
import model.ProductContainer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductControllerTest {

	@Before
	public void setUp() throws Exception {
		ProductController controller = new ProductController();
	}

	@After
	public void tearDown() throws Exception {
		//Because the tests will contain usage off containers that are configured as singletons
		//We need to make the reset call after each test
		ProductContainer.getInstance().resetSingleton();
	}

	@Test
	public void createProductTest() {
		//TODO
	}
	@Test
	public void removeProductTest() {
		//TODO
	}
	@Test
	public void addProductTest() {
		//TODO
	}
	@Test
	public void findProductTest() {
		//TODO
	}
	@Test
	public void changeProductNameTest() {
		//TODO
	}
	@Test
	public void changeProductDescriptionTest() {
		//TODO
	}
	@Test
	public void changeProductBarcodeTest() {
		//TODO
	}
	@Test
	public void changeProductCategoryTest() {
		//TODO
	}
	@Test
	public void changeProductStorageLocationTest() {
		//TODO
	}
	@Test
	public void changeProductWarehouseLocationTest() {
		//TODO
	}
	@Test
	public void changeProductStorageAmountTest() {
		//TODO
	}
	@Test
	public void changeProductWarehouseAmountTest() {
		//TODO
	}

}

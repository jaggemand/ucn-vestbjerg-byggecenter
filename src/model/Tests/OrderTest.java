package model.Tests;

import model.*;
import model.Order.OrderStatus;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private Order testOrder;
	private Product testProduct;

	@Before
	public void setUp() throws Exception {
		testOrder = new Order(true);
		testProduct = new Product("Test produkt", "1234", "Test Description", new String[] {"Test 1","Test 2"}	, "1", "2", 20, 50);
		testProduct.setSalesPrice(10);
		testOrder.addProduct(testProduct, 42);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void removeProductTest() {
		//Verify amount of orderLines in order
		assertEquals(1, testOrder.getOrderLines().size());
		//remove the product in orderline using the expected barcode
		assertEquals(true, testOrder.removeProduct("1234")); 
		//Verify that the product is removed
		assertEquals(0, testOrder.getOrderLines().size());
	}
	@Test
	public void addProductTest() {
		
		//Create a new test product.
		Product newTestProduct = new Product("Nyt Test produkt", "2345", "New Test Description", new String[] {"New Test 1","New Test 2"}	, "1", "2", 20, 50);
				
		//Verify amount of orderLines in order
		assertEquals(1, testOrder.getOrderLines().size());
		//add new product to orderline
		testOrder.addProduct(newTestProduct, 69);
		//Verify amount of orderLines in order
		assertEquals(2, testOrder.getOrderLines().size());
		
	}
	@Test
	public void getOrderLinesTest() {
		//Test that the method are able to get access to the orderlines
		assertEquals("Test produkt", testOrder.getOrderLines().get(0).getProduct().getName());
		assertEquals(42, testOrder.getOrderLines().get(0).getQuantity());
	}
	@Test
	public void getTotalPriceTest() {
		//Create a new test product.
		Product newTestProduct = new Product("Nyt Test produkt", "2345", "New Test Description", new String[] {"New Test 1","New Test 2"}	, "1", "2", 20, 50);
		
		//Set the sales prices prices
		newTestProduct.setSalesPrice(20);
		
		//Test the total price with one product in order
		assertEquals(420, testOrder.getTotalPrice(),0);
		
		//add the new product to the order
		testOrder.addProduct(newTestProduct, 20);
		
		//Test the total price with two products in order
		assertEquals(820, testOrder.getTotalPrice(),0);
	}
	
	@Test
	public void setStatusTest() {
		//Test that the status is not FINISHED
		assertNotEquals(Order.OrderStatus.FINISHED, testOrder.getStatus());
		
		//Change the status to FINISHED
		testOrder.setStatus(OrderStatus.FINISHED);
		//Test that the status is FINISHED
		assertEquals(Order.OrderStatus.FINISHED, testOrder.getStatus());
	}
	@Test
	public void setPickupTest() {
		//testdate will be set to 10 days from now
		LocalDate testDate = LocalDate.now().plusDays(10);
		//test that pickupDate has not changed yet
		assertNotEquals(testDate, testOrder.getPickup());
		//Set the new date
		testOrder.setPickup(testDate);
		//test that the change has occured
		assertEquals(testDate, testOrder.getPickup());
	}
	@Test
	public void getStatusTest() {
		//Test the current status for instantiated status
		assertEquals(Order.OrderStatus.SALE, testOrder.getStatus());
	}
	
	@Test
	public void getPickupTest() {
		//testdate will be set to 10 days from now
		LocalDate testDate = LocalDate.now().plusDays(2);
	
		//test that the change has occured
		assertEquals(testDate.getDayOfYear(), testOrder.getPickup().getDayOfYear());
	}
	
	@Test
	public void getDateTest() {
		//In the constructor the date of the testOrder is a snapshot
		//for that reason the seconds/millisecond might be off
		//so this test will try to mitigate that
		
		//Take a timestamp
		LocalDate testDate = LocalDate.now();
		//Test that the order matches the expected day, month and year
		assertEquals(testDate.getDayOfMonth(), testOrder.getDate().getDayOfMonth());
		assertEquals(testDate.getMonth(), testOrder.getDate().getMonth());
		assertEquals(testDate.getYear(), testOrder.getDate().getYear());
	}
	@Test
	public void getOrderNumberTest() {
		//Due to the nature of the ordernumber system this test may have to be re-written
		//All ordernumber has a prefix of "352-" which is 4 chars long
		//in the constructor a progressivly increacing number is added
		//ex: 352-1 OR 352-165894
		//Both valid numbers have one thing iin common, they are larger than 4 chars long
		
		//Ordernumber is larger that 4 in length
		assertEquals(true, testOrder.getOrderNumber().length() > 4);
		//test prefix
		assertEquals("352-", testOrder.getOrderNumber().substring(0, 4));
	}
}
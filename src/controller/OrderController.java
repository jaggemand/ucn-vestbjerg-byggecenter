package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.*;
import model.Order.OrderStatus;

public class OrderController {
	private Order currentOrder;
	private ProductController productController;

	/**
	 * Constructor doesn't need parameters
	 */
	public OrderController() {
		productController = new ProductController();
	}

	/*
	 * This method adds current order from the attributes to the
	 * OrderContainer-class
	 * 
	 * @return returns true if order is added to container
	 */
	public boolean addOrder() {
		boolean success = false;
		if(currentOrder != null) {
			success = OrderContainer.getInstance().addOrder(getCurrentOrder());
		}
		return success;

		// Method could be shorter.
		// return OrderContainer.getInstance().addOrder(getCurrentOrder());
	}

	/*
	 * This method creates a new order in the currentOrder attribute
	 * 
	 */
	public void createOrder(boolean saleStatus) {
		currentOrder = new Order(saleStatus);
	}

	/*
	 * This method return the attribute named: currentOrder
	 * 
	 * @return Order-object, can be null
	 */
	public Order getCurrentOrder() {
		return currentOrder;
	}

	/*
	 * This method takes two input parameters search and quantity, searches for a
	 * product if product exists, the method will add the product to current order
	 * with the input amount
	 * 
	 * @param search This String should contain a barcode or product ID
	 * 
	 * @param quantity Amount of elements of givin product
	 * 
	 * @return returns true if the product is added to the order
	 */
	public boolean addProduct(String search, int quantity) {
		boolean success = false;
		Product toAdd = productController.findProduct(search);
		if (toAdd != null) {
			success = true;
			currentOrder.addProduct(toAdd, quantity);
		}

		return success;
	}

	/*
	 * This method takes one parameter and uses that to find an object if product
	 * exists, the method will add the product to current order with the input
	 * amount
	 * 
	 * @param search This String should contain a barcode or product ID
	 * 
	 * @param quantity Amount of elements of givin product
	 * 
	 * @return returns true if the product is added to the order
	 */
	public boolean removeProduct(String search) {
		boolean success = false;
		success = currentOrder.removeProduct(search);
		return success;
	}

	public Order findOrder(String orderNumber) {
		Order outputOrder = null;
		ArrayList<Order> orders = OrderContainer.getInstance().getOrders();
		boolean found = false;
		Iterator<Order> it = orders.iterator();
		while (!found && it.hasNext()) {
			outputOrder = it.next();
			if (outputOrder.getOrderNumber().equals(orderNumber)) {
				found = true;
			} else {
				outputOrder = null;
			}
		}

		return outputOrder;
	}
	
	public ArrayList<Order> findOrdersWithinDate(String startDate, String endDate, boolean saleStatus){
		
		//Array Lists and Iterators
		ArrayList<Order> orders = OrderContainer.getInstance().getOrders();
		ArrayList<Order> ordersWithinDate = new ArrayList<>();
		Iterator<Order> it = orders.iterator();
		
		//Date search parameters parsed from String to LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		//It searches for days after and before, but not the given date, so the start and end date has one extra day added
		LocalDate start = LocalDate.parse(startDate, formatter).minusDays(1);
		LocalDate end = LocalDate.parse(endDate, formatter).plusDays(1);
		
		
		boolean notWithinDate = false; //Stops loop if a order isn't within date
		boolean check; //Boolean to Check if its a Sale
		
		
		//Itrator loop to check for orders within Date
		while (!notWithinDate && it.hasNext()) {
			
			//The order to check
			Order temp = it.next();
			
			//Check if its a Sale
			if (saleStatus) {
				check = temp.getStatus() == OrderStatus.SALE;
			} else {
				check = temp.getStatus() != OrderStatus.SALE;
			}
			
			//Get the creation date of the order
			LocalDate orderDate = LocalDate.parse(temp.getDate().format(formatter),formatter);
			
			//Check if its within the searched date and added if true
			//The loop ends if a order isn't within date considering orders are added in chronological order
			//Meaning if one is no longer within date, every order that came after can't be either
			if (orderDate.isAfter(start) && orderDate.isBefore(end)) {
				if (check) {
					ordersWithinDate.add(temp);//Adds the current order to list of orders within date
				} else if (orderDate.isAfter(end)) {
					notWithinDate = true; //Ends the loop
				}
			}
		}
		return ordersWithinDate; //List of Orders within date
	}

	public boolean removeOrder(String orderNumber) {
		boolean success = false;
		Order orderToRemove = findOrder(orderNumber);
		success = OrderContainer.getInstance().removeOrder(orderToRemove);
		return success;
	}
}

package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.*;
import model.Order.OrderStatus;

/**
 * It's a controller class for the Order class
 */
public class OrderController {
	private Order currentOrder;
	private ProductController productController;

	// Creating a new instance of the ProductController class.
	public OrderController() {
		productController = new ProductController();
	}

	/**
	 * If the current order is not null, add it to the order container.
	 * 
	 * @return A boolean value true if successful
	 */
	public boolean addOrder() {
		boolean success = false;
		if (currentOrder != null) {
			success = OrderContainer.getInstance().addOrder(getCurrentOrder());
		}
		return success;
	}

	/**
	 * This function creates a new order object and assigns it to the currentOrder
	 * variable
	 * 
	 * @param saleStatus true if the order is a sale, false if it's a normal Order
	 */
	public void createOrder(boolean saleStatus) {
		currentOrder = new Order(saleStatus);
	}

	/**
	 * This function returns the current order
	 * 
	 * @return The currentOrder object.
	 */
	public Order getCurrentOrder() {
		return currentOrder;
	}

	/**
	 * This method takes two input parameters search and quantity, searches for a
	 * product if product exists, the method will add the product to current order
	 * with the input amount
	 * 
	 * @param search   The Barcode/ProductID of the product to add to the order
	 * @param quantity the amount of the product to add
	 * @return A boolean value. True if successful
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

	/**
	 * This method takes one parameter and uses that to find an object if product
	 * exists, the method will add the product to current order with the input
	 * amount
	 * 
	 * @param search The Barcode/ProductID of the product to be removed.
	 * @return A boolean value. True if successful
	 */
	public boolean removeProduct(String search) {
		boolean success = false;
		success = currentOrder.removeProduct(search);
		return success;
	}

	/**
	 * This function iterates through a List of Orders and returns the first Order
	 * that matches
	 * the orderNumber parameter
	 * 
	 * @param orderNumber The order number of the order you want to find.
	 * @return The order object that matches the order number.
	 */
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

	/**
	 * It searches for orders within a given date range and returns a list of orders
	 * within that date
	 * range
	 * 
	 * @param startDate  The start date of the search
	 * @param endDate    The end date of the search
	 * @param saleStatus true if you want to find sales, false if you want to find
	 *                   orders
	 * @return An ArrayList of Orders within the given date parameters
	 */
	public ArrayList<Order> findOrdersWithinDate(String startDate, String endDate, boolean saleStatus) {

		// Array Lists and Iterators
		ArrayList<Order> orders = OrderContainer.getInstance().getOrders();
		ArrayList<Order> ordersWithinDate = new ArrayList<>();
		Iterator<Order> it = orders.iterator();

		// Date search parameters parsed from String to LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// It searches for days after and before, but not the given date, so the start
		// and end date has one extra day added
		LocalDate start = LocalDate.parse(startDate, formatter).minusDays(1);
		LocalDate end = LocalDate.parse(endDate, formatter).plusDays(1);

		boolean notWithinDate = false; // Stops loop if a order isn't within date
		boolean check; // Boolean to Check if its a Sale

		// Itrator loop to check for orders within Date
		while (!notWithinDate && it.hasNext()) {

			// The order to check
			Order temp = it.next();

			// Check if its a Sale
			if (saleStatus) {
				check = temp.getStatus() == OrderStatus.SALE;
			} else {
				check = temp.getStatus() != OrderStatus.SALE;
			}

			// Get the creation date of the order
			LocalDate orderDate = LocalDate.parse(temp.getDate().format(formatter), formatter);

			// Check if its within the searched date and added if true
			// The loop ends if a order isn't within date considering orders are added in
			// chronological order
			// Meaning if one is no longer within date, every order that came after can't be
			// either
			if (orderDate.isAfter(start) && orderDate.isBefore(end)) {
				if (check) {
					ordersWithinDate.add(temp);// Adds the current order to list of orders within date
				} else if (orderDate.isAfter(end)) {
					notWithinDate = true; // Ends the loop
				}
			}
		}
		return ordersWithinDate; // List of Orders within date
	}

	/**
	 * This function removes an order from the order container
	 * 
	 * @param orderNumber The order number of the order to be removed.
	 * @return A boolean value. True if successful
	 */
	public boolean removeOrder(String orderNumber) {
		boolean success = false;
		Order orderToRemove = findOrder(orderNumber);
		success = OrderContainer.getInstance().removeOrder(orderToRemove);
		return success;
	}
	
	public ArrayList<Order> getAllOrders() {
		return OrderContainer.getInstance().getOrders();
	}
	
	public ArrayList<OrderStatus> getAllStatusTypes() {
		ArrayList<OrderStatus> statuses = new ArrayList<>();
		statuses.add(OrderStatus.CONFIRMATION);
		statuses.add(OrderStatus.DELIVERED);
		statuses.add(OrderStatus.ENROUTE);
		statuses.add(OrderStatus.FINISHED);
		statuses.add(OrderStatus.PACKING);
		statuses.add(OrderStatus.SALE);
		return statuses;
	}
}

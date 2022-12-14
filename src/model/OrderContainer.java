package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a singleton class that stores a list of orders
 */
public class OrderContainer {
	private static OrderContainer instance;
	private List<Order> orders;

	// A constructor.
	private OrderContainer() {
		orders = new ArrayList<>();
	}

	/**
	 * This function resets the singleton instance of the class and clears the
	 * orders list
	 */
	public void resetSingleton() {
		instance = null;
		orders.clear();
	}

	/**
	 * If the instance is null, create a new instance of the class
	 * 
	 * @return The instance of the OrderContainer class.
	 */
	public static OrderContainer getInstance() {
		if (instance == null) {
			instance = new OrderContainer();
		}
		return instance;
	}

	/**
	 * This function adds an order/sale to the list of orders if the order/sale is
	 * not already in the list
	 * 
	 * @param o The order/sale to be added to the list of orders.
	 * @return A boolean value
	 */
	public boolean addOrder(Order o) {
		boolean success = false;

		if (!orders.contains(o)) {
			success = true;
			orders.add(o);
		}
		return success;
	}

	/**
	 * This function removes an order/sale from the list of orders
	 * 
	 * @param o The order to be removed.
	 * @return A boolean value.
	 */
	public boolean removeOrder(Order o) {
		boolean success = false;
		if (o != null && orders.contains(o)) {
			success = true;
			orders.remove(o);
		}
		return success;
	}

	/**
	 * This function returns a copy of the orders list.
	 * 
	 * @return A copy of the orders list.
	 */
	public ArrayList<Order> getOrders() {
		return new ArrayList<>(orders);
	}
}

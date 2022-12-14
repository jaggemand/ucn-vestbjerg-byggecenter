package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.time.LocalDate;

/**
 * It's a class that represents an order.
 */
public class Order {
	private static int tempOrderNumber;
	private String orderNumber;
	private LocalDate date;
	private double totalPrice;
	private OrderStatus status;
	private LocalDate pickupDate;
	private List<OrderLine> orderLines;

	// An enum that is used to set the status of an order.
	public enum OrderStatus {
		CONFIRMATION, PACKING, FINISHED, ENROUTE, DELIVERED, SALE;
	}

	// The constructor for the Order class.
	public Order(boolean saleStatus) {
		orderLines = new ArrayList<OrderLine>();
		date = LocalDate.now();
		if (saleStatus) {
			this.status = OrderStatus.SALE;
		} else {
			this.status = OrderStatus.CONFIRMATION;
		}
		this.pickupDate = LocalDate.now().plusDays(2); // Default 2 day deliverytime value

		this.orderNumber = "352-" + tempOrderNumber;
		tempOrderNumber++;

	}

	/**
	 * This function removes a product from the orderLines list
	 * 
	 * @param search The barcode of the product to be removed.
	 * @return The method returns a boolean value.
	 */
	public boolean removeProduct(String search) {
		boolean removed = false;
		Iterator<OrderLine> it = orderLines.iterator();
		while (it.hasNext() && !removed) {
			OrderLine ol = it.next();
			if (ol.getProduct().getBarcode().equals(search)) {
				orderLines.remove(ol);
				removed = true;
			}
		}
		return removed;
	}

	/**
	 * If the product is already in the order, increase the quantity. Otherwise, add
	 * a new orderline
	 * 
	 * @param p        Product to add to the order
	 * @param quantity the number of products to be added
	 */
	public void addProduct(Product p, int quantity) {
		boolean alreadyExists = false;
		Iterator<OrderLine> it = orderLines.iterator();
		while (it.hasNext() && !alreadyExists) {
			OrderLine ol = it.next();
			if (ol.getProduct().equals(p)) {
				ol.setQuantity(ol.getQuantity() + quantity);
				alreadyExists = true;
			}
		}
		if (!alreadyExists) {
			orderLines.add(new OrderLine(quantity, p));
		}
	}

	/**
	 * This function returns a copy of the orderLines ArrayList.
	 * 
	 * @return A copy of the orderLines ArrayList.
	 */
	public ArrayList<OrderLine> getOrderLines() {
		return new ArrayList<>(orderLines);
	}

	/**
	 * It calculates the total price of the order by adding the price of each
	 * orderline to the total price
	 * 
	 * @return The total price of the order.
	 */
	public double getTotalPrice() {
		totalPrice = 0;
		// makes sure there is no nullpointer exception
		if (orderLines.get(0) != null) {
			for (OrderLine element : orderLines) {
				totalPrice += (element.getProduct().getSalesPrice() * element.getQuantity());
			}
		}
		return totalPrice;
	}

	/**
	 * This function sets the status of the order
	 * 
	 * @param status The status of the order.
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * This function sets the pickup date of the order
	 * 
	 * @param pickup The date the order is to be delivered
	 */
	public void setPickup(LocalDate pickup) {
		this.pickupDate = pickup;
	}

	/**
	 * This function returns the status of the order
	 * 
	 * @return The status of the order.
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * It takes the status of the order and returns a string with the status in
	 * plain text
	 * 
	 * @return A string with the status of the order.
	 */
	public String getStatusAsString() {
		String statusString = "";
		switch (status) {
			case CONFIRMATION:
				statusString = "Bekræftet";
				break;
			case PACKING:
				statusString = "Pakkes";
				break;
			case FINISHED:
				statusString = "Klar til afsending";
				break;
			case ENROUTE:
				statusString = "På vej";
				break;
			case DELIVERED:
				statusString = "Leveret";
				break;
			case SALE:
				statusString = "Salg";
				break;
			default:
				statusString = "Fejl! Status kunne ikke findes";
				break;
		}
		return statusString;
	}

	/**
	 * This function returns the pickup date
	 * 
	 * @return The pickup date.
	 */
	public LocalDate getPickup() {
		return pickupDate;
	}

	/**
	 * This function returns the date the order was created
	 * 
	 * @return The date the order was created.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * !FOR TESTING PURPOSES ONLY!
	 * This function sets the date to the current date minus the number of days
	 * passed in
	 * 
	 * @param days The number of days to subtract from the current date.
	 */
	public void setDate(long days) {
		date = LocalDate.now().minusDays(days);
	}

	/**
	 * This function returns the order number
	 * 
	 * @return The orderNumber is being returned.
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
}

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.time.LocalDate;

public class Order {
	//date needs to be a dateTime something
	//orderNumber needs to be set to a value or auto incremented?

	private static int tempOrderNumber;
	private String orderNumber;
	private LocalDate date;
	private double totalPrice;
	private OrderStatus status;
	private LocalDate pickupDate;
	private List<OrderLine> orderLines;

	public enum OrderStatus {
		CONFIRMATION, PACKING, FINISHED, ENROUTE, DELIVERED, SALE;
	}

	// Order constructor does not take any parameters
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

	public ArrayList<OrderLine> getOrderLines() {
		return new ArrayList<>(orderLines);
	}

	/**
	 * @return the calculated total price of the order
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
	 * @param the new status the user/system wishes the order to have
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * @param the pickup type the user/system which the order to have
	 */
	public void setPickup(LocalDate pickup) {
		this.pickupDate = pickup;
	}

	/**
	 * @return returns the status of the order
	 */
	public OrderStatus getStatus() {
		return status;
	}
	
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
	 * @return returns the type of pickup
	 */
	public LocalDate getPickup() {
		return pickupDate;
	}

	/**
	 * @return return the date at which the order was instantiated
	 */
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(long days){
		date = LocalDate.now().minusDays(days);
	}

	public String getOrderNumber() {
		return orderNumber;
	}
}

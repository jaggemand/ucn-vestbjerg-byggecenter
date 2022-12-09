package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; 


public class Order {
	//date needs to be a dateTime something
	private static int tempOrderNumber;
	private String orderNumber;
	private String date;
	private double totalPrice;
	private String status;
	private String pickup;
	private List<OrderLine> orderLines;
	
	//Order constructor does not take any parameters
	public Order() {
		orderLines = new ArrayList<OrderLine>();
		date = setDate();
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
	
	public void addProduct(Product p, int quantity){
		boolean alreadyExists = false;
		Iterator<OrderLine> it = orderLines.iterator();
		while(it.hasNext() && !alreadyExists){
			OrderLine ol = it.next();
			if(ol.getProduct().equals(p)){
				ol.setQuantity(ol.getQuantity()+quantity);
				alreadyExists = true;
			}
		}
		if(!alreadyExists){
			orderLines.add(new OrderLine(quantity, p));
		}
	}
	
	public ArrayList<OrderLine> getOrderLines(){
		return new ArrayList<>(orderLines);
	}
	
	//sets the date in the constructor
	private String setDate() {
		LocalDate dateObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String tempdate = dateObj.format(formatter);
		return tempdate;
	}
	
	/**
	 * @return the calculated total price of the order
	 */
	public double getTotalPrice() {	
		totalPrice = 0;
		//makes sure there is no nullpointer exception
		if (orderLines.get(0) != null) {
			for (OrderLine element: orderLines) {
			totalPrice += (element.getProduct().getSalesPrice() * element.getQuantity());
			}
		}
		return totalPrice;
	}
	
	/**
	 * @param the new status the user/system wishes the order to have
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @param the pickup type the user/system wiches the order to have
	 */
	public void setPickup(String pickup) {
		this.pickup = pickup;
	}
	
	/**
	 * @return returns the status of the order
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @return returns the type of pickup
	 */
	public String getPickup() {
		return pickup;
	}
	/**
	 * @return return the date at which the order was instantiated
	 */
	public String getDate() {
		return date;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
}

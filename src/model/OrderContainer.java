package model;

import java.util.ArrayList;
import java.util.Iterator; 

public class OrderContainer {
	private static OrderContainer instance;
	private ArrayList<Order> orders;
	
	private OrderContainer() {
		orders = new ArrayList<>();
	}
	
	public static OrderContainer getInstance() {
		if (instance == null) {
			instance = new OrderContainer();
		}
		return instance;
	}
	
	public boolean addOrder(Order o) {
		boolean success = false;
		
		if (!orders.contains(o)) {
			success = true;
			orders.add(o);
		}
		return success;
	}
	
	public boolean removeOrder(String orderNumber) {
		boolean removed = false;
		Iterator<Order> it = orders.iterator();
		while (it.hasNext()) {
			Order p = it.next();
			if (p.getOrderNumber().equals(orderNumber)) {
				it.remove();
				removed = true;
			}
		}
		return removed;
	}
	
	public ArrayList<Order> getOrders() {
		return new ArrayList<>(orders);
	}
}

package model;

import java.util.ArrayList;

public class OrderContainer {
	private static OrderContainer instance;
	private ArrayList<Order> orders;
	
	private OrderContainer() {
		orders = new ArrayList<>();
	}
	
	public void resetSingleton() {
		instance = null;
		orders.clear();
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
	
	public boolean removeOrder(Order o){
		boolean success = false;
		if (o != null && orders.contains(o)) {
			success = true;
			orders.remove(o);
		}
		return success;
	}
	
	public ArrayList<Order> getOrders() {
		return new ArrayList<>(orders);
	}
}

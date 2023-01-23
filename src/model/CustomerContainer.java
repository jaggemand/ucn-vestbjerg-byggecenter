package model;

import java.util.ArrayList;
import java.util.List;

import model.Customer.customerType;

public class CustomerContainer {
	private static CustomerContainer instance;
	private ArrayList<Customer> customers;
	
	private CustomerContainer() {
		customers = new ArrayList<>();
		customers.add(new Customer("Kassesalg", "N/A", "N/A", "0", "N/A", 0.0, "N/A", "N/A", customerType.PRIVATE));
	}
	
	public static CustomerContainer getInstance() {
		if(instance == null) {
			instance = new CustomerContainer();
		}
		return instance;
	}
	
	public boolean addCustomer(Customer customer) {
		boolean result = false;
		if(customer != null) {
			result = true;
			customers.add(customer);
		}
		return result;
	}
	
	public ArrayList<Customer> getCustomers(){
		return customers;
	}
	
	public boolean removeCustomer(Customer c) {
		boolean success = false;
		if (c != null && customers.contains(c)) {
			success = true;
			customers.remove(c);
		}
		return success;
	}
}

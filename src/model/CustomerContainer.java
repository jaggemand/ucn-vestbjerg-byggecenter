package model;

import java.util.ArrayList;
import java.util.List;

public class CustomerContainer {
	private static CustomerContainer instance;
	private List<Customer> customers;
	
	private CustomerContainer() {
		custommers = new ArrayList<>();
	}
	
	public static CustomerContainer getInstance() {
		if(instance == null) {
			instance = new CustomerContainer();
		}
		return instance;
	}
	
	public boolean  addCustomer(Customer customer) {
		boolean result = false;
		if(customer != null) {
			result = true;
			customers.add(customer);
		}
		return result;
	}
	
	
	
	

}

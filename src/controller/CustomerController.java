package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.Customer;
import model.CustomerContainer;

public class CustomerController {
	
	private Customer currentCustomer;
	
	public CustomerController() {
		currentCustomer = null;
	}
	
	//TODO add functionality in container
	private boolean addCustomer(Customer c) {
		
	}
	public boolean removeCustomer(String customerID) {
	
	}
	
	public void setCustomer(Customer c) {
		this.currentCustomer = c;
	}
	
	public Customer getCustomer() {
		return this.currentCustomer;
	}
	
	public Customer findCustomerByInformation(String phone){
		Customer returnCustomer = null;
		ArrayList<Customer> customers = CustomerContainer.getInstance().getCustomers();
		boolean found = false;
		Iterator<Customer> it = customers.iterator();
		while (!found && it.hasNext()) {
			returnCustomer = it.next();
			if(returnCustomer.getPhone().equals(phone)) {
				found = true;
			}
			else {
				returnCustomer = null;
			}
		}
		return returnCustomer;
	}
	
	public ArrayList<Customer> getAllCustomers() {
		return CustomerContainer.getInstance().getCustomers();
	}
	
	//TODO add functionality in container
	public boolean createCustomer (String name, String sirname, String address, String deliveryAddress
			, String paymentAddress, String phone, String email, double credit, String postCode, String companyName) {
		boolean result = false;
		Customer c = new Customer(name, sirname, address, postCode, credit, phone, email, companyName);
		result = addCustomer(c);
		return result; 
	}
}



package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Customer;
import model.CustomerContainer;

public class CustomerController {
	
	private Customer currentCustomer;
	
	public CustomerController() {
		currentCustomer = null;
	}
	
	//TODO add functionality in customerContainer
	private boolean addCustomer(Customer c) {
		boolean success = false;
		if (currentCustomer != null) {
			success = CustomerContainer.getInstance().addCustomer(c);
		}
		return success;
	}
	
	//TODO add function to customerContainer
	public boolean removeCustomer(String phone) {
		boolean success = false;
		Customer c = findCustomerByInformation(phone);
		
		if(c != null) {
			success = CustomerContainer.getInstance().removeCustomer(c);
		}
		return success;
	}
	
	public void setCustomer(Customer c) {
		this.currentCustomer = c;
	}
	
	public Customer getCustomer() {
		return this.currentCustomer;
	}
	
	//TODO add functionality to customerContainer
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
		
		Customer c = new Customer(name, sirname, address, deliveryAddress,
				paymentAddress, phone, email, credit, postCode, companyName);
				
		result = addCustomer(c);
		return result; 
	}
}
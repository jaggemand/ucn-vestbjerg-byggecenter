package controller;

import java.util.ArrayList;

import model.Customer;

public class CustomerController {
	
	private Customer currentCustomer;
	
	public CustomerController() {
		currentCustomer = null;
	}
	
	public boolean addCustomer() {
		
	}
	public boolean removeCustomer(String customerID) {
	
	}
	
	public Customer setCustomer() {
	
	
	}
	
	public Customer getCustomer() {
	
	
	}
	
	public ArrayList<Customer> findCustomerByInformation(){
	
	}
	
	public ArrayList<Customer> getAllCustomers() {
		
	}
	
	public boolean createCustomer(String name, String sirname, String, address, String deliveryAddress
			, String paymentAddress, String phone, String email, double credit, String postCode, String companyName) {
		Custemer c = new Customer(name, sirname, address, postCode, credit, phone, email, companyName);
		
	}
}



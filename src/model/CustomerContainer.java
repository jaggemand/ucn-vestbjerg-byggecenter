package model;

import java.util.ArrayList;
import java.util.List;

public class CustomerContainer {
	private static CustomerContainer instance;
	private List<Customer> customers;
	
	private CustomerContainer() {
		customers = new ArrayList<>();
	}
	
	public static CustomerContainer getInstance() {
		
	}

}

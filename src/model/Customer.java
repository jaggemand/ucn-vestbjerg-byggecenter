package model;

import java.util.Iterator;
import java.util.List;

public class Customer {
	private String name;
	private String address;
	private String deliveryAddress;
	private String paymentAddress;
	private String phone;
	private String email;
	private double credit;
	private String postcode;
	private String companyName;
	private customerType accountType;
	
	public enum customerType {
		PRIVATE, BUSINESS;
	}

	public Customer(String name, String address, String deliveryAddress, String paymentAddress,
			String phone, String email, double credit, String postcode, String companyName, customerType type) {
		this.name = name;
		this.address = address;
		this.deliveryAddress = deliveryAddress;
		this.paymentAddress = paymentAddress;
		this.phone = phone;
		this.email = email;
		this.credit = credit;
		this.postcode = postcode;
		this.companyName = companyName;
		this.accountType = type;
	}
	
	public customerType getCustomerType() {
		return accountType;
	}
	
	public void setCustomerType(customerType type) {
		this.accountType = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getPaymentAddress() {
		return paymentAddress;
	}

	public void setPaymentAddress(String paymentAddress) {
		this.paymentAddress = paymentAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCompamyName() {
		return companyName;
	}

	public void setCompamyName(String compamyName) {
		this.companyName = companyName;
	}
	
	
}
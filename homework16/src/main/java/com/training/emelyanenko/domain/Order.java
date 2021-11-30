package com.training.emelyanenko.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents POJO of order.
 */
public class Order {

	private int id;
	private int userId;
	private String customer;
	private List<Product> products = new ArrayList<>();
	private double totalPrice = 0.0;
	public Order(String customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}

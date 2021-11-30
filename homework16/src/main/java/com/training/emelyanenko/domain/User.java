package com.training.emelyanenko.domain;

/**
 * Class that represents POJO of user.
 */
public class User {

	Order order;
	private int id;
	private String name;

	public User(String name) {
		this.name = name;
		order = new Order(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}

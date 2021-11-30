package com.training.emelyanenko.service;

import com.training.emelyanenko.domain.Order;
import com.training.emelyanenko.domain.PriceList;
import com.training.emelyanenko.domain.Product;
import com.training.emelyanenko.domain.User;
import com.training.emelyanenko.exception.InvalidArgumentException;
import com.training.emelyanenko.repository.OrderRepository;
import com.training.emelyanenko.repository.UserRepository;

import java.util.List;

/**
 * Order service class.
 */
public class OrderService {

	/**
	 * Creates order and returns saved order.
	 *
	 * @param customer customer name.
	 * @return created order.
	 */
	public static Order createOrGetOrder(String customer) {
		if (customer == null) {
			throw new InvalidArgumentException("Name can't be null");
		}
		if (UserRepository.getByName(customer).isPresent() && UserRepository.getByName(customer).get().getOrder() != null) {
			return UserRepository.getByName(customer).get().getOrder();
		} else {
			return OrderRepository.save(new Order(customer));
		}
	}

	/**
	 * Adds product to order.
	 *
	 * @param user             the user in whose order the product is adds.
	 * @param selectedProducts string array of product keys from product map.
	 * @return order with saved order.
	 */
	public static Order addProducts(User user, String[] selectedProducts) {
		if (user == null || selectedProducts == null) {
			throw new InvalidArgumentException("Arguments cant be null");
		}

		Order order = user.getOrder();
		List<Product> products = order.getProducts();
		String productName = selectedProducts[0];
		Product product = new Product();
		product.setName(productName);
		product.setPrice(PriceList.getPRODUCTS().get(productName));
		products.add(product);
		order.setTotalPrice(calcTotalPrice(order));
		order.setProducts(products);
		return order;
	}

	/**
	 * Calculates total price of order.
	 *
	 * @param order order for calculation.
	 * @return total price.
	 */
	public static double calcTotalPrice(Order order) {
		double totalPrice = 0.0;
		for (Product product : order.getProducts()) {
			totalPrice += product.getPrice();
		}
		return totalPrice;
	}
}

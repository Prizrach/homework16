package com.training.emelyanenko.service;

import com.training.emelyanenko.domain.Order;
import com.training.emelyanenko.domain.PriceList;
import com.training.emelyanenko.domain.Product;
import com.training.emelyanenko.exception.InvalidArgumentException;
import com.training.emelyanenko.repository.ProductRepository;

import java.util.List;

public final class ProductService{

	private ProductService() {
	}

	public static void addProductToOrder(Order order, String[] selectedProducts) {
		if (order == null || selectedProducts == null) {
			throw new InvalidArgumentException("Arguments cant be null");
		}

		List<Product> products = order.getProducts();
		String productName = selectedProducts[0];
		Product product = new Product();
		product.setName(productName);
		product.setPrice(PriceList.getPRODUCTS().get(productName));
		product.setId(ProductRepository.getIdByName(productName));
		products.add(product);
		order.setTotalPrice(OrderService.calcTotalPrice(order));
		order.setProducts(products);

		ProductRepository.saveOrderGood(product, order.getId());
	}

}

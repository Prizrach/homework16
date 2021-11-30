package com.training.emelyanenko.domain;

import com.training.emelyanenko.repository.ProductRepository;

import java.util.Map;


/**
 * Map of products.
 */
public class PriceList {

	private static final Map<String, Double> PRODUCTS = initMap();

	private PriceList() {
	}

	private static Map<String, Double> initMap() {
		return ProductRepository.getAll();
	}

	public static Map<String, Double> getPRODUCTS() {
		return PRODUCTS;
	}
}

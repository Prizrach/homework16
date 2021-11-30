package com.training.emelyanenko.service;

import com.training.emelyanenko.domain.Order;
import com.training.emelyanenko.domain.User;
import com.training.emelyanenko.exception.InvalidArgumentException;
import com.training.emelyanenko.repository.UserRepository;

import java.util.Optional;

public final class UserService {

	private UserService () {
	}

	/**
	 * Returns the user from user repository if it was created.
	 * Creates and returns the user from user repository if it wasn't created.
	 *
	 * @param customer it's customer name.
	 * @return user from user repository.
	 */
	public static User createOrGet(String customer) {
		if (customer == null || customer.equals("")) {
			throw new InvalidArgumentException("Customer name can't be null");
		}
		Optional<User> user;
		if (UserRepository.getByName(customer).isPresent()) {
			return UserRepository.getByName(customer).get();
		} else {
			return UserRepository.save(new User(customer));
		}
	}
}

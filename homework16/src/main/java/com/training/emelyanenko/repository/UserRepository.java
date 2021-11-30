package com.training.emelyanenko.repository;

import com.training.emelyanenko.domain.Order;
import com.training.emelyanenko.domain.Product;
import com.training.emelyanenko.domain.User;
import com.training.emelyanenko.service.OrderService;
import com.training.emelyanenko.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class UserRepository {

	private static Connection conn = SqlHelper.getConnection();
	private UserRepository() {

	}

	/**
	 * Saves user in "data base" (list of orders).
	 *
	 * @param user it's order to save.
	 * @return saved user.
	 */
	public static User save(User user) {
		//Connection conn = SqlHelper.getConnection();

		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO USER (id, LOGIN) VALUES (?,?)")) {
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.execute();
			user.getOrder().setUserId(user.getId());
			OrderRepository.save(user.getOrder());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Returns user by id if user is exist.
	 *
	 * @param id user id.
	 * @return user by id if user is exist.
	 */
	public static Optional<User> getById(String id) {
		User user = null;
		ResultSet rs = null;
		try (PreparedStatement ps = conn.prepareStatement("" +
				"SELECT * FROM USER user\n" +
				"LEFT JOIN SHOP_ORDER o\n" +
				"ON user.ID = o.USER_ID\n" +
				"LEFT JOIN ORDER_GOOD OG\n" +
				"ON o.ID = OG.ORDER_ID\n" +
				"LEFT JOIN GOOD good\n" +
				"ON OG.GOOD_ID = good.ID\n" +
				"WHERE user.ID = ?")) {
			ps.setString(1, id);
			rs = ps.executeQuery();
			List<Product> products = new ArrayList<>();

			if (!rs.next()) {
				return Optional.empty();
			}

			user = new User(rs.getString("LOGIN"));
			user.setId(rs.getInt("id"));
			Product product = new Product();
			Order order = new Order(user.getName());
			order.setUserId((rs.getInt("user_id")));
			order.setCustomer(user.getName());
			order.setProducts(products);
			do {
				if (rs.getString("title") == null) {
					continue;
				}
				product.setName(rs.getString("title"));
				product.setPrice(rs.getDouble("price"));
				product.setId(rs.getInt("good_id"));
				products.add(product);
			} while (rs.next());

			order.setId(rs.getInt("order_id"));
			//order.setTotalPrice();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (user == null) {
			return Optional.empty();
		} else {
			return Optional.of(user);
		}
	}

	public static Optional<User> getByName(String customer) {
		User user = null;
		ResultSet rs = null;
		try (PreparedStatement ps = conn.prepareStatement("" +
				"SELECT * FROM USER user\n" +
				"LEFT JOIN SHOP_ORDER o\n" +
				"ON user.ID = o.USER_ID\n" +
				"LEFT JOIN ORDER_GOOD OG\n" +
				"ON o.ID = OG.ORDER_ID\n" +
				"LEFT JOIN GOOD good\n" +
				"ON OG.GOOD_ID = good.ID\n" +
				"WHERE user.LOGIN = ?")) {
			ps.setString(1, customer);
			rs = ps.executeQuery();
			List<Product> products = new ArrayList<>();

			if (!rs.next()) {
				return Optional.empty();
			}

			user = new User(customer);
			user.setId(rs.getInt("id"));
			Product product;
			Order order = new Order(user.getName());
			order.setUserId((rs.getInt("user_id")));
			order.setCustomer(user.getName());
			order.setId(rs.getInt("order_id"));
			do {
				if (rs.getString("title") == null) {
					continue;
				}
				product = new Product();
				product.setName(rs.getString("title"));
				product.setPrice(rs.getDouble("price"));
				product.setId(rs.getInt("good_id"));
				products.add(product);
			} while (rs.next());

			order.setProducts(products);
			order.setTotalPrice(OrderService.calcTotalPrice(order));
			user.setOrder(order);

			//order.setTotalPrice();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (user == null) {
			return Optional.empty();
		} else {
			return Optional.of(user);
		}
	}
}

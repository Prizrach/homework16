package com.training.emelyanenko.repository;

import com.training.emelyanenko.domain.Order;
import com.training.emelyanenko.domain.Product;
import com.training.emelyanenko.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Repository class that emulate data base and has some repository methods.
 */
public class OrderRepository {

	private static Connection conn = SqlHelper.getConnection();

	private OrderRepository() {
	}


	/**
	 * Returns order by user id if order is exist.
	 *
	 * @param id order id.
	 * @return order by id if order is exist.
	 */
	public static Order getByUserId(int id) {
		Order r = null;
		ResultSet rs = null;
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM SHOP_ORDER WHERE USER_ID =?")) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				//return Optional.empty();
			}
			r = new Order("");
			r.setId(rs.getInt("id"));
			r.setUserId(id);
			r.setTotalPrice(rs.getDouble("total_price"));
			r.setProducts(ProductRepository.getByOrderId(id));
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
		if (r == null) {
			throw new RuntimeException("ad");
		} else {
			return r;
		}
	}

	/**
	 * Saves order in database.
	 *
	 * @param order it's order to save.
	 * @return saved order.
	 */
	public static Order save(Order order) {
		Connection conn = SqlHelper.getConnection();
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO SHOP_ORDER (id, user_id, total_price) VALUES (?,?,?)")) {
			ps.setInt(1, order.getId());
			ps.setInt(2, order.getUserId());
			ps.setDouble(3, order.getTotalPrice());
			ps.execute();


			for (Product product : order.getProducts()) {
				ProductRepository.saveOrderGood(product, order.getId());
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
}

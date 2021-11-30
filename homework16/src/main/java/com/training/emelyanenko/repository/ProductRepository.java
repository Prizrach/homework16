package com.training.emelyanenko.repository;

import com.training.emelyanenko.domain.Product;
import com.training.emelyanenko.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductRepository {
	private static Connection conn = SqlHelper.getConnection();

	public static void saveOrderGood(Product product, int orderId) {
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO ORDER_GOOD (ORDER_ID, GOOD_ID) VALUES (?,?)")) {
			ps.setInt(1, orderId);
			ps.setInt(2, product.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Product> getByOrderId(int id) {
		List<Product> products = new ArrayList<>();
		Product product = null;
		ResultSet rs = null;
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM ORDER_GOOD WHERE ORDER_ID =?")) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("title"));
				product.setPrice(rs.getDouble("price"));
				products.add(product);
			}

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
		return products;
	}

	public static Map<String, Double> getAll() {
		Map<String, Double> products = new HashMap<>();
		ResultSet rs = null;
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM GOOD")) {
			rs = ps.executeQuery();
			while (rs.next()) {
				products.put(rs.getString("title"), rs.getDouble("price"));
			}

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
		return products;

	}

	public static int getIdByName(String productName) {
		ResultSet rs = null;
		int productId = -1;
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM GOOD WHERE TITLE =?")) {
			ps.setString(1, productName);
			rs = ps.executeQuery();

			if (!rs.next()) {
			}
			productId = rs.getInt("id");

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
		if (productId == -1) {
			throw new RuntimeException("ada");
		} else {
			return productId;
		}
	}
}

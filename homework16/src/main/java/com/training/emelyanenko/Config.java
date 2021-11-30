package com.training.emelyanenko;

import com.training.emelyanenko.sql.SqlHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Config {
	private static final File DB_PROPS = new File("src/main/resources/db.properties");
	private static Properties props = intiProps();

	private static Properties intiProps() {
		Properties props = new Properties();
		try (InputStream is = new FileInputStream(DB_PROPS)) {
			props.load(is);
		} catch (IOException e) {
			throw new IllegalStateException("Invalid config file " + DB_PROPS.getAbsolutePath());
		}
		return props;
	}

	public static String getPassword() {
		return props.getProperty("db.password");
	}

	public static String getUrl() {
		return props.getProperty("db.url");
	}

	public static String getUser() {
		return props.getProperty("db.user");
	}


}

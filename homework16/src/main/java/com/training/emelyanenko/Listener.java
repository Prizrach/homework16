package com.training.emelyanenko;

import com.training.emelyanenko.sql.SqlHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		SqlHelper.initDB();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}

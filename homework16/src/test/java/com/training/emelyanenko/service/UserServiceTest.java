package com.training.emelyanenko.service;

import com.training.emelyanenko.Listener;
import com.training.emelyanenko.exception.InvalidArgumentException;
import com.training.emelyanenko.sql.SqlHelper;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContextAttributeEvent;

import static org.junit.Assert.*;

public class UserServiceTest {

	@Before
	public void setUp() throws Exception {
		SqlHelper.initDB();

	}

	@Test
	public void createOrGet() {
		assertEquals("Stanislav", UserService.createOrGet("Stanislav").getName());
	}

	@Test(expected = InvalidArgumentException.class)
	public void createOrGetWitExc() {
		UserService.createOrGet(null);
	}


}
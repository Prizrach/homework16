package com.training.emelyanenko.controller;

import com.training.emelyanenko.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller with mapping "/basket".
 */
@WebServlet(value = "/basket")
public class BasketServlet extends HttpServlet {

	/**
	 * Handles requests to the basket.
	 *
	 * @param req  it's http request.
	 * @param resp it's http response.
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("order", OrderService.createOrGetOrder(req.getSession().getAttribute("customer").toString()));
		req.getRequestDispatcher("WEB-INF/jsp/receipt.jsp").forward(req, resp);
	}
}

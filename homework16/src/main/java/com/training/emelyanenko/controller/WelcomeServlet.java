package com.training.emelyanenko.controller;

import com.training.emelyanenko.domain.PriceList;
import com.training.emelyanenko.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller with mapping "/login".
 */
@WebServlet(value = "/login")
public class WelcomeServlet extends HttpServlet {

	/**
	 * Forwards to login page if user didn't login. Forwards to login page if user have logged in.
	 *
	 * @param req  it's http request.
	 * @param resp it's http response.
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("customer") != null) {
			req.setAttribute("products", PriceList.getPRODUCTS());
			req.setAttribute("order", OrderService.createOrGetOrder(req.getSession().getAttribute("customer").toString()));
			req.getRequestDispatcher("WEB-INF/jsp/order.jsp").forward(req, resp);
		}
		req.getRequestDispatcher("WEB-INF/jsp/homePage.jsp").forward(req, resp);
	}
}

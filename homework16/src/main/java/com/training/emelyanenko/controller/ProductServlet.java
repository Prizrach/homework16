package com.training.emelyanenko.controller;

import com.training.emelyanenko.domain.PriceList;
import com.training.emelyanenko.repository.ProductRepository;
import com.training.emelyanenko.service.OrderService;
import com.training.emelyanenko.service.ProductService;
import com.training.emelyanenko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller with mapping "/product".
 */
@WebServlet(value = "/product")
public class ProductServlet extends HttpServlet {

	/**
	 * Forwards to order page and sends product lis as an attribute.
	 *
	 * @param req  it's http request.
	 * @param resp it's http response.
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("products", PriceList.getPRODUCTS());
		req.setAttribute("order", OrderService.createOrGetOrder(req.getSession().getAttribute("customer").toString()));
		req.getRequestDispatcher("WEB-INF/jsp/order.jsp").forward(req, resp);
	}

	/**
	 * Handles requests for adding goods to the order.
	 *
	 * @param req  it's http request.
	 * @param resp it's http response.
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("customer", req.getParameter("customer"));
		if (req.getParameterValues("selected") != null) {
			ProductService.addProductToOrder(UserService.createOrGet(req.getParameter("customer")).getOrder(), req.getParameterValues("selected"));
		}
		UserService.createOrGet(req.getSession().getAttribute("customer").toString());
		req.setAttribute("products", PriceList.getPRODUCTS());
		req.setAttribute("order", OrderService.createOrGetOrder(req.getParameter("customer")));
		req.getRequestDispatcher("WEB-INF/jsp/order.jsp").forward(req, resp);
	}
}

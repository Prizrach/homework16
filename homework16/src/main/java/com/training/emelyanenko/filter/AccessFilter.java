package com.training.emelyanenko.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AccessFilter", urlPatterns = {"/product", "/basket"})
public class AccessFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if ((((HttpServletRequest) req).getSession().getAttribute("customer") == null &&
				req.getParameter("customer") == null || ((HttpServletRequest) req).getSession().getAttribute("customer") == null &&
				req.getParameter("customer").equals("") ||
				(req.getParameter("acceptTerms") == null &&
						((HttpServletRequest) req).getSession().getAttribute("acceptTerms") == null))) {
			((HttpServletResponse) resp).sendError(401);
		} else {
			((HttpServletRequest) req).getSession().setAttribute("acceptTerms", "accepted");
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {

	}
}

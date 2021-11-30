<%@ page import="com.training.emelyanenko.domain.Order" %>
<%@ page import="com.training.emelyanenko.domain.Product" %>
<%@ page import="com.training.emelyanenko.repository.OrderRepository" %>
<%@ page import="com.training.emelyanenko.exception.OrderNotFoundException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Order order;
    if (OrderRepository.getById(request.getParameter("id")).isPresent()) {
        order = OrderRepository.getById(request.getParameter("id")).get();
    } else {
        order = null;
        throw new OrderNotFoundException("Order not found");
    }%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
    <title>Online-shop</title>
</head>
<body>
<div class="box">
    <p>Dear <%= order.getCustomer()%>, your order:</p>
    <%! int index = 0; %>
    <% for (Product productT : order.getProducts()) { %>
    <p><%=index += 1%>) <%= productT.getName() + " " + productT.getPrice()%>$</p>
    <%}%>
    <p>Total: <%= order.getTotalPrice()%>$</p>
</div>
</body>
</html>
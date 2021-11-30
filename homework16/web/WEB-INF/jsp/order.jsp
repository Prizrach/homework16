<%@ page import="com.training.emelyanenko.domain.Order" %>
<%@ page import="com.training.emelyanenko.service.OrderService" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% Order order = OrderService.createOrGetOrder(session.getAttribute("customer").toString());%>
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
    <p class="msg">Hello <%=order.getCustomer()%> !</p>
    <p class="msg">Make you order</p>
    <form method="post" action="product">
        <select name="selected">
            <c:forEach var="product" items="${products}">
                <option value="${product.key}">${product.key} (${product.value}$)</option>
            </c:forEach>
        </select>
        <input type="submit" value="Add item"/>
        <input type="hidden" name="id" value="<%= order.getId()%>"/>
        <input type="hidden" name="customer" value="<%= order.getCustomer()%>"/>
    </form>
    <form method="post" action="basket">
        <input type="submit" value="Submit">
        <input type="hidden" name="id" value="<%= order.getId()%>"/>
        <input type="hidden" name="customer" value="<%= order.getCustomer()%>"/>
    </form>
    <p>You have already chosen:</p>
    <c:forEach var="pickedProduct" items="${order.getProducts()}">
        <P>${pickedProduct.getName()} ${pickedProduct.getPrice()}</P>
    </c:forEach>
</div>
</body>
</html>


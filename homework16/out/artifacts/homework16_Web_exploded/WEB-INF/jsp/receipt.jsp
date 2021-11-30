<%@ page import="com.training.emelyanenko.domain.Order" %>
<%@ page import="com.training.emelyanenko.service.OrderService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <p>Dear <%= order.getCustomer()%>, your order:</p>
    <% int index = 0; %>
    <c:forEach var="pickedProduct" items="${order.getProducts()}">
        <p><%=index += 1%>) ${pickedProduct.getName()} ${pickedProduct.getPrice()}</p>
    </c:forEach>
    <p>Total: <%= order.getTotalPrice()%>$</p>
</div>
</body>
</html>
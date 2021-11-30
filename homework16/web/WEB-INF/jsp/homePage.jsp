<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online-Shop</title>
    <meta charset=\"utf-8\">
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<div class="box">
    <p class="msg">Welcome to Online Shop</p>
    <form method="post" action="product">
        <input type="text" name="customer" placeholder="Enter your name">
        <br>
        <p><input type="checkbox" id="acceptTerms" name="acceptTerms"/>I agree with the terms of service</p>
        <input type="submit" value="Enter">
    </form>
</div>
</body>
</html>
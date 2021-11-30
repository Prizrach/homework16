<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Online-shop</title>
</head>
<body>
<div class="container">
    <div>
        <h1>Hello <%=request.getParameter("name")%>!</h1>
        <form method="post">
            <p><i>Make your order</i></p>
            <select multiple size="1">
                <c:forEach var="product" items="${products}">
                    <option value="${product.key}">${product.key} (${product.value}$)</option>
                </c:forEach>
            </select>
            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>

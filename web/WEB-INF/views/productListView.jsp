<%--
  Created by IntelliJ IDEA.
  User: iraimiks
  Date: 18.30.9
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Product List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Product Name</th>
        <th>Product Price</th>
        <th>Product Qty</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${productList}" var="product" >
        <tr>
            <td>${product.productName}</td>
            <td>${product.qty}</td>
            <td>${product.oneProductPrice}</td>
            <td>
                <a href="editProduct?code=${product.productName}">Edit</a>
            </td>
            <td>
                <a href="deleteProduct?code=${product.productName}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="createProduct" >Create Product</a>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
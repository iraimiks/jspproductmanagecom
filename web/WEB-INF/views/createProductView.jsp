<%--
  Created by IntelliJ IDEA.
  User: iraimiks
  Date: 18.30.9
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Product</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Create Product</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/createProduct">
    <table border="0">
        <tr>
            <td>ProductName</td>
            <td><input type="text" name="productName" value="${product.productName}" /></td>
        </tr>
        <tr>
            <td>Qty</td>
            <td><input type="text" name="qty" value="${product.qty}" /></td>
        </tr>
        <tr>
            <td>OnePiecePrice</td>
            <td><input type="text" name="oneProductPrice" value="${product.oneProductPrice}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
                <a href="productList">Cancel</a>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>

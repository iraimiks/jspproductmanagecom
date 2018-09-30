<%--
  Created by IntelliJ IDEA.
  User: iraimiks
  Date: 18.30.9
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Edit Product</h3>

<p style="color: red;">${errorString}</p>

<c:if test="${not empty product}">
    <form method="POST" action="${pageContext.request.contextPath}/editProduct">
        <input type="hidden" name="productName" value="${product.productName}" />
        <table border="0">
            <tr>
                <td>ProductName</td>
                <td style="color:red;">${product.productName}</td>
            </tr>
            <tr>
                <td>QTY</td>
                <td><input type="text" name="qty" value="${product.qty}" /></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="oneProductPrice" value="${product.oneProductPrice}" /></td>
            </tr>
            <tr>
                <td colspan = "2">
                    <input type="submit" value="Submit" />
                    <a href="${pageContext.request.contextPath}/productList">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</c:if>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>

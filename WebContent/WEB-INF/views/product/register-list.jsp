<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>등록한 상품 목록</title>
</head>
<body>
    <h1>등록한 상품 목록</h1>
    <table border="1">
        <tr>
            <th>상품 ID</th>
            <th>상품 이름</th>
            <th>설명</th>
            <th>가격</th>
            <th>재고</th>
            <th>썸네일</th>
        </tr>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.stock}</td>
                <td><img src="${product.thumbnail}" alt="${product.productName}" width="50"></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

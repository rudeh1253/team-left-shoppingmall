<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${ title }</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            width: 1200px;
            height: 600px;
            margin-top: 100px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>${ title }</h2>
        <table class="table table-bordered text-center align-middle">
            <thead class="table-light">
                <tr>
                    <th>이미지</th>
                    <th>상품 명</th>
                    <th>가격</th>
                    <th>수량</th>
                    <th>합계</th>
                    <th>배송 상태</th>
                </tr>
            </thead>
            <tbody>
           		<c:forEach var="receipt" items="${receiptList}">
            		<tr>
            			<td><img src="${receipt.thumbnail}" alt="상품 이미지" class="img-thumbnail" /></td>
            			<td>${receipt.productName}</td>
            			<td>${receipt.price}원</td>
            			<td>${receipt.amount}</td>
            			<td>${receipt.totalPrice}원</td>
            			<td>${receipt.state}</td>
           			</tr>
           		</c:forEach>
            </tbody>
        </table>

        <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">4</a></li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


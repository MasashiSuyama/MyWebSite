<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -購入確認-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="buyConfirm-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="buyConfirm-top">購入確認</h2>
		<table class="table table-secondary buyConfirm-table" align="center">
			<thead>
				<tr>
					<th scope="col">商品名</th>
					<th scope="col">単価</th>
					<th scope="col">個数</th>
					<th scope="col">小計</th>
				</tr>
			</thead>
			<c:forEach var="cart" items="${cart}" varStatus="status">
				<tbody>
					<tr>
						<td>${cart.name}</td>
						<td>${cart.price}円</td>
						<td>${cart.buyCount}個</td>
						<td>${cart.price * cart.buyCount}円</td>
					</tr>
				</tbody>
			</c:forEach>
			<tbody>
				<tr>
					<td>普通配送</td>
					<td></td>
					<td></td>
					<td>${deliveryPrice}</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td></td>
					<td></td>
					<td>合計</td>
					<td>${totalMoney}円</td>
				</tr>
			</tbody>
		</table>
		<h3 class="buyConfirm-delivery"><b>${arrivalDate}</b> 発送到着予定です。</h3>
		<div class="buyConfirm-button-position">
			<form action="BuyResult" method="post">
				<input class="btn btn-info btn-lg buyConfirm-button" type="submit" value="購入">
			</form>
		</div>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
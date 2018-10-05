<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -購入詳細-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="buyDetail-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="buyDetail-top">購入詳細</h2>
		<div class="row">
			<div class="col-sm-8 offset-sm-2">
				<table class="table table-secondary text-center">
					<thead>
						<tr>
							<th scope="col">購入日時</th>
							<th scope="col">配達予定日</th>
							<th scope="col">合計金額</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${bdb.buyDate}</td>
							<td>${bdb.arrivalDate}</td>
							<td>${bdb.totalPrice}円</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buyDetail-item-area row">
			<div class="col-sm-10 offset-sm-1">
				<table class="table table-secondary text-center">
					<thead>
						<tr>
							<th scope="col">商品名</th>
							<th scope="col">単価</th>
							<th scope="col">個数</th>
							<th scope="col">小計</th>
						</tr>
					</thead>
					<c:forEach var="buy_item" items="${idbList}">
						<tbody>
							<tr>
								<td>${buy_item.name}</td>
								<td>${buy_item.price}円</td>
								<td>${buy_item.buyCount}個</td>
								<td>${buy_item.price * buy_item.buyCount}円</td>
							</tr>
						</tbody>
					</c:forEach>

					<tbody>
						<tr>
							<td colspan="3" class="text-center">日付指定配達</td>
							<td>${deliveryPrice}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
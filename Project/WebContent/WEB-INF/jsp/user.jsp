<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -ユーザ詳細-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="user-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="user-top">ユーザ詳細</h2>
		<div class="row">
			<div class="col-sm-11 text-right">
				<a href="UserUpdate?id=${userData.id}" class="userUpdate-link"><b>情報更新</b></a>
			</div>
			<div class="col-sm-1">
				<a href="UserDelete?id=${userData.id}" class="userDelete-link"><b>削除</b></a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>ログインID</b></p>
			</div>
			<div class="col-sm-5"><p>${userData.loginId}</p></div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>ユーザ名</b></p>
			</div>
			<div class="col-sm-5"><p>${userData.name}</p></div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>生年月日</b></p>
			</div>
			<div class="col-sm-5"><p>${userData.birthDateStr}</p></div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>登録日時</b></p>
			</div>
			<div class="col-sm-5"><p>${userData.createDateStr}</p></div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>更新日時</b></p>
			</div>
			<div class="col-sm-5"><p>${userData.updateDateStr}</p></div>
		</div>
		<hr class="user-line">
		<div class="col-sm-3 text-center">
			<h4 class="user-buyHistory">購入履歴</h4>
		</div>
		<div class="row">
			<div class="col-sm-10 offset-sm-1">
				<table class="table text-center">
					<thead>
						<tr>
							<th scope="col">購入日時</th>
							<th scope="col">配達予定日</th>
							<th scope="col">合計金額</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<c:forEach var="buy_detail" items="${bdbList}">
						<tbody>
							<tr>
								<td>${buy_detail.buyDateStr}</td>
								<td>${buy_detail.arrivalDateStr}</td>
								<td>${buy_detail.totalPrice}円</td>
								<td>
									<a href="BuyDetail?buy_id=${buy_detail.id}">
										<input class="btn btn-info" type="submit" value="詳細">
									</a>
								</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
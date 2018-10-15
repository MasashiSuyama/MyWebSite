<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -買い物かご-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="buyConfirm-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="buyConfirm-top">買い物かご</h2>
		<c:if test="${itemIntoCartMessage != null}" >
			<div class="alert alert-success" role="alert">${itemIntoCartMessage}</div>
		</c:if>
		<c:if test="${cartActionMessage != null}" >
			<div class="alert alert-danger" role="alert">${cartActionMessage}</div>
		</c:if>
		<c:if test="${buyErrMessage != null}" >
			<div class="alert alert-danger" role="alert"><b>${buyErrMessage}</b></div>
		</c:if>
		<form action="BuyComfirm">
			<table class="table table-secondary buyConfirm-table" align="center">
				<thead>
					<tr>
						<th scope="col">商品名</th>
						<th scope="col">個数</th>
						<th scope="col">単価</th>
						<th scope="col" width="10%">削除</th>
					</tr>
				</thead>
				<c:forEach var="cart" items="${cart}" varStatus="status">
					<tbody>
						<tr>
							<td>${cart.name}</td>
							<td>
								<select name="buy_count" class="custom-select item-select-number">
									<option value="1" <c:if test="${cart.buyCount == 1}"> selected</c:if> >１</option>
									<option value="2" <c:if test="${cart.buyCount == 2}"> selected</c:if> >２</option>
									<option value="3" <c:if test="${cart.buyCount == 3}"> selected</c:if> >３</option>
									<option value="4" <c:if test="${cart.buyCount == 4}"> selected</c:if> >４</option>
									<option value="5" <c:if test="${cart.buyCount == 5}"> selected</c:if> >５</option>
									<option value="6" <c:if test="${cart.buyCount == 6}"> selected</c:if> >６</option>
									<option value="7" <c:if test="${cart.buyCount == 7}"> selected</c:if> >７</option>
									<option value="8" <c:if test="${cart.buyCount == 8}"> selected</c:if> >８</option>
									<option value="9" <c:if test="${cart.buyCount == 9}"> selected</c:if> >９</option>
									<option value="10"<c:if test="${cart.buyCount == 10}"> selected</c:if>>１０</option>
								</select>
							</td>
							<td>${cart.price}円</td>
							<td width="10%">
								<input type="checkbox" name="delete_item" class="form-check-input" value="${status.index}">
							</td>
						</tr>
					</tbody>
				</c:forEach>
				<c:if test="${!(empty cart)}">
					<tbody>
						<tr>
							<td>日時指定配送</td>
							<td>
								<input type="date" name="arrival_date" value="${arrivalDateStart}" min="${arrivalDateStart}"
									max="${arrivalDateEnd}" required></input>
							</td>
							<td>200円 (5000円以上で無料)</td>
						</tr>
					</tbody>
				</c:if>
			</table>
			<c:if test="${!(empty cart)}">
				<div class="buyConfirm-button-position">
					<input class="btn btn-info btn-lg buyConfirm-button" type="submit" value="購入確認">
				</div>
			</c:if>
		</form>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
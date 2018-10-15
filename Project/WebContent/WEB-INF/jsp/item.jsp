<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -商品詳細-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="item-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="item-top">商品詳細</h2>
		<c:if test="${stockMessage != null}" >
			<div class="alert alert-danger" role="alert">${stockMessage}</div>
		</c:if>
		<div class="row">
			<div class="col-sm-6">
				<div class="itemDetail-photo">
					<img src="image/${itemData.fileName}" alt="" width="380" height="304">
				</div>
				<div class="allergy-table-area shadow-lg p-3 mb-5 bg-warning">
					<b>アレルギー表示</b> (●の付いているものを使用しています)
					<table class="table table-light allergy-table text-center">
						<thead>
							<tr>
								<th scope="col">卵</th>
								<th scope="col">小麦</th>
								<th scope="col">乳</th>
								<th scope="col">その他</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<c:if test="${itemData.allergyEgg}">●</c:if>
									<c:if test="${!(itemData.allergyEgg)}">－</c:if>
								</td>
								<td><c:if test="${itemData.allergyWheat}">●</c:if>
									<c:if test="${!(itemData.allergyWheat)}">－</c:if>
								</td>
								<td>
									<c:if test="${itemData.allergyMilk}">●</c:if>
									<c:if test="${!(itemData.allergyMilk)}">－</c:if>
								</td>
								<td>
									<c:if test="${itemData.allergyOther == &quot;&quot;}">なし</c:if>
									<c:if test="${!(itemData.allergyOther == &quot;&quot;)}">${itemData.allergyOther}</c:if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<form action="ItemAdd?id=${itemData.id}" method="post">
					<div class="row">
						<div class="form-group col-sm-7">
							<div class="row">
								<div class="col-sm-6 text-center">購入個数</div>
								<div class="col-sm-4">
									<select name="buy_count" class="custom-select item-select-number">
										<option value="1">１</option>
										<option value="2">２</option>
										<option value="3">３</option>
										<option value="4">４</option>
										<option value="5">５</option>
										<option value="6">６</option>
										<option value="7">７</option>
										<option value="8">８</option>
										<option value="9">９</option>
										<option value="10">１０</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-sm-4 text-center">
							<c:if test="${itemData.stock > 0}">
								<input class="btn btn-success btn-lg" type="submit" value="購入する">
							</c:if>
							<c:if test="${itemData.stock <= 0}">
								<input class="btn btn-danger btn-lg" type="submit" value="在庫がありません" disabled="disabled">
							</c:if>
						</div>
					</div>
				</form>
			</div>
			<div class="col-sm-6">
				<h2 class="itemDetail-name text-center"><b>${itemData.name}</b></h2>
				<h5>${itemData.detail}</h5>
				<h2 class="itemDetail-price"><b>(単価)　${itemData.price}円</b></h2>
			</div>
		</div>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
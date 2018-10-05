<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -ホーム-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<h1 class="index-top">～　CAKE&nbspSHOP&nbspへようこそ　～</h1>>
	<c:if test="${logoutMsg != null}" >
			<div class="alert alert-success" role="alert">${logoutMsg}</div>
		</c:if>
	<div class="popularityItem-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="popularityItem-title">人気ランキング</h2>
		<div class="popularityItem row">
			<c:forEach var="item" items="${popularItemList}"  varStatus="status">
				<div class="col-sm-4">
					<c:if test="${status.index == 0}">
						<h3 class="first-popularityItem">１位</h3>
					</c:if>
					<c:if test="${status.index == 1}">
						<h3 class="second-popularityItem">２位</h3>
					</c:if>
					<c:if test="${status.index == 2}">
						<h3 class="third-popularityItem">３位</h3>
					</c:if>
					<a href="ItemData?id=${item.id}" class="item-topage">
						<div class="popularityItem-photo">
							<img src="image/${item.fileName}" alt="" width="162" height="129.6">
							<h5 class="popularityItem-price text-center"><b>${item.price}円</b></h5>
						</div>
						<div>
							<h4 class="text-center">${item.name}</h4>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="search-area shadow-lg p-3 mb-5 bg-light">
		<h3 class="text-center search-title">検索</h3>
		<div class="search-form">
			<form action="ItemSearchResult" method="post">
				<div class="form-group row">
					<label for="item" class="col-sm-2 text-center">商品名</label>
					<input type="text" name="search_itemName" class="form-control col-sm-9" id="item" placeholder="部分一致">
				</div>
				<div class="form-group row">
					<label for="cost" class="col-sm-2 text-center">価格</label>
					<input type="text" name="low_itemPrice" class="form-control col-sm-3" id="cost">
					<p class="col-sm-1 text-right">円</p>
					<p class="col-sm-1">～</p>
					<input type="text" name="high_itemPrice" class="form-control col-sm-3" id="cost">
					<p class="col-sm-1 text-right">円</p>
				</div>
				<div class="search-position">
					<input type="submit" class="btn btn-primary search-button" value="検索">
				</div>
			</form>
		</div>
	</div>
	<div class="recommendedItem-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="recommendedItem-title">おすすめ</h2>
		<div class="recommendedItem row">
			<c:forEach var="item" items="${randItemList}">
				<div class="col-sm-4">
					<div>
					<a href="ItemData?id=${item.id}" class="item-topage">
						<div class="recommendedItem-photo">
							<img src="image/${item.fileName}" alt="" width="150" height="120">
							<h6 class="recommendedItem-price text-center"><b>${item.price}円</b></h6>
						</div>
						<div>
							<h5 class="text-center">${item.name}</h5>
						</div>
					</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>

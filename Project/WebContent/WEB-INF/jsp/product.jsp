<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -商品マスタ詳細-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="product-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="product-top">商品マスタ詳細</h2>
		<div class="row">
			<div class="col-sm-11 text-right">
				<a href="ProductUpdate?id=${productData.id}" class="protectUpdate-link"><b>更新</b></a>
			</div>
			<div class="col-sm-1">
				<a href="ProductDelete?id=${productData.id}"" class="protectDelete-link"><b>削除</b></a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>商品名</b></p>
			</div>
			<div class="col-sm-5"><p>${productData.name}</p></div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>詳細</b></p>
			</div>
			<div class="col-sm-5"><p>${productData.detail}</p></div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>アレルギー情報</b></p>
			</div>
			<div class="col-sm-5">
				<p>
					卵：<c:if test="${productData.allergyEgg}">●</c:if><c:if test="${!(productData.allergyEgg)}">─</c:if>　
					小麦：<c:if test="${productData.allergyWheat}">●</c:if><c:if test="${!(productData.allergyWheat)}">─</c:if>　
					乳：<c:if test="${productData.allergyMilk}">●</c:if><c:if test="${!(productData.allergyMilk)}">─</c:if>
				</p>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>その他アレルギー情報</b></p>
			</div>
			<div class="col-sm-5"><p>${productData.allergyOther}</p></div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>価格</b></p>
			</div>
			<div class="col-sm-5"><p>${productData.price}円</p></div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>残り在庫数</b></p>
			</div>
			<div class="col-sm-5"><p>${productData.stock}個</p></div>
		</div>
		<div class="row">
			<div class="col-sm-7 text-center">
				<p><b>売上個数</b></p>
			</div>
			<div class="col-sm-5"><p>${productData.buySum}個</p></div>
		</div>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
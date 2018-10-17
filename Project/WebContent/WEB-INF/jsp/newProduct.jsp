<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -商品マスタ新規登録-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="newProduct-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="newProduce-top">商品マスタ新規登録</h2>
		<c:if test="${newProductErrMsg != null}">
			<div class="alert alert-danger" role="alert">${newProductErrMsg}</div>
		</c:if>
		<form action="NewProductResult" method="post">
			<div class="newUser-input-area">
				<div class="form-group row">
					<label for="product" class="col-sm-4 text-center">商品名</label>
					<input type="text" name="name" class="form-control col-sm-7" id="product" value="${name}">
				</div>
				<div class="form-group row">
					<label for="productDetail" class="col-sm-4 text-center">詳細</label>
					<textarea name="detail" class="form-control col-sm-7" id="productDetail" rows="4">${detail}</textarea>
				</div>
				<div class="form-group row">
					<label for="photo" class="col-sm-4 text-center">画像</label>
					<input type="file" name="photo" class="form-control col-sm-7" accept="image/*" id="photo" value="${photo}">
				</div>
				<div class="form-group row">
					<div class="col-sm-4 text-center">
						<label for="productDetail">アレルギー項目</label>
					</div>
					<div class="col-sm-2 text-center">
						<input class="form-check-input" type="checkbox" name="eggAllergy" id="eggAllergy" value="egg"
							<c:if test="${eggAllergy}"> checked </c:if> >
						<label class="form-check-label" for="eggAllergy">卵</label>
					</div>
					<div class="col-sm-2 text-center">
						<input class="form-check-input" type="checkbox" name="wheatAllergy" id="wheatAllergy" value="wheat"
							<c:if test="${wheatAllergy}"> checked </c:if> >
						<label class="form-check-label" for="wheatAllergy">小麦</label>
					</div>
					<div class="col-sm-2 text-center">
						<input class="form-check-input" type="checkbox" name="milkAllergy" id="milkAllergy" value="milk"
							<c:if test="${milkAllergy}"> checked </c:if> >
						<label class="form-check-label" for="milkAllergy">乳</label>
					</div>
				</div>
				<div class="form-group row">
					<label for="Allergy" class="col-sm-4 text-center">その他アレルギー記入欄</label>
					<input type="text" name="allergyOther" class="form-control col-sm-7" id="Allergy" value="${allergyOther}">
				</div>
				<div class="form-group row">
					<label for="price" class="col-sm-4 text-center">単価</label>
					<input type="number" name="price" class="form-control col-sm-3" id="price" min="0" value="${price}">
					<p class="col-sm-1 text-center">円</p>
				</div>
				<div class="form-group row">
					<label for="productNumbers" class="col-sm-4 text-center">販売個数</label>
					<input type="number" name="stock" class="form-control col-sm-3" id="productNumbers" min="0" value="${stock}">
					<p class="col-sm-1 text-center">個</p>
				</div>
			</div>
			<div class="newProduct-position">
				<input type="submit" class="btn btn-secondary btn-lg newProduct-getButton" value="新規登録">
			</div>
		</form>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
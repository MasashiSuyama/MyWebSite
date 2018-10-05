<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -商品マスタ情報更新-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="productUpdete-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="productUpdete-top">商品マスタ情報更新</h2>
		<c:if test="${productUpdateErrMsg != null}" >
			<div class="alert alert-danger" role="alert">${productUpdateErrMsg}</div>
		</c:if>
		<form action="ProductUpdateResult?id=${productInfo.id}" method="post">
			<div class="productUpdete-input-area">
				<div class="form-group row">
					<label for="product" class="col-sm-4 text-center"><b>商品名</b></label>
					<input type="text" name="name"  class="form-control col-sm-7" id="product" value="${productInfo.name}">
				</div>
				<div class="form-group row">
					<label for="productDetail" class="col-sm-4 text-center"><b>詳細</b></label>
					<textarea name="detail" class="form-control col-sm-7" id="productDetail" rows="4">${productInfo.detail}</textarea>
				</div>
				<div class="form-group row">
					<label for="photo" class="col-sm-4 text-center"><b>画像</b></label>
					<input type="file" name="photo" class="form-control col-sm-7" accept="image/*" id="photo">
				</div>
				<div class="form-group row">
					<div class="col-sm-4 text-center">
						<label for="productDetail"><b>アレルギー項目</b></label>
					</div>
					<div class="col-sm-2 text-center">
							<input class="form-check-input" type="checkbox" name="eggAllergy" id="eggAllergy" value="egg"
								<c:if test="${productInfo.allergyEgg}">checked </c:if> >

						<label class="form-check-label" for="eggAllergy">卵</label>
					</div>
					<div class="col-sm-2 text-center">
						<input class="form-check-input" type="checkbox" name="wheatAllergy" id="wheatAllergy" value="wheat"
							<c:if test="${productInfo.allergyWheat}">checked </c:if> >
						<label class="form-check-label" for="wheatAllergy">小麦</label>
					</div>
					<div class="col-sm-2 text-center">
						<input class="form-check-input" type="checkbox" name="milkAllergy" id="milkAllergy" value="milk"
							<c:if test="${productInfo.allergyMilk}">checked </c:if> >
						<label class="form-check-label" for="milkAllergy">乳</label>
					</div>
				</div>
				<div class="form-group row">
					<label for="Allergy" class="col-sm-4 text-center"><b>その他アレルギー記入欄</b></label>
					<input type="text" name="allergyOther" class="form-control col-sm-7" id="Allergy"
						<c:if test="${productInfo.allergyOther == &quot;&quot;}"> value="なし" </c:if>
						<c:if test="${!(productInfo.allergyOther == &quot;&quot;)}">value="${productInfo.allergyOther}" </c:if>>
				</div>
				<div class="form-group row">
					<label for="price" class="col-sm-4 text-center"><b>単価</b></label>
					<input type="text" name="price" class="form-control col-sm-3" id="price" value="${productInfo.price}">
					<p class="col-sm-1 text-center">円</p>
				</div>
				<div class="form-group row">
					<label for="stock" class="col-sm-4 text-center"><b>現在の在庫数</b></label>
					<input type="text" name="stock" class="form-control col-sm-3" id="stock" value="${productInfo.stock}" readonly>
					<p class="col-sm-1 text-center">個</p>
				</div>
				<div class="form-group row">
					<label for="productNumbers" class="col-sm-4 text-center"><b>販売追加個数</b></label>
					<input type="text" name="sellAddNum" class="form-control col-sm-3" id="productNumbers" value="０">
					<p class="col-sm-1 text-center">個</p>
				</div>
			</div>
			<div class="productUpdate-position">
				<input type="submit" class="btn btn-primary btn-lg productUpdate-getButton" value="更新">
			</div>
		</form>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
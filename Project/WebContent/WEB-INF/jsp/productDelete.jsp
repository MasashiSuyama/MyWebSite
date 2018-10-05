<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -商品マスタ削除-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="userDelete-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="userDelete-top">商品マスタ削除確認</h2>
		<div class="text-center">
			<p><h5><b>商品名 :　${productData.name}</b></h5></p>
			<p>を本当に削除してもよろしいでしょうか。</p>
		</div>
		<div class="userDelete-button-area row">
			<div class="col-sm-6 text-right">
				<a href="javascript:history.back();">
					<input class="userDelete-choice-button btn btn-primary" type="submit" value="キャンセル">
				</a>
			</div>
			<div class="userDelete-choice-button col-sm-6">
				<form action="ProductDeleteResult?id=${productData.id}" method="post">
					<input class="userDelete-choice-button btn btn-primary" type="submit" value="OK">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
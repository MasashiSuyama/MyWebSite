<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -管理者ページ-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="adminTop-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="adminTop-top">管理者ページ</h2>
		<div class="adminTop-button-area">
			<a href="UserList">
				<input type="submit" class="btn btn-primary adminTop-button" value="ユーザ一覧ページ　へ">
			</a>
		</div>
		<div class="adminTop-button-area">
			<a href="ProductList">
				<input type="submit" class="btn btn-primary adminTop-button" value="商品マスタ一覧ページ　へ">
			</a>
		</div>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
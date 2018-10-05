<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -エラーページ-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="error-area shadow-lg p-3 mb-5 bg-light">
		<div class="alert alert-danger text-center error-message-area" role="alert">
			<h2 class="error-message-top"><b>システムエラーが発生しました</b></h2>
			<h4 class="error-message-content">${errorMessage}</h4>
		</div>

		<div class="error-button-area">
			<a href="Index">
				<input type="submit" class="btn btn-primary error-botton" value="トップページ　へ">
			</a>
		</div>
	</div>
</body>
</html>
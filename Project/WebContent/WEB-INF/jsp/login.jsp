<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -ログイン-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="login-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="login-top">ログイン</h2>
		<c:if test="${errMsg != null}" >
			<div class="alert alert-danger" role="alert">${errMsg}</div>
		</c:if>
		<div class="text-right">
			<a href="NewUser">新規登録</a>
		</div>
		<form action="LoginResult" method="post">
			<div class="login-input-area">
				<div class="form-group row">
					<label for="loginId" class="col-sm-4 text-center">ログインID</label>
					<input type="text" name="loginId" class="form-control col-sm-7" id="loginId">
				</div>
				<div class="form-group row">
					<label for="password" class="col-sm-4 text-center">パスワード</label>
					<input type="password" name="password" class="form-control col-sm-7" id="password">
				</div>
			</div>
			<div class="login-position">
				<input type="submit" class="btn btn-secondary btn-lg login-button" value="ログイン">
			</div>
		</form>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
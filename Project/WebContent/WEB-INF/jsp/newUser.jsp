<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -ユーザ新規登録-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="newUser-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="newUser-top">ユーザ新規登録</h2>
		<c:if test="${newUserErrMsg != null}" >
		    <div class="alert alert-danger">${newUserErrMsg}</div>
		</c:if>
		<form action="NewUserResult" method="post">
			<div class="newUser-input-area">
				<div class="form-group row">
					<label for="loginId" class="col-sm-4 text-center">ログインID</label>
					<input type="text" name="loginId" class="form-control col-sm-7" id="loginId" pattern="^[0-9A-Za-z]+$" value="${loginId}">
				</div>
				<div class="form-group row">
					<label for="userName" class="col-sm-4 text-center">ユーザー名</label>
					<input type="text" name="name" class="form-control col-sm-7" id="userName" value="${userName}">
				</div>
				<div class="form-group row">
					<label for="birthdate" class="col-sm-4 text-center">生年月日</label>
					<input type="date" name="birthdate" id="birthdate" class="form-control col-sm-7" min="1900-01-01" max="${today}" value="${birthday}">
				</div>
				<div class="form-group row">
					<label for="password" class="col-sm-4 text-center">パスワード</label>
					<input type="text" name="password" class="form-control col-sm-7" id="password" pattern="^[0-9A-Za-z]+$">
				</div>
				<div class="form-group row">
					<label for="passwordCheck" class="col-sm-4 text-center">パスワード(確認)</label>
					<input type="password" name="passwordCheck" class="form-control col-sm-7" id="passwordCheck" pattern="^[0-9A-Za-z]+$">
				</div>
			</div>
			<div class="newUser-position">
				<input type="submit" class="btn btn-secondary btn-lg newUser-button" value="新規登録">
			</div>
		</form>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
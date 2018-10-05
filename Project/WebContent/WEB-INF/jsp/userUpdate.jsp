<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -ユーザ情報更新-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="userUpdate-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="userUpdate-top">ユーザ情報更新</h2>
		<c:if test="${UserUpdateErrMsg != null}" >
			<div class="alert alert-danger" role="alert">${UserUpdateErrMsg}</div>
		</c:if>
		<form action="UserUpdateResult?id=${userUpdate.id}" method="post">
			<div class="userUpdate-input-area">
				<div class="row">
					<div class="col-sm-7 text-center">
						<p><b>ログインID</b></p>
					</div>
					<div class="col-sm-5"><p>${userUpdate.loginId}</p></div>
				</div>
				<div class="form-group row">
					<label for="userName" class="col-sm-7 text-center"><b>ユーザ名</b></label>
					<input type="text" name="userName" class="form-control col-sm-4" id="userName" value="${userUpdate.name}">
				</div>
				<div class="form-group row">
					<label for="birthdate" class="col-sm-7 text-center"><b>生年月日</b></label>
					<input type="date" name="birthdate" id="birthdate" class="form-control col-sm-4" value="${userUpdate.birthDate}">
				</div>
				<div class="form-group row">
					<label for="password" class="col-sm-7 text-center"><b>パスワード</b></label>
					<input type="text" name="password" class="form-control col-sm-4" id="password">
				</div>
				<div class="form-group row">
					<label for="passwordCheck" class="col-sm-7 text-center"><b>パスワード(確認)</b></label>
					<input type="password" name="passwordCheck" class="form-control col-sm-4" id="passwordCheck">
				</div>
			</div>
			<div class="userUpdate-position">
				<input type="submit" class="btn btn-primary btn-lg userUpdate-button" value="更新">
			</div>
		</form>
		<div class="back-button">
			<a href="javascript:history.back();">戻る</a>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="alert alert-secondary" role="alert">
	<div class="row">
		<div class="col-sm-3 text-center">
			<a href="Index" class="logo"><b>CAKE&nbsp;SHOP</b></a>
		</div>
		<div class="col-sm-9 text-right">
			<c:if test="${isLogin == true}">
			<a href="UserData?id=${userInfo.id}">${userInfo.name}さん</a>
			</c:if>
			<c:if test="${!(isLogin == true)}">
			<a href="NewUser">新規登録</a>
			</c:if>
			<a href="Cart" class="cart-button"><b>商品購入</b></a>
			<c:if test="${!(isLogin == true)}">
				<a href="Login">ログイン</a>
			</c:if>
			<c:if test="${isLogin == true}">
				<a href="Logout">ログアウト</a>
			</c:if>
		</div>
	</div>
</div>
<div class="admin-page">
	<a href="Admin">管理者用</a>
</div>
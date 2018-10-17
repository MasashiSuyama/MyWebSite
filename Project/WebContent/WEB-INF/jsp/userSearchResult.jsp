<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -ユーザ一覧検索-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="userList-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="userList-top">ユーザ一覧検索</h2>
		<form action="UserSearchResult">
			<div class="user-search-area">
				<div class="form-group row">
					<label for="loginId" class="col-sm-3 text-center">ログインID</label>
					<input type="text" name="search_loginId" class="form-control col-sm-7" id="loginId" placeholder="完全一致" value="${searchLoginId}">
				</div>
				<div class="form-group row">
					<label for="userName" class="col-sm-3 text-center">ユーザ名</label>
					<input type="text" name="search_userName" class="form-control col-sm-7" id="userName" placeholder="部分一致" value="${searchUserName}">
				</div>
				<div class="form-group row">
					<label for="birthdate" class="col-sm-3 text-center">生年月日</label>
					<input type="date" name="start_Birthdate" id="birthdate" class="form-control col-sm-3" value="${startBirthdate}">
					<p class="col-sm-1 text-center">～</p>
					<input type="date" name="end_Birthdate" id="birthdate" class="form-control col-sm-3" value="${endBirthdate}">
				</div>
				<div class="user-search-position col-sm-11">
					<input type="submit" class="btn btn-primary user-search-button" value="検索">
				</div>
			</div>
		</form>
		<hr class="userList-line">
		<div class="text-center"> （${userCount}件中${userPageTop}～${userPageBottom}件目）
		<div class="row">
			<div class="col-sm-10 offset-sm-1">
				<table class="table text-center">
					<thead>
						<tr>
							<th scope="col" width="20%">ログインID</th>
							<th scope="col" width="30%">ユーザ名</th>
							<th scope="col" width="25%">生年月日</th>
							<th scope="col" width="25%"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${userList}">
						<tr>
							<th scope="row">${user.loginId}</th>
							<td>${user.name}</td>
							<td>${user.birthDateStr}</td>
							<td>
								<div class="row">
									<a href="UserUpdate?id=${user.id}">
										<input class="btn btn-success" type="submit" value="編集">
									</a>
									<a href="UserData?id=${user.id}">
										<input class="btn btn-primary" type="submit" value="参照">
									</a>
									<a href="UserDelete?id=${user.id}">
										<input class="btn btn-danger" type="submit" value="削除">
									</a>
								</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:if test="${pageNum == 1}">
					<li class="page-item disabled">
						<a class="page-link" tabindex="-1"><<</a>
					</li>
				</c:if>
				<c:if test="${!(pageNum == 1)}">
					<li class="page-item">
						<a class="page-link" href="UserSearchResult?search_loginId=${searchLoginId}&
							search_userName=${searchUserName}&start_Birthdate=${startBirthdate}&end_Birthdate=${endBirthdate}&page_num=1"><<</a>
					</li>
				</c:if>

				<c:if test="${pageNum == pageMax && pageMax >= 3}">
					<li class="page-item"><a class="page-link"  href="UserSearchResult?search_loginId=${searchLoginId}&search_userName=${searchUserName}
						&start_Birthdate=${startBirthdate}&end_Birthdate=${endBirthdate}&page_num=${pageNum - 2}">${pageNum - 2}</a></li>
				</c:if>
				<c:if test="${!(pageNum == 1)}">
					<li class="page-item"><a class="page-link" href="UserSearchResult?search_loginId=${searchLoginId}&search_userName=${searchUserName}
						&start_Birthdate=${startBirthdate}&end_Birthdate=${endBirthdate}&page_num=${pageNum - 1}">${pageNum - 1}</a></li>
				</c:if>

				<li class="page-item active">
					<a class="page-link">${pageNum}<span class="sr-only">(current)</span></a>
				</li>

				<c:if test="${!(pageNum == pageMax) && !(userCount == 0)}">
					<li class="page-item"><a class="page-link"  href="UserSearchResult?search_loginId=${searchLoginId}&search_userName=${searchUserName}
						&start_Birthdate=${startBirthdate}&end_Birthdate=${endBirthdate}&page_num=${pageNum + 1}">${pageNum + 1}</a></li>
				</c:if>
				<c:if test="${pageNum == 1 && pageMax >= 3}">
					<li class="page-item"><a class="page-link" href="UserSearchResult?search_loginId=${searchLoginId}&search_userName=${searchUserName}
						&start_Birthdate=${startBirthdate}&end_Birthdate=${endBirthdate}&page_num=${pageNum + 2}">${pageNum + 2}</a></li>
				</c:if>

				<c:if test="${pageNum == pageMax || userCount == 0}">
					<li class="page-item disabled">
						<a class="page-link" tabindex="-1">>></a>
					</li>
				</c:if>
				<c:if test="${!(pageNum == pageMax) && !(userCount == 0)}">
					<li class="page-item">
						<a class="page-link" href="UserSearchResult?search_loginId=${searchLoginId}&
							search_userName=${searchUserName}&start_Birthdate=${startBirthdate}&end_Birthdate=${endBirthdate}&page_num=${pageMax}">>></a>
					</li>
				</c:if>
			</ul>
		</nav>
	</div>
</body>
</html>
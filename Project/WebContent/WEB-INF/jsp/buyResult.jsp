<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -購入完了-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="buyReselt-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="buyReselt-top">購入完了しました</h2>
		<h3 class="buyConfirm-delivery"><b>${arrivalDate}</b> 発送到着予定です。</h3>
		<div class="buyReselt-botton-area row">
			<div class="col-sm-6 text-right">
				<a href="Index">
					<input class="btn btn-primary btn-lg" type="submit" value="ホーム画面へ">
				</a>
			</div>
			<div class="col-sm-6">
				<a href="UserData?id=${userInfo.id}">
					<input class="btn btn-primary btn-lg" type="submit" value="ユーザ画面へ">
				</a>
			</div>
		</div>
	</div>
</body>
</html>
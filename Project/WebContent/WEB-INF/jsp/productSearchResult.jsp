<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -商品マスタ検索結果-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="productList-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="productList-top">商品マスタ検索結果</h2>
		<div class="newProduct-button">
			<a href="NewProduct">
				<input class="btn btn-info btn-lg" type="submit" value="新規商品登録">
			</a>
		</div>
		<form action="ProductSearchResult" method="post">
			<div class="product-search-area">
				<div class="form-group row">
					<label for="name" class="col-sm-3 text-center">商品名</label>
					<input type="text" name="search_productName" class="form-control col-sm-7" id="name" placeholder="部分一致"  value="${searchProductName}">
				</div>
				<div class="form-group row">
					<label for="cost" class="col-sm-3 text-center">価格</label>
					<input type="text" name="low_productCost" class="form-control col-sm-2" id="cost" value="${lowProductCost}">
					<p class="col-sm-1 text-right">円</p>
					<p class="col-sm-1">～</p>
					<input type="text" name="high_productCost" class="form-control col-sm-2" id="cost" value="${highProductCost}">
					<p class="col-sm-1 text-right">円</p>
				</div>
				<div class="user-search-position col-sm-11">
					<input type="submit" class="btn btn-primary user-search-button" value="検索">
				</div>
			</div>
		</form>
		<hr class="userList-line">
		<div class="text-center"> （${productCount}件中${productPageTop}～${productPageBottom}件目）</div>
		<div class="row">
			<div class="col-sm-10 offset-sm-1">
				<table class="table text-center">
					<thead>
						<tr>
							<th scope="col" width="45%">商品名</th>
							<th scope="col" width="15%">価格</th>
							<th scope="col" width="15%">在庫数</th>
							<th scope="col" width="25%"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${productList}">
							<tr>
								<th scope="row">${product.name}</th>
								<td>${product.price}円</td>
								<td>${product.stock}個</td>
								<td>
									<div class="row">
										<a href="ProductData?id=${product.id}">
											<input class="btn btn-primary" type="submit" value="参照">
										</a>
										<a href="ProductUpdate?id=${product.id}">
											<input class="btn btn-success" type="submit" value="更新">
										</a>
										<a href="ProductDelete?id=${product.id}">
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
						<a class="page-link" href="ProductList"><<</a>
					</li>
				</c:if>

				<c:if test="${pageNum == pageMax && pageMax >= 3}">
					<li class="page-item"><a class="page-link" href="ProductList?page_num=${pageNum - 2}">${pageNum - 2}</a></li>
				</c:if>
				<c:if test="${!(pageNum == 1)}">
					<li class="page-item"><a class="page-link" href="ProductList?page_num=${pageNum - 1}">${pageNum - 1}</a></li>
				</c:if>

				<li class="page-item active">
					<a class="page-link">${pageNum}<span class="sr-only">(current)</span></a>
				</li>

				<c:if test="${!(pageNum == pageMax) && !(productCount == 0)}">
					<li class="page-item"><a class="page-link" href="ProductList?page_num=${pageNum + 1}">${pageNum + 1}</a></li>
				</c:if>
				<c:if test="${pageNum == 1 &&  pageMax >= 3}">
					<li class="page-item"><a class="page-link" href="ProductList?page_num=${pageNum + 2}">${pageNum + 2}</a></li>
				</c:if>

				<c:if test="${pageNum == pageMax || productCount == 0}">
					<li class="page-item disabled">
						<a class="page-link" tabindex="-1">>></a>
					</li>
				</c:if>
				<c:if test="${!(pageNum == pageMax) && !(productCount == 0)}">
					<li class="page-item">
						<a class="page-link" href="ProductList?page_num=${pageMax}">>></a>
					</li>
				</c:if>
			</ul>
		</nav>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>CAKE SHOP -商品検索結果-</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/baselayout/header.jsp"/>
	<div class="itemSeaechResult-area shadow-lg p-3 mb-5 bg-light">
		<h2 class="itemSeaechResult-top">商品検索結果</h2>
		<h4 class="text-center">(${itemCount}件中${itemPageTop}～${itemPageBottom}件目)</h4>
		<div class="top-pagination">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test="${pageNum == 1}">
						<li class="page-item disabled">
							<a class="page-link" tabindex="-1"><<</a>
						</li>
					</c:if>
					<c:if test="${!(pageNum == 1)}">
						<li class="page-item">
							<a class="page-link" href="ItemSearchResult"><<</a>
						</li>
					</c:if>

					<c:if test="${pageNum == pageMax && pageMax >= 3}">
						<li class="page-item"><a class="page-link" href="ItemSearchResult?page_num=${pageNum - 2}">${pageNum - 2}</a></li>
					</c:if>
					<c:if test="${!(pageNum == 1)}">
						<li class="page-item"><a class="page-link" href="ItemSearchResult?page_num=${pageNum - 1}">${pageNum - 1}</a></li>
					</c:if>

					<li class="page-item active">
						<a class="page-link">${pageNum}<span class="sr-only">(current)</span></a>
					</li>

					<c:if test="${!(pageNum == pageMax) && !(itemCount == 0)}">
						<li class="page-item"><a class="page-link" href="ItemSearchResult?page_num=${pageNum + 1}">${pageNum + 1}</a></li>
					</c:if>
					<c:if test="${pageNum == 1 &&  pageMax >= 3}">
						<li class="page-item"><a class="page-link" href="ItemSearchResult?page_num=${pageNum + 2}">${pageNum + 2}</a></li>
					</c:if>

					<c:if test="${pageNum == pageMax || itemCount == 0}">
						<li class="page-item disabled">
							<a class="page-link" tabindex="-1">>></a>
						</li>
					</c:if>
					<c:if test="${!(pageNum == pageMax) && !(itemCount == 0)}">
						<li class="page-item">
							<a class="page-link" href="ItemSearchResult?page_num=${pageMax}">>></a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
		<c:forEach var="item" items="${itemList}" varStatus="status">
			<c:if test="${status.index % 3 == 0}">
				<div class="row">
			</c:if>
				<div class="col-sm-4">
					<a href="ItemData?id=${item.id}" class="item-topage">
						<div class="itemSeaechResult-photo">
							<img src="image/${item.fileName}" alt="" width="300" height="240">
							<h4 class="text-center search-item-name">${item.name}</h4>
							<h5 class="popularityItem-price text-center"><b>${item.price}円</b></h5>
						</div>
					</a>
				</div>
			<c:if test="${status.index % 3 == 2 || status.last}">
				</div>
			</c:if>
		</c:forEach>
		<div class="bottom-pagination">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test="${pageNum == 1}">
						<li class="page-item disabled">
							<a class="page-link" tabindex="-1"><<</a>
						</li>
					</c:if>
					<c:if test="${!(pageNum == 1)}">
						<li class="page-item">
							<a class="page-link" href="ItemSearchResult"><<</a>
						</li>
					</c:if>

					<c:if test="${pageNum == pageMax && pageMax >= 3}">
						<li class="page-item"><a class="page-link" href="ItemSearchResult?page_num=${pageNum - 2}">${pageNum - 2}</a></li>
					</c:if>
					<c:if test="${!(pageNum == 1)}">
						<li class="page-item"><a class="page-link" href="ItemSearchResult?page_num=${pageNum - 1}">${pageNum - 1}</a></li>
					</c:if>

					<li class="page-item active">
						<a class="page-link">${pageNum}<span class="sr-only">(current)</span></a>
					</li>

					<c:if test="${!(pageNum == pageMax) && !(itemCount == 0)}">
						<li class="page-item"><a class="page-link" href="ItemSearchResult?page_num=${pageNum + 1}">${pageNum + 1}</a></li>
					</c:if>
					<c:if test="${pageNum == 1 &&  pageMax >= 3}">
						<li class="page-item"><a class="page-link" href="ItemSearchResult?page_num=${pageNum + 2}">${pageNum + 2}</a></li>
					</c:if>

					<c:if test="${pageNum == pageMax || itemCount == 0}">
						<li class="page-item disabled">
							<a class="page-link" tabindex="-1">>></a>
						</li>
					</c:if>
					<c:if test="${!(pageNum == pageMax) && !(itemCount == 0)}">
						<li class="page-item">
							<a class="page-link" href="ItemSearchResult?page_num=${pageMax}">>></a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>
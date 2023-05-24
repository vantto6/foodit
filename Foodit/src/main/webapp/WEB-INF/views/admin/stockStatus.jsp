<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 현황</title>
<jsp:include page="/WEB-INF/views/layout/staticHeader.jsp" />
<style type="text/css">
h1 {
	text-align: center;
}

.inventory {
	width: 100%;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 8px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
}

.container {
	display: flex;
	justify-content: center;
	align-items: center;
}

.go-to-first-page {
	position: fixed;
	bottom: 20px;
	right: 20px;
	background-color: #88b04B;
	border: none;
	color: white;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 4px;
}
</style>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>
	<h1>재고 현황</h1>
	<main>
		<div class="inventory">
			<table>
				<thead>
					<tr>
						<th rowspan="2" style="text-align: center;">이미지</th>
						<th>상품번호</th>
						<th>상품이름</th>
						<th>남은수량</th>
						<th></th>
						<th>카테고리이름</th>
						<th>브랜드이름</th>

					</tr>
				</thead>
				<c:forEach var="dto" items="${list}">
					<tbody>
						<tr>
							<td class="container"><img style="width: 100px;"
								src="${pageContext.request.contextPath}/uploads/admin/${dto.saveFilename}">
							</td>
							<td>${dto.itemNo }</td>
							<td>${dto.itemName }</td>
							<td>${dto.cnt}</td>
							<c:choose>
								<c:when test="${dto.cnt <= 10}">
									<td><img
										src="${pageContext.request.contextPath}/resource/images/exclamation.png"
										style="width: 50px; height: 30px;">
									</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<td>${dto.categoryName }</td>
							<td>${dto.brandName }</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
		<footer>
			<div class="page-navigation" style="width: 900px; margin: 0 auto;">${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
				<button class="go-to-first-page"
					onclick="location.href='${pageContext.request.contextPath}/admin/admin.do';">처음
					페이지</button>
			</div>
		</footer>
	</main>
</body>
</html>
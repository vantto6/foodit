<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>spring</title>
<jsp:include page="/WEB-INF/views/layout/staticHeader.jsp" />
<style type="text/css">
.body-main {
	min-height: 800px;
}

table {
	margin: 20px auto;
	border-collapse: collapse;
}

th {
	border-top: 1px solid black;
	border-bottom: 1px solid black;
	font-size: 1.2em;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: none;
	padding-right: none;
}

td {
	border-bottom: 1px solid #dcdcdc;
	color: #646464;
	padding-top: 18px;
	padding-bottom: 18px;
	padding-left: none;
	padding-right: none;
	font-size: 1.2em;
}

.subject {
	text-align: left;
	padding: 5px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.sub {
	color: #828282;
	font-size: 0.6em;
	padding: 20px;
}

.btn {
	color: #333333;
	border: 1px solid #999999;
	background-color: #ffffff;
	padding: 5px 10px;
	border-radius: 4px;
	font-weight: 500;
	cursor: pointer;
	font-size: 14px;
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
	vertical-align: baseline;
	width: 80px;
	height: 45px;
	border: none;
}

.btn_right {
	display: flex;
	justify-content: end;
	text-align: center;
	margin-right: 60px;
}

.btn_left {
	display: flex;
	justify-content: start;
	text-align: center;
	margin-right: 60px;
}

.btn:active, .btn:focus, .btn:hover {
	background-color: #f8f9fa;
	color: #333333;
}

.tit_page h2.tit {
	font-weight: 700;
	font-size: 28px;
	color: #333;
	line-height: 35px;
	text-align: center;
	letter-spacing: -1px;
}

.tit_page {
	overflow: hidden;
	width: 1050px;
	margin: 0 auto;
	padding: 50px 0 51px;
}

.sub_page {
	overflow: hidden;
	width: 1050px;
	margin: 0 auto;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: none;
	padding-right: none;
}

.table-container {
	text-align: center;
}

input[type=checkbox] {
	opacity: 1;
	position: relative;
	z-index: 10;
}

.write-container {
	min-height: 800px;
}
</style>
<script type="text/javascript">
function showAlert() {
	out();
}

</script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div class="write-container">
			<div class="tit_page">
				<h2 class="tit">1:1질문</h2>
			</div>
			<br>
			<form name="listForm" method="post">

				<div class="sub_page">
					<div class="btn_right" align="center">
						<input type="hidden" name="page" value="${page}">

							<button type="button" class="btn"
								onclick="location.href='${pageContext.request.contextPath}/inquiry/write.do';">글올리기</button>
						<button type="button" class="btn"
							onclick="location.href='${pageContext.request.contextPath}/inquiry/list.do';"
							title="새로고침">
							<i class="fa-solid fa-arrow-rotate-right"></i>
						</button>
					</div>
				</div>
				<table class="table-container">
					<thead>
						<tr>
							<th width="100">번호</th>
							<c:if test="${sessionScope.member.memberId=='admin'}">
								<th >작성자</th>
							</c:if>
							<th width="500">제목</th>
							<th width="150">작성일</th>
							<th width="150">상태</th>
						</tr>
					</thead>

					<c:forEach var="dto" items="${list}" varStatus="status">

							<tr>
								<td>${dataCount - (page-1) * size - status.index}</td>
								<c:if test="${sessionScope.member.memberId=='admin'}">
									<td> ${dto.memberId}</td>
								</c:if>
								<td class="left"><a
									href="${articleUrl}&num=${dto.inquiryNo}" onclick="showAlert();">${dto.subject}</a></td>
								<td>${dto.inquiryDate}</td>
								<td>${dto.answer != null ? "답변완료" :"답변대기" }</td>
							</tr>
					</c:forEach>
				</table>
			</form>
		</div>
			<div class="page-navigation" style="width: 900px; margin: 0 auto;">${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
			</div>
	</main>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" />
</body>
</html>
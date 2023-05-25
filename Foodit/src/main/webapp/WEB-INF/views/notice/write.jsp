﻿<%@ page contentType="text/html; charset=UTF-8"%>
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
	max-width: 700px;
	padding-top: 15px;
}

.table-form td {
	padding: 7px 0;
}

.table-form p {
	line-height: 200%;
}

.table-form tr:first-child {
	border-top: 2px solid #212529;
}

.table-form tr>td:first-child {
	width: 110px;
	text-align: center;
	background: #f8f8f8;
}

.table-form tr>td:nth-child(2) {
	padding-left: 10px;
}

.table-form input[type=text], .table-form input[type=file], .table-form textarea
	{
	width: 96%;
}

.table-form input[type=checkbox] {
	vertical-align: middle;
}

input[type=checkbox] {
	opacity: 1;
	position: relative;
	z-index: 10;
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
	border: none;
	
}

.btn:active, .btn:focus, .btn:hover {
	background-color: #f8f9fa;
	color: #333333;
}
td .center_container{
	display: flex; justify-content: space-around;
}
</style>

<script type="text/javascript">
	function sendOk() {
		const f = document.inquiryForm;
		let str;

		str = f.subject.value.trim();
		if (!str) {
			alert("제목을 입력하세요. ");
			f.subject.focus();
			return;
		}

		str = f.content.value.trim();
		if (!str) {
			alert("내용을 입력하세요. ");
			f.content.focus();
			return;
		}

		f.action = "${pageContext.request.contextPath}/notice/${mode}_ok.do";
		f.submit();
	}

	 
</script>
</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div class="container body-container">

			<div class="tit_page">
				<h2 class="tit">공지사항</h2>
			</div>
			<div class="body-main mx-auto">
				<form name="inquiryForm" method="post">
					<table class="table table-border table-form">
						<tr>
							<td>제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
							<td><input type="text" name="subject" maxlength="100"
								class="form-control" value="${dto.subject}"></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td>
								<p>${sessionScope.member.name}</p>
							</td>
						</tr>

						<tr>
							<td valign="top">내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
							<td><textarea name="content" class="form-control">${dto.content}</textarea>
							</td>
						</tr>
					</table>

					<table class="table">
						<tr>
							<td class="center_contianer" style="display: flex; justify-content: space-around;" >
								<button type="button" class="btn" onclick="sendOk();">${mode=='update'?'수정완료':'등록하기'}</button>
								<button type="reset" class="btn">다시입력</button>
								<button type="button" class="btn"
									onclick="location.href='${pageContext.request.contextPath}/notice/list.do?size=${size}';">${mode=='update'?'수정취소':'등록취소'}</button>
								<input type="hidden" name="size" value="${size}"> <c:if
									test="${mode=='update'}">
									<input type="hidden" name="num" value="${dto.noticeNo}">
									<input type="hidden" name="page" value="${page}">
								</c:if>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</main>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" />
</body>
</html>
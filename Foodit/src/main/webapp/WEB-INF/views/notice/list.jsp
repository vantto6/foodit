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
	width: 1000px;
	margin: 0 auto;
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

.list-container {
	min-height: 800px;
}
</style>
<script type="text/javascript">
	<c:if test="${sessionScope.member.memberId=='admin'}">
	$(function() {
		$("#chkAll").click(function() {
			if ($(this).is(":checked")) {
				$("input[name=nums]").prop("checked", true);
			} else {
				$("input[name=nums]").prop("checked", false);
			}
		});

		$("#btnDeleteList")
				.click(
						function() {
							let cnt = $("input[name=nums]:checked").length;
							if (cnt === 0) {
								alert("삭제할 게시물을 먼저 선택하세요.");
								return false;
							}

							if (confirm("선택한 게시물을 삭제 하시겠습니까 ?")) {
								const f = document.listForm;
								f.action = "${pageContext.request.contextPath}/notice/deleteList.do";
								f.submit();
							}
						});
	});
	</c:if>
</script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div class="list-container ">
			<div class="tit_page">
				<h2 class="tit">공지사항</h2>
			</div>
			<br>
			<form name="listForm" method="post">

				<div class="sub_page">
					<div class="btn_right" align="center">
						<c:if test="${sessionScope.member.memberId =='admin'}">
							<button type="button" class="btn" id="btnDeleteList">삭제</button>
						</c:if>
						<input type="hidden" name="page" value="${page}">
						<c:if test="${sessionScope.member.memberId=='admin'}">

							<button type="button" class="btn"
								onclick="location.href='${pageContext.request.contextPath}/notice/write.do';">글올리기</button>
						</c:if>
						<c:if test="${sessionScope.member.memberId !='admin'}">
							<span style="float: left">${dataCount}개(${page}/${total_page}페이지)</span>
						</c:if>
					</div>
				</div>
				<table class="table-container">

					<tr>
						<c:if test="${sessionScope.member.memberId=='admin'}">
							<th class="chk"><input type="checkbox" name="chkAll"
								id="chkAll"></th>
						</c:if>
						<th width="100">번호</th>
						<th width="500">제목</th>
						<th width="100">작성자</th>
						<th width="200">작성일</th>
					</tr>
					<c:forEach var="dto" items="${list}" varStatus="status">
						<tr>
							<c:if test="${sessionScope.member.memberId=='admin'}">
								<td><input type="checkbox" name="nums"
									value="${dto.noticeNo}"></td>
							</c:if>
							<td>${dataCount - (page-1) * size - status.index}</td>
							<td class="left"><a href="${articleUrl}&num=${dto.noticeNo}">${dto.subject}</a>
							</td>
							<td>관리자</td>
							<td>${dto.createDate}</td>
						</tr>
					</c:forEach>

				</table>
			</form>
			<div class="page-navigation" style="width: 900px; margin: 0 auto;">${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
			</div>
		</div>
	</main>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" />
</body>
</html>
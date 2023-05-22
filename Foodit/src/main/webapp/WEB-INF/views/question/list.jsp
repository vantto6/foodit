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
	max-width: 1000px;
}

#question_list {
	min-height: 800px;
	width: 900px;
	margin: 0 auto;
	text-align: center;
	width: 900px;
}

#question_title {
	font-size: 1.8em;
	font-weight: bold;
	text-align: left;
}

#num {
	font-size: 1.1em;
	color: #646464;
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

#question_list a:hover {
	text-decoration: none;
	color: red;
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

		$("#btnDeleteList").click(function() {
			let cnt = $("input[name=nums]:checked").length;
			if (cnt === 0) {
				alert("삭제할 게시물을 먼저 선택하세요.");
					return false;
			}
			if (confirm("선택한 게시물을 삭제 하시겠습니까 ?")) {
				const f = document.listForm;
				f.action = "${pageContext.request.contextPath}/question/deleteList.do";
				f.submit();
			}
		});
		
		$("#btnUpdate").click(
				function() {
					let cnt = $("input[name=nums]:checked").length;
				    if (cnt !== 1) {
				        alert("수정할 게시물 한 개만 선택해주세요.");
				        return false;
				    }

					
					if(confirm("선택한 게시물을 수정하시겠습니까?")){
						const f = document.listForm;
						f.action = "${pageContext.request.contextPath}/question/update.do";
						f.submit();
					}
				}	
				
			);
			

	});
	
	</c:if>

	$(function() {
		$(".answer").hide(); // 초기에 모든 답변 숨기기

		$(".q_body").click(function() {
			let $answer = $(this).next();
			let isVisible = $answer.is(':visible');

			// 다른 질문의 답변을 닫음
			$(".answer").not($answer).hide();

			// 현재 질문의 답변을 토글
			$answer.fadeToggle(100);
		});
	});
</script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div class="list-container ">
			<div class="tit_page">
				<h2 class="tit">자주묻는 질문</h2>
			</div>
			<br>
			<form name="listForm" method="post">

				<div class="sub_page">
					<div class="btn_right" align="center">

						<input type="hidden" name="page" value="${page}">
						<c:if test="${sessionScope.member.memberId=='admin'}">
							<input type="hidden" name="size" value="${size}">
							<button type="button" class="btn"
								onclick="location.href='${pageContext.request.contextPath}/question/write.do?size=${size}';">글올리기</button>
							<button type="button" class="btn" id=btnUpdate>수정</button>
							<button type="button" class="btn" id="btnDeleteList">삭제</button>

						</c:if>
						<button type="button" class="btn"
							onclick="location.href='${pageContext.request.contextPath}/question/list.do';"
							title="새로고침">
							<i class="fa-solid fa-arrow-rotate-right"></i>
						</button>
					</div>
				</div>
				<table class="table-container">
					<tr>
						<c:if test="${sessionScope.member.memberId=='admin'}">
							<th class="chk"><input type="checkbox" name="chkAll"
								id="chkAll"></th>
						</c:if>
						<th width="100">번호</th>
						<th width="100">분류</th>
						<th width="700">제목</th>
					</tr>
					<c:forEach var="dto" items="${list}" varStatus="status">
						<tr class="q_body">
							<c:if test="${sessionScope.member.memberId=='admin'}">
								<td><input type="checkbox" name="nums"
									value="${dto.questionNo}"></td>
							</c:if>
							<td>${dataCount - (page-1) * size - status.index}</td>
							<td>${dto.category == "order" ? "주문/결제/배송" : dto.category == "refund" ? " 환불/취소/교환" : "기타"}</td>
							<td class="question left">${dto.subject}</td>
						</tr>
						<tr class="answer">
							<c:if test="${sessionScope.member.memberId=='admin'}">
								<td></td>
							</c:if>
							<td></td>
							<td></td>
							<td>${dto.content }</td>
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
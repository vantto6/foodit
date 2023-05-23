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
	max-width: 700px;
	padding-top: 15px;
	
}

.table-article tr>td {
	padding-left: 5px;
	padding-right: 5px;
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

.reply {
	clear: both;
	padding: 20px 0 10px;
}

.reply .bold {
	font-weight: 600;
}

.reply .form-header {
	padding-bottom: 7px;
}

.reply-form  tr>td {
	padding: 2px 0 2px;
}

.reply-form textarea {
	width: 100%;
	height: 75px;
}

.reply-form button {
	padding: 8px 25px;
}

.reply .reply-list tr>td {
	padding: 7px 5px;
}

.reply .reply-list .bold {
	font-weight: 600;
}


.reply-list .list-header {
	border: 1px solid #ccc;
	background: #f8f8f8;
}

.reply-list tr>td {
	padding-left: 7px;
	padding-right: 7px;
}
</style>
<script type="text/javascript">
	<c:if test="${sessionScope.member.memberId=='admin'}">
	function deleteInquiry() {
		if (confirm("게시글을 삭제 하시 겠습니까 ? ")) {
			let query = "inquiryNo=${dto.inquiryNo}&${query}";
			let url = "${pageContext.request.contextPath}/inquiry/delete.do?"
					+ query;
			location.href = url;
		}
	}
	</c:if>
</script>


<script type="text/javascript">
	function login() {
		location.href = "${pageContext.request.contextPath}/member/login.do";
	}

	function ajaxFun(url, method, query, dataType, fn) {
		$.ajax({
			type : method, // 메소드(get, post, put, delete)
			url : url, // 요청 받을 서버주소
			data : query, // 서버에 전송할 파라미터
			dataType : dataType, // 서버에서 응답할 형식(json, xml, text)
			success : function(data) { // 성공할 경우 fn 함수 실행
				fn(data);
			},
			beforSend : function(jqXHR) {
				jqXHR.setRequestHeader("AJAX", true); // 사용자 정의 헤더	

			},
			error : function(jqXHR) {
				if (jqXHR.status === 403) {
					login();
					return false;
				} else if (jqXHR.status === 400) {
					alert("요청 처리가 실패했습니다.");
					return false;
				}
				console.log(jqXHR.responseText);
			}
		});
	}

	//댓글 리스트 및 페이징
	$(function() {
		listPage(1);
	});

	function listPage(page) {
		let url = "${pageContext.request.contextPath}/inquiry/listReply.do";
		let qs = "inquiryNo=${dto.inquiryNo}&pageNo=" + page;
		let selector = "#listReply";

		const fn = function(data) {
			$(selector).html(data);
		}

		ajaxFun(url, "get", qs, "text", fn);
		// ajaxFun(url, "get", qs, "html", fn); // 가능
	}

	//댓글등록
	$(function() {
		$(".btnSendReply")
				.click(                   
						function() {
							let inquiryNo = "${dto.inquiryNo}";
							const $tb = $(this).closest("table");
							let answer = $tb.find("textarea").val().trim();

							if (!answer) {
								$tb.find("textarea").focus();
								return false;
							}
							answer = encodeURIComponent(answer);

							let url = "${pageContext.request.contextPath}/inquiry/insertReply.do";
							let qs = "inquiryNo=" + inquiryNo + "&answer="
									+ answer;

							const fn = function(data) {
								$tb.find("textarea").val("");

								let state = data.state;
								if (state === "false") {
									alert("댓글을 추가하지 못했습니다.");
								}
							}
							ajaxFun(url, "post", qs, "json", fn);
						});
	});
</script>
</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div class="container body-container">
			<div class="tit_page">
				<h2 class="tit">1:1질문</h2>
			</div>

			<div class="body-main mx-auto">
				<table class="table table-border table-article">
					<thead>
						<tr>
							<td colspan="2" align="center">${dto.subject}</td>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td width="50%">이름 : ${dto.memberId}</td>
							<td align="right">${dto.inquiryDate}</td>
						</tr>

						<tr>
							<td colspan="2" valign="top" height="200">${dto.content}</td>
						</tr>
					</tbody>
				</table>


				<table class="table">
					<tr>
						<td width="50%"
							style="display: flex; justify-content: space-around; margin: 0 auto;">
							<button type="button" class="btn"
								onclick="location.href='${pageContext.request.contextPath}/inquiry/list.do?${query}';">리스트</button>
						</td>
					</tr>
				</table>
			</div>

			<div class="body-main mx-auto">
				<hr>
				<!-- admin일 경우에만 답변창 표시 -->
				<c:if test="${sessionScope.member.memberId == 'admin'}">

					<div class="reply">
						<form name="replyForm" method="post">
							<div class='form-header'>
								<span class="bold">답변</span>
							</div>

							<table class="table table-borderless reply-form">
								<tr>
									<td><textarea class='form-control' name="answer"></textarea>
									</td>
								</tr>
								<tr>
									<td align='right'>
										<button type='button' class='btn btn-light btnSendReply'>답변
											등록</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</c:if>

				<div id="listReply"></div>
			</div>
		</div>
	</main>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" />
</body>
</html>
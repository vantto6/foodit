<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>spring</title>
<jsp:include page="/WEB-INF/views/layout/staticHeader.jsp"/>
<style type="text/css">
.body-main {
	max-width: 800px;
	padding-top: 15px;
}

.table-article tr > td { padding-left: 5px; padding-right: 5px; }

.reply { clear: both; padding: 20px 0 10px; }
.reply .bold { font-weight: 600; }

.reply .form-header { padding-bottom: 7px; }
.reply-form  tr>td { padding: 2px 0 2px; }
.reply-form textarea { width: 100%; height: 75px; }
.reply-form button { padding: 8px 25px; }

.reply .reply-info { padding-top: 25px; padding-bottom: 7px; }
.reply .reply-info  .reply-count { color: #3EA9CD; font-weight: 700; }

.reply .reply-list tr>td { padding: 7px 5px; }
.reply .reply-list .bold { font-weight: 600; }

.reply .deleteReply, .reply .deleteReplyAnswer { cursor: pointer; }
.reply .notifyReply { cursor: pointer; }

.reply-list .list-header { border: 1px solid #ccc; background: #f8f8f8; }
.reply-list tr>td { padding-left: 7px; padding-right: 7px; }

.reply-answer { display: none; }
.reply-answer .answer-left { float: left; width: 5%; }
.reply-answer .answer-right { float: left; width: 95%; }
.reply-answer .answer-list { border-top: 1px solid #ccc; padding: 0 10px 7px; }
.reply-answer .answer-form { clear: both; padding: 3px 10px 5px; }
.reply-answer .answer-form textarea { width: 100%; height: 75px; }
.reply-answer .answer-footer { clear: both; padding: 0 13px 10px 10px; text-align: right; }

</style>
<script type="text/javascript">
function ajaxFun(url, method, query, dataType, fn) {
	$.ajax({
		type:method,		// 메소드(get, post, put, delete)
		url:url,			// 요청 받을 서버주소
		data:query,			// 서버에 전송할 파라미터
		dataType:dataType,	// 서버에서 응답하는 형식(json, xml, text)
		success:function(data) {
			fn(data);
		},
		beforeSend:function(jqXHR) { 
			jqXHR.setRequestHeader("AJAX", true); // 사용자 정의 헤더
		},
		error:function(jqXHR) {
			if(jqXHR.status === 403) {
				login();
				return false;
			} else if(jqXHR.status === 400) {
				alert("요청 처리가 실패 했습니다.");
				return false;
			}
			console.log(jqXHR.responseText);
		}
	});
}

$(function() {
	$(".btnSendReply").click(function() {
		let num = "${dto.itemNo}";
		const $tb = $(this).closest("table");
		let content =  $tb.find("textarea").val().trim();
		
		if(!content){
			$tb.find("textarea").focus();
			return;
		}
		
		content = encodeURIComponent(content);
		
		let url = "${pageContext.request.contextPath}/itemInquiry/insertReply.do";
		let qs = "itemNo="+itemNo+"&content="+content;
		
		const fn = function(data) {
			$tb.find("textarea").val("");
			let state = data.state;
			if(state === "true"){
				alert("성공");
			} else{
				alert("댓글을 추가하지 못했습니다.");
			}
		};
		
		ajaxFun(url,"post",qs,"json",fn);
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
	    
	    <div class="body-main mx-auto">
			<table class="table table-border table-article">
				<thead>
					<tr>
						<td colspan="2" align="center">
							제목: [${dto.subject}]
						</td>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td width="50%">
							이름 : ${dto.memberId}
						</td>
						<td align="right">
							${dto.createDate} | 조회 10
							
						</td>
					</tr>
					
					<tr>
						<td colspan="2" valign="top" height="200">
							${dto.content}
						</td>
					</tr>
					
				</tbody>
			</table>
			
			<table class="table">
				<tr>
					<td align="right">
						<button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/itemInquiry/itemInquiry.do?${query}&itemNo=${dto.itemNo}';">리스트</button>
					</td>
				</tr>
			</table>
			
			<c:if test="${sessionScope.member.memberId== 'admin'}">				
			<div class="reply">
				<form name="replyForm" method="post">
					<div class='form-header'>
						<span class="bold">문의 답변</span><span> - 고객을 위해 정성스럽게 작성해주세요.</span>
					</div>
					
					<table class="table reply-form">
						<tr>
							<td>
								<textarea class='form-control' name="content"></textarea>
							</td>
						</tr>
						<tr>
						   <td align='right'>
								<button type='button' class='btn btnSendReply'>답변 등록</button>
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

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>
</body>
</html>
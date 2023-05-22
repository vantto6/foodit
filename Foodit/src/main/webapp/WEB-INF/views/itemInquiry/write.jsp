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
	height: 800px;
	padding-top: 15px;
}

.table-form td { padding: 7px 0; }
.table-form p { line-height: 200%; }
.table-form tr:first-child { border-top: 2px solid #212529; }
.table-form tr > td:first-child { width: 110px; text-align: center; background: white; }
.table-form tr > td:nth-child(2) { padding-left: 10px; }

.table-form input[type=text], .table-form input[type=file], .table-form textarea {
	width: 96%; }
.table-form input[type=checkbox] { vertical-align: middle; }

.inquriy-title {
color: #424951;
    padding-top: 35px;
    padding-bottom: 7px;
    margin: 100px 25px 0;
    text-align: center;
}

.inquiry-btn {
	border: none;
	background: white;
	margin: 15px;
    font-size: 15px;
}
</style>

<script type="text/javascript">
function sendOk() {
    const f = document.noticeForm;
	let str;
	
    str = f.subject.value.trim();
    if(!str) {
        alert("제목을 입력하세요. ");
        f.subject.focus();
        return;
    }

    str = f.content.value.trim();
    if(!str) {
        alert("내용을 입력하세요. ");
        f.content.focus();
        return;
    }

    f.action = "${pageContext.request.contextPath}/itemInquiry/write_ok.do";
    f.submit();
    alert("${itemNo}");
}
</script>
</head>
<body>

<header>
	<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
</header>
	
<main>
	<div class="container body-container">
	    <div class="inquriy-title">
			<h2>[${vo.brandName }] ${vo.itemName }에 대한 문의</h2>
	    </div>
	    
	    <div class="body-main mx-auto">
			<form name="noticeForm" method="post">
				<table class="table table-border table-form">
					<tr>
						<td>제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
						<td> 
							<input type="text" name="subject" maxlength="100" class="form-control" value="">
						</td>
					</tr>
	
					<tr> 
						<td>작성자</td>
						<td> 
							<p>${sessionScope.member.memberId}</p>
						</td>
					</tr>
					
					<tr> 
						<td valign="top">내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
						<td> 
							<textarea name="content" class="form-control"></textarea>
						</td>
					</tr>
						
				</table>
					
				<table class="table">
					<tr> 
						<td align="center">
							<button type="button" class="inquiry-btn" onclick="sendOk();">${mode=='update'?'수정완료':'등록하기'}</button>
							<button type="reset" class="inquiry-btn">다시입력</button>
							<button type="button" class="inquiry-btn" onclick="location.href='${pageContext.request.contextPath}/itemInquiry/itemInquiry.do';">${mode=='update'?'수정취소':'등록취소'}</button>
							<input type="hidden" name="itemNo" value="${itemNo}">
							<input type="hidden" name="page" value="${page}">
							<c:if test="${mode=='update'}">
								<input type="hidden" name="itemNo" value="${itemNo}">
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

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>
</body>
</html>
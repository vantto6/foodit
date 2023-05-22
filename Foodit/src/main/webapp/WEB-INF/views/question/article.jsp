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
	max-width: 700px;
	padding-top: 15px;
}

.table-article tr > td { padding-left: 5px; padding-right: 5px; }

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


</style>
<script type="text/javascript">
<c:if test="${sessionScope.member.memberId=='admin'}">
function deleteNotice() {
    if(confirm("게시글을 삭제 하시 겠습니까 ? ")) {
        let query = "num=${dto.noticeNo}&${query}";
        let url = "${pageContext.request.contextPath}/notice/delete.do?" + query;
    	location.href = url;
    }
}
</c:if>
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
			<table class="table table-border table-article">
				<thead>
					<tr>
						<td colspan="2" align="center">
							${dto.subject}
						</td>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td width="50%">
							이름 : ${sessionScope.member.name}
						</td>
						<td align="right">
							${dto.createDate}  
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
					<td width="50%" style="display: flex; justify-content: space-around; margin: 0 auto;">
						<c:choose>
							<c:when test="${sessionScope.member.memberId=='admin'}">
								<button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/notice/update.do?num=${dto.noticeNo}&page=${page}&size=${size}';">수정</button>
							</c:when>
						</c:choose>
				    	
						<c:choose>
				    		<c:when test="${sessionScope.member.memberId=='admin'}">
				    			<button type="button" class="btn" onclick="deleteNotice();">삭제</button>
				    		</c:when>
				    	</c:choose>
						<button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/notice/list.do?${query}';">리스트</button>
				    	
					</td>
				</tr>
			</table>
	    </div>
	</div>
</main>

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>
</body>
</html>
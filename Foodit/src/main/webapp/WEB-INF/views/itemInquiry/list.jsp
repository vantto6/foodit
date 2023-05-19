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
	max-width: 1000px;
}


.table-list th, .table-list td { text-align: center; font-size: 12px;margin-bottom: none}

.table-list .notice { display: inline-block; padding:1px 3px; background: #ed4c00; color: #fff; }
.table-list .left { text-align: left; padding-left: 5px; }





.inquriy-title {
color: #424951;
    padding-top: 35px;
    padding-bottom: 7px;
    margin: 100px 25px 0;
    text-align: center;
}

.inquiry-col {
	margin: 10px
}
</style>

<script type="text/javascript">
</script>
</head>
<body>

<header>
	<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
</header>
	
<main>
	<div class="container body-container">
	    <div class="inquriy-title">
			<h2>[${vo.brandName }] ${vo.itemName } 의 상품 문의</h2>
	    </div>
	    
	    <div class="body-main mx-auto">
	        <form name="listForm" method="post">
				<table class="table">
					<tr>
						<td width="50%">
						</td>
						<td align="right">
							<input type="hidden" name="page" value="${page}">
						</td>
					</tr>
				</table>
				
				<table class="table table-border table-list">
			
					<thead class="inquiry-col">
						<tr>
							<th class="num" style="width: 50px">번호</th>
							<th class="subject" style="width: 250px">제목</th>
							<th style="width: 50px">작성자</th>
							<th class="date" style="width: 50px">작성일</th>
							<th class="hit" style="width: 50px">답변유무</th>
						</tr>
					</thead>
					
					
					<tbody>						
					<c:forEach var="dto" items="${list}">
							<tr>

								<td>${dataCount - (page-1) * size - status.index}</td>
								<td class="left">
									<a href="#">${dto.subject}</a>
								</td>
								<td>${dto.memberId}</td>
								<td>${dto.createDate }</td>
								<td>1/0</td>
							</tr>
					</c:forEach>
					</tbody>
					
				</table>
			</form>
			
			<div class="page-navigation">
				${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
			</div>
			
			<table class="table">
				<tr>
					<td width="100">
						<button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/notice/list.do';" title="새로고침"><i class="fa-solid fa-arrow-rotate-right"></i></button>
					</td>
					<td align="center">
					</td>
					<td align="right" width="100">
						<c:if test="${sessionScope.member.memberId=='admin'}">
							<button type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/itemInquiry/write.do?itemNo=${itemNo}&page=${page}';">문의하기</button>
						</c:if>
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
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

#notice_list {
	min-height: 800px;
	width: 900px;
	margin: 0 auto;
	text-align: center;
	width: 900px;
}

#notice_title {
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

#notice_list a:hover {
	text-decoration: none;
	color: red;
}

.sub {
	color: #828282;
	font-size: 0.6em;
	padding: 20px;
}

.w-btn {
	border: none;
	padding: 15px 30px;
	font-weight: 600;
	background-color: #5f0080;
	color: white;
	font-size: 1em;
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
</style>
<script type="text/javascript">
	function searchList() {
		const f = document.searchForm;
		f.submit();
	}
</script>
</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div>
			<div class="tit_page">
				<h2 class="tit">공지사항</h2>
			</div>
			<br>
	    <div class="body-main mx-auto">
	        <form name="listForm" method="post">
				<table class="table">
					<tr>
						<td width="50%">
							<c:if test="${sessionScope.member.userId=='admin'}">
								<button type="button" class="btn" id="btnDeleteList">삭제</button>
							</c:if>
							<c:if test="${sessionScope.member.userId!='admin'}">
								${dataCount}개(${page}/${total_page} 페이지)
							</c:if>
						</td>
						<td align="right">
							<c:if test="${dataCount!=0 }">
								<select name="size" class="form-select" onchange="changeList();">
									<option value="5"  ${size==5 ? "selected='selected' ":""}>5개씩 출력</option>
									<option value="10" ${size==10 ? "selected='selected' ":""}>10개씩 출력</option>
									<option value="20" ${size==20 ? "selected='selected' ":""}>20개씩 출력</option>
									<option value="30" ${size==30 ? "selected='selected' ":""}>30개씩 출력</option>
									<option value="50" ${size==50 ? "selected='selected' ":""}>50개씩 출력</option>
								</select>
							</c:if>
							<input type="hidden" name="page" value="${page}">
						</td>
					</tr>
				</table>
				
				<table class="table table-border table-list">
					<thead>
						<tr>
							<c:if test="${sessionScope.member.userId=='admin'}">
								<th class="chk">
									<input type="checkbox" name="chkAll" id="chkAll">        
								</th>
							</c:if>
							<th class="noticeNo">번호</th>
							<th class="userId">작성자</th>
							<th class="subject">제목</th>
							<th class="createDate">작성일</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="dto" items="${listNotice}">
							<tr>
								<c:if test="${sessionScope.member.userId=='admin'}">
									<td>
										<input type="checkbox" name="nums" value="${dto.num}">
									</td>
								</c:if>
								<td><span class="notice">공지</span></td>
								<td class="left">
									<a href="${articleUrl}&num=${dto.num}">${dto.subject}</a>
								</td>
								<td>${dto.userId}</td>
								<td>${dto.subject}</td>
								<td>${dto.createDate}</td>
							</tr>
						</c:forEach>
						
						<c:forEach var="dto" items="${listNotice}" varStatus="status">
							<tr>
						 
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			
			<div class="page-navigation" style="width: 100%" >
				${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
			</div>
			
		</div>

	</main>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" />
</body>
</html>
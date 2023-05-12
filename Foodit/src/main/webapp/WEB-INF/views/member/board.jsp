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
}

#notice_list  {
	width: 900px;
	margin: 0 auto;
	text-align: center;
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
	border-top : 1px solid black;
	border-bottom : 1px solid black;
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
	<div id="notice_list">
	<br><br><br>
	<div id="notice_title">
	공지사항<span class="sub">  컬리의 새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</span>
	</div>	
	<br>
	
	<table>
		<tr>
			<th width="100">번호</th>
			<th width="500">제목</th>
			<th width="100">작성자</th>
			<th width="200">작성일</th>
		</tr>

		
			
			
			<tr>
				
				<td>7</td>
				

				
				<td class="subject">
						<a href="index.jsp?folder=notice&category=notice_detail&num=7&pageNum=1">
							컬리컬리 오프라인 매장 안내
						</a>
				</td>
				
				<td style="color: black">KurlyKurly</td>
				
				
				<td>
					
						2022-07-03
					
				</td>
			</tr>
			
			<tr>
				
				<td>6</td>
				

				
				<td class="subject">
						<a href="index.jsp?folder=notice&category=notice_detail&num=5&pageNum=1">
							컬리컬리 식품유형변경 안내 공지
						</a>
				</td>
				
				<td style="color: black">KurlyKurly</td>
				
				
				<td>
					
						2022-07-03
					
				</td>
			</tr>
			
			<tr>
				
				<td>5</td>
				

				
				<td class="subject">
						<a href="index.jsp?folder=notice&category=notice_detail&num=4&pageNum=1">
							품절된 제품 재입고 신청하는 방법 
						</a>
				</td>
				
				<td style="color: black">KurlyKurly</td>
				
				
				<td>
					
						2022-07-03
					
				</td>
			</tr>
			
			<tr>
				
				<td>4</td>
				

				
				<td class="subject">
						<a href="index.jsp?folder=notice&category=notice_detail&num=3&pageNum=1">
							온라인 구매 시 온라인에서만 교환반품 가능합니다 
						</a>
				</td>
				
				<td style="color: black">KurlyKurly</td>
				
				
				<td>
					
						2022-07-03
					
				</td>
			</tr>
			
			<tr>
				
				<td>3</td>
				

				
				<td class="subject">
						<a href="index.jsp?folder=notice&category=notice_detail&num=2&pageNum=1">
							선물포장 서비스 안내
						</a>
				</td>
				
				<td style="color: black">KurlyKurly</td>
				
				
				<td>
					
						2022-07-03
					
				</td>
			</tr>
			
			<tr>
				
				<td>2</td>
				

				
				<td class="subject">
						<a href="index.jsp?folder=notice&category=notice_detail&num=1&pageNum=1">
							컬리컬리 배송 안내
						</a>
				</td>
				
				<td style="color: black">KurlyKurly</td>
				
				
				<td>
					
						2022-07-03
					
				</td>
			</tr>
			
			<tr>
				
				<td>1</td>
				

				
				<td class="subject">
						<a href="index.jsp?folder=notice&category=notice_detail&num=0&pageNum=1">
							종이박스 포장재 회수 종료 안내
						</a>
				</td>
				
				<td style="color: black">KurlyKurly</td>
				
				 
				<td>
					
						2022-07-03
					
				</td>
			</tr>
			
		
	</table>
	
	
		
	<div id="num">
	
		[처음][이전]
	
	
	
		
			[1]
		
	
	
	
		[다음][마지막]
	
	</div>
</div>

	
</main>

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>
</body>
</html>
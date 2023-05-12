<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<style type="text/css">
#board_detail {
	width: 800px;
	margin: 0 auto;
}

table {
	border: none;
	border-collapse: collapse;
	color: #282828;
	font-size: 1.1em;
	margin: 50 px;
}

th, td {
	border-bottom: 1px solid #aaaaaa;
	border-top: 1px solid #aaaaaa;
	padding: 15px;

}

th {
	width: 150px;
	background-color: #f7f5f8; 
}

td {
	width: 650px;
}

.subject, .content {
	text-align: left;
}

.content {
	height: 100px;
	vertical-align: middle;
}

#board_menu {
	text-align: right;
	margin: 5px;
}

 .w-btn {
   width: 60px; height: 30px;
    border: none;
    font-weight: 600;
    background-color: #5f0080;
    color: white;
    font-size: 1em;
   }
   

</style>

</head>
<body>

<div id="board_detail">
	<br><br><br>
	<h1>공지사항</h1><br><br>
	<table>
		<tr>
			<th>작성자</th>
			<td style="text-align: center;" >
				운영자
			</td>
		</tr>
		<tr>	
			<th>작성일</th>
			<td style="text-align: center;">
				2022-07-03
			</td>
		</tr>
		<tr>	
			<th>제목</th>
			<td style="padding: 10px;" class="subject">
				컬리컬리 오프라인 매장 안내
			</td>
		</tr>
		<tr>	
			<th>내용</th>
			<td style="padding: 10px;" class="content">
				안녕하세요 마켓컬리 입니다.
<br>
<br> 
<br>컬리컬리 오프라인 매장 안내드립니다.
<br>
<br> 
<br>
<br>[오프라인 입점 매장]
<br>
<br>- 신세계백화점 강남점 지하 1층 식품관
<br>
<br>- 신세계백화점 경기점 지하 2층 식품관
<br>
<br>- 현대백화점 무역센터점 지하 1층 식품관
<br>
<br>- 현대백화점 판교점 지하 1층 식품관
<br>
<br>- 롯데백화점 잠실점 지하 1층 식품관
<br>
<br>- 롯데마트(서초점, 잠실점, 인천터미널점 등 86곳)
<br>
<br> 
<br>
<br>오프라인 매장은 컬리컬리 상품 중 일부 품목이 판매되고 있으며, 일자별 품목은 상이할 수 있습니다.
<br>
<br> 
<br>
<br>감사합니다. 
			</td>
		</tr>
		
	</table>
	
	
	<div id="board_menu">
			
		<button type="button" id="listBtn" class="w-btn">목록</button>	
	</div>
	
	
	<form method="post" id="menuForm">
		
		<input type="hidden" name="num" value="7">

		
		<input type="hidden" name="pageNum" value="1">
	</form>
</div>





</body>
</html>
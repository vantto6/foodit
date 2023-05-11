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
#qna_list {
	width: 900px;
	margin: 0 auto;
	text-align: center;
}

#qna_title {
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

#qna_list a:hover {
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
	<div id="qna_list">
	<br><br><br>
	<div id="qna_title">질문과 답변<span class="sub">컬리에게 질문해 주시면 최대한 빠르게 답변해 드립니다.</span></div>
	
	<table>
		<tr>
			<th width="100">번호</th>
			<th width="500">제목</th>
			<th width="100">작성자</th>
			<th width="200">작성일</th>
		</tr>

		
			
			
			<tr>
				
				<td>12</td>
				

				
				<td class="subject">
										
					
					
					
					
						<a href="index.jsp?folder=qna&category=qna_detail&num=101&pageNum=1&search=&keyword=">
							테스트
						</a>
					
				</td>
					
					<td style="color: black">
					
						qqq123
					
					</td>
		
					
					<td>
						
							2023-01-03
						
					</td>
			</tr>
			
			<tr>
				
				<td>11</td>
				

				
				<td class="subject">
										
					
					
					
					
						<a href="index.jsp?folder=qna&category=qna_detail&num=73&pageNum=1&search=&keyword=">
							만두전골 없나요?
						</a>
					
				</td>
					
					<td style="color: black">
					
						abc123
					
					</td>
		
					
					<td>
						
							2022-07-05
						
					</td>
			</tr>
			
			<tr>
				
				<td>10</td>
				

				
				<td class="subject">
										
					
						
						<span style="margin-left: 20px;">└[답변]</span>
					
					
					
					
						<a href="index.jsp?folder=qna&category=qna_detail&num=78&pageNum=1&search=&keyword=">
							해당 상품은 품절되었습니다
						</a>
					
				</td>
					
					<td style="color: black">
					
					KurlyKurly
					
					</td>
		
					
					<td>
						
							2022-07-05
						
					</td>
			</tr>
			
			<tr>
				
				<td>9</td>
				

				
				<td class="subject">
										
					
					
					
					
						<span class="secret">&#128274;</span>
						
						
							작성자 또는 관리자만 확인 가능합니다.
							
					
				</td>
					
					<td style="color: black">
					
						abc123
					
					</td>
		
					
					<td>
						
							2022-07-05
						
					</td>
			</tr>
			
			<tr>
				
				<td>8</td>
				

				
				<td class="subject">
										
					
						
						<span style="margin-left: 20px;">└[답변]</span>
					
					
					
					
						<a href="index.jsp?folder=qna&category=qna_detail&num=79&pageNum=1&search=&keyword=">
							배송지 변경 처리되었습니다
						</a>
					
				</td>
					
					<td style="color: black">
					
					KurlyKurly
					
					</td>
		
					
					<td>
						
							2022-07-05
						
					</td>
			</tr>
			
			<tr>
				
				<td>7</td>
				

				
				<td class="subject">
										
					
					
					
					
						<a href="index.jsp?folder=qna&category=qna_detail&num=71&pageNum=1&search=&keyword=">
							아 갈비탕 맛없어요 ㅡㅡ 환불해주세요 
						</a>
					
				</td>
					
					<td style="color: black">
					
						abc123
					
					</td>
		
					
					<td>
						
							2022-07-05
						
					</td>
			</tr>
			
			<tr>
				
				<td>6</td>
				

				
				<td class="subject">
										
					
						
						<span style="margin-left: 20px;">└[답변]</span>
					
					
					
					
						<a href="index.jsp?folder=qna&category=qna_detail&num=80&pageNum=1&search=&keyword=">
							고객센터에 전화문의 부탁드립니다
						</a>
					
				</td>
					
					<td style="color: black">
					
					KurlyKurly
					
					</td>
		
					
					<td>
						
							2022-07-05
						
					</td>
			</tr>
			
			<tr>
				
				<td>5</td>
				

				
				<td class="subject">
										
					
					
					
					
						<a href="index.jsp?folder=qna&category=qna_detail&num=8&pageNum=1&search=&keyword=">
							테스트-8
						</a>
					
				</td>
					
					<td style="color: black">
					
						abc123
					
					</td>
		
					
					<td>
						
							2022-07-03
						
					</td>
			</tr>
			
			<tr>
				
				<td>4</td>
				

				
				<td class="subject">
										
					
					
					
					
						<a href="index.jsp?folder=qna&category=qna_detail&num=7&pageNum=1&search=&keyword=">
							테스트-7
						</a>
					
				</td>
					
					<td style="color: black">
					
						abc123
					
					</td>
		
					
					<td>
						
							2022-07-03
						
					</td>
			</tr>
			
			<tr>
				
				<td>3</td>
				

				
				<td class="subject">
										
					
					
					
					
						<a href="index.jsp?folder=qna&category=qna_detail&num=4&pageNum=1&search=&keyword=">
							테스트-4
						</a>
					
				</td>
					
					<td style="color: black">
					
						abc123
					
					</td>
		
					
					<td>
						
							2022-07-03
						
					</td>
			</tr>
			
		
	</table>
	
	
	
		
	<div id="num">
	
		[처음][이전]
	
	
	
		
			[1]
		
	
		
			<a href="index.jsp?folder=qna&category=qna_list&pageNum=2&search=&keyword=">[2]</a>
		
	
	
	
		[다음][마지막]
	
	</div>
	<br>
	
	<form action="index.jsp?folder=qna&category=qna_list" method="post">
		
		<select name="search" style="height: 30px; width: 70px; font-size: 15px; ">
			<option value="q_name" selected="selected">&nbsp;작성자&nbsp;</option>
			<option value="q_title">&nbsp;제목&nbsp;</option>
			<option value="q_content">&nbsp;내용&nbsp;</option>
		</select>
		<input type="text" name="keyword" style="height: 30px; width: 180px;">
		<button type="submit" style="height: 30px; width: 50px; font-size: 15px;" >검색</button>
	</form>
</div>

</main>

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>
</body>
</html>
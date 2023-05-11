<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/layout/staticHeader.jsp"/>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.4.0/css/all.css">

<style type="text/css">
* { padding: 0; margin: 0 auto; }
*, *::after, *::before { box-sizing: border-box; }

.product_view {position:relative; padding: 0 0 0 430px; width: 1050px; box-sizing: border-box;}
.img {position: absolute; left: 0; top: 0}
.img > img { width: 3px solid #e8e8e8}
.product_view h2{
    width: 500px;
    font-weight: 500;
    font-size: 24px;
    color: rgb(51, 51, 51);
    line-height: 34px;
    letter-spacing: -0.5px;
    word-break: keep-all;
    margin: 20px; 
    padding-bottom: 20px;
    border-bottom: 2px solid #333}
.product_view table {margin-left: 10px}  
.product_view table th {    
    width: 128px;
    height: 100%;
    color: rgb(102, 102, 102);
    font-weight: 400;
    line-height: 19px;}
    
.product_view th{padding: 17px 0}
.product_view td {padding: 17px 0}

.product_view .btns{margin-left: 60px}
.product_view .btn2{text-align: center; margin: 50px}
.product_view .btn2 {display: inline-block;height: 50px;font-size: 16px; width: 350px;padding: 13px 0; color: black; text-decoration: none; border: 2px solid #333;}
.product_view a.btn1{color: black;}
.product_view a.btn2{background: white;}

.detail{width: 1050px;}


#Review_list {
	width: 1000px;
	margin: 0 auto;
	text-align: center;
	
}
.review_text {
	font-size: 1em;
	text-align: left;
}


#Review_title {
	font-size: 3em;
	font-weight: bold;
	padding : 15px;
}

table.review {
	width: 1000px;
	margin-top:10px;
	border-top: 1px solid  #5f0081;
	border-bottom: 1px solid white;
	border-left: none;
	border-right: none;
	padding: 10px 10px;
	text-align: center;
	font-size: 1.1em;
}

th.review {
	border-bottom: 1px solid rgb(230,230,230);
	padding-top: 10px;
	padding-bottom: 10px;
	font-size: 1.1em;
}

td.review {
	border-bottom: 1px solid rgb(230,230,230);
	text-align: center;	
	overflow: hidden;
	padding-top: 10px;
	padding-bottom: 10px;
	font-size: 1.1em;
}

.subject {
	border-bottom: 1px solid rgb(230,230,230);
	text-align: left;
	padding: 5px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	font-size: 1.1em;
}

.write_review {
	margin-top: 15px;
	margin-left: 850px;
	width: 100px;
	text-align: center;
	border-style: solid #5f0081;
	background-color: #5f0081;
	color: white;
	font-weight: bold;
	border-radius: 3px;
	
}

.review_write {
	color: white;
	font-weight: bold;
	border-style: none;
	background-color: #5f0081;
	padding: 10px;
	border-radius: 3px;
	font-size: 1.1em;
	
}

button, select {
    text-transform: none;
}
button {
    overflow: visible;
    border-radius: 0;
}
button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    margin: 0;
}
button {
    outline: none;
   	
}
.review_number{
	font-size: 1.1em;
}

#Review_list a:hover {
	text-decoration: none;
	color: red;
}

.secret, .remove {
	background-color: black;
	color: white;
	font-size: 14px;
	border: 1px solid black;
	border-radius: 4px;
}



</style>

</head>
<body>

<header>
    <jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
</header>

<div class="product_view">
	<h2>[상품]상품 이름</h2>
	<table>

		<tbody>
		<tr>
			<th>브랜드명</th>
			<td>비비고</td>
		</tr>
		<tr>
			<th>판매단위</th>
			<td>500g</td>
		</tr>
		<tr>
			<th>원산지</th>
			<td>국산</td>
		</tr>
		<tr>
			<th>구매수량</th>
			<td>
				<input type="number" style="height: 30px">

			</td>
		</tr>
		<tr>
			<th>가격</th>
			<th>9000</th>
		</tr>

		<tr>
			<th>총상품금액</th>
			<th>9000</th>
		</tr>

		</tbody>		
	</table>

	<div class="img">
		<img src="image/item.jpg">
	</div>
	
	<div class="btns">
		<a href="#" class="btn1"><i class="fa-solid fa-cart-shopping fa-xl"></i></a>
		<a href="#" class="btn2">구매하기</a>
	</div>
</div>

<div class="detail">
	<div>
		<h3>상품설명</h3>
	</div>
</div>
<div id="Review_list">
	<div id="Review_title">상품 후기(게시글 : 16)</div>
		<div class="review_text" >
			PRODUCT REVIEW
			<br>
			상품에 대한 문의를 남기는 공간입니다. 해당 게시판의 성격과 다른 글은 사전동의 없이 담당 게시판으로 이동될 수 있습니다.
			<br>
			배송관련, 주문(취소/교환/환불)관련 문의 및 요청사항은 마이컬리 내 1:1 문의에 남겨주세요.
		</div>
	
	
	<table class="review">
		<tr>
			<th class="review" width="100">번호</th>
			<th class="review" width="500">제목</th>
			<th class="review" width="100">작성자</th>
			<th class="review" width="200">작성일</th>
		</tr>

		
			
			
			<tr>
				
				<td class="review">16</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
			<tr>
				
				<td class="review">15</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
			<tr>
				
				<td class="review">14</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
			<tr>
				
				<td class="review">13</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
			<tr>
				
				<td class="review">12</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
		
	</table>
	

	<div class="write_review" >
		<button class="review_write" type="button">후기쓰기</button>
	</div>

	
	
		
	<div class="review_number">
	
		[&lt;&lt;][&lt;]
	
	
	
		
			[1]
		
	
		
			<a href="#">[2]</a>
		
	
		
			<a href="#">[3]</a>
		
	
		
			<a href="#">[4]</a>
		
	
	
	
		[&gt;][&gt;&gt;]
	
	</div>
</div>

			<div id="product_review"></div>
		
	
</body>
	

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>


</body>
</html>

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
.text-center {
	text-align: center;
}

.text-right {
	text-align: right;
}

.margin-top5 {
	margin-top: 5px;
}

.margin-top10 {
	margin-top: 10px;
}

.mypage-selectbox {
	width: 150px;
}

.mypage-selectbox .mypage-ul {
	list-style: none;
}

.mypage-selectbox .mypage-ul li {
	border: 1px solid #ccc;
	font-size: 18px;
	color: #333;
	line-height: 20px;
	background: #fff;
	outline: none;
	vertical-align: top;
	border-bottom: 0;
}

.mypage-selectbox .mypage-ul li:last-child {
	border-bottom: 1px solid #ccc !important;
}

.right-content-orderinfoleft>ul {
	display: inline-block;
}

.mypage-ul>li {
	height: 50px;
	background: white;
	text-align: center;
	font-size: 15px;
	border: 1px solid gray;
}

.mypage-ul>li a {
	margin-top: 14px;
	display: block;
}

.mypage-right {
	margin-left: 100px;
	width: 800px;
	height: 400px;
	float: left;
}

.mypage-right-subject {
	height: 10%;
	border-bottom: 2px solid black;
}

.mypage-right-content {
	height: 90%;
	width: 100%;
	display: flex;
	gap: 15px;
	flex-direction: column
}

.mypage-right:after {
	clear: both
}

.mypage-left-subject {
	width: 150px;
	height: 50px;
	text-align: center;
	vertical-align: center;
}

.abc {
	witdh: 120px;
	height: 100%;
	float: left;
}

.body-container2 {
	height: 900px; 
}	
.right-content-orderinfo img { widith : 50px;
	height: 60px;
}

.right-content-orderinfo {
	display: flex;
	height: 100%;
	margin-left: 5px;
}

.right-content-orderinforight {
	float: right;
}

.right-content-orderinfoleft {
	float: left;
}

.clearboth {
	clear: both;
}

.order-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
	gap: 15px;
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
	<div class="tit_page" >
		<h2 class="tit">마이 페이지</h2>
	</div>
	<div class="container body-container">
		<div class = "abc">
			<div class = "mypage-left-subject"><h2>마이푸딧</h2></div>
				<div class ="mypage-selectbox">
					<ul class="mypage-ul">
						<li><a href ="${pageContext.request.contextPath}/mypage/order.do">주문내역</a></li>				
						<li><a href ="${pageContext.request.contextPath}/basket/cart.jsp">장바구니</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/modify_checkPw.do">개인정보수정</a></li>			
						<li><a href ="${pageContext.request.contextPath}/mypage/addr.do">배송지관리</a></li>				
						<li><a>내가쓴후기</a></li>				
					</ul>
				</div>
		</div>
		<div class="mypage-right">		
			<div class ="mypage-right-subject">
				<h2> 주문 내역 </h2> <span style="font-size : 14px;"> 최대 지난 3년간의 주문 내역을 확인할 수 있습니다.</span>
				<select style="display : inline; float : right">
					<option>3개월</option>
					<option>6개월</option>
					<option>1년</option>
					<option>3년</option>
				</select>
			</div>
			
			<div class ="mypage-right-content"  >
			
				<c:forEach var="dto" items="${list}" varStatus="status">
					<div style="display: flex; justify-content: space-between; align-items: center; gap: 15px;">
						<div style="margin-top: 5px;">
							<div style="width:100px; height: 120px; float : left "><img src="photo.png"/></div>
								<div style="margin-top : 20px; float : left;" >
								<div>상품명: ${dto.itemName }</div>
								<div>주문번호: ${dto.orderNo }</div>
								<div>신용카드: ${dto.payOption }</div>
								<div>결제금액: ${dto.totPrice }</div> 
							</div>
							
						</div>
						<div>
							<span>배송완료</span>
							<div style="display: inline-block;">
								<button type="button">
									<span>구매 확정</span>	
								</button>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="page-navigation">
				${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
				</div>
			</div>
		</div>
	</div>
</main>

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>
</body>
</html>
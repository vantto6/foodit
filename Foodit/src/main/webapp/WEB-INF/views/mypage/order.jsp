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

.min-height{
	min-height : 600px;
}

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

.mypage-right {margin-left : 100px; width : 800px; height : 100% ; float : left; 1px solid black;}
.mypage-right-subject { height : 50px ; border-bottom : 2px solid black;}
.mypage-right-content { height : 100% ; }

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

.table-border-addrmanage thead > tr { border-bottom: 1px solid black; border-bottom: 1px solid black; }

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
	<div class="container body-container min-height">
		<div class = "abc">
			<div class = "mypage-left-subject"><h2>마이푸딧</h2></div>
				<div class ="mypage-selectbox">
					<ul class="mypage-ul">
						<li><a href ="${pageContext.request.contextPath}/mypage/order.do">주문내역</a></li>				
						<li><a href ="${pageContext.request.contextPath}/basket/cart.do">장바구니</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/checkPw.do">개인정보수정</a></li>			
						<li><a href ="${pageContext.request.contextPath}/mypage/addr.do">배송지관리</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/review.do">상품문의</a></li>				
					</ul>
				</div>
		</div>
		<div class="mypage-right">		
			<div class ="mypage-right-subject">
				<span style="font-size : 20px;">주문 내역</span>
				<div>
					<span style="font-size : 14px; float:left;"> 최대 지난 3년간의 주문 내역을 확인할 수 있습니다.</span>
					<div style="float: right;">
						<select class="select-orderDate">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</div>
				</div>
			</div>
			
			<div class ="mypage-right-content"  >
				
					<%-- <table class="table table-border-addrmanage table-list">
					<thead>
						<tr>
							<th class="tel">상품사진</th>
							<th class="tel">상품명</th>
							<th class="tel">주문번호</th>
							<th class="tel">상품명</th>
							<th class="tel">결제방법</th>
							<th class="tel">결제금액</th>
						</tr>
					</thead>
				
					<tbody>
						<c:forEach var="dto" items="${list}" varStatus="status">
							<tr style="margin-top: 10px;">
								<td>주문일자 : 2011-11-11</td>
							</tr>
							<tr style="border-top: 1px solid lightgray">
								<td class="addr">${dto.itemName }</td>
								<td>${dto.orderNo }</td>
								<td class="sender">${dto.payOption }</td>
								<td class="sender">${dto.payOption }</td>
								<td class="sender">${dto.totPrice }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table> --%>
				
					<c:forEach var="dto" items="${list}" varStatus="status">	
						<div style="display: flex; justify-content: space-between; align-items: center; gap: 15px; border-bottom: 1px solid lightgray">
						<div style="margin-top: 5px; ">
							<div style="width:100px; height: 120px; float : left; border: 1px solid gray "><img src="photo.png"/></div>
								<div style="margin : 20px; float : left; display: grid ; gap:5px" >
									<div><b>주문일자</b>	 : ${dto.payDate }</div>
									<div><b>상품명</b> 	 : 
											<c:if test="${dto.cnt < 2}">
											${dto.itemName}
											</c:if>
											<c:if test="${dto.cnt >= 2}">
											${dto.itemName} 외 ${dto.cnt-1} 건
											</c:if>
									</div>
									<div><b>주문번호</b>	 : ${dto.orderNo }</div>
									<div><b>결제방법</b> 	 : ${dto.payOption }</div>
									<div><b>결제금액</b> 	 : ${dto.totPrice }</div>
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
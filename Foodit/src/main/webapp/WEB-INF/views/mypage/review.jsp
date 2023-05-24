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
.members-form { max-width: 360px; margin: 0 auto; background: #fefeff;}
.members-form .row { margin-bottom: 1.5rem; }
.members-form label { display: block; font-weight: 500; margin-bottom: 0.5rem; font-family: Verdana, sans-serif; }
.members-form input { display: block; width: 100%; padding: 7px 5px; }
.members-form button { padding: 8px 30px; font-size: 15px; width: 97%; }

.members-message { margin: 0 auto; padding: 20px 5px; }
.members-message p { color: #023b6d; }

.text-center { text-align: center; }
.text-right { text-align: right; }
.margin-top5 { margin-top: 5px; }
.margin-top10 { margin-top: 10px; }

.mypage-selectbox { width : 150px;}
.mypage-selectbox .mypage-ul {  list-style: none; }
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
  

.mypage-ul > li { 
	height : 50px; 
	background : white; 
	text-align : center; 
	font-size: 15px; 
	border : 1px solid gray;
}

.mypage-ul > li a {
	margin-top : 14px;
	display: block;
}

.mypage-right {margin-left : 100px; width : 800px; height : 100% ; float : left; 1px solid black;}
.mypage-right-subject { height : 50px ; border-bottom : 2px solid black;}
.mypage-right-content { height : 100% ; }

.mypage-left-subject { width : 150px; height : 50px; text-align : center; vertical-align: center;}

.abc {witdh : 120px; height : 100%; float: left;}
.body-container2 {
	height: 900px;
}

.table-border-addrmanage thead > tr { border-bottom: 1px solid black; }

.table-list .subject {
	width: 40%; color: #787878;
}
.table-list .num {
	width: 10%; color: #787878; text-align : center;
}
.table-list .reg_date {
	width: 20%; color: #787878; text-align : center;
}
.table-list .img {
	width: 15%; color: #787878; text-align : center;
}
.table-list .itemName {
	width: 15%; color: #787878; text-align : center;
}
img {
	width : 50px;
	height : 70px;
}


.table tbody tr td {
	text-align : center;
}

.openAddr {
	padding : 3px;
	font-size : 15px;
	float: right
	
}

#wrapper {
	display: flex;
}

#address,
#addressDetail {
	width: 100%;
}
#addressCode {
	flex: 1;
}

.field_address__wrapper {
	width: 100%;
	margin-left : 5px;
}

.member_join th .ico {
    color: #ee6a7b;
}
.btn {
	 color: #88b04B;  
	 border: 1px solid #88b04B; 
	 background-color: #ffffff; 
	 padding: 5px 10px; 
	border-radius: 4px;
	font-weight: 500;
	cursor:pointer;
	font-size: 14px;
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
	vertical-align: baseline;
	text-align: center;
}

.type_form .btn.active {
   border: 1px solid #88b04B;
   background-color: #fff;
   color: #88b04B;
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
						<li><a href = "${pageContext.request.contextPath}/basket/cart.do">장바구니</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/modify_checkPw.do">개인정보수정</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/addr.do">배송지관리</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/review.do">상품문의</a></li>				
					</ul>
				</div>
		</div>
		<div class="mypage-right">		
			<div class ="mypage-right-subject">
				<div><span style="font-size: 20px; ">상품문의</span></div>
			</div>
			
			<div class ="mypage-right-content">
					<table class="table table-border-addrmanage table-list">
						<thead>
							<tr>
								<th class="num">번호</th>
								<th class="itemName">상품명</th>
								<th class="subject">제목</th>
								<th class="reg_date">작성일자</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach var="dto" items="${list}" varStatus="status">
								<tr style="border-bottom: 1px solid lightgray;">
									<td>${(page-1) * size + status.index + 1}</td>
									<td>${dto.itemName}</td>
									<td><a href="${pageContext.request.contextPath}/itemInquiry/article.do?itemNo=${dto.itemNo}&inquiryNo=${dto.inquiryNo}">${dto.subject}</a></td>
									<td>${dto.createDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				<div class="page-navigation">
					${dataCount == 0 ? "등록된 리뷰가 없습니다." : paging}
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
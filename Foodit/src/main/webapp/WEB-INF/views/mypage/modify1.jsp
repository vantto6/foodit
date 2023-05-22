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
.members-form button {font-size: 15px; padding: inherit; }

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
function sendLogin() {
	const f = document.loginForm;
	
	let str = f.memberId.value;
	if (!str) {
		alert("아이디를 입력하세요. ");
		f.memberId.focus();
		return;
	}

	str = f.pwd.value;
	if (!str) {
		alert("패스워드를 입력하세요. ");
		f.pwd.focus();
		return;
	}

	f.action = "${pageContext.request.contextPath}/mypage/login_ok.do";
	f.submit();
}
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
						<li><a>장바구니</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/modify_checkPw.do">개인정보수정</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/addr.do">배송지관리</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/review.do">내가쓴후기</a></li>				
					</ul>
				</div>
		</div>
		<div class="mypage-right">		
			<div class ="mypage-right-subject">
				<h2> 개인정보수정 </h2> <span style="font-size : 14px;"></span>
				
			</div>
			
			<div class ="mypage-right-content margin-top10">
				<h2> 비밀번호 재확인 </h2>
				<h4> 회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해주세요. </h4>
				
				<form name="loginForm" method="post">
					<div class="members-form">
						<div class="row text-center">
							
						</div>
						<div class="row">
							<p>아이디</p><input name="memberId" type="text" class="form-control" id="memberId" value="${username}" readonly>
							<p>비밀번호</p><input name="pwd" type="password" class="form-control margin-top5" id="pwd" placeholder="비밀번호를 입력하세요">
						</div>
						<div class="row text-center">
							<button type="button" class="btn" onclick="sendLogin();">확인</button>
						</div>
				</div>
			</form>
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
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

.mypage-right {margin-left : 100px; width : 600px; height 300px; float : left; 1px solid black;}
.mypage-right-subject { height : 50px ; border-bottom : 2px solid black;}
.mypage-right-content { height : 300px; }

.mypage-left-subject { width : 150px; height : 50px; text-align : center; vertical-align: center;}

.abc {witdh : 120px; height : 300px; float: left;}

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
						<li><a>주문내역</a></li>				
						<li><a>장바구니</a></li>				
						<li><a>개인정보수정</a></li>				
						<li><a>배송지관리</a></li>				
						<li><a>내가쓴후기</a></li>				
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
							<p>아이디</p><input name="userId" type="text" class="form-control" id="login-userId" placeholder="아이디가져와야함">
							<p>비밀번호</p><input name="user" type="password" class="form-control margin-top5" id="login-password" placeholder="비밀번호를 입력하세요">
						</div>
						<div class="row text-center">
							<button type="button" class="btn btn-primary" onclick="sendLogin();">확인</button>
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
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
button {
	background-color: #88b04B;
	border: 0px solid #88b04B;
	outline: none;
}

.btn_type1 {
	border: 1px solid #88B049;
}

.btn_type2 {
	border: 1px solid #88B049;
	background-color: #fff;
	color: white;
}

#btn_type1>button {
	width: 100%;
}

.members-form {
	max-width: 360px;
	margin: 0 auto;
	background: #fefeff;
	padding: 30px 25px;
	box-shadow: 0 0 15px 0 rgb(2 59 109/ 10%);
}

.members-form .row {
	margin-bottom: 1.5rem;
}

.members-form label {
	display: block;
	font-weight: 500;
	margin-bottom: 0.5rem;
	font-family: Verdana, sans-serif;
}

.members-form input {
	display: block;
	width: 100%;
	padding: 7px 5px;
}

.members-form button {
	padding: 8px 30px;
	font-size: 15px;
	width: 97%;
}

.members-message {
	margin: 0 auto;
	padding: 20px 5px;
}

.members-message p {
	color: #023b6d;
}

.text-center {
	text-align: center;
}

.text-right {
	text-align: right;
}

.margin-top {
	margin-top: 5px;
}
.btn_type2 .txt_type {
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

		f.action = "${pageContext.request.contextPath}/member/login_ok.do";
		f.submit();
	}
</script>

</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div id="main">
			<div id="content">
				<div class="section_login">
					<h3 class="tit_login">로그인</h3>
					<div class="write_form">
						<div class="write_view login_view">
							<form name="loginForm" id="form" method="post">
								<input type="text" name="memberId" id="memberId" size="20"
									placeholder="아이디를 입력해주세요" value=""> <input
									type="password" name="pwd" id="pwd" size="20"
									placeholder="비밀번호를 입력해주세요">
								<div class="checkbox_save">
									<div class="login_search">
										<a class="link" href="${pageContext.request.contextPath}/member/findId.do">
											아이디 찾기 </a> <span class="bar"></span> <a class="link"
											href="${pageContext.request.contextPath}/member/pwdFind.do"> 비밀번호 찾기 </a>
									</div>
									<div id="message"></div>
								</div>
								<div class="btn_type1" id="btn_type1">
									<button class="txt_type" onclick="sendLogin();">로그인</button>
								</div>
							</form>
							<a class="btn_type2 btn_member"
								href="${pageContext.request.contextPath}/member/join.do"> <span
								class="txt_type">회원가입</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>

	</main>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" />
</body>
</html>
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
.text-center {
	text-align: center;
}

.text-right {
	text-align: right;
}

.title {
	overflow: hidden;
	width: 1050px;
	margin: 0 auto;
	padding: 50px 0 51px;
	text-align: center;
	font-size: 20px;
}

.flex-container {
	display: flex;
	letter-spacing: -0.5px;
}

.flex-container .select-container {
	flex-grow: 3;
}

.flex-container .cart_delivery {
	flex-grow: 1;
	position: relative;
	z-index: 3
}
</style>

<script type="text/javascript">
	function sendLogin() {
		const f = document.loginForm;

		let str = f.userId.value;
		if (!str) {
			alert("아이디를 입력하세요. ");
			f.userId.focus();
			return;
		}

		str = f.userPwd.value;
		if (!str) {
			alert("패스워드를 입력하세요. ");
			f.userPwd.focus();
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
		<div class="container body-container">
			<div class="title">
				<h3>장바구니</h3>
			</div>
			<div class="flex-container">
				<div class="select-container" style="border: 1px solid red">
					<div
						style="display: block; align-items: center; padding: 18px 10px 16px 2px; font-size: 14px; line-height: 26px; font-weight: 500;">
						<form method="get" action="form-action.html">
							<div>
								<input type="checkbox" id="horns" name="horns"> <label
									for="horns">전체선택</label>	<p>선택삭제</p>
									
							</div>
						</form>
						<label> <input type="checkbox"> <span
							style="border-top: 1px solid bloack;">전체선택</span>

						</label>
					</div>
				</div>
				<!--  
				<div class="cart_delivery">
					<h3 class="title">배송지</h3>
					<div class="address">
						<p class="addr">경기 성남시 분당구 대왕판교로 372</p>
						<p class="addr">11</p>
					</div>
				</div>
				-->
				<div class="cart_delivery" style="border: 1px solid blue">
					<div></div>

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
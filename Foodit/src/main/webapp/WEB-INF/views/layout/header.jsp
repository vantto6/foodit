<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v6.3.0/css/all.css">

<style>
.header-right a {
	padding: 10px;
}

.nav-container {
	width: 1110px;
	height: 100px;
	margin: 0 auto;
	text-align: center;
}

.nav{
	z-index: 100;
}
.main-menu a {
	padding: 10px;
}

.right-style {
	margin-left: 580px;
}
</style>




<div class="nav-container">

	<div class="header-top">
		<div class="header-left">&nbsp;</div>
		<div class="header-center">
			<h1 class="logo">
				<a href="${pageContext.request.contextPath}/">FoodIt</a>
			</h1>
		</div>
		<div class="header-right">
			<c:if test="${empty sessionScope.member}">

				<a href="${pageContext.request.contextPath}/member/login.do"
					title="로그인"><i class="fa-solid fa-arrow-right-to-bracket fa-lg"></i></a> 
				&nbsp; 
                <a
					href="${pageContext.request.contextPath}/member/join.do"
					title="회원가입"><i class="fa-solid fa-user-plus fa-lg"></i></a>

			</c:if>
			<c:if test="${not empty sessionScope.member}">
				<a href="#" title="알림"><i class="fa-regular fa-bell fa-lg"></i></a>
            	&nbsp;
				<a href="${pageContext.request.contextPath}/member/logout.do"
					title="로그아웃"><i
					class="fa-solid fa-arrow-right-from-bracket fa-lg"></i></a>
			</c:if>
			<c:if test="${sessionScope.member.memberId == 'admin'}">
            	&nbsp;
				<a href="${pageContext.request.contextPath}/member/admin.do" title="관리자"><i class="fa-solid fa-gear fa-lg"></i></a>
			</c:if>
		</div>
	</div>
<<<<<<< HEAD
</div>
<nav>
	<ul class="main-menu">
		<li><a href="#"><i class="fa-solid fa-bars" style="padding: 10px;"></i>카테고리</a>
			<ul class="sub-menu">
				<li><a href="${pageContext.request.contextPath}/item/item.do" aria-label="subemnu">야채/과일</a></li>
				<li><a href="${pageContext.request.contextPath}/item/item.do" aria-label="subemnu">해/수산물</a></li>
				<li><a href="${pageContext.request.contextPath}/item/item.do" aria-label="subemnu">정육</a></li>
			</ul></li>
		<li><a href="${pageContext.request.contextPath}/item/item.do">신상품</a></li>
		<li><a href="${pageContext.request.contextPath}/item/item.do">베스트</a></li>
				<li><a href="#">고객센터</a>
			<ul class="sub-menu">
				<li><a href="#" aria-label="subemnu">공지사항</a></li>
				<li><a href="#" aria-label="subemnu">1:1문의</a></li>
				<li><a href="#" aria-label="subemnu">Q&A</a></li>
			</ul></li>
=======
	<nav class="nav">
		<ul class="main-menu">
			<li><a href="#"><i class="fa-solid fa-bars"
					style="padding: 10px;"></i>카테고리</a>
				<ul class="sub-menu">
					<li><a href="#" aria-label="submenu">야채/과일</a></li>
					<li><a href="#" aria-label="submenu">해/수산물</a></li>
					<li><a href="#" aria-label="submenu">정육</a></li>
				</ul></li>
			<li><a href="#">신상품</a></li>
			<li><a href="#">베스트</a></li>
>>>>>>> branch 'main' of https://github.com/vantto6/foodit.git

			<li class="right-style"><a href="#">고객지원센터</a>
				<ul class="sub-menu">
					<li><a href="#">마이페이지</a></li>
					<li><a href="${pageContext.request.contextPath}/basket/cart.jsp">장바구니</a></li>
					<li><a
						href="${pageContext.request.contextPath}/notice/list.do"
						aria-label="submenu">공지사항</a></li>
					<li><a href="#" aria-label="submenu">1:1문의</a></li>
					<li><a href="#" aria-label="submenu">자주묻는질문</a></li>
				</ul></li>
		</ul>
	</nav>
</div>

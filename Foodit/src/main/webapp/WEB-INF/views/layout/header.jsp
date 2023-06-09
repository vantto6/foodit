<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v6.3.0/css/all.css">

<style>
.header-right a {
	padding: 8px;
}

.nav-container {
	width: 1110px;
	margin: 0 auto;
	text-align: center;
}

.nav{
	z-index: 100;
}
.main-menu a {
	padding: 10px;
}
.main-menu {
	box-shadow: 10px 10px 10px #F7F7F7;
}
.right-style {
	margin-left: 580px;
}
img {
	width: 220px;
}
</style>




<div class="nav-container" >

	<div class="header-top">
		<div class="header-left">&nbsp;</div>
		<div class="header-center">
			<h1 class="logo">
				<a href="${pageContext.request.contextPath}/">
				<img src="${pageContext.request.contextPath}/resource/images/foodIt.png" ></a>
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
			
				<a href="${pageContext.request.contextPath}/basket/cart.do" title="장바구니"><i class="fa-solid fa-cart-shopping fa-lg"></i></a>
            	&nbsp;
				<a href="${pageContext.request.contextPath}/mypage/mypage.do" title="마이페이지"><i class="fa-solid fa-circle-user fa-lg"></i></a>
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
	<nav class="nav" >
		<ul class="main-menu">
			<li><a href="#"><i class="fa-solid fa-bars"
					style="padding: 10px;"></i>카테고리</a>
				<ul class="sub-menu">
					<li><a href="${pageContext.request.contextPath}/item/item.do?category=1" aria-label="submenu">야채/과일</a></li>
					<li><a href="${pageContext.request.contextPath}/item/item.do?category=2" aria-label="submenu">해/수산물</a></li>
					<li><a href="${pageContext.request.contextPath}/item/item.do?category=3" aria-label="submenu">정육</a></li>
				</ul></li>
			<li><a href="${pageContext.request.contextPath}/item/newItem.do?num=1">신상품</a></li>
			<li><a href="${pageContext.request.contextPath}/item/newItem.do?num=2">베스트</a></li>

			<li class="right-style"><a href="#">고객지원센터</a>
            <ul class="sub-menu">
               <li><a
                  href="${pageContext.request.contextPath}/notice/list.do" aria-label="submenu">공지사항</a></li>
               <li><a href="${pageContext.request.contextPath}/inquiry/list.do" aria-label="submenu">1:1문의</a></li>
               <li><a href="${pageContext.request.contextPath}/question/list.do" aria-label="submenu">자주묻는질문</a></li>
            </ul></li>
		</ul>
	</nav>
</div>

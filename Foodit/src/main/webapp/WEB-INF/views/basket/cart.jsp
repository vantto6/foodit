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
.title {
	style ="font-size: 14px;
	align-items: center;
	padding: 18px 10px 16px 2px;
	font-size: 14px;
	line-height: 26px;
	font-weight: 500;
}

.cartList_left {
	float: left;
	width: 766px; min-height 300px;
	float: left;
	border-bottom: 1px solid black;
}

img {
	display: block;
	width: 80%;
	height: 80px;
	margin: auto;
}

.cart {
	width: 80%;
	margin: auto;
	padding: 30px;
}

.cart ul {
	background-color: whitesmoke;
	padding: 30px;
	margin-bottom: 50px;
	border: whitesmoke solid 1px;
	border-radius: 5px;
	font-size: 13px;
	font-weight: 300;
}

table {
	border-top: solid 1.5px black;
	border-collapse: collapse;
	width: 100%;
	font-size: 14px;
}

thead {
	text-align: center;
	font-weight: bold;
}

tbody {
	font-size: 12px;
}

td {
	padding: 15px 0px;
	border-bottom: 1px solid lightgrey;
}
 
.cartList_detail :nth-child(3) p {
	font-weight: bold;
}

.cartList_option {
	padding: 20px;
}

.cartList_optionbtn {
	background-color: white;
	font-size: 10px;
	border: lightgrey solid 1px;
	padding: 7px;
}

.cartList_detail :nth-child(3), .cartList_detail :nth-child(4),.cartList_detail :nth-child(5) {
	text-align: center;
}

.price {
	font-weight: bold;
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


		<div class="tit_page">
			<h2 class="tit">장바구니</h2>
		</div>

		<div id="cartItemList" class="only_pc" style="min-height: 800px">
			<div class="empty">
				<div class="select-container">
					<div class="cartList_left">
						<div class="title">
							<input id="cart_checkbox" type="checkbox" name="cart_checkbox"
								value="TRUE"> <label>전체선택 | </label><span> 선택삭제</span>
						</div>
						<table class="cartList">
							<thead>
								<tr>
									<td><input type="checkbox"></td>
									<td colspan="2">상품정보</td>
									<td>옵션</td>
									<td>상품금액</td>
								</tr>
							</thead>
							<tbody>
						        <c:set var="totalPrice" value="0" />
								<c:forEach var="dto" items="${list}">
									<tr class="cartList_detail">
										<td><input type="checkbox"></td>
										<td><img src="galbitang.jpeg" alt="food_img"></td>
										<td><a href="#"></a><span class="cartList_smartstore">
										</span>
											<p class="price">${dto.itemName}</p></td>
										<td class="cartList_option">
											<button class="cartList_optionbtn">주문조건 추가/변경</button>
										</td>
										<td><span class="price">${dto.price}</span><span
											style="text-decoration: line-through; color: lightgray;">13,000</span><br>
									</tr>
									<c:set var="totalPrice" value="${totalPrice + dto.price}" />
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="3"><input type="checkbox">
										<button class="cartList_optionbtn">선택상품 삭제</button>
										<button class="cartList_optionbtn">선택상품 찜</button></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tfoot>

						</table>

					</div>
				</div>

				<div class="cart_result">
					<div class="inner_result">
						<div class="cart_delivery">
							<h3 class="tit">배송지</h3>
							<div class="address">
								<p class="addr">경기 성남시 분당구 대왕판교로 372</p>
								<p class="addr">11</p>
							</div>
						</div>
						<div class="amount_view">
							<dl class="amount">
								<dt class="tit">상품금액</dt>
								<dd class="price">
									<span class="num">₩${totalPrice}</span><span class="won">원</span>
								</dd>
							</dl>
							<dl class="amount">
								<dt class="tit">상품할인금액</dt>
								<dd class="price">
									<span class="num">0</span><span class="won">원</span>
								</dd>
							</dl>

							<dl class="amount">
								<dt class="tit">배송비</dt>
								<dd class="price">
									<span class="num">0</span><span class="won">원</span>
								</dd>
							</dl>
							<dl class="amount lst">
								<dt class="tit">결제예정금액</dt>
								<dd class="price">
									<span class="num">₩${totalPrice}</span><span class="won">원</span>
								</dd>
							</dl>

							<div class="reserve"></div>
						</div>
						<div class="btn_submit">
							<button type="button" class="btn active"
								onclick="location.href='index.jsp?folder=pay&category=pay_page';">주문하기</button>
						</div>
						<div class="notice">
							<span class="txt"><span class="ico">·</span>[배송준비중] 이전까지
								주문 취소 가능합니다.</span><span class="txt"><span class="ico">·</span>[마이컬리
								&gt; 개인정보수정 페이지] 에서 주소지를 변경하실수 있습니다.</span>
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
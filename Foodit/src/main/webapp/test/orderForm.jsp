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
.body-main {
	width: 1000px;
	margin: 0 auto;
	text-align: center;
}

table {
	margin: 0 auto;
	border-collapse: collapse;
	margin-bottom: 40px;
}

th {
	text-align: left;
	border-top: none;
	border-bottom: 1px solid black;
	font-size: 1.2em;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: none;
	padding-right: none;
}

td {
	border-bottom: 1px solid #dcdcdc;
	color: #646464;
	padding-top: 18px;
	padding-bottom: 18px;
	padding-left: none;
	padding-right: none;
	font-size: 1.2em;
}

.sub {
	color: #828282;
	font-size: 0.6em;
	padding: 20px;
}
.btn {
    display: inline-block;
    width: 220px;
    margin-left: 5px;
    vertical-align: top;
}


.btn:active, .btn:focus, .btn:hover {
	background-color: #f8f9fa;
	color: #333333;
}

.table-container {
	text-align: center;
}

.orderInfo td {
	text-align: left;
	line-height: 20px;
}
button.btn.active.btn_order {
	line-height: 46px !important;
}
input[type=text] {
	width: 500px;
}
</style>

</head>
<body>


	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div class="body-main">

			<div class="tit_page">
				<h2 class="tit">주문서</h2>
			</div>
			<br>
			<form>
				<table>
					<tr>
						<th colspan="4">주문상품</th>
					</tr>

					<c:forEach var="n" begin="1" end="5" step="1">
						<tr>
							<td width="100"><img src="galbitang.jpeg" alt="food_img"></td>
							<td width="600">[사미헌]갈비탕</td>
							<td width="200">1개</td>
							<td width="100"><span class="price">20,000</span>
								<div style="text-decoration: line-through; color: lightgray;">13,000</div></td>
						</tr>
					</c:forEach>
				</table>

				<table class="orderInfo">
					<tr>
						<th colspan="2">주문자 정보</th>
					</tr>
					<tr>
						<td width="200">받으실 분</td>
						<td width="800">이민석</td>
					</tr>
					<tr>
						<td width="200">휴대폰</td>
						<td width="800">010-2222-2222</td>
					</tr>
					<tr>
						<td width="200">이메일<br> <br></td>
						<td width="800">user@gmail.com<br> <span class="info">이메일을
								통해 주문처리 과정을 보내드립니다. <br> 정보 변경은 푸드잇>개인정보 수정 메뉴에서 가능합니다.
						</span>
						</td>
					</tr>
				</table>

				<table class="orderInfo">
					<tr>
						<th colspan="2">배송지 정보</th>
					</tr>
					<tr>
						<td width="200">배송지</td>
						<td width="800">서울 마포구 월드컵북로 21</td>
					</tr>
					<tr>
						<td width="200">상세정보</td>
						<td width="800">이민석 &nbsp; &nbsp; &nbsp; 010-2222-2222</td>
					</tr>
					<tr>
						<td width="200">요청사항</td>
						<td width="800"><input type="text" name="request" maxlength="100"
								class="form-control" value="${dto.request}"></td>
					</tr>
				</table>
				<table class="orderInfo">
					<tr>
						<th colspan="2">결제 금액</th>
					</tr>
					<tr>
						<td width="200">주문금액</td>
						<td width="800"><span class="price">97,400원</span></td>
					</tr>
					<tr>
						<td width="200" style="color: silver;">└ &nbsp;&nbsp;상품금액</td>
						<td width="800"><div style="color: lightgray;">113,900원</div></td>
					</tr>
					<tr>
						<td width="200" style="color: silver;">└ &nbsp;&nbsp;상품할인금액</td>
						<td width="800"><div style="color: lightgray;">-16,500원</div></td>
					</tr>
					<tr>
						<td width="200">최종결제금액</td>
						<td width="800"><span class="price">97,400원</span></td>
					</tr>
				</table>
				
				 <button type="button" class="btn active btn_order" name="btnOk" onclick="orderOk();">97,400원 결제하기</button>
			</form>
		</div>
	</main>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" />
</body>
</html>
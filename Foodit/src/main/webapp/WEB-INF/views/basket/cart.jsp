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
.product_count{
	display: flex;
	justify-content: center;
}
input[type=checkbox] {
   opacity: 1;
   position: relative;
   z-index: 10;
}

/* 모달대화상자 */
.ui-widget-header { /* 타이틀바 */
	background: none;
	border: none;
	border-bottom: 1px solid #ccc;
	border-radius: 0;
}
.ui-dialog .ui-dialog-title {
	padding-top: 5px; padding-bottom: 5px;
}
.ui-widget-content { /* 내용 */
   /* border: none; */
   border-color: #ccc; 
}
.form-control{
	width: 30px;
}
</style>

<script type="text/javascript">
window.onload=function(){
	var inven = '<%= request.getAttribute("inven") %>';
	if(inven === "0"){
		alert("상품 재고가 없습니다.");
	}
}
function sendOrder() {
	
	var f = document.orderForm;
	if(! f.address.value) {
		alert("배송지를 선택하세요 ...");
		return;
	}
	
	
	f.action="${pageContext.request.contextPath}/basket/order.do";
	f.submit();
}

function submitSelecteditems() {
	  var checkboxes = document.getElementsByName("Selecteditem");
	  var selecteditems = [];
	  
	  

	  for (var i = 0; i < checkboxes.length; i++) {
	    if (checkboxes[i].checked) {
	    	selecteditems.push(checkboxes[i].value);
	    }
	  }
	  
	  if(selecteditems.length === 0){
		  alert("선택된 상품이 없습니다.");
		  return;
	  }
	  
	  // 폼 동적 생성
	  var form = document.createElement("form");
	  form.setAttribute("method", "post");
	  form.setAttribute("action", "${pageContext.request.contextPath}/basket/cart_delete.do");

	  // 선택된 체크박스 값들을 폼에 추가
	  for (var j = 0; j < selecteditems.length; j++) {
	    var input = document.createElement("input");
	    input.setAttribute("type", "hidden");
	    input.setAttribute("name", "itemsToDelete");
	    input.setAttribute("value", selecteditems[j]);
	    form.appendChild(input);
	  }

	  // 폼을 body에 추가하고 제출
	  document.body.appendChild(form);
	  form.submit();
}


$(function(){
	$(".count_down").click(function(){
		let count = parseInt($(this).closest("div").find("input[name=count]").val());
		let maxCount = parseInt($(this).closest("div").find("input[name=maxCount]").val());
		if(count <= 1) {
			return false;
		}
		
		count--;
		$(this).closest("div").find("input[name=count]").val(count);

		let $el = $(this).closest("tbody").find(".product_count");
		let total = 0;
		let disTotal = 0;
		$el.each(function(index, item){
			let price = parseInt($(item).attr("data-price"));
			let discountPrice = parseInt($(item).attr("data-discountPrice"));
			let c = parseInt($(item).find("input[name=count]").val());
			total += price * c;
			disTotal += discountPrice * c;
		});
			discount = total - disTotal;
		
		$(".totalPrice").text(total);
		$(".realTotalPrice").text(disTotal);
		$(".totalDiscount").text(discount);

	});
	
	$(".count_up").click(function(){
		let count = parseInt($(this).closest("div").find("input[name=count]").val());
		let maxCount = parseInt($(this).closest("div").find("input[name=maxCount]").val());
		if(count >= maxCount) {
			return false;
		}
		
		count++;
		$(this).closest("div").find("input[name=count]").val(count);

		let $el = $(this).closest("tbody").find(".product_count");
		let total = 0;
		let disTotal = 0;
		$el.each(function(index, item){
			let price = parseInt($(item).attr("data-price"));
			let discountPrice = parseInt($(item).attr("data-discountPrice"));
			let c = parseInt($(item).find("input[name=count]").val());
			total += price * c;
			disTotal += discountPrice * c;
		});
			discount = total - disTotal;

		$(".totalPrice").text(total);
		$(".realTotalPrice").text(disTotal);
		$(".totalDiscount").text(discount);

	});	
});

function openAddress() {
	
	$(".dialog-address").dialog({
		title:"배송지",
		width: 600,
		height: 530,
		modal: true
	});	
}

$(function(){
	
	$(".btnAddressOk").click(function(){
		
		const $td = $(this).closest("tr").find("td");
		let addressCode = $td.eq(0).text();
		let address = $td.eq(1).text();
		let addressDetail = $td.eq(2).text();
		
		$(".addr-basic").text(address);
		$(".addr-detail").text(addressDetail);
		
		$("form input[name=addressCode]").val(addressCode);
		$("form input[name=address]").val(address);		
		$("form input[name=addressDetail]").val(addressDetail);
		
		$( ".dialog-address" ).dialog( "close" );
	});
});

function checkAll(source) {
	var checkboxes = document.getElementsByName('Selecteditem');
	for (var i = 0; i < checkboxes.length; i++) {
	  checkboxes[i].checked = source.checked;
	}
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
								value="TRUE" onchange="checkAll(this)"> <label>전체선택</label>
						</div>
						
						<form name="orderForm" method="post">
							<table class="cartList">
								<thead>
									<tr>
										<td></td>
										<td>상품사진</td>
										<td>상품정보</td>
										<td>수량</td>
										<td>상품금액</td>
									</tr>
								</thead>
								<tbody>
							        <c:set var="totalPrice" value="0" />
							        <c:set var="totalDiscountPrice" value="0" />
							        <c:set var="totalDiscount" value="0" />
									<c:forEach var="dto" items="${list}">
										<tr class="cartList_detail">
											<td><input type="checkbox" name="Selecteditem" value="${dto.basketNo}"></td>
											<td><img src="galbitang.jpeg" alt="food_img"></td>
											<td><a href="#"></a><span class="cartList_smartstore">
											</span>
												<p class="price">${dto.itemName}</p></td>
											<td class="cartList_option">
												<div class="product_count" data-price="${dto.price}" data-discountPrice="${dto.discountPrice}">
													<button type="button" class="count_down"><i class="fa-solid fa-minus"></i></button>
													<div class="count">
														<input type="text" name="count" class="form-control" value="${dto.basketCnt}">
														<input type="hidden" name="maxCount" class="form-control" value="${dto.cnt}">
													</div>
													<button type="button" class="count_up"><i class="fa-solid fa-plus"></i></button>
													<input type="hidden" name="itemNo" value="${dto.itemNo}">
												</div>
											</td>
											<td><span class="price">${dto.discountPrice}</span><span
												style="text-decoration: line-through; color: lightgray;">${dto.price}</span><br>
										</tr>
										<c:set var="totalPrice" value="${totalPrice + (dto.price * dto.basketCnt)}" />
										<c:set var="totalDiscountPrice" value="${totalDiscountPrice + (dto.discountPrice * dto.basketCnt)}" />
										<c:set var="totalDiscount" value="${(totalPrice - totalDiscountPrice)}" />
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="3">
											<button class="cartList_optionbtn" type="button" onclick="submitSelecteditems();">선택상품 삭제</button>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</tfoot>
	
							</table>
							<input type="hidden" name="addressCode">
							<input type="hidden" name="address">
							<input type="hidden" name="addressDetail">														
						</form>
					</div>
				</div>

				<div class="cart_result">
					<div class="inner_result">
						<div class="cart_delivery">
							<h3 class="tit">배송지</h3>
							<div class="address">
								<p class="addr addr-basic">배송지를 선택하세요</p>
								<p class="addr addr-detail"></p>
								<button type="button" class="btn active"
								onclick="openAddress()">배송지 선택</button>
							</div>
						</div>
						<div class="amount_view">
							<dl class="amount">
								<dt class="tit">상품금액</dt>
								<dd class="price">
									<span class="totalPrice">₩${totalPrice}</span><span class="won">원</span>
								</dd>
							</dl>
							<dl class="amount">
								<dt class="tit">상품할인금액</dt>
								<dd class="price">
									<span class="totalDiscount">${totalDiscount}</span><span class="won">원</span>
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
									<span class="realTotalPrice">₩${totalDiscountPrice}</span><span class="won">원</span>
								</dd>
							</dl>

							<div class="reserve"></div>
						</div>
						<div class="btn_submit">
							<button type="button" class="btn active"
								onclick="sendOrder();">주문하기</button>
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
	
	<div class="dialog-address" style="display:none;" >
		<div class="address-layout">
			<table>
				<tr>
					<td>우편번호</td>
					<td>기본주소</td>
					<td>상세주소</td>
					<td>선택</td>
				</tr>
				<c:forEach var="vo" items="${addressList}">
					<tr>
						<td>${vo.addressCode}</td>
						<td>${vo.address}</td>
						<td>${vo.addressDetail}</td>
						<td>
							<button type="button" class="btn btnAddressOk">선택</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" />
</body>
</html>
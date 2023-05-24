<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/layout/staticHeader.jsp"/>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.4.0/css/all.css">

<style type="text/css">
* { padding: 0; margin: 0 auto; }
*, *::after, *::before { box-sizing: border-box; }

.product_view {position:relative; padding: 0 0 0 430px; width: 1050px; box-sizing: border-box; margin-top: 130px}
.img {position: absolute; left: 0; top: 0}
.img > img { width: 3px solid #e8e8e8}
.product_view h2{
    width: 500px;
    font-weight: 500;
    font-size: 24px;
    color: rgb(51, 51, 51);
    line-height: 34px;
    letter-spacing: -0.5px;
    word-break: keep-all;
    margin: 20px; 
    padding-left: 30px;
    }
.product_view table {margin-left: 10px}  
.product_view table th {    
    width: 128px;
    height: 100%;
    color: rgb(102, 102, 102);
    font-weight: 400;
    line-height: 19px;}
    
.product_view th{padding: 17px 0}
.product_view td {padding: 17px 0}

.product_view .btns{margin-left: 60px}
.product_view .btn2{text-align: center; margin: 50px}
.product_view .btn2 {display: inline-block;height: 50px;font-size: 16px; width: 425px;padding: 13px 0; color: black; text-decoration: none; border: 2px solid #88b04B; background: white;}
.product_view a.btn1{color: black;}
.product_view a.btn2{background: white;}

.detail{width: 1050px;}


#Review_list {
	width: 1000px;
	margin: 0 auto;
	text-align: center;
	
}
.review_text {
	font-size: 1em;
	text-align: left;
}


#Review_title {
	font-size: 3em;
	font-weight: bold;
	padding : 15px;
}

table.review {
	width: 1000px;
	margin-top:10px;
	border-top: 1px solid  #5f0081;
	border-bottom: 1px solid white;
	border-left: none;
	border-right: none;
	padding: 10px 10px;
	text-align: center;
	font-size: 1.1em;
}

th.review {
	border-bottom: 1px solid rgb(230,230,230);
	padding-top: 10px;
	padding-bottom: 10px;
	font-size: 1.1em;
}

td.review {
	border-bottom: 1px solid rgb(230,230,230);
	text-align: center;	
	overflow: hidden;
	padding-top: 10px;
	padding-bottom: 10px;
	font-size: 1.1em;
}

.subject {
	border-bottom: 1px solid rgb(230,230,230);
	text-align: left;
	padding: 5px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	font-size: 1.1em;
}

.write_review {
	margin-top: 15px;
	margin-left: 850px;
	width: 100px;
	text-align: center;
	border-style: solid #5f0081;
	background-color: #5f0081;
	color: white;
	font-weight: bold;
	border-radius: 3px;
	
}

.review_write {
	color: white;
	font-weight: bold;
	border-style: none;
	background-color: #5f0081;
	padding: 10px;
	border-radius: 3px;
	font-size: 1.1em;
	
}

button, select {
    text-transform: none;
}
button {
    overflow: visible;
    border-radius: 0;
}
button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    margin: 0;
}
button {
    outline: none;
   	
}
.review_number{
	font-size: 1.1em;
}

#Review_list a:hover {
	text-decoration: none;
	color: red;
}

.secret, .remove {
	background-color: black;
	color: white;
	font-size: 14px;
	border: 1px solid black;
	border-radius: 4px;
}

.discount {
    padding-right: 9px;
    font-size: 28px;
    color: rgb(250, 98, 47);
}

.price {
    padding-right: 4px;
    font-size: 28px;
    color: rgb(51, 51, 51);
}

.product_view tr {
    width: 128px;
    height: 100%;
    color: rgb(102, 102, 102);
    font-weight: 400;
    line-height: 19px;
}


.count-wrap {position: relative;padding: 0 38px;border: 1px solid #ddd;overflow: hidden;width: 60px;}
.count-wrap > button {border: 0;background: #ddd;color: #000;width: 38px;height: 38px;position: absolute;top: 0;font-size: 12px;}
.count-wrap > button.minus {left: 0;}
.count-wrap > button.plus {right: 0;}
.count-wrap .inp {border: 0;height: 38px;text-align: center;display: block;width: 100%;}
.original-price {
    padding-top: 1px;
    color: rgb(181, 181, 181);
    font-size: 14px;
    font-weight: 400;
    line-height: 18px;
    text-decoration: line-through;
}

.likeBtn {
	height:40px;
	width:60px;
	background:none;
	border:none;
	
}

.likeBtn i {
	font-size:40px
}

.bt {
	padding: 10px;
	background: white;
	border: none;
}


.fixed-box {
	width: 150px; height: 30px;
	
	position: fixed;
	left :1500px; top :200px; /* 기준점 : 브라우저 최상단 */
	font-weight: bold;
	text-align: center;
	font-size: 20px;
}

.detail-view{
	width: 1050px;
	margin-top: 50px;
	
}

.context h3 {
    display: block;
    margin: 75px 0px 0px;
    padding-bottom: 35px;
    font-size: 38px;
    line-height: 46px;
    text-align: center;
    border-bottom: 1px solid rgb(193, 193, 193);
    color: rgb(102, 102, 102);
}

.context .detail-description {
	font-size: 18px;
    line-height: 32px;
    color: rgb(55, 55, 55);
    text-align: center;
    margin-bottom: 100px;
    margin-top: 50px;
}

.foodit_point {
	display: inline-block;
    position: relative;
    z-index: 10;
    font-size: 50px;
    width: 100%;
    margin-bottom: 70px;
    font-weight: 400;
    font-family: "Noto Sans";
    color: rgb(102, 102, 102);
    text-align: center;
    
}

.intro-img {
	width: 1050px;
    height: 700px;
}

.small-style {
	display: block;
	font-size: 30px;
	margin-top: 30px
}
</style>

<script type="text/javascript">
function ajaxFun(url, method, query, dataType, fn) {
	$.ajax({
		type:method,		// 메소드(get, post, put, delete)
		url:url,			// 요청 받을 서버주소
		data:query,			// 서버에 전송할 파라미터
		dataType:dataType,	// 서버에서 응답하는 형식(json, xml, text)
		success:function(data) {
			fn(data);
		},
		beforeSend:function(jqXHR) { 
			jqXHR.setRequestHeader("AJAX", true); // 사용자 정의 헤더
		},
		error:function(jqXHR) {
			if(jqXHR.status === 403) {
				login();
				return false;
			} else if(jqXHR.status === 400) {
				alert("요청 처리가 실패 했습니다.");
				return false;
			}
			console.log(jqXHR.responseText);
		}
	});
}


$(function() {
	$("#plus").click(function() {
		let count = $("#count").text();
		let price = $("#price").text();
		$("#count").text(++count);
		
		$("#hiddencount").val(count);
		
		let result = count*price;
		result = result.toLocaleString('ko-KR');
		$("#totprice").text(result);
	});
	
	$("#minus").click(function() {
		let count = $("#count").text();
		let price = $("#price").text();
		if(count > 1){
			$("#count").text(--count);
			
			$("#hiddencount").val(count);
			let result = count*price;
			result = result.toLocaleString('ko-KR');
			
			$("#totprice").text(result);
		}
	});

});


$(function() {
	$(".btnSendItemLike").click(function() {
		const $i = $(this).find("i");
		let isNoLike = $i.css("color") == "rgb(0, 0, 0)";
		
		let url = "${pageContext.request.contextPath}/item/insertItemLike.do";
		let itemNo = "${dto.itemNo}";
//		let category = "${category}";
		let page = "${page}";
//		let num = "${num}";
		
		let qs = "page="+page + "&itemNo=" + itemNo + "&isNoLike=" + isNoLike;
		
		const fn = function(data) {
			let state = data.state;
			if(state === "true"){
				let color = "black";
				if(isNoLike){
					color = "red";
				}
				$i.css("color",color);
				
				//let count = data.boardLikeCount;
				//$("#boardLikeCount").text(count);
			}else if(state === "liked"){
				alert("좋아요는 한번만 가능합니다.");
			}

		};
		
		ajaxFun(url,"post",qs,"json",fn);
	});
	
})


$(function() {
	$("#sendBasket").click(function() {
		let url = "${pageContext.request.contextPath}/item/basketCheck.do";
		let itemNo = "${dto.itemNo}";
//		let category = "${category}";
//		let page = "${page}";
//		let num = "${num}";
		
		let qs = "&itemNo=" + itemNo;
		
		const fn = function(data) {
			let state = data.state;
			if(state === "true"){
				alert("상품이 이미 장바구니에 있습니다.");
			}else if(state === "false"){
				const f = document.myDetailForm;
				
				f.action = "${pageContext.request.contextPath}/item/basket_ok.do";
				f.submit();
				alert("상품을 장바구니에 담겼습니다");
			}

		};
		
		ajaxFun(url,"post",qs,"json",fn);
	});
	
})


$(function() {
	let price = "${dto.discountPrice}";
	$('#price').text(price.toLocaleString('ko-KR'));
})

	

</script>

</head>
<body>

<header>
    <jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
</header>

<div class="product_view">
	<h2>[${dto.brandName }] ${dto.itemName }</h2>
	<form name="myDetailForm" method="post">
	<input type="hidden" name="itemNo" value="${dto.itemNo}">
	<input type="hidden" name="category" value="${category}">
	<input type="hidden" name="page" value="${page}">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="main" value="${main}">
	<table>

		<tbody>
		<tr>			
			<h2>
				<span class="discount">${dto.discount }%</span>
				<span class="price" id="price"></span>
				<span>원</span>
				<span class="original-price">${dto.price }<span class="won">원</span></span>
			</h2>
		</tr>
		
		<tr>
			<th>브랜드명</th>
			<td>${dto.brandName }</td>
		</tr>
		<tr>
			<th>판매단위</th>
			<td>${dto.saleUnit }</td>
		</tr>
		<tr>
			<th>상품 설명</th>
			<td>${dto.description }</td>
		</tr>
		<tr>
			<th>유통기한</th>
			<td>${dto.deadline}</td>
		</tr>


		<tr>
			<th>구매수량</th>
			<td>
				<div>
                      <button type="button" id="minus" style="border: none;background: white;">
                      	<i class="fa-solid fa-minus"></i>
                      </button>
  					  <span id="count" style="font-size: 15px;padding: 5px;">1</span>
  					  <input type="hidden" id="hiddencount" name="basketCnt" value="1">
                      <button type="button" id="plus" style="border: none;background: white;">
                      	<i class="fa-solid fa-plus"></i>
                      </button>
				</div>
			</td>
		</tr>

		</tbody>		
	</table>
	
	<div align="right" style="margin-right: 30px">
	    <h2>
			<span>총상품금액</span>
			<span id="totprice">${dto.discountPrice}</span>
			<span>원</span>
		</h2>
		
	</div>
	<div>
		<img class="img" src="${pageContext.request.contextPath}/uploads/admin/${dto.saveFilename}" style="width: 450px;height: 556px">
	</div>
	
	<div class="btns">
		<button type="button" class="likeBtn btnSendItemLike" title="좋아요" style="width: 30px"><i class="fa-sharp fa-regular fa-heart fa-2xl" style="color: ${isMemberLike ? 'red':'black'}"></i></button>
		<button type="button" class="btn2" id="sendBasket" onclick="sendBasket();">장바구니</button>
		<c:if test="${not empty msg}">
			<p>${msg}</p>
		</c:if>
	</div>
	</form>
</div>
<div class="fixed-box">
	<button type="button" class="bt" onclick="location.href='${pageContext.request.contextPath}/itemInquiry/itemInquiry.do?itemNo=${dto.itemNo}'"><div><i class="fa-solid fa-pen" style="color: #88b04b;"></i></div></button>
	<h3>상품문의</h3>	
</div>


<div class="detail-view">
	<div class="pic">
		<img class="intro-img" src="https://img-cf.kurly.com/shop/data/goodsview/20230516/gv30000697467_1.jpg">
	</div>
	<div class="context">
		<h3>[${dto.brandName }] ${dto.itemName }</h3>
		<p class="detail-description">${dto.description }</p>
	</div>
	
	<div class="foodit_point">
		<h3>
			<span>Foodit's Check Point</span>
			<small class="small-style"> ▷ 신선한 음식</small>
			<small class="small-style"> ▷ 정직한 유통과정</small>
		</h3>
		
	
	</div>
</div>




		
	
</body>
	

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>


</body>
</html>

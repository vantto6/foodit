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
.product_view .btn2 {display: inline-block;height: 50px;font-size: 16px; width: 350px;padding: 13px 0; color: black; text-decoration: none; border: 2px solid #333;}
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
		
		$("#totprice").text(result);
	});
	
	$("#minus").click(function() {
		let count = $("#count").text();
		let price = $("#price").text();
		if(count > 1){
			$("#count").text(--count);
			
			$("#hiddencount").val(count);
			
			let result = count*price;
			
			$("#totprice").text(result);
		}
	});

});

function sendBasket() {
	const f = document.myDetailForm;
	
	f.action = "${pageContext.request.contextPath}/item/basket_ok.do";
	f.submit();
	alert("상품이 장바구니에 담겼습니다");
}

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
	<table>

		<tbody>
		<tr>			
			<h2>
				<span class="discount">${dto.discount }%</span>
				<span class="price" id="price">${dto.discountPrice}</span>
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
                      <button type="button" id="minus" style="border: none">
                      	<i class="fa-solid fa-minus"></i>
                      </button>
  					  <span id="count" style="font-size: 20px;">1</span>
  					  <input type="hidden" id="hiddencount" name="basketCnt">
                      <button type="button" id="plus" style="border: none">
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
	<div class="img">
		<img src="image/item.jpg">
	</div>
	
	<div class="btns">
		<button type="button" class="likeBtn btnSendItemLike" title="좋아요" style="width: 30px"><i class="fa-sharp fa-regular fa-heart fa-2xl" style="color: ${isMemberLike ? 'red':'black'}"></i></button>
		<button type="button" class="btn2" onclick="sendBasket();">장바구니</button>
	</div>
	</form>
</div>

<div class="detail">
	<div>
		<h3>상품설명</h3>
	</div>
</div>



<div id="Review_list">
	<div id="Review_title">상품 후기(게시글 : 16)</div>
		<div class="review_text" >
			PRODUCT REVIEW
			<br>
			상품에 대한 문의를 남기는 공간입니다. 해당 게시판의 성격과 다른 글은 사전동의 없이 담당 게시판으로 이동될 수 있습니다.
			<br>
			배송관련, 주문(취소/교환/환불)관련 문의 및 요청사항은 마이컬리 내 1:1 문의에 남겨주세요.
		</div>
	
	
	<table class="review">
		<tr>
			<th class="review" width="100">번호</th>
			<th class="review" width="500">제목</th>
			<th class="review" width="100">작성자</th>
			<th class="review" width="200">작성일</th>
		</tr>

		
			
			
			<tr>
				
				<td class="review">16</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
			<tr>
				
				<td class="review">15</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
			<tr>
				
				<td class="review">14</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
			<tr>
				
				<td class="review">13</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
			<tr>
				
				<td class="review">12</td>
				

				
				<td class="subject">
							<a href="#">
								asd
							</a>

					
					<td class="review">abc1234</td>
					
					
					<td class="review"> 
						
							2022-07-05 11:02:03
						
					</td>
			</tr>
			
		
	</table>
	

	<div class="write_review" >
		<button class="review_write" type="button">후기쓰기</button>
	</div>

	
	
		
	<div class="review_number">
	
		[&lt;&lt;][&lt;]
	
	
	
		
			[1]
		
	
		
			<a href="#">[2]</a>
		
	
		
			<a href="#">[3]</a>
		
	
		
			<a href="#">[4]</a>
		
	
	
	
		[&gt;][&gt;&gt;]
	
	</div>
</div>

			<div id="product_review"></div>
		
	
</body>
	

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>


</body>
</html>

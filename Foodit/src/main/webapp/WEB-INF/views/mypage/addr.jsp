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
.mypage-selectbox .mypage-ul li {
	border: 1px solid #ccc;
    font-size: 18px;
    color: #333;
    line-height: 20px;
    background: #fff;
    outline: none;
    vertical-align: top;
    border-bottom: 0;
	
  }

.mypage-selectbox .mypage-ul li:last-child {
	border-bottom: 1px solid #ccc !important;
} 
  

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

.mypage-right {margin-left : 100px; width : 800px; height : 100% ; float : left; 1px solid black;}
.mypage-right-subject { height : 50px ; border-bottom : 2px solid black;}
.mypage-right-content { height : 100% ; }

.mypage-left-subject { width : 150px; height : 50px; text-align : center; vertical-align: center;}

.abc {witdh : 120px; height : 100%; float: left;}
.body-container2 {
	height: 900px;
}

.table-border-addrmanage thead > tr { border-bottom: 1px solid black; }

.table-list .addr {
	width: 70%; color: #787878;
}
.table-list .num {
	width: 15%; color: #787878; text-align : center;
}
.table-list .delete {
	width: 15%; color: #787878; text-align : center;
}

.table tbody tr td {
	text-align : center;
}

.openAddr {
	padding : 3px;
	font-size : 15px;
	float: right
	
}

#wrapper {
	display: flex;
}

#address,
#addressDetail {
	width: 100%;
}
#addressCode {
	flex: 1;
}

.field_address__wrapper {
	width: 100%;
	margin-left : 5px;
}

.member_join th .ico {
    color: #ee6a7b;
}
.btn {
	 color: #88b04B;  
	 border: 1px solid #88b04B; 
	 background-color: #ffffff; 
	 padding: 5px 10px; 
	border-radius: 4px;
	font-weight: 500;
	cursor:pointer;
	font-size: 14px;
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
	vertical-align: baseline;
	text-align: center;
}

.type_form .btn.active {
   border: 1px solid #88b04B;
   background-color: #fff;
   color: #88b04B;
}



</style>
<script type="text/javascript">
function openAddress() {
	
	$(".dialog-address").dialog({
		title:"배송지 추가",
		width: 400,
		height: 300,
		modal: true
	});	
}

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
						<li><a href ="${pageContext.request.contextPath}/mypage/order.do">주문내역</a></li>				
						<li><a href ="${pageContext.request.contextPath}/basket/cart.do">장바구니</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/modify_checkPw.do">개인정보수정</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/addr.do">배송지관리</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/review.do">상품문의</a></li>				
					</ul>
				</div>
		</div>
		<div class="mypage-right">		
			<div class ="mypage-right-subject">
				<div><span style="font-size: 20px; ">배송지 관리</span></div> 
				<button class="openAddr" type="button" onclick="openAddress();"> 새 배송지 추가 </button>
			</div>
			
			<div class ="mypage-right-content">
				<form name="addrForm">
					<table class="table table-border-addrmanage table-list">
						<thead>
							<tr>
								<th class="num">번호</th>
								<th class="addr">주소</th>
								<th class="delete">삭제</th>
							</tr>
						</thead>
					
						<tbody>
									<c:forEach var="dto" items="${list}" varStatus="status">
								<tr>
									<td>${(page-1) * size + status.index + 1}</td>
									<td class="addr">${dto.address}&nbsp;${dto.addressDetail}</td>
									<td><button style="padding: auto" onclick="deleteAddr();">&nbsp;X&nbsp;</button></td>
								</tr>
								
									<input type="hidden" name="addrNo" value="${dto.addrNo}">
									
							</c:forEach>
								
						</tbody>
					</table>
			</form>
			<div class="page-navigation">
				${dataCount == 0 ? "등록된 배송지가 없습니다." : paging}
			</div>
			</div>
		</div>
	</div>
</main>

<div class="dialog-address" style="display:none;" >
		
		<form  method="post" name="memberForm" id="join">
			<table class="tbl_comm" style="margin:30px auto; border:none">
				<tr>
					<td class="field_address">
						<div class="field_address__wrapper">
							<div id="wrapper">
								<input type="text" name="addressCode" id="addressCode" size="7"
									readonly="readonly" placeholder="번지를 검색해 주세요."> 
								<a id="addressSearch" class="search"> 
								<button type="button" class="btn" onclick="daumPostcode();">주소검색</button>
								</a>
							</div>
							<input type="text" name="address" id="address"
								readonly="readonly"  placeholder="주소를 검색해주세요."> 
							<input type="text" name="addressDetail" id="addressDetail" placeholder="나머지 주소를 입력해주세요">
						</div>
					</td>
				</tr>
				</tbody>
			</table>
					<button type="button" class="btn btnAddressOk" onclick="btnAddressOk();">선택</button>
		</form>		
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function daumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('addressCode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('address').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('addressDetail').focus();
        }
    }).open();
}

function btnAddressOk() {
	const f = document.memberForm;
	
	f.action = "${pageContext.request.contextPath}/mypage/addAddr.do";
    f.submit();
	
}

</script>

<script>

function deleteAddr() {
	const f = document.addrForm;
	if(confirm("배송지를 삭제하시겠습니까? ")){
		
	f.action = "${pageContext.request.contextPath}/mypage/deleteAddr.do";
	f.submit();
	
	} else {
		event.preventDefault();
	}
}
</script>



<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>
</body>
</html>
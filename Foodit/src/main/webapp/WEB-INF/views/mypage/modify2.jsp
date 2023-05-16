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

button {
	background-color: transparent;
	border: 0px solid transparent;
	outline: none;
	padding: 0;
}

button.btn.active.btn_join {
	line-height: 46px !important;
}
.type_form label {
    font-size: 14px;
    line-height: 22px;
    padding-left: 13px;
    font-size: 17px;
    }
.member_join .tbl_comm .fst th {
    padding-top: 29px;
}
.tbl_comm > tbody > tr > th {
	padding-left: 0 !important;
}
.tbl_comm > tbody > tr > td {
	display: flex;
}


.tbl_comm > tbody > tr > td > input {
	flex: 1;
}

#wrapper {
	display: flex;
}

#address1,
#address2 {
	width: 100%;
}
#zipcode {
	flex: 1;
}

.field_address__wrapper {
	width: 100%;
}


.member_join th {
    width: 159px;
    padding: 20px 0 0 20px;
    font-weight: 700;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    vertical-align: top;
    text-align: left;
}

.member_join input[type=text], .member_join input[type=password] {
    width: 332px;
}
.type_form textarea, .type_form input[type=text], .type_form input[type=password] {
    height: 44px;
    padding: 0 14px;
    border: 1px solid #ccc;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    border-radius: 3px;
    background: #fff;
    outline: none;
    vertical-align: top;
}
input {
    line-height: normal;
}
button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    margin: 0;
}
body, input, select, textarea, button {
    font-family: noto sans,malgun gothic,AppleGothic,dotum;
    line-height: 1;
    letter-spacing: -.05em;
    color: #4c4c4c;
    font-size: 12px;
    max-width: 100%;
}
p {
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}



body, input, select, textarea, button {
    font-family: noto sans,malgun gothic,AppleGothic,dotum;
    line-height: 1;
    letter-spacing: -.05em;
    color: #4c4c4c;
    font-size: 12px;
    max-width: 100%;
}

input[type="text" i] {
    padding: 1px 2px;
}

input {
    writing-mode: horizontal-tb !important;
    font-style: ;
    font-variant-ligatures: ;
    font-variant-caps: ;
    font-variant-numeric: ;
    font-variant-east-asian: ;
    font-variant-alternates: ;
    font-weight: ;
    font-stretch: ;
    font-size: ;
    font-family: ;
    font-optical-sizing: ;
    font-kerning: ;
    font-feature-settings: ;
    font-variation-settings: ;
    text-rendering: auto;
    color: fieldtext;
    letter-spacing: normal;
    word-spacing: normal;
    line-height: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: start;
    appearance: auto;
    -webkit-rtl-ordering: logical;
    cursor: text;
    background-color: field;
    margin: 0em;
    padding: 1px 2px;
    border-width: 2px;
    border-style: inset;
    border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
    border-image: initial;
}
div, th, td, li, dt, dd, p {
    word-break: break-all;
}



.member_join td {
    padding: 10px 0;
    border-top: 0;
    font-size: 14px;
    vertical-align: top;
    text-align: left;
}

table {
    border-collapse: collapse;
    border-spacing: 0;
}


table {
    border-collapse: separate;
    text-indent: initial;
    border-spacing: 2px;
}
html {
    font-family: sans-serif;
    -ms-text-size-adjust: 100%;
    -webkit-text-size-adjust: 100%;
}

*, *:after, *:before {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

body {
    display: block;
    margin: 8px;
}
.member_join .btn {
    display: inline-block;
    width: 120px;
    margin-left: 5px;
    vertical-align: top;
}

body, input, select, textarea, button {
    font-family: noto sans,malgun gothic,AppleGothic,dotum;
    line-height: 1;
    letter-spacing: -.05em;
    color: #4c4c4c;
    font-size: 12px;
    max-width: 100%;
}
.member_join #addressSearch {
    display: inline-block !important;
   	margin-left: -2px !important;
 }
 .member_join #addressSearch {
    display: block;
    overflow: hidden;
    width: 332px;
    height: 44px;
    border: 1px solid #5f0080;
    border-radius: 3px;
    text-align: center;
}

#zipcode {
	width: 181px !important;
}

#addressSearch {
	width: 150px !important;
}

.type_form .btn {
    display: block;
    width: 100%;
    height: 44px;
    border: 0;
    border-radius: 3px;
    font-weight: 700;
    font-size: 14px;
    line-height: 40px;
    text-align: center;
    outline: none;
}

#addressNo{
cursor : pointer;
}

.type_form {

	
	width: 100%;
	margin: 0 auto;	

}
#mobile1, #mobile2, #mobile3 {
    height: 44px;
    width: 110px;
    padding: 0 14px;
    border: 1px solid #ccc;
    font-size: 14px;
    color: #333;
    line-height: 20px;
    border-radius: 3px;
    background: #fff;
    outline: none;
    vertical-align: top;
}
.member_join .form_footer .btn_join {
    width: 240px;
    height: 56px;
    font-size: 16px;
    line-height: 54px;
  
}

.member_join th .ico {
    color: #ee6a7b;
}
.member_join .page_sub {
    padding-bottom: 10xp;
    font-size: 12px;
    color: #666;
    line-height: 17px;
    text-align: right;
    
}

.tit_page h2.tit {
    font-weight: 700;
    font-size: 28px;
    color: #333;
    line-height: 35px;
    text-align: center;
    letter-spacing: -1px;
}
.tit_page {
    overflow: hidden;
    width: 100%;
    margin: 0 auto;
    padding: 20px 0px 0px;
}

#idCheck.btn.default, #emailCheck.btn.default {
    cursor: pointer;
    margin-left: 10px;
    padding: 2px 10px;
    border: 1px solid purple;
}
.type_form .btn {
    display: block;
    width: 100%;
    height: 44px;
    border: 0;
    border-radius: 3px;
    font-weight: 700;
    font-size: 14px;
    line-height: 40px;
    text-align: center;
    outline: none;
}
a {
    background-color: transparent;
    text-decoration: none;
    color: inherit;
}

table {
    border-collapse: collapse;
    border-spacing: 0;
}
.member_join .btn {
    display: inline-block;
    width: 120px;
    margin-left: 5px;
    vertical-align: top;
    padding-bottom: 50px;
    text-align: center;
    
}
.type_form .btn.default {
    border: 1px solid #5f0080;
    background-color: #fff;
    color: #5f0080;
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
.member_join #addressNo {
    display: inline-block;
    height: 100%;
    margin-left: -14px;
    padding-left: 24px;
    font-weight: 700;
    font-size: 14px;
    color: #5f0080;
    line-height: 40px;
    background: url(https://res.kurly.com/pc/service/common/2006/ico_search.svg) no-repeat 0 50%;
    background-size: 25px 24px;
    vertical-align: top;
}
.type_form .btn.active {
    border: 1px solid #5f0081;
    background-color: #5f0080;
    color: #fff;
}
.member_join .form_footer {
    padding-top: 40px;
    padding-bottom: 40px;
    text-align: center;
}

.type_form input[type="radio"] {
	position: relative;
	opacity: 1.0;
	z-index: unset;
}


</style>
<script type="text/javascript">
function memberOk() {
	const f = document.memberForm;
	let str;

	/* str = f.memberId.value;
	if( !/^[a-z][a-z0-9_]{4,9}$/i.test(str) ) { 
		alert("아이디를 다시 입력 하세요. ");
		f.memberId.focus();
		return;
	} */

	/* str = f.before_pwd.value;
	if( !/^(?=.*[a-z])(?=.*[!@#$%^*+=-]|.*[0-9]).{5,10}$/i.test(str) ) { 
		alert("패스워드를 다시 입력 하세요. ");
		f.before_pwd.focus();
		return;
	} */
	
	str = f.after_pwd.value;
	if( !/^(?=.*[a-z])(?=.*[!@#$%^*+=-]|.*[0-9]).{5,10}$/i.test(str) ) { 
		alert("패스워드를 다시 입력 하세요. ");
		f.after_pwd.focus();
		return;
	}

	if( str !== f.re_pwd.value ) {
        alert("패스워드가 일치하지 않습니다. ");
        f.re_pwd.focus();
        return;
	}
	
    str = f.name.value;
    if( !/^[가-힣]{2,5}$/.test(str) ) {
        alert("이름을 다시 입력하세요. ");
        f.name.focus();
        return;
    }
    
    str = f.email.value;
    if( !str ) {
        alert("이메일을 입력하세요. ");
        f.email.focus();
        return;
    }
    
    str = f.tel1.value;
    if( !str ) {
        alert("전화번호 앞 3자리를 입력하세요. ");
        f.tel1.focus();
        return;
    }

    str = f.tel2.value;
    if( !/^\d{3,4}$/.test(str) ) {
        alert("전화번호 가운데 4자리를 입력하세요. ");
        f.tel2.focus();
        return;
    }

    str = f.tel3.value;
    if( !/^\d{4}$/.test(str) ) {
    	alert("전화번호 마지막 4자리를 입력하세요. ");
        f.tel3.focus();
        return;
    }
   
   	f.action = "${pageContext.request.contextPath}/mypage/update_ok.do";
    f.submit();
    
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
	<div class="container body-container2">
		<div class = "abc">
			<div class = "mypage-left-subject"><h2>마이푸딧</h2></div>
				<div class ="mypage-selectbox">
					<ul class="mypage-ul">
						<li><a href ="${pageContext.request.contextPath}/mypage/modify_mypage.do">주문내역</a></li>				
						<li><a>장바구니</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/modify_checkPw.do">개인정보수정</a></li>				
						<li><a href ="${pageContext.request.contextPath}/mypage/addrmanage.do">배송지관리</a></li>				
						<li><a>내가쓴후기</a></li>				
					</ul>
				</div>
		</div>
		<div class="mypage-right">		
			<div class ="mypage-right-subject">
				<h2> 개인정보수정 </h2> <span style="font-size : 14px;"></span>
				
			</div>
					<div class ="mypage-right-content">
						<div class="page_article" >
							<div class="type_form member_join">
						<form method="post" name = "memberForm">
							<input type="hidden" name="idCheckResult" id="idCheckResult" value="0"> 
							<input type="hidden" name="emailCheckResult" id="emailCheckResult" value="0">
							<table class="tbl_comm">
								<tr class="fst">
									<th>아이디<span class="ico">*</span></th>
									<td>
										<input type="text" name="memberId" id="id" value = "${username}" readonly>
								</tr>
								<tr>
									<th>현재 비밀번호<span class="ico">*</span></th>
									<td>
										<input type="password" name="before_pwd" id="password" placeholder="비밀번호를 입력해주세요">
									</td>
								</tr>
								<tr>
									<th>변경할 비밀번호<span class="ico">*</span></th>
									<td>
										<input type="password" name="after_pwd" id="password" placeholder="비밀번호를 입력해주세요">
									</td>
								</tr>
								<tr class="member_pwd">
									<th>비밀번호 확인<span class="ico">*</span></th>
									<td>
										<input type="password" name="re_pwd" id ="repassword"placeholder="비밀번호를 한번 더 입력해주세요">
									</td>
								</tr>
								<tr>
									<th>이름<span class="ico">*</span></th>
									<td>
										<input type="text" name="name" id="name" 	placeholder="이름을 입력해주세요">
								</tr>
								<tr>
									<th>이메일<span class="ico">*</span></th>
									<td>
										<input type="text" name="email" class="email" id="email" placeholder="예: marketkurly@kurly.com">
									<span class="btn default" id="emailCheck">중복확인</span>
									</td>
								</tr>
								<tr class="field_phone">
									<th>휴대폰<span class="ico">*</span></th>
									<td>
									<input type="text" name="tel1" id="mobile1" maxlength="3" >
									<input type="text" name="tel2" id="mobile2" maxlength="4" >
									<input type="text" name="tel3" id="mobile3" maxlength="4" >
								</tr>
								<tr>
									<th>성별<span class="ico">*</span></th>
										<td>
										 	<div style="padding-top: 7px;">
												<label for="male" style="padding:0">
													<input style="margin-right: 5px;" type="radio" name="gender" id="male" value="1" checked />남자 
												</label>
												<label for="female">
													<input style="margin-right: 5px;" type="radio" name="gender" id="female" value="0" />여자 
												</label>
											</div>
											
										</td>
								</tr>
								</tbody>
							</table>
							<div id="formSubmit" class="form_footer">
								<button type="button" class="btn active btn_join" onclick="memberOk();">회원정보수정</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>
</body>
</html>
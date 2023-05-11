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

button {
	background-color: transparent;
	border: 0px solid transparent;
	outline: none;
	padding: 0;
}

button.btn.active.btn_join {
	line-height: 46px !important;
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

	
	width: 817px;
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


</head>
<body>

<header>
    <jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
</header>
	
<main>


		
		
<div class="tit_page" >
	<h2 class="tit">회원가입</h2>
</div>

<div class="page_article" >
	<div class="type_form member_join">
		<form action="index.jsp?folder=login&category=join_action" method="post" id="join">
			<input type="hidden" name="idCheckResult" id="idCheckResult" value="0"> 
			<input type="hidden" name="emailCheckResult" id="emailCheckResult" value="0">
			<p class="page_sub">
				<span class="ico">*</span>필수입력사항
			</p>
			<hr>
			<table class="tbl_comm" style="margin:30px 0; border:none	">
				<tr class="fst">
					<th>아이디<span class="ico">*</span></th>
					
					<td>
						<input type="text" name="id" id="id" 	placeholder="6자 이상의 영문 혹은 영문과 숫자를 조합"> 
					<button class="btn default" id="emailCheck">중복확인</button></td>
				</tr>
				<tr>
					<th>비밀번호<span class="ico">*</span></th>
					<td>
						<input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요">
					</td>
				</tr>
				<tr class="member_pwd">
					<th>비밀번호확인<span class="ico">*</span></th>
					<td>
						<input type="password" name="repassword" id ="repassword"placeholder="비밀번호를 한번 더 입력해주세요">
					</td>
				</tr>
				<tr>
					<th>이름<span class="ico">*</span></th>
					<td>
						<input type="text" name="name" id="name" 	placeholder="이름을 입력해주세요">
				</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
					 	<div style="padding-top: 7px;">
							<label for="male">
								<input type="radio" name="gender" id="male" value="1" checked  />남자 
							</label>
							<label for="female">
								<input type="radio" name="gender" id="female" value="0" />여자 
							</label>
						</div>
						
					</td>
				</tr>
				
						<tr>
						<th>이메일<span class="ico">*</span></th>
					<td>
						<input type="text" name="email" class="email" id="email" placeholder="예: marketkurly@kurly.com">
					<button class="btn default" id="emailCheck">중복확인</button>
					</td>
				</tr>
				<tr class="field_phone">
					<th>휴대폰<span class="ico">*</span></th>
					<td>
					<input type="text" name="mobile1" id="mobile1" maxlength="3" >
					<input type="text" name="mobile2" id="mobile2" maxlength="4" >
					<input type="text" name="mobile3" id="mobile3" maxlength="4" >
				</tr>
				<tr>
					<th>주소<span class="ico">*</span></th>
					<td class="field_address">
						<div class="field_address__wrapper">
							<div id="wrapper">
								<input type="text" name="zipcode" id="zipcode" size="7"
									readonly="readonly" placeholder="번지를 검색해 주세요."> 
								<a id="addressSearch" class="search"> 
								<button id="addressNo" class="address_no" data-text="재검색">주소 검색</button>
								</a>
							</div>
							<input type="text" name="address1" id="address1"
								readonly="readonly"  placeholder="주소를 검색해주세요."> 
							<input type="text" name="address2" id="address2" placeholder="나머지 주소를 입력해주세요">
							
						</div>
					</td>
					
				</tr>
				</tbody>
			</table>
			<div id="formSubmit" class="form_footer">
				<button type="submit" class="btn active btn_join">가입하기</button>
			</div>
		</form>
	</div>
</div>
</main>

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
                document.getElementById('zip').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('addr1').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('addr2').focus();
            }
        }).open();
    }
</script>

<script type="text/javascript">
function memberOk() {
	const f = document.memberForm;
	let str;

	str = f.userId.value;
	if( !/^[a-z][a-z0-9_]{4,9}$/i.test(str) ) { 
		alert("아이디를 다시 입력 하세요. ");
		f.userId.focus();
		return;
	}

	str = f.userPwd.value;
	if( !/^(?=.*[a-z])(?=.*[!@#$%^*+=-]|.*[0-9]).{5,10}$/i.test(str) ) { 
		alert("패스워드를 다시 입력 하세요. ");
		f.userPwd.focus();
		return;
	}

	if( str !== f.userPwd2.value ) {
        alert("패스워드가 일치하지 않습니다. ");
        f.userPwd.focus();
        return;
	}
	
    str = f.userName.value;
    if( !/^[가-힣]{2,5}$/.test(str) ) {
        alert("이름을 다시 입력하세요. ");
        f.userName.focus();
        return;
    }

    str = f.birth.value;
    if( !str ) {
        alert("생년월일를 입력하세요. ");
        f.birth.focus();
        return;
    }
    
    str = f.tel1.value;
    if( !str ) {
        alert("전화번호를 입력하세요. ");
        f.tel1.focus();
        return;
    }

    str = f.tel2.value;
    if( !/^\d{3,4}$/.test(str) ) {
        alert("숫자만 가능합니다. ");
        f.tel2.focus();
        return;
    }

    str = f.tel3.value;
    if( !/^\d{4}$/.test(str) ) {
    	alert("숫자만 가능합니다. ");
        f.tel3.focus();
        return;
    }
    
    str = f.email1.value.trim();
    if( !str ) {
        alert("이메일을 입력하세요. ");
        f.email1.focus();
        return;
    }

    str = f.email2.value.trim();
    if( !str ) {
        alert("이메일을 입력하세요. ");
        f.email2.focus();
        return;
    }

   	f.action = "${pageContext.request.contextPath}/member/${mode}_ok.do";
    f.submit();
}

function changeEmail() {
    const f = document.memberForm;
	    
    let str = f.selectEmail.value;
    if(str !== "direct") {
        f.email2.value = str; 
        f.email2.readOnly = true;
        f.email1.focus(); 
    }
    else {
        f.email2.value = "";
        f.email2.readOnly = false;
        f.email1.focus();
    }
}

</script>

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/>
</body>
</html>
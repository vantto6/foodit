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
@charset "UTF-8";

.css-140915z {
    min-width: 1050px;
    padding: 50px 0;
    background-color: white;
}

.css-7500ra {
    padding-bottom: 30px;
    font-weight: 500;
    font-size: 28px;
    text-align: center;
}

.css-u3y03b {
    max-width: 400px;
    padding: 0 10px 6px 10px;
    margin: auto;
    position: relative;
    background-color: white;
}

.css-1izr46f {
    padding: 0;
    margin: 0 20px;
}

.css-1vhrqx7 {
    display: block;
    -webkit-box-flex: 1;
    -webkit-flex-grow: 1;
    -ms-flex-positive: 1;
    flex-grow: 1;
    position: relative;
    height: 48px;
    font-weight: 400;
    font-size: 16px;
    color: #666;
    line-height: 18px;
}

.css-1izr46f {
    background-color: white;
    padding: 0 15px 0 15px;
    margin: 0;
    box-shadow: inset 0 -0.5px 0 0 #ddd;
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-flex-wrap: nowrap;
    -webkit-flex-wrap: nowrap;
    -ms-flex-wrap: nowrap;
    flex-wrap: nowrap;
    text-align: center;
}

.css-s3iz85 {
    padding: 24px 20px;
}

.css-1yjqrpx {
    padding-bottom: 12px;
}

.css-c3g9of {
    display: inline-block;
    padding: 8px 0 11px;
    font-size: 14px;
    font-weight: 500;
    line-height: 19px;
    color: #333;
}

.css-176lya2 {
    position: relative;
    height: 48px;
}

.css-upmixo {
    width: 100%;
    height: 46px;
    padding: 0 11px 1px 15px;
    border-radius: 4px;
    border: 1px solid #ddd;
    font-weight: 400;
    font-size: 16px;
    line-height: 1.5;
    color: #333;
    outline: none;
}

.css-1blp8ou:not(:first-of-type) {
    margin-top: 8px;
}

.css-1yjqrpx {
    padding-bottom: 12px;
}

.css-c3g9of {
    display: inline-block;
    padding: 8px 0 11px;
    font-size: 14px;
    font-weight: 500;
    line-height: 19px;
    color: #333;
}

.css-3vxi16 {
    margin-top: 18px;
}

.css-1s9rhb5:disabled {
    background-color: #ddd;
}

.css-1s9rhb5 {
    height: 56px;
}

.css-1s9rhb5, button {
    display: block;
    padding: 0 10px;
    text-align: center;
    overflow: hidden;
    width: 100%;
    height: 52px;
    border-radius: 4px;
    color: #fff;
    background-color: #88b04B;
    border: 0 none;
}

.css-1dqhxzp {
    display: inline-block;
    font-size: 16px;
    margin-top: 18px !important;
}

.btn-submit:hover {
	cursor:pointer;
	filter:brightness(80%);
}

</style>

</head>
<body>


	
<main>

<div id = "content">
		
	

	<div class="css-140915z e13dlrpy2">
		<div class="css-7500ra e13dlrpy0">비밀번호 찾기</div>
		<div class="css-u3y03b e13dlrpy1">
			
			<form id ="findForm" class="css-s3iz85 e1h5g482" action = "index.jsp?folder=login&category=Find_id_action" method = "post">
				<div class="css-1blp8ou e1h5g481">
					<div class="css-1yjqrpx e1uzxhvi4">
						<label for="name" class="css-c3g9of e1uzxhvi2" >이름</label>
						<div class="css-176lya2 e1uzxhvi1">
							<input id="name" name="name" placeholder="이름을 입력해 주세요" type="text"
								class="css-upmixo e1uzxhvi0" >
						</div>
					</div>
				</div>
				<div class="css-1blp8ou e1h5g481">
					<div class="css-1yjqrpx e1uzxhvi4">
						<label for="email" class="css-c3g9of e1uzxhvi2">이메일</label>
						<div class="css-176lya2 e1uzxhvi1">
							<input  id="email" name="email"	placeholder="이메일을 입력해 주세요" type="email"
								class="css-upmixo e1uzxhvi0" >
						</div>
					</div>
				</div>
				<div id="message"></div>
				<div class="css-3vxi16 e1h5g480 btn-submit">
					<div class="css-1s9rhb5 e4nu7ef2"  id= "clear" style="display:flex; flex-direction:column; justify-content:center" onclick="hello()" >
						<div>확인</div>	
						
					</div>
				</div>
			</form>
		</div>
		</div>
		</div>
		</main>
		
<script type="text/javascript">

	function hello() {
		let str = $('#name').val();
		
		if (!str) {
			alert("이름 입력하세요. ");
			return;
		}
		 if( !/^[가-힣]{2,5}$/.test(str) ) {
		   alert("이름이 올바르지 않습니다. ");
		        return;
		 }
		
		let email = $("#email").val();
		var valid_txt = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		if (email == "") { 
			alert('이메일을 입력해주세요.');

			return;
		}
		if (valid_txt.test(email) == false) {

			alert("이메일 주소가 올바르지 않습니다.");
			$("#email").focus();

			return;
		}
		f.action = "${pageContext.request.contextPath}/member/_ok.do";
		f.submit();
	}

	
</script>


</body>
</html>
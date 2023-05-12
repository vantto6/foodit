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

.mypage-right {margin-left : 100px; width : 600px; height 300px; float : left; 1px solid black;}
.mypage-right-subject { height : 50px ; border-bottom : 2px solid black;}
.mypage-right-content { height : 300px; }

.mypage-left-subject { width : 150px; height : 50px; text-align : center; vertical-align: center;}

.abc {witdh : 120px; height : 300px; float: left;}
.body-container2 {
	height: 900px;
}
</style>
<script type="text/javascript">

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
						<li><a>주문내역</a></li>				
						<li><a>장바구니</a></li>				
						<li><a>개인정보수정</a></li>				
						<li><a>배송지관리</a></li>				
						<li><a>내가쓴후기</a></li>				
					</ul>
				</div>
		</div>
		<div class="mypage-right">		
			<div class ="mypage-right-subject">
				<h2> 개인정보수정 </h2> <span style="font-size : 14px;"></span>
				
			</div>
			
			<div class ="mypage-right-content margin-top10">
				<div class="page_article">
					<div class="type_form member_join">
						<form action="index.jsp?folder=login&category=join_action" method="post" id="join">
							<input type="hidden" name="idCheckResult" id="idCheckResult" value="0"> 
							<input type="hidden" name="emailCheckResult" id="emailCheckResult" value="0">
							<table class="tbl_comm" style="margin:30px;">
								<tr class="fst">
									<th>아이디<span class="ico">*</span></th>
									
									<td>
										<input type="text" name="id" id="id" 	placeholder="아이디 가져와야함">
								</tr>
								<tr>
									<th>현재 비밀번호<span class="ico">*</span></th>
									<td>
										<input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요">
									</td>
								</tr>
								<tr>
									<th>변경할 비밀번호<span class="ico">*</span></th>
									<td>
										<input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요">
									</td>
								</tr>
								<tr class="member_pwd">
									<th>비밀번호 확인<span class="ico">*</span></th>
									<td>
										<input type="password" name="repassword" id ="repassword"placeholder="비밀번호를 한번 더 입력해주세요">
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
									<input type="text" name="mobile1" id="mobile1" maxlength="3" >
									<input type="text" name="mobile2" id="mobile2" maxlength="4" >
									<input type="text" name="mobile3" id="mobile3" maxlength="4" >
								</tr>
								</tbody>
							</table>
							<div id="formSubmit" class="form_footer">
								<button type="submit" class="btn active btn_join">회원정보수정</button>
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
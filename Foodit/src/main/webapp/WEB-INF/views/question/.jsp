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
	max-width: 1000px;
}

#perInquery_list {
	min-height: 800px;
	width: 900px;
	margin: 0 auto;
	text-align: center;
	width: 900px;
}

#perInquery_title {
	font-size: 1.8em;
	font-weight: bold;
	text-align: left;
}

#num {
	font-size: 1.1em;
	color: #646464;
}

table {
	margin: 20px auto;
	border-collapse: collapse;
}

th {
	border-top: 1px solid black;
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

.subject {
	text-align: left;
	padding: 5px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

#perInquery_list a:hover {
	text-decoration: none;
	color: red;
}

.sub {
	color: #828282;
	font-size: 0.6em;
	padding: 20px;
}

.btn {
	color: #333333;
	border: 1px solid #999999;
	background-color: #ffffff;
	padding: 5px 10px;
	border-radius: 4px;
	font-weight: 500;
	cursor: pointer;
	font-size: 14px;
	font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
	vertical-align: baseline;
	width: 80px;
	height: 45px;
	border: none;
}

.btn_right {
	display: flex;
	justify-content: end;
	text-align: center;
	margin-right: 60px;
}

.btn_left {
	display: flex;
	justify-content: start;
	text-align: center;
	margin-right: 60px;
}

.btn:active, .btn:focus, .btn:hover {
	background-color: #f8f9fa;
	color: #333333;
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
	width: 1050px;
	margin: 0 auto;
	padding: 50px 0 51px;
}

.sub_page {
	overflow: hidden;
	width: 1050px;
	margin: 0 auto;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: none;
	padding-right: none;
}

.table-container {
	text-align: center;
}

input[type=checkbox] {
	opacity: 1;
	position: relative;
	z-index: 10;
}

.write-container {
	min-height: 500px;
	letter-spacing: 2px;
	width: 800px;
	margin: 0 auto;
}
</style>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.2/themes/smoothness/jquery-ui.css"
	type="text/css" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.8.8/i18n/jquery.ui.datepicker-ko.js"></script>

<script type="text/javascript">
	$(function() {
		// $("#accordion").accordion();

		// 처음에 활성화되지 않고 활성화된것 누르면 닫히도록
		$("#accordion").accordion({
			active : false,
			collapsible : true
		});

		// $("#accordion").accordion("option","active",1);
	});
</script>

</head>
<body>


	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
			<div class="tit_page">
				<h2 class="tit">자주하는질문</h2>
			</div>
			<br>
		<div class="write-container">
			<div class="container">
				<div id="accordion">
					<h3>교환(반품) 진행 시, 배송비가 부과 되나요?</h3>
					<div>
						<strong > ■ 교환(반품) 진행 시, 배송비 안내 <br>
						</strong>
						<div>
							- 단순변심에 의한 교환/반품 시 배송비 6,000원 (주문건 배송비를 낸 경우 3,000원)을 고객님께서
							부담하셔야합니다.<br> - 파트너사 판매상품의 경우, 상품의 상세페이지 내 안내 정책을 참고 부탁드립니다.
						</div>

						<strong> ▶신선/냉장/냉동 식품 <br>
						</strong>

						<div>

							재판매가 불가한 상품의 특성상, 단순변심/주문 착오 시 교환 및 반품이 어려운 점 양해 부탁드립니다.<br>

							상품에 따라 조금씩 맛이 다를 수 있으며, 개인의 기호에 따라 같은 상품도 다르게 느끼실 수 있습니다.
						</div>
						<strong> ▶유통기한 30일 이상 식품 (신선/냉장/냉동 제외) 및 기타 상품<br>
						</strong>

						<div>
							상품을 받은 날부터 7일 이내에 고객행복센터로 문의 부탁드립니다.
						</div>

					</div>
					<h3>■ 아이디, 비밀번호 찾기 안내</h3>
					<div>

					
					- 하기 경로를 통해 아이디 및 비밀번호 찾기가 가능하며, 임시 비밀번호의 경우 회원가입 시 등록한 이메일 주소로 발송 됩니다.
					
					
					(PC) 컬리홈 상단 [로그인] > 화면 아래 [아이디 찾기] [비밀번호 찾기]
					
					(모바일) 아래 탭에서 [마이컬리] >  로그인 화면 아래 [아이디 찾기] [비밀번호 찾기]
					
					
					[참고]
					
					▣ 가입시 기재한 메일 주소가 기억나지 않으시거나 오류가 발생하는 경우 고객센터로 문의 바랍니다.
					
					▣ 상담시에는 고객님의 개인정보보호를 위해 기존에 사용하시던 비밀번호는 안내가 불가하며, 개인정보 확인 후 임시비밀번호를 설정해드립니다.
					</div>
					<h3>■ 시스템 이상 확인 시</h3>
					<div>
						
						① 컬리APP의 최신 업데이트 버전 여부 확인해주세요
						② 업데이트 버전이 가장 최신일 경우에는 아래와 같이 점검해주세요.
						
						(PC) 모든 인터넷창을 종료 > 컬리 홈페이지 재접속 > 재로그인 진행
						(모바일) 웹 - 사용하는 브라우저에서 탭/창 닫기 > 컬리 홈페이지 재접속 > 재로그인 진행
						(모바일) 앱 - 앱 종료 > 재 로그인 진행
						
						※ 해당 이슈들은 최근 개선되었으나 재발생 시 자체 점검하시어 고객센터로 문의 부탁드립니다.
						
						▣ 재로그인/재접속 이후에도 동일한 문제가 발생하시는 경우 고객센터로 하기 정보와 함께 문의 바랍니다.
						- 오류 발생 플랫폼 정보: 앱 (안드로이드), 앱 (iOS), 모바일 웹, PC
						- 모바일인 경우
						ㄴ 기기 정보 ( PC인 경우: 윈도우 정보 )
						ㄴ버전정보 ( PC인 경우: 브라우저 정보 - 예, 인터넷 익스플로러, 크롬, 파이어폭스, 사파리 등) 
						- 구체적 오류 내용. 어느 화면에서 어떤 부분을 눌렀을 때 어떤 현상이 발생되는지 상세한 설명과 화면 캡쳐해주시면 많은 도움이 됩니다.
						</div>
					<h3>javascript 란 ?</h3>
					<div>javascript는 ...</div>
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
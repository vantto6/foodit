<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/WEB-INF/views/layout/staticHeader.jsp" />
</head>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

</head>
<style>
.third_slick button.slick-prev {
	position: absolute;
	z-index: 10;
	top: 40%;
	left: -27px;
	transform: translateY(-50%);
	width: 52px;
	height: 52px;
	border-radius: 100%;
	opacity: 1;
	border: none;
	color: transparent;
	background:
		url(https://s3.ap-northeast-2.amazonaws.com/res.kurly.com/kurly/ico/2021/arrow_list_left_60_60.svg)
		50% 50% no-repeat;
}

.third_slick button.slick-next {
	position: absolute;
	z-index: 10;
	top: 40%;
	right: -15px;
	transform: translateY(-50%);
	width: 52px;
	height: 52px;
	border-radius: 100%;
	color: transparent;
	opacity: 1;
	border: none;
	background:
		url(https://s3.ap-northeast-2.amazonaws.com/res.kurly.com/kurly/ico/2021/arrow_list_right_60_60.svg)
		50% 50% no-repeat;
}

.product img:hover {
	transform: scale(1.02);
	transition: all .3s ease-in-out;
	cursor: pointer;
}

.recommend-head, .recommend-container {
	padding-bottom: 60px;
	padding-top: 30px
}

.original-price {
	padding-top: 1px;
	color: rgb(181, 181, 181);
	font-size: 10px;
	font-weight: 400;
	line-height: 18px;
	text-decoration: line-through;
}

.container_banner {
	max-width: 1900px;
	margin: 0px auto;
}

.slick_slide button.slick-prev {
	position: absolute;
	z-index: 10;
	top: 50%;
	left: 50px;
	width: 52px;
	height: 52px;
	transform: translateY(-50%);
	border-radius: 100%;
	opacity: 1;
	border: none;
	color: transparent;
	background:
		url(https://s3.ap-northeast-2.amazonaws.com/res.kurly.com/kurly/ico/2021/arrow_banner_left_52_52.svg)
		50% 50% no-repeat;
}

.slick_slide button.slick-next {
	position: absolute;
	z-index: 10;
	top: 50%;
	right: 50px;
	transform: translateY(-50%);
	width: 52px;
	height: 52px;
	border-radius: 100%;
	color: transparent;
	opacity: 1;
	border: none;
	background:
		url(https://s3.ap-northeast-2.amazonaws.com/res.kurly.com/kurly/ico/2021/arrow_banner_right_52_52.svg)
		50% 50% no-repeat;
	cursor: pointer;
}
</style>
<script type="text/javascript">
	function sendNewList() {

	}
</script>
<body>

	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div class="container body-container">
			<div class="inner-page mx-auto">
				<div id="content">
					<div class="recommend-container">
						<div class="recommend-head">
							<span class="head">&nbsp;주문이 완료되었습니다.&nbsp;</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" /></body>
</html>
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

.recommend-head, .recommend-container{
	padding-bottom: 60px;
	padding-top:30px
}
 

</style>

<body>

	<header>
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</header>

	<main>
		<div class="container body-container" >
			<div class="inner-page mx-auto">
				<div id="content">
					<div class="recommend-container">
						<div class="recommend-head">
							<span class="head">오늘의 특가</span>
						</div>

						<div class="slick-slider13">

							<c:forEach var="a" begin="1" end="10">
								<div class="container">

									<div class="product_container">
										<div class="product">
											<img class="product_img" src="/sambab/product_image/장어.JPG"
												onclick="javascript:location.href='index.jsp?folder=product&category=Product_details&p_no=36">
											<img
												onclick="javascript:location.href='index.jsp?folder=product&category=Product_details&p_no=36'"
												class="cart"></img>

										</div>
										<div class="description_list">
											<h3>
												<a
													href="index.jsp?folder=product&category=Product_details&p_no=36"
													class="product_name">국내산 손질 민물장어 600g 특미 2미 (생물)</a>
											</h3>
											<div class="product_price">
												<div class="product_calcu">
													<div class="coupon">0%</div>
													<div class="price">
														₩52,900<span>원</span>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>

					</div>
				</div>

				<script>
						$('.slick-slider13').slick({
							  dots: true,
							  infinite: false,
							  speed: 300,
							  slidesToShow: 4,
							  slidesToScroll: 4,
							  responsive: [
							    {
							      breakpoint: 1024,
							      settings: {
							        slidesToShow: 3,
							        slidesToScroll: 3,
							        infinite: true,
							        dots: true
							      }
							    },
							    {
							      breakpoint: 600,
							      settings: {
							        slidesToShow: 2,
							        slidesToScroll: 2
							      }
							    },
							    {
							      breakpoint: 480,
							      settings: {
							        slidesToShow: 1,
							        slidesToScroll: 1
							      }
							    }
							   
							  ]
							});
					
						</script>

				<div class="recommend-container" >
					<div class="recommend-head">
						<span class="head">많이본 상품</span>
					</div>
					<div class="third_slick">
						<c:forEach var="a" begin="1" end="10">

							<div class="container mx-auto">

								<div class="product_container">
									<div class="product">
										<img
											onclick="javascript:location.href='index.jsp?folder=product&category=Product_details&p_no=41'"
											class="product_img" src="/sambab/product_image/레몬.JPG" /> <img
											class="cart"></img>

									</div>
									<div class="description_list">
										<h3>
											<a
												href="index.jsp?folder=product&category=Product_details&p_no=41"
												class="product_name">[썬키스트] 팬시 레몬 130g (1개)</a>
										</h3>
										<div class="product_price">
											<div class="product_calcu">
												<div class="coupon">0%</div>
												<div class="price">
													₩1,250<span>원</span>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</c:forEach>
					</div>
				</div>
				<script>
						$('.third_slick').slick({
							  dots: true,
							  infinite: false,
							  speed: 300,
							  slidesToShow: 4,
							  slidesToScroll: 4,
							  responsive: [
							    {
							      breakpoint: 1024,
							      settings: {
							        slidesToShow: 3,
							        slidesToScroll: 3,
							        infinite: true,
							        dots: true
							      }
							    },
							    {
							      breakpoint: 600,
							      settings: {
							        slidesToShow: 2,
							        slidesToScroll: 2
							      }
							    },
							    {
							      breakpoint: 480,
							      settings: {
							        slidesToShow: 1,
							        slidesToScroll: 1
							      }
							    }
							   
							  ]
							});
					
				</script>
			</div>
		</div>
	</main>

	<footer>
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</footer>

	<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp" /></body>
</html>
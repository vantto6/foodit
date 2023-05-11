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
</head>
<body>

<header>
    <jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
</header>

<main>
	<div class="things">
	    <div class="content">
	    	<div class= "banner">
	    		<img src="add_photo.png" onclick = "">
	    	</div>
	    	<div class= "category">
	    		<h3 class= "tit">카테고리명</h3>
	    	</div>
			<ul class="list on">
				<li><a href="#">전체</a></li>
				<li><a href="#">야채/과일</a></li>
				<li><a href="#">해/수산물</a></li>
				<li><a href="#">정육</a></li>
			</ul>
			<div>
			</div>
			
	    
		    <div class="goodslist">			
				<div class="item">
					<div class="thumb">
						<a class="img">
							 <img src="add_photo.png" onclick = "">
						</a>
						
						<div class="group_btn">
							<button type="button" onclick = "" class="btn btn_cart">
								<span class="screen_out"></span>
							</button>
						</div>
					</div>
					<a class="info">
						<span class="name">상품이름</span> 
						<span class="price">상품가격</span> 
						<span class="tag"></span>
					</a>
				</div>
				<div class="item">
					<div class="thumb">
						<a class="img">
							 <img src="add_photo.png" onclick = "">
						</a>
						
						<div class="group_btn">
							<button type="button" onclick = "" class="btn btn_cart">
								<span class="screen_out"></span>
							</button>
						</div>
					</div>
					<a class="info">
						<span class="name">상품이름</span> 
						<span class="price">상품가격</span> 
						<span class="tag"></span>
					</a>
				</div>
					
				<div class="item">
					<div class="thumb">
						<a class="img">
							 <img src="add_photo.png" onclick = "">
						</a>
						
						<div class="group_btn">
							<button type="button" onclick = "" class="btn btn_cart">
								<span class="screen_out"></span>
							</button>
						</div>
					</div>
					<a class="info">
						<span class="name">상품이름</span> 
						<span class="price">상품가격</span> 
						<span class="tag"></span>
					</a>
				</div>
					
				<div class="item">
					<div class="thumb">
						<a class="img">
							 <img src="add_photo.png" onclick = "">
						</a>
						
						<div class="group_btn">
							<button type="button" onclick = "" class="btn btn_cart">
								<span class="screen_out"></span>
							</button>
						</div>
					</div>
					<a class="info">
						<span class="name">상품이름</span> 
						<span class="price">상품가격</span> 
						<span class="tag"></span>
					</a>
				</div>
					
				<div class="item">
					<div class="thumb">
						<a class="img">
							 <img src="add_photo.png" onclick = "">
						</a>
						
						<div class="group_btn">
							<button type="button" onclick = "" class="btn btn_cart">
								<span class="screen_out"></span>
							</button>
						</div>
					</div>
					<a class="info">
						<span class="name">상품이름</span> 
						<span class="price">상품가격</span> 
						<span class="tag"></span>
					</a>
				</div>
				
				<div class="item">
					<div class="thumb">
						<a class="img">
							 <img src="add_photo.png" onclick = "">
						</a>
						
						<div class="group_btn">
							<button type="button" onclick = "" class="btn btn_cart">
								<span class="screen_out"></span>
							</button>
						</div>
					</div>
					<a class="info">
						<span class="name">상품이름</span> 
						<span class="price">상품가격</span> 
						<span class="tag"></span>
					</a>
				</div>
			
			
			</div>
				
				
				
				
			</div>
		</div>

	
</main>

<footer>
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</footer>

<jsp:include page="/WEB-INF/views/layout/staticFooter.jsp"/></body>
</html>
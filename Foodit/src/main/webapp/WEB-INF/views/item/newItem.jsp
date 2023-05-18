<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/layout/staticHeader.jsp" />

<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<style type="text/css">
		.container {
			display: grid;
			grid-template-columns: repeat(3, 1fr);
			grid-gap: 60px;
			padding: 20px;
			font-family: sans-serif;
			width: 1110px
		}

.box {
			background-color: #333;
			
			text-align: center;
			font-size: 1.5em;
			width: 300px;height: 500px;
			border-top: none;
			
		}
		
.box .imgtest {
	width: 300px;height: 500px
}
.css-i804ml {
    margin-top: 50px;
    font-weight: 500;
    font-size: 28px;
    color: rgb(51, 51, 51);
    line-height: 35px;
    letter-spacing: -1px;
    text-align: center;
    margin-top: 130px
}

.product-name {
    font-size: 16px;
    line-height: 1.45;
    font-weight: 400;
    margin-bottom: 8px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    letter-spacing: normal;
    word-break: break-word;
    overflow-wrap: break-word;
}

.price{
    font-size: 16px;
    font-weight: 800;
    line-height: 1.5;
    white-space: nowrap;
}

.price .discount{
	color: rgb(250, 98, 47)
}
</style>
<script type="text/javascript">

</script>
</head>
<body>

<header>
    <jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
</header>
<div>
	<h3 class="css-i804ml eaf14xt0">${num==1?'신제품':'베스트' }</h3>
</div>
	<div class="container">
	
	<c:forEach var="dto" items="${list}" varStatus="status">
		<div class="box">
		<a href="${detailUrl}&itemNo=${dto.itemNo}">
			<img class="imgtest" src="imgtest/도라에몽.png">
		</a>
			<h2 class="product-name">${dto.itemName }</h2> 
			<div class="price">
			<script type="text/javascript">
			</script>
				<span class="discount">${dto.discount}%</span>
				<span id="price">${dto.discountPrice }<span>원</span></span>
			</div>
		</div>
	</c:forEach>
	</div>
</body>
</html>
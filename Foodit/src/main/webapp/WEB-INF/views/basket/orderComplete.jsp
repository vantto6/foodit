<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>
<h1> 결제가 완료되었습니다! </h1>
<button onclick="redirectToAddress()">홈으로</button>
</body>
<script type="text/javascript">
function redirectToAddress() {
	window.location.href = "${pageContext.request.contextPath}/basket/home.do";
}
</script>

</html>
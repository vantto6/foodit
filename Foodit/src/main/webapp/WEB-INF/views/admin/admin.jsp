<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
</head>
<body>
    <h1>관리자 페이지</h1>
    
    <h2>상품관리</h2>
    <ul>
        <li><a href="discount.jsp">상품할인</a></li>
        <li><a href="addProduct.jsp">품목추가</a></li>
        <li><a href="deleteProduct.jsp">품목삭제</a></li>
        <li><a href="modifyProduct.jsp">품목수정</a></li>
    </ul>
    
    <h2>판매관리</h2>
    <ul>
        <li>
            <a href="customerSales.jsp">회원</a>
            <ul>
                <li><a href="customerDetails.jsp">회원ID</a></li>
            </ul>
        </li>
        <li>
            <a href="brandSales.jsp">입점브랜드</a>
            <ul>
                <li><a href="brandList.jsp">브랜드 이름 리스트</a></li>
                <li><a href="stockStatus.jsp">재고현황</a></li>
            </ul>
        </li>
    </ul>
    
    <h2>회원관리</h2>
    <ul>
        <li><a href="userList.jsp">전체 회원 목록</a></li>
    </ul>
    
    <h2>통계</h2>
    <ul>
        <li><a href="visitorStats.jsp">접속자수/가입자수 현황</a></li>
        <li><a href="salesStats.jsp">판매현황</a></li>
    </ul>
</body>
</html>
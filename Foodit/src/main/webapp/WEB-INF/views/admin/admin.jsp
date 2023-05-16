<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        
        h1 {
            background-color: #333;
            color: #fff;
            padding: 20px;
            margin: 0;
        }
        
        h2 {
            margin: 30px 0 10px;
        }
        
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }
        
        li {
            margin-bottom: 10px;
        }
        
        a {
            color: #333;
            text-decoration: none;
        }
        
        a:hover {
            text-decoration: underline;
        }
        
        .sublist {
            margin-left: 20px;
        }
    </style>
</head>
<body>
    <h1>관리자 페이지</h1>
    
    <h2>상품관리</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin/discount.do">상품할인</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/addProduct.do">품목추가</a></li>
        <li><a href="deleteProduct.jsp">품목삭제</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/updateForm.do"">품목수정</a></li>
    </ul>
    
    <h2>판매관리</h2>
    <ul>
        <li>
            <a href="customerSales.jsp">회원</a>
            <ul class="sublist">
                <li><a href="customerDetails.jsp">회원ID</a></li>
            </ul>
        </li>
        <li>
            <a href="brandSales.jsp">입점브랜드</a>
            <ul class="sublist">
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

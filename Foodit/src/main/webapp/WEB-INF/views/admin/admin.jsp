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
            background-color: #88b04B;
            color: #fff;
            padding: 20px;
            margin: 0;
            text-align: center;
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
        .go-to-first-page {
            position: absolute;
            top: 20px;
            right: 20px; /* Updated */
            background-color: #88b04B;
            border: none;
            color: white;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            cursor: pointer;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <h1>FoodIT 관리자 페이지</h1>
    
    <h2>상품관리</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin/addProduct.do">품목추가</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/list.do">품목리스트</a></li>
    </ul>
    
    <h2>판매관리</h2>
    <ul>
        <li>
            <ul>
                <li><a href="${pageContext.request.contextPath}/admin/brand.do">브랜드 이름 리스트</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/stock.do">재고현황</a></li>
            </ul>
        </li>
    </ul>
    
    <h2>회원관리</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin/seeMembers.do">전체 회원 목록</a></li>
    </ul>
    
    <h2>통계</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin/stats.do">접속자수/가입자수/판매 현황</a></li>
    </ul>
    
    <footer>
				<button class="go-to-first-page"
					onclick="location.href='${pageContext.request.contextPath}/';">메인
					페이지</button>
		</footer>
</body>
</html>

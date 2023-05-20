<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>브랜드 리스트</title>
    <jsp:include page="/WEB-INF/views/layout/staticHeader.jsp" />
    <style type="text/css">
 h1 {
      text-align: center;
    }
.inventory {
  width: 100%;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #f2f2f2;
}


.container {
  display: flex;
  justify-content: center;
  align-items: center;
}
    </style>
</head>
<body>
<main>
    <h1>브랜드 리스트</h1>
  <div>  
    <table >
        <thead>
            <tr>
                <th>브랜드 번호</th>
                <th>브랜드 이름</th>
            </tr>
        </thead>
            <c:forEach var="dto" items="${list}">
        <tbody>
                <tr>
                    <td>${dto.brandNo}</td>
                    <td>${dto.brandName}</td>
                </tr>
        </tbody>
            </c:forEach>
    </table>
    </div> 
    <form action="${pageContext.request.contextPath}/admin/addBrand.do" method="post">
        <label for="brandNo">브랜드 번호:</label>
        <input type="text" id="brandNo" name="brandNo">
        <label for="brandName">브랜드 이름:</label>
        <input type="text" id="brandName" name="brandName">
        <input type="submit" value="추가">
    </form>
    
    
<footer>
		<div class="page-navigation" style="width: 900px; margin: 0 auto;">${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
		</div>
</footer>
</main>
</body>
</html>

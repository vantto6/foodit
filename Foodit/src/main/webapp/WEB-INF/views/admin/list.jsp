<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품 리스트</title>
<jsp:include page="/WEB-INF/views/layout/staticHeader.jsp" />
  <style type="text/css">
    h1 {
     text-align: center;
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

    .button-container {
      display: flex;
      justify-content: center;
    }

    .button {
      margin-right: 8px;
      padding: 6px 12px;
      border: none;
      background-color: #88b04B;
      color: white;
      cursor: pointer;
    }

    .button:hover {
      background-color: #45a049;
    }
    .body-main {
	max-width: 800px;
	
}
        .go-to-first-page {
      position: fixed;
      bottom: 20px;
      right: 20px;
      background-color: #88b04B;
      border: none;
      color: white;
      padding: 8px 16px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 14px;
      margin: 4px 2px;
      cursor: pointer;
      border-radius: 4px;
    }
  </style>

</head>
<body>
<h1>상품 리스트</h1>
<main>
<div >
  <table>

    <tr>
      <th colspan="2" style="text-align: center;">이미지</th> 
      <th>상품 번호</th>
      <th>상품 이름</th>
      <th>가격</th>
      <th>등록된 날짜</th>
      <th>수정된 날짜</th>
      <th>카테고리 이름</th>
      <th>브랜드 이름</th>
      <th>수정/삭제</th>
    </tr>

<c:forEach var="dto" items="${list}">

  <tr>
  <td class="center">
      <img style="width: 100px;" src="${pageContext.request.contextPath}/uploads/admin/${dto.saveFilename}">
  </td>
  <td> </td>
  <td>${dto.itemNo}</td>
  <td>${dto.itemName}</td>
  <td>${dto.price}</td>
  <td>${dto.createDate}</td>
  <td>${dto.updateDate}</td>
  <td>${dto.categoryName}</td>
  <td>${dto.brandName}</td>
  <td class="button-container">
    <button class="button" onclick="location.href='${pageContext.request.contextPath}/admin/update.do?itemNo=${dto.itemNo}&page=${page}';">수정</button>
  </td>
  <td class="button-container">
    <button type="submit" class="button" onclick="location.href='${pageContext.request.contextPath}/admin/delete.do?itemNo=${dto.itemNo}&page=${page}';">삭제</button>
  </td>
</tr>

</c:forEach>
  </table>
  </div>
  <footer>
  		<div class="page-navigation" style="width: 900px; margin: 0 auto;">${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
		<button class="go-to-first-page" onclick="location.href='${pageContext.request.contextPath}/admin/admin.do';">처음 페이지</button>
		</div>
</footer>
</main>
</body>




</html>
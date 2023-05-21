<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원 리스트</title>
<jsp:include page="/WEB-INF/views/layout/staticHeader.jsp" />
  <style>
    body {
      font-family: Arial, sans-serif;
    }

    h1 {
      text-align: center;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    th, td {
      padding: 8px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    th {
      background-color: #f2f2f2;
    }

    tr:hover {
      background-color: #f5f5f5;
    }

    .button {
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
        .name-cell {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      max-width: 150px;
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

  <h1>회원 관리 페이지</h1>
<main>
<div>
  <table>
    <tr>
      <th>회원번호</th>
      <th>회원아이디</th>
      <th>비밀번호</th>
      <th>이메일</th>
      <th>성별</th>
      <th>이름</th>
      <th>전화번호</th>
      <th>주소번호</th>
      <th>우편번호</th>
      <th>주소</th>
      <th>상세주소</th>
      <th>가입날짜</th>
      <th>수정날짜</th>
      <th>회원탈퇴날짜</th>
      <th>회원구분</th>
      <th>탈퇴</th>
    </tr>

<c:forEach var="dto" items="${list}">
    
    <tr class="name-cell">
    
      <td>${dto.clientNo}</td>
      <td>${dto.memberId }</td>
      <td>${dto.pwd }</td>
      <td>${dto.email }</td>
      <td>        
      <c:choose>
          <c:when test="${dto.gender eq '1'}">
            남자
          </c:when>
          <c:when test="${dto.gender eq '0'}">
            여자
          </c:when>
        </c:choose></td>
      <td>${dto.name }</td>
      <td>${dto.tel }</td>
      <td>${dto.addrNo}</td>
      <td>${dto.addressCode }</td>
      <td>${dto.address }</td>
      <td>${dto.addressDetail }</td>
      <td>${dto.createDate }</td>
      <td>${dto.updateDate }</td>
      <td>${dto.deleteDate }</td>
      <td>
      <c:choose>
          <c:when test="${dto.gubun eq '1'}">
            일반
          </c:when>
          <c:when test="${dto.gubun eq '0'}">
            카카오
          </c:when>
        </c:choose></td>      
   	<td>
  <button type="button" class="button" onclick="location.href='${pageContext.request.contextPath}/admin/deleteMember.do?clientNo=${dto.clientNo}&page=${page}';">탈퇴</button>
  </td>
    </tr>
</c:forEach>
  </table>
  </div>
  <footer>
  		<div class="page-navigation" style="width: 900px; margin: 0 auto;">${memberCount == 0 ? "등록된 게시물이 없습니다." : paging}
		<button class="go-to-first-page" onclick="location.href='${pageContext.request.contextPath}/admin/admin.do';">처음 페이지</button>
		</div>
</footer>
</main>
</body>
</html>
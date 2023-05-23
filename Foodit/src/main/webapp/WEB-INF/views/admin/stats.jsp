<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통계</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<style type="text/css">
body {
  font-family: Arial, sans-serif;
}

.container {
  max-width: 900px;
  margin: 0 auto;
  padding: 20px;
}

.header {
  text-align: center;
  margin-bottom: 30px;
}

.visitors,
.subscribers,
.sales {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.count {
  font-size: 24px;
  font-weight: bold;
}

.label {
  font-size: 14px;
  color: #888;
}

.sales-status {
  margin-top: 30px;
}

.sales-status h3 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.sales-status .status {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.status .label {
  font-size: 14px;
  color: #888;
}

.status .value {
  font-size: 16px;
  font-weight: bold;
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
 <div class="container">
    <div class="header">
      <h1>통계</h1>
    </div>
    <div class="visitors">
      <div>
        <div class="count">${totalCount}명</div>
        <div class="label">총 방문자수</div>
      </div>
      <div>
        <div class="count">${memberCount}명</div>
        <div class="label">회원수</div>
      </div>
      <div>
        <div class="count">10000</div>
        <div class="label">판매현황</div>
      </div>
    </div>
    <div class="sales-status">
      <h3>접속자 수</h3>
      <div class="status">
        <div class="label">현재 접속자 수:</div>
        <div class="value"> ${currentCount}명</div>
      </div>
      <div class="status">
        <div class="label">오늘 접속자 수:</div>
        <div class="value">${toDayCount}명</div>
      </div>
      <div class="status">
        <div class="label">어제 접속자 수:</div>
        <div class="value">${yesterDayCount}명</div>
      </div>
    </div>
  </div>
<footer>
  		<div class="page-navigation" style="width: 900px; margin: 0 auto;">${memberCount == 0 ? "등록된 게시물이 없습니다." : paging}
		<button class="go-to-first-page" onclick="location.href='${pageContext.request.contextPath}/admin/admin.do';">처음 페이지</button>
		</div>
</footer>
</body>
</html>
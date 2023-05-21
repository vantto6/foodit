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
</style>
</head>
<body>
 <div class="container">
    <div class="header">
      <h1>통계</h1>
    </div>
    <div class="visitors">
      <div>
        <div class="count">1000</div>
        <div class="label">방문자수</div>
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
      <h3>Sales Status</h3>
      <div class="status">
        <div class="label">일일 판매금액:</div>
        <div class="value">$500</div>
      </div>
      <div class="status">
        <div class="label">주간 판매금액:</div>
        <div class="value">$3000</div>
      </div>
      <div class="status">
        <div class="label">월간 판매금액:</div>
        <div class="value">$10000</div>
      </div>
    </div>
  </div>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<table class='table reply-list'>
	<c:forEach var = "dto" items="${list}">
		<tr class='list-header'>
			<td width='50%'>
				<span class='bold'>관리자</span>
			</td>
			<td width='50%' align='right'>
				<span>${dto.reg_date}</span> |								
			</td>
		</tr>
		<tr>
			<td colspan='2' valign='top'>${dto.content}</td>
		</tr>
	</c:forEach>
</table>


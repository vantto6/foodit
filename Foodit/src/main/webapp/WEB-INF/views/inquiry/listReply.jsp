<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<table class='table reply-list'>
    <tr class='list-header'>
        <td width='50%'><span class='bold'>답변</span></td>
        <td width='50%' align='right'><span>${dto.answerDate}</span>
            <c:choose>
                <c:when test="${sessionScope.member.memberId == 'admin' || sessionScope.member.memberId == dto.memberId}">
                </c:when>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td colspan='2' valign='top' style="border: 1px solid silver;">
            <c:choose>
                <c:when test="${empty dto.answer}">
                    답변 대기중입니다.
                </c:when>
                <c:otherwise>
                    ${dto.answer}
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>

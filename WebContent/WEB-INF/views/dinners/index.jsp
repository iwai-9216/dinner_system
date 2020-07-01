<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush_}"></c:out>
            </div>
        </c:if>
        <h2>料理 一覧</h2>
        <table id="dinner_list">
            <tbody>
                <tr>
                    <th class="dinner_dinner">料理名</th>
                    <th class="dinner_user">ユーザー名</th>
                    <th class="dinner_action">編集</th>
                </tr>
                <c:forEach var="dinner" items="${dinners}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="dinner_dinner">${dinner.dinner}</td>
                        <td class="dinner_user"><c:out value="${dinner.user.user}" /></td>
                        <td class="dinner_action"><a href="<c:url value='/dinners/show?id=${dinner.dinner_id}' />">編集する</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${dinners_count} 件)<br />
            <c:forEach var="i" begin="1" end="${((dinners_count -1) / 12) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/dinners/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/dinners/new' />">料理の登録</a></p>

    </c:param>
</c:import>

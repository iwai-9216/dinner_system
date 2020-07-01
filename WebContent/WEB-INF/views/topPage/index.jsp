<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>Dinner systemへようこそ</h2>
        <h3>【今日の料理】</h3>

        今日のおすすめ料理は"${value}"です！<br />

        <div id="pagination">
        </div>
        <p><a href="<c:url value='/dinners/new' />">料理の登録</a></p>
    </c:param>
</c:import>

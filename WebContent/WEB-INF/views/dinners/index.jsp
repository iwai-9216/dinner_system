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
                    <th class="dinner_dish">料理名</th>
                    <th class="dinner_name">ユーザー名</th>
                    <th class="dinner_url">URL</th>
                    <th class="dinner_action">操作</th>
                </tr>
                <c:forEach var="dinner" items="${dinners}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="dinner_dish">${dinner.dish}</td>
                        <td class="dinner_name"><c:out value="${dinner.user.name}" /></td>
                        <td class="dinner_url">
                            <c:choose>
                                <c:when test="${empty dinner.url}" >なし</c:when>
                                <c:otherwise><a href="<c:out value="${dinner.url}" />">あり</a></c:otherwise>
                            </c:choose>
                        </td>
                        <td class="dinner_action"><a href="<c:url value='/dinners/show?id=${dinner.id}' />">詳細を見る</a></td>

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

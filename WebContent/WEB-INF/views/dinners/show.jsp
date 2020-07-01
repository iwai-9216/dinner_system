<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>料理 詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>料理名</th>
                            <td>
                                <pre><c:out value="${dinner.dinner}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${Dinner.user.user}" /></td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.id == dinner.user.user_id}">
                    <p><a href="<c:url value="/dinners/edit?id=${dinner.dinner_id}" />">この料理を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/dinners/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>

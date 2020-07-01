<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${user != null}">
                <h2>id : ${user.user_id} のユーザー情報詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ユーザー名</th>
                            <td><c:out value="${user.name}" /></td>
                        </tr>
                        <tr>
                            <th>Eメール</th>
                            <td><c:out value="${user.email}" /></td>
                        </tr>

                    </tbody>
                </table>

                <p><a href="<c:url value='/users/edit?id=${user.user_id}' />">この従業員情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>

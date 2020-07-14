<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
        </c:forEach>

    </div>
</c:if>
<label for="dish">料理名</label><br />
<input type="text" name="dish" value="${dinner.dish}" />
<br /><br />

<label for="user">ユーザー名</label><br />
<c:out value="${sessionScope.login_user.name}" />
<br /><br />

<label for="url">クックパッド URL</label><br />
<input type="text" name="url" value="${dinner.url}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>

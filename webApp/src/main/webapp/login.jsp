<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseURL" value="${req.requestURL}"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="headersAndStyle.jsp" %>


</div>
        <strong><c:if test="${not empty oauth.firstName}">
           <h1> Welcome ${oauth.firstName} ${oauth.secondName}</h1>
        </c:if> &nbsp;
        </strong><br>
    <c:choose>
    <c:when test="${empty oauth.email}">
    <form method="post" action="/login">
        <input name="login" type="hidden" value="1">
        <input type="submit" value="Sign in with Google" class="btn btn-login btn-lg">
    </form>
    </c:when>
    <c:otherwise>
    <form method="get" action="/start">
    <input type="submit" class="btn btn-login btn-lg" value="Start you calculation">
    </form>
    <form method="get" action="/logout">
    <input type="submit" class="btn btn-login btn-lg" value="Sign out">
    </form>
    </c:otherwise>
    </c:choose>
    <c:if test="${not empty error}">
    <div class="errorbox">
            ${error}
    </div>
    </c:if>
</div>


<%@ include file="footer.jsp" %>
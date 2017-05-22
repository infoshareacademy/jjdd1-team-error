<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="headAndStyle.jsp" %>


</div>
    <c:choose>
    <c:when test="${empty oauth.email}">
        <body>
        <div id="header">
            <div id="logo">
                <p>Trip Calculator</p>
            </div>
            <div class="car">
                <img  id="car" src="img/moving-car-mirror.png" alt="car">
            </div>
        </div>
        <div style="margin:0; padding-bottom:120px; padding-top:50px;"><br/>
            <strong>
                <h1> Welcome to Trip Calculator! </h1> <br/>
                <h4> Sign in with Google to start your calculation </h4>
            </strong><br/><br/>
            <form method="post" action="/login">
                <input name="login" type="hidden" value="1">
                <input type="submit" value="Sign in" class="btn btn-primary btn-lg">
            </form>
        </div>
    </c:when>
    <c:otherwise>
    <%@ include file="header.jsp" %>
        <div class="row" style="margin:0; padding-bottom:120px; padding-top:50px;">
            <div class="col-md-4" style="padding:0;">
             <img class="photo" src="img/photo2.jpg" alt="car">
            </div>
            <div class="col-md-4" style="padding:0;">
                <h1>Welcome ${oauth.given_name} ${oauth.family_name}!</h1><br/><br/>
                <form method="get" action="/start">
                    <input type="submit" class="btn btn-primary btn-lg" value="Start you calculation">
                </form>
            </div>
            <div class="col-md-4" style="padding:0;" >
                <img class="photo" src="img/photo1.jpg" alt="car">
            </div>
        </div>
        <div style="clear:both;"></div>
    </c:otherwise>
    </c:choose>
    <c:if test="${not empty error}">
        <div>
                ${error}
        </div>
    </c:if>
</div>


<%@ include file="footer.jsp" %>
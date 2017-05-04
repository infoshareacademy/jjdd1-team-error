<%@ include file="headersAndStyle.jsp" %>



<!--  forward to login page for login if user info is not in session  -->
<% if (session.getAttribute("userName") == null) {%>
<jsp:forward page="/login.jsp"/>
<% } %>

<h3>Welcome  ${userName}</h3>
</br>

<form method="get" action="/calc">
    <div class="buttons">
        <button type="submit" name="start" value="">Start your calculation</button>
    </div>
</form>

<%@ include file="footer.jsp" %>
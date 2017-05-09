<%@ include file="headersAndStyle.jsp" %>



<!--  forward to login page for login if user info is not in session  -->
<% if (session.getAttribute("userName") == null) {%>
<jsp:forward page="/login.jsp"/>
<% } %>
<div class="row" style="margin:0; padding-top:50px;">
    <div class="col-md-4" style="padding:0;">
        <img class="photo" src="img/photo2.jpg" alt="car">
    </div>
    <div class="col-md-4" style="padding:0;">
<h1>Welcome  ${userName}!</h1>
</br>

<form method="post" action="/calc">
    <div class="buttons">
        <button type="submit" name="start" value="" class="btn btn-primary btn-lg">Start your calculation</button>
    </div>
</form>
</div>
<div class="col-md-4" style="padding:0;" >
    <img class="photo" src="img/photo1.jpg" alt="car">
</div>
</div>
<div style="clear:both;"></div>
<%@ include file="footer.jsp" %>
<%@ include file="headersAndStyle.jsp" %>
<%@ include file="sessionChecker.jsp" %>

<div class="row" style="margin:0; padding-bottom:120px; padding-top:50px;">
    <div class="col-md-4" style="padding:0;">
        <img class="photo" src="img/photo2.jpg" alt="car">
    </div>
    <div class="col-md-4" style="padding:0;">
<h1>Welcome  ${userName}!</h1>
<br>

<form method="get" action="/start">
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

<form action="${pageContext.request.contextPath}/logout" method="post">


<%--<a href="https://accounts.google.com/Logout" ><input type="submit" value="Logout" /></a>--%>
    <%--<input onClick="window.location='https://accounts.google.com/Logout' " target="_parent" type="submit" value="Logout">--%>
    <input onClick="" type="submit" value="Logout">
</form>

<%--window in selected size--%>
<a href="https://accounts.google.com/Logout" onclick="window.open(this.href, 'mywin',
'left=20,top=20,width=500,height=500,toolbar=1,resizable=0'); return false;" >Share this</a>



<%@ include file="footer.jsp" %>
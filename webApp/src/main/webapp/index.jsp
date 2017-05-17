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
<a href="https://accounts.google.com/Logout" onclick="signOut();" target="_blank">Sign out</a>
<script>
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().disconnect().then(function () {
            console.log('User signed out.');
        });
    }

</script>

<%@ include file="footer.jsp" %>
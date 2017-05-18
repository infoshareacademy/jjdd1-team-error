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

<input onclick=logOut() type="submit" value="Sign out" >

<script>
    var a;
    function logOut() {
        if (a = window.open("https://accounts.google.com/Logout", 'mywin',
                'left=450,top=100,width=500,height=500,toolbar=1,resizable=0'))
        {
            var redirectUrl = 'logout';
            var form = $('<form action="' + redirectUrl + '" method="post"> </form>');
            form.submit();
//                setTimeout(function() {
//                    a.close();
//                }, 5000);
            }
    }
</script>

<a href="#" id="target">Log</a>
<script>
    (function() {

        document.getElementById("target").onclick = function() {
            var wnd = window.open("https://accounts.google.com/Logout", 'mywin',
                'left=450,top=100,width=500,height=500,toolbar=1,resizable=0');
            setTimeout(function() {
                wnd.close();
            }, 3000)
//            {
//                var form = $('<form action="' + redirectUrl + '" method="post"> </form>');
//                form.submit();
//            }
        };

    })();

</script>



<%@ include file="footer.jsp" %>


<%--
  Created by IntelliJ IDEA.
  User: igafalkowska
  Date: 28.04.17
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--<title>Login</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h3>You need to log in to start your calculation</h3>--%>
<%--<form method=”GET” action=”googleplus”>--%>
<%--<button type=”submit”>Login</button>--%>
<%--</form>--%>
<%--&lt;%&ndash;<form action="/login" method="post">&ndash;%&gt;--%>
<%--&lt;%&ndash;<div>Login: <input type="text" name="username"></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div>Password: <input type="password" name="password"></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="hidden" name="referrer" value="${param.url}">&ndash;%&gt;--%>
<%--&lt;%&ndash;<div><input type="submit" value="Log in"></div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</form>&ndash;%&gt;--%>
<%--</body>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="447589672882-lon09s9eq542cpusfm4njbkjcuhpgif7.apps.googleusercontent.com">

    <title>Servlet OAuth example</title>
</head>
<body>
<h1>Welcome to Trip Calculator</h1>
</br>
<h3>Sign in with Google to start your calculation</h3>
<br>
<div class="g-signin2" data-onsuccess="onSignIn"></div>

<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId());
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
        console.log('id_token: ' + googleUser.getAuthResponse().id_token);

        //do not post above info to the server because that is not safe.
        //just send the id_token

        var redirectUrl = 'login';
        //using jquery to post data dynamically
        var form = $('<form action="' + redirectUrl + '" method="post">' +
            '<input type="text" name="id_token" value="' +
            googleUser.getAuthResponse().id_token + '" />' +
            '</form>');
        $('body').append(form);
        form.submit();
    }

</script>
</body>
</html>
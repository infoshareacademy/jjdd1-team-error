
<%@ include file="headersAndStyle.jsp" %>

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

        var redirectUrl = 'calc';
        //using jquery to post data dynamically
        var form = $('<form action="' + redirectUrl + '" method="get">' +
            '<input type="text" name="id_token" value="' +
            googleUser.getAuthResponse().id_token + '" />' +
            '</form>');
        $('body').append(form);
        form.submit();
    }

</script>

<%@ include file="footer.jsp" %>
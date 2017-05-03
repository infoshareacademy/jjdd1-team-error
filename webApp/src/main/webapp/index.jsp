<%@ include file="headersAndStyle.jsp" %>
    <h1>Welcome to Trip Calculator</h1>

<form action="/calc" method="post" enctype="multipart/form-data">
    <input type="text" currencyCode="description" />
    <input type="file" currencyCode="file" />
    <input type="submit" />
</form>

<form action="/calc" method="get" >
    <div class="buttons">
        <button type="submit" currencyCode="start" value="">Start your calculation</button>
    </div>
</form>
<%@ include file="footer.jsp" %>
<%@ include file="headersAndStyle.jsp" %>
    <h1>Welcome to Trip Calculator</h1>

    <form action="upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" />
    </form>
    <form method="get" action="/calc">
        <div class="buttons">
            <button type="submit" name="start" value="">Start your calculation</button>
        </div>
    </form>
<%@ include file="footer.jsp" %>
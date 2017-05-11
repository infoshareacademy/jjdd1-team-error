<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 23.04.2017
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headersAndStyle.jsp" %>
<h1>Welcome to Trip Calculator</h1>
<h2 class="error">Error. Unfortunately at least one source file is missing, therefore Trip Calculator is not available.</h2>
<form style="display: inline" method="post" action="/fileUploader.jsp">
    <div class="buttons">
        <button type="submit" name="uploadFiles">Upload files</button>
    </div>
</form>
<%@ include file="footer.jsp" %>

<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 23.04.2017
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headersAndStyle.jsp" %>
<h2 class="error">Error. One or more source file was not detected.</h2>
<li>Petrol file is: ${petrolFileStatus}</li>
<li>Currency info file is: ${currencyInfoFileStatus}</li>
<li>Currency zip file is: ${currencyZipFileStatus}</li>
<br>
<form style="display: inline" method="post" action="/fileUploader.jsp">
    <div class="buttons">
        <input type="hidden" name="petrolFileStatus" value="${petrolFileStatus}" />
        <input type="hidden" name="currencyInfoFileStatus" value="${currencyInfoFileStatus}" />
        <input type="hidden" name="currencyZipFileStatus" value="${currencyZipFileStatus}" />
        <button type="submit" name="uploadFiles">Upload files</button>
    </div>
</form>
<%@ include file="footer.jsp" %>

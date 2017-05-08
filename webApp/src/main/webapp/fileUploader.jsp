<%--
  Created by IntelliJ IDEA.
  User: sebastian_los
  Date: 08.05.17
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headersAndStyle.jsp" %>
<h1>Upload your files</h1>

<!-- Select Basic -->
<form method="post" action="/upload" class="form-horizontal" id="reg_form">
    <div class="form-group">
        <label class="col-md-4 control-label">Input File</label>
        <div class="col-md-6 selectContainer">
            <input type="file" class="form-control" name="petrolFile" />
            <asp:Label ID="Label3" runat="server" Text="Navigate to the file you wish to upload" CssClass="label_under_text"></asp:Label>
        </div>
    </div>
</form>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="footer.jsp" %>

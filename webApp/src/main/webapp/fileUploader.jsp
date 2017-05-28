<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>

<h1>Upload your files</h1>
<br>
<li>Petrol file is: ${param.petrolFileStatus}</li>
<li>Currency info file is: ${param.currencyInfoFileStatus}</li>
<li>Currency zip file is: ${param.currencyZipFileStatus}</li
<br>
<!-- Select Basic -->
<form method="post" action="/upload" class="form-horizontal" id="reg_form" enctype="multipart/form-data" style="padding-bottom: 120px">
    <div class="form-group">
        <label class="col-md-4 control-label">Petrol file</label>
        <div class="col-md-6 selectContainer">
            <input type="file" class="form-control" name="petrolFile"/>
            <asp:Label ID="Label3" runat="server" Text="Navigate to the file you wish to upload"
                       CssClass="label_under_text"></asp:Label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Currency info file</label>
        <div class="col-md-6 selectContainer">
            <input type="file" class="form-control" name="currencyInfoFile"/>
            <asp:Label ID="Label3" runat="server" Text="Navigate to the file you wish to upload"
                       CssClass="label_under_text"></asp:Label>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Currency zip file</label>
        <div class="col-md-6 selectContainer">
            <input type="file" class="form-control" name="currencyZipFile"/>
            <asp:Label ID="Label3" runat="server" Text="Navigate to the file you wish to upload"
                       CssClass="label_under_text"></asp:Label>
        </div>
    </div>
    <div class="buttons">
        <button type="submit">Upload files</button>
    </div>
</form>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="footer.jsp" %>

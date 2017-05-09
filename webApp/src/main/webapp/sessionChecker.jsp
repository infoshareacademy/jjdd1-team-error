<%--
  Created by IntelliJ IDEA.
  User: sebastian_los
  Date: 09.05.17
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<% if (session.getAttribute("userName") == null) {%>
<jsp:forward page="/login.jsp"/>
<% } %>
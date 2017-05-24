<%--
  Created by IntelliJ IDEA.
  User: igafalkowska
  Date: 23.05.17
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>

<h3>Information about users logged in to Trip Calculator</h3>
<div class="row" style="padding-bottom: 120px; margin:0;">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <table class="table table-bordered">
            <thead>
                <tr class="info">
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Email</th>
                    <th>Recent login date</th>
                    <th>Recent login hour</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${usersList}" var="user" begin="0" end="${usersList.size()}">
                    <tr class="active">
                        <td><c:out value="${user.userFirstName}"></c:out></td>
                        <td><c:out value="${user.userSecondName}"></c:out></td>
                        <td><c:out value="${user.email}"></c:out></td>
                        <td><c:out value="${user.recentLoginDate}"></c:out></td>
                        <td><c:out value="${user.recentLoginTime}"></c:out></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-3"></div>
</div>


<%@ include file="footer.jsp" %>

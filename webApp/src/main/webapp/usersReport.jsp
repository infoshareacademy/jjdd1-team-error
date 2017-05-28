<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>

<table>
    <tr>
        <th>Firstname</th>
        <th>Secondname</th>
        <th>Email</th>
        <th>Recent login date</th>
        <th>Recent login hour</th>
        //TODO not working
        <c:forEach begin="0" end="${usersFirstName.size()}" var="loop">
            <tr>
                <td>${usersFirstName.get(loop)}</td>
                <td>${usersSecondName.get(loop)}</td>
                <td>${usersEmail.get(loop)}</td>
                <td>${usersRecentLoginDate.get(loop)}</td>
                <td>${usersRecentLoginTime.get(loop)}</td>
            </tr>
        </c:forEach>
    </tr>
</table>

<%@ include file="footer.jsp" %>

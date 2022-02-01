<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<style>
    td {
        text-align: center;
    }
</style>
<html>
<body>
<h2>Main page</h2>
Welcome to Main page
<br>
<br>
<ul>
    <li><a href="viewAll">View employee</a></li>
    <li><a href="seeAllAboutProjects">View projects</a></li>
    <li><a href="seeAccounts">See accounts to log in</a></li>
</ul>

<c:if test="${task==1}">
    <h2>All users list:</h2>
    <table border='1' cellpadding='2' width='50%'>
    <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Password</th>
    <th>Role</th>
    <sec:authorize access="hasRole('ADMIN')">
        <th>Delete</th>
    </sec:authorize>
    </tr>
    <c:forEach var="user" items="${listUsers}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <sec:authorize access="hasRole('ADMIN')">
                <td><a href="deleteUser/${user.id}">Delete</a></td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </table>
</c:if>
</body>
</html>

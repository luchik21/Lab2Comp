<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page isELIgnored="false" %>
<style>
    td {
        text-align: center;
    }
</style>
<html>
<head>
    <title>Find page</title>
</head>
<body>
<ul type="round">
    <li><a href="/Lab2Comp/viewAll">View employee</a></li>
    <li><a href="/Lab2Comp/seeAllAboutProjects">View projects</a></li>
</ul>

<c:if test="${task==1}">
<h2>Find employee by project id:(${findId})</h2>
<table border='1' cellpadding='2' width='50%'>
    <tr>
        <th>ID</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Project</th>
        <th>Job</th>
        <th>Salary</th>
    </tr>
    <c:forEach var="employee" items="${listEmployee}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.firstname}</td>
            <td>${employee.lastname}</td>
            <td>${employee.project}</td>
            <td>${employee.job}</td>
            <td>${employee.salary}</td>
        </tr>
    </c:forEach>
    </c:if>
</table>

<c:if test="${task==2}">
<h2>Find employee by job id:${findId} </h2>
<table border='1' cellpadding='2' width='50%'>
    <tr>
        <th>ID</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Project</th>
        <th>Job</th>
        <th>Salary</th>
    </tr>
    <c:forEach var="employee" items="${listEmployee}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.firstname}</td>
            <td>${employee.lastname}</td>
            <td>${employee.project}</td>
            <td>${employee.job}</td>
            <td>${employee.salary}</td>
        </tr>
    </c:forEach>
    </c:if>
</table>

<c:if test="${task==3}">
<h2>Find project by location id:${findId} </h2>
<table border='1' cellpadding='2' width='50%'>
    <tr>
        <th>ID</th>
        <th>Project name</th>
        <th>Budget</th>
        <th>Location</th>
        <th>Customer</th>
        <th>Requirements</th>
        <th>Time to build</th>
    </tr>
    <c:forEach var="project" items="${projects}">
        <tr>
            <td>${project.id}</td>
            <td>${project.projectName}</td>
            <td>${project.budget}</td>
            <td>${project.location}</td>
            <td>${project.customer}</td>
            <td>${project.requirements}</td>
            <td>${project.timeToBuild} year(-s)</td>
        </tr>
    </c:forEach>
    </c:if>
</table>

<c:if test="${task==4}">
<h2>Find project by customer id:${findId} </h2>
<table border='1' cellpadding='2' width='50%'>
    <tr>
        <th>ID</th>
        <th>Project name</th>
        <th>Budget</th>
        <th>Location</th>
        <th>Customer</th>
        <th>Requirements</th>
        <th>Time to build</th>
    </tr>
    <c:forEach var="project" items="${projects}">
        <tr>
            <td>${project.id}</td>
            <td>${project.projectName}</td>
            <td>${project.budget}</td>
            <td>${project.location}</td>
            <td>${project.customer}</td>
            <td>${project.requirements}</td>
            <td>${project.timeToBuild} year(-s)</td>
        </tr>
    </c:forEach>
    </c:if>
</table>

<c:if test="${task==5}">
<h2>Find project by requirements id:${findId} </h2>
<table border='1' cellpadding='2' width='50%'>
    <tr>
        <th>ID</th>
        <th>Project name</th>
        <th>Budget</th>
        <th>Location</th>
        <th>Customer</th>
        <th>Requirements</th>
        <th>Time to build</th>
    </tr>
    <c:forEach var="project" items="${projects}">
        <tr>
            <td>${project.id}</td>
            <td>${project.projectName}</td>
            <td>${project.budget}</td>
            <td>${project.location}</td>
            <td>${project.customer}</td>
            <td>${project.requirements}</td>
            <td>${project.timeToBuild} year(-s)</td>
        </tr>
    </c:forEach>
    </c:if>
</table>
</body>
</html>

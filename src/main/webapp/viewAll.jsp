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
    <title>View all page</title>
</head>
<body>
<ul>
    <li><a href="logout">Logout</a></li>
</ul>
<br>
<ul type="round">
    <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
        <li><a href="addEmployee">Add new employee</a></li>
        <li><a href="addProject">Add new project</a></li>
        <li><a href="addJob">Add new job</a></li>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMIN')">
        <li><a href="index">Go to menu</a></li>
        <li><a href="addUser">Add new user</a></li>
    </sec:authorize>
</ul>
<ul type="square">
    <li><a href="seeAllAboutProjects">View projects-></a></li>
    <li><a href="expand">↓Expand projects and jobs↓</a></li>
</ul>

<c:if test="${task==1}">
    <h2>All employees list:</h2>
    <table border='1' cellpadding='2' width='50%'>
        <tr>
            <th>ID</th>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Project</th>
            <th>Job</th>
            <th>Salary</th>
            <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                <th>Edit</th>
                <th>Delete</th>
            </sec:authorize>
        </tr>
        <c:forEach var="employee" items="${listEmployee}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.firstname}</td>
                <td>${employee.lastname}</td>
                <td><a href="findByProject/${employee.project}">${employee.project}</a></td>
                <td><a href="findByJob/${employee.job}">${employee.job}</a></td>
                <td>${employee.salary}</td>
                <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                    <td><a href="editEmployee/${employee.id}">Edit</a></td>
                    <td><a href="deleteEmployee/${employee.id}">Delete</a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:if test="${more==1}">
        <h2>Project list:</h2>
        <table class="tg" border='1' cellpadding='2' width='50%'>
            <tr>
                <th>ID</th>
                <th>Project name</th>
                <th>Budget</th>
                <th>Location</th>
                <th>Customer</th>
                <th>Requirements</th>
                <th>Time to build</th>
                <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                    <th>Edit</th>
                    <th>Delete</th>
                </sec:authorize>
            </tr>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td>${project.id}</td>
                    <td>${project.projectName}</td>
                    <td>${project.budget}</td>
                    <td><a href="findByLocation/${project.location}">${project.location}</a></td>
                    <td><a href="findByCustomer/${project.customer}">${project.customer}</a></td>
                    <td><a href="findByRequirements/${project.requirements}">${project.requirements}</a></td>
                    <td>${project.timeToBuild} year(-s)</td>
                    <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                        <td><a href="editProject/${project.id}">Edit</a></td>
                        <td><a href="deleteProject/${project.id}">Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            <br>
        </table>
        <br>
        <h2>Job list:</h2>
        <table class="tg" border='1' cellpadding='2' width='50%'>
            <tr>
                <th>ID</th>
                <th>Job name</th>
                <th>Salary</th>
                <th>Premium</th>
                <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                    <th>Edit</th>
                    <th>Delete</th>
                </sec:authorize>
            </tr>
            <c:forEach var="job" items="${jobs}">
                <tr>
                    <td>${job.id}</td>
                    <td>${job.jobName}</td>
                    <td>${job.salary}</td>
                    <td>${job.premium}</td>
                    <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                        <td><a href="editJob/${job.id}">Edit</a></td>
                        <td><a href="deleteJob/${job.id}">Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</c:if>
</body>
</html>

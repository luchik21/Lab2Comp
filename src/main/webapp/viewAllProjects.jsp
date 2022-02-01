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
    <title>View all about projects page</title>
</head>
<body>
<ul>
    <li><a href="logout">Logout</a></li>
</ul>
<br>
<ul>
    <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
        <li><a href="addProject">Add new project</a></li>
        <li><a href="addLocation">Add new location</a></li>
        <li><a href="addCustomer">Add new customer</a></li>
        <li><a href="addRequirements">Add new requirements</a></li>
    </sec:authorize>
</ul>
<ul type="square">
    <li><a href="viewAll"><-View employee</a></li>
</ul>
<c:if test="${do==1}">
    <h2>Project list:</h2>
    <table border='1' cellpadding='2' width='50%'>
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
    </table>
    <h2>Location list:</h2>
    <table border='1' cellpadding='2' width='50%'>
        <tr>
            <th>ID</th>
            <th>City</th>
            <th>Address</th>
            <th>Area</th>
            <th>Permission</th>
            <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                <th>Delete</th>
            </sec:authorize>
        </tr>
        <c:forEach var="location" items="${locations}">
            <tr>
                <td>${location.locationId}</td>
                <td>${location.city}</td>
                <td>${location.address}</td>
                <td>${location.area} km²</td>
                <td>${location.permission}</td>
                <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                    <td><a href="deleteLocation/${location.locationId}">Delete</a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
        <br>
    </table>
    <h2>Customer list:</h2>
    <table class="tg" border='1' cellpadding='2' width='50%'>
        <tr>
            <th>ID</th>
            <th>Company name</th>
            <th>Budget</th>
            <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                <th>Delete</th>
            </sec:authorize>
        </tr>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.companyName}</td>
                <td>${customer.budget}</td>
                <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                    <td><a href="deleteCustomer/${customer.id}">Delete</a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
        <br>
    </table>
    <h2>Requirements list:</h2>
    <table class="tg" border='1' cellpadding='2' width='50%'>
        <tr>
            <th>ID</th>
            <th>Material</th>
            <th>Color</th>
            <th>Type</th>
            <th>Floors count</th>
            <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                <th>Delete</th>
            </sec:authorize>
        </tr>
        <c:forEach var="requirement" items="${requirements}">
            <tr>
                <td>${requirement.reqId}</td>
                <td>${requirement.material}</td>
                <td>${requirement.color}</td>
                <td>${requirement.type}</td>
                <td>${requirement.floorsCount}</td>
                <sec:authorize access="hasAnyRole('ADMIN','BOSS')">
                    <td><a href="deleteRequirements/${requirement.reqId}">Delete</a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
        <br>
    </table>
</c:if>
</body>
</html>

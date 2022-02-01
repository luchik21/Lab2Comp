<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Edit page</title>
</head>
<body>
<ul>
    <li><a href="../viewAll">Back to employee list</a></li>
    <li><a href="../seeAllAboutProjects">Back to project list</a></li>
</ul>

<c:if test="${task == 1}">
    <h2>Edit employee</h2>
    <form:form action="saveEditEmployee" method="post">
        <form:hidden path="id" value="${employee.id}"/>
        <table>
            <tr>
                <td>Firstname:</td>
                <td><form:input type="text" path="firstname" value="${employee.firstname}"/></td>
            </tr>
            <tr>
                <td>Lastname:</td>
                <td><form:input type="text" path="lastname" value="${employee.lastname}"/></td>
            </tr>
            <tr>
                <td>Project:</td>
                <td>
                    <select required name="project">
                        <c:forEach items="${projects}" var="project">
                            <option value="${project.id}">ID: ${project.id}, Project
                                name: ${project.projectName}</option>
                        </c:forEach>
                    </select>
                    <br>
                </td>
            </tr>
            <tr>
                <td>Job:</td>
                <td>
                    <select required name="job">
                        <c:forEach items="${jobs}" var="job">
                            <option value="${job.id}">ID: ${job.id}, Job name:${job.jobName}</option>
                        </c:forEach>
                    </select></td>
            </tr>
            <tr>
                <td>Salary:</td>
                <td><form:input type="number" path="salary" value="${employee.salary}"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Edit"></td>
            </tr>
        </table>
    </form:form>
</c:if>

<c:if test="${task == 2}">
    <h2>Edit project</h2>
    <form:form action="saveEditProject" method="post">
        <form:hidden path="id" value="${project.id}"/>
        <table>
            <tr>
                <td>Project name:</td>
                <td><form:input type="text" path="projectName" value="${project.projectName}"/></td>
            </tr>
            <tr>
                <td>Budget:</td>
                <td><form:input type="number" path="budget" value="${project.budget}"/></td>
            </tr>
            <tr>
                <td>Location:</td>
                <td>
                    <select required name="location">
                        <c:forEach items="${locations}" var="location">
                            <option value="${location.locationId}">ID: ${location.locationId}, Location
                                : ${location.city}, ${location.address}</option>
                        </c:forEach>
                    </select>
                    <br>
                </td>
            </tr>
            <tr>
                <td>Customer:</td>
                <td>
                    <select required name="customer">
                        <c:forEach items="${customers}" var="customer">
                            <option value="${customer.id}">ID: ${customer.id}, Company
                                name:${customer.companyName}</option>
                        </c:forEach>
                    </select></td>
            </tr>
            <tr>
                <td>Requirements:</td>
                <td>
                    <select required name="requirements">
                        <c:forEach items="${requirements}" var="requirement">
                            <option value="${requirement.reqId}">ID: ${requirement.reqId},
                                color:${requirement.color}, material:${requirement.material}</option>
                        </c:forEach>
                    </select></td>
            </tr>
            <tr>
                <td>Time to build:</td>
                <td><form:input type="number" path="timeToBuild" value="${project.timeToBuild}"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Edit"></td>
            </tr>
        </table>
    </form:form>
</c:if>

<c:if test="${task == 3}">
    <h2>Edit job</h2>
    <form:form action="saveEditJob" method="post">
        <form:hidden path="id" value="${job.id}"/>
        <table>
            <tr>
                <td>Job name:</td>
                <td><form:input type="text" path="jobName" value="${job.jobName}"/></td>
            </tr>
            <tr>
                <td>Salary:</td>
                <td><form:input type="number" path="salary" value="${job.salary}"/></td>
            </tr>
            <tr>
                <td>Premium:</td>
                <td><form:input type="number" path="premium" value="${job.premium}"/></td>
            </tr>
            <td></td>
            <td><input type="submit" value="Edit"></td>
            </tr>
        </table>
    </form:form>
</c:if>
</body>
</html>


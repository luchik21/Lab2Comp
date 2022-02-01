<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Add page</title>
</head>
<body>
<ul>
    <li><a href="./viewAll">Back to employee list</a></li>
    <li><a href="./seeAllAboutProjects">Back to project list</a></li>
</ul>
<c:if test="${task==1}">
    <h2>Add new employee</h2>
    <form:form action="saveEmployee" method="post">
        <table>
            <tr>
                <td>Firstname:</td>
                <td><form:input type="text" path="firstname"/></td>
            </tr>
            <tr>
                <td>Lastname:</td>
                <td><form:input type="text" path="lastname"/></td>
            </tr>
            <tr>
                <td>Project:</td>
                <td>
                    <select required name="project">
                        <c:forEach items="${projects}" var="project">
                            <option value="${project.id}"> ID: ${project.id}, Project
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
                            <option value="${job.id}"> ID: ${job.id}, Project name: ${job.jobName} </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Salary:</td>
                <td><form:input type="number" path="salary"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</c:if>

<c:if test="${task==2}">
    <h2>Add new project</h2>
    <form:form action="saveProject" method="post">
        <table>
            <tr>
                <td>Project name:</td>
                <td><form:input type="text" path="projectName"/></td>
            </tr>
            <tr>
                <td>Budget:</td>
                <td><form:input type="number" path="budget"/></td>
            </tr>
            <tr>
                <td>Location:</td>
                <td>
                    <select required name="location">
                        <c:forEach items="${locations}" var="location">
                            <option value="${location.locationId}"> ID: ${location.locationId}, Location
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
                            <option value="${customer.id}"> ID: ${customer.id}, Company
                                name:${customer.companyName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Requirements:</td>
                <td>
                    <select required name="requirement">
                        <c:forEach items="${requirements}" var="requirement">
                            <option value="${requirement.reqId}"> ID: ${requirement.reqId},
                                color:${requirement.color}, material:${requirement.material}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Time to build:</td>
                <td><form:input type="number" path="timeToBuild"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</c:if>

<c:if test="${task==3}">
    <h2>Add new job</h2>
    <form:form action="saveJob" method="post">
        <table>
            <tr>
                <td>Job name:</td>
                <td><form:input type="text" path="jobName"/></td>
            </tr>
            <tr>
                <td>Salary:</td>
                <td><form:input type="number" path="salary"/></td>
            </tr>
            <tr>
                <td>Premium:</td>
                <td><form:input type="number" path="premium"/></td>
            </tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</c:if>

<c:if test="${task==4}">
    <h2>Add new location</h2>
    <form:form action="saveLocation" method="post">
        <table>
            <tr>
                <td>City:</td>
                <td><form:input type="text" path="city"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input type="text" path="address"/></td>
            </tr>
            <tr>
                <td>Area:</td>
                <td><form:input type="number" path="area"/></td>
            </tr>
            <tr>
                <td>Permission:</td>
                <td><form:input type="text" path="permission"/></td>
            </tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</c:if>

<c:if test="${task==5}">
    <h2>Add new customer</h2>
    <form:form action="saveCustomer" method="post">
        <table>
            <tr>
                <td>Company name:</td>
                <td><form:input type="text" path="companyName"/></td>
            </tr>
            <tr>
                <td>Budget:</td>
                <td><form:input type="number" path="budget"/></td>
            </tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</c:if>

<c:if test="${task==6}">
    <h2>Add new requirements</h2>
    <form:form action="saveRequirements" method="post">
        <table>
            <tr>
                <td>Material:</td>
                <td><form:input type="text" path="material"/></td>
            </tr>
            <tr>
                <td>Color:</td>
                <td><form:input type="text" path="color"/></td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><form:input type="text" path="type"/></td>
            </tr>
            <tr>
                <td>Floors count:</td>
                <td><form:input type="number" path="floorsCount"/></td>
            </tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</c:if>

<c:if test="${task==7}">
    <h2>Add new user</h2>
    <form:form action="saveUser" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><form:input type="text" path="username"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input type="text" path="password"/></td>
            </tr>
            <tr>
                <td>Role:</td>
                <td>
                    <select required name="role">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.role}">Role:${role.role}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
                <form:hidden path="enable" value="${1}"/>
            <td></td>
            <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</c:if>
</body>
</html>

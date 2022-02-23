<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<style>
    td {
        text-align: center;
    }
</style>
<meta charset="UTF-8">
<html>
<head>
    <title>View news</title>
</head>
<body>
<ul>
    <li><a href="logout">Logout</a></li>
</ul>
<br>
<ul>
    <li><a href="./viewAll">Back to employee list</a></li>
    <li><a href="./seeAllAboutProjects">Back to project list</a></li>
</ul>

<c:if test="${news==1}">
    <h2>News</h2>
    <form:form action="viewNews" method="get">
        <select required name="country">
            <c:forEach items="${countries}" var="country">
                <option value="${country}"> Country: ${country}</option>
            </c:forEach>
        </select>
        <select required name="category">
            <c:forEach items="${categories}" var="category">
                <option value="${category}"> Category: ${category}</option>
            </c:forEach>
        </select>
        <br>
        <br>
        <input type="submit" value="Search news"/>
        <br>
        <hr>
        <br>
        <c:forEach items="${articles}" var="article">
            <img src="${article.urlToImage}" alt="img" height="400" width="400">
            <br>
            <h1><b>${article.title}</b></h1>
            <h2>${article.description}</h2>
            <h2>Author: ${article.author}</h2>
            <h2>Date: ${article.publishedAt}</h2>
            <h3>${article.source}</h3>
            <hr>
        </c:forEach>
    </form:form>
</c:if>
</body>
</html>


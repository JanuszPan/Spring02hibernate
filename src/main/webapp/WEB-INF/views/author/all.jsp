<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach items="${authors}" var="author">
        <tr>
            <td>${author.id}</td>
            <td>${author.firstName}</td>
            <td>${author.lastName}</td>
            <td>${author.pesel}</td>
            <td>${author.email}</td>
            <td><a href="/form/author/delete?id=${author.id}">Usuń</a></td>
            <td><a href="/form/author/edit?id=${author.id}">Edytuj</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

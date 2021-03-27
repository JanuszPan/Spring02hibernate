<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.rating}</td>
            <td>${book.description}</td>
            <td>${book.publisher.name}</td>
            <td><a href="/form/book/delete/{id}" ${book.id}>Usuń</a>a></td>
            <td><a href="/form/book/edit/?=${book.id}>Edytuj</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
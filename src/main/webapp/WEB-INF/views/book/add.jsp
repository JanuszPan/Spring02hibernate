<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Add book</h1>
<%--@elvariable id="book" type=""--%>
<form:form method="post" modelAttribute="book">
    Title:<form:input path="title"/><form:errors path="title" cssClass="error"/>
    Rating: <form:input path="rating" type="number" min="1" max="10" value="1"/><form:errors path="rating" cssClass="error"/>
    Pages: <form:input path="pages" type="number" min="0"/><form:errors path="pages" cssClass="error"/>
    Description: <form:input path="description"/><form:errors path="description" cssClass="error"/>
    Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name"
                            itemValue="id"/><form:errors path="publisher.id" cssClass="error"/>
    Authors: <form:select path="authors" items="${authors}" itemLabel="name"
                          itemValue="id" multiple="true"/><form:errors path="authors" cssClass="error"/>
    <input type="submit" value="Add book">
</form:form>
<form action="/auth/logout" method="post">
    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
    <button type="submit">Logout</button>
</form>
</body>
</html>
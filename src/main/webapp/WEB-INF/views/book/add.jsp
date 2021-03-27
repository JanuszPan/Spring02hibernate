<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add book</h1>
<%--@elvariable id="book" type=""--%>
<form:form method="post" modelAttribute="book">
    Title:<form:input path="title"/>
    Rating: <form:input path="rating"/>
    Description: <form:input path="description"/>
    Publisher: <form:select path="publisher.id" items="${publishers}" itemLabel="name"
                            itemValue="id"/>
    Authors: <form:select path="author" items="${authors}" itemLabel="name"
                          itemValue="id"/>
    <input type="submit" value="Add book">
</form:form>
</body>
</html>
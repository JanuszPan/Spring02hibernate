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
<h1>Add author</h1>
<%--@elvariable id="author" type=""--%>
<form:form method="post" modelAttribute="author">
    First name:<form:input path="firstName"/><form:errors path="firstName" cssClass="error"/><br>
    Last name:<form:input path="lastName"/><form:errors path="lastName" cssClass="error"/><br>
    PESEL:<form:input path="pesel"/><form:errors path="pesel" cssClass="error"/><br>
    E-mail:<form:input path="email"/><form:errors path="email" cssClass="error"/>
    <input type="submit" value="Add author">
</form:form>
</body>
</html>
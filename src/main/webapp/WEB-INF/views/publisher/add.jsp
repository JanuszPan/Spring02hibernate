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
<h1>Add publisher</h1>
<%--@elvariable id="publisher" type=""--%>
<form:form method="post" modelAttribute="publisher">
    Name:<form:input path="name"/><form:errors path="name" cssClass="error"/><br>
    REGON:<form:input path="regon"/><form:errors path="regon" cssClass="error"/><br>
    NIP:<form:input path="nip"/><form:errors path="nip" cssClass="error"/>
    <input type="submit" value="Add publisher">
</form:form>
</body>
</html>
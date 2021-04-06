<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--@elvariable id="person" type=""--%>
<%--<form:form method="post" modelAttribute="person">--%>
<%--    Login: <form:input path="login"/><br><br>--%>
<%--    Password: <form:input path="password"/><br><br>--%>
<%--    E-mail: <form:input path="email"/><br><br>--%>
<%--    <input type="submit" value="Submit"><br>--%>
<%--</form:form>--%>
<form method="post" action="savePersonForm">
    Login: <input type="text" name="login"/>
    Password: <input type="password" name="password"/>
    E-mail: <input type="email" name="email"/>
    <input type="submit" value="Wyślij">
</form>
</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Student form</title>
</head>
<body>
<%--@elvariable id="student" type=""--%>
<form:form method="post" modelAttribute="student">
    FirstName: <form:input path="firstName"/><br>
    LastName: <form:input path="lastName"/><br><br>
    Gender: Male: <form:radiobutton path="gender" value="M"/>
    Female: <form:radiobutton path="gender" value="F"/><br><br>
    Choose country:
    <form:select path="country">
        <form:options items="${countries}"/>
    </form:select><br><br>
    Notes: <br> <form:textarea path="notes"/><br>
    Mailing list: <form:checkbox path="mailingList"/><br>
    Programming skills: <br>
    <form:select path="programmingSkills" multiple="true">
        <form:options items="${programmingSkills}"/>
    </form:select><br><br>
    Hobbies: <form:checkboxes path="hobbies" items="${hobbies}"/><br><br>
    <input type="submit" value="Submit"><br>
</form:form>
</body>
</html>
<%--firstName (pole tekstowe)
lastName (pole tekstowe)
gender (radio button)
country (select z możliwością pojedynczego wyboru)
notes (textarea)
mailingList (checkbox)
programmingSkills (select z możliwością wyboru wielu opcji)
hobbies (grupa checkboxów)--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="saveStudent">
    First Name: <label>
    <input type="text" name="firstName"/>
</label><br>
    Last Name: <label>
    <input type="text" name="lastName"/>
</label><br>
    Gender: <label>
    <input type="radio" name="gender" value="M"/>
</label><br>
    Gender: <label>
    <input type="radio" name="gender" value="F"/>
</label><br>
    Country: <label>
    <select name="country">
    <option selected="selected">Poland</option>
    <option>Germany</option>
    <option>France</option>
    <option>Russia</option>
    <option>Denmark</option>
</select>
</label><br>
    Notes: <label>
    <textarea name="notes" rows="5" cols="1"></textarea>
</label><br>
    <form:checkbox path="mailingList" value="Subscribe"/>
    </label><br>
    Programming Skills: <label>
    <select name="programmingSkills">
        <option>Java</option>
        <option>Java Script</option>
        <option>PHP</option>
        <option>Scratch</option>
    </select>
</label><br>
    <form:checkbox path="programmingSkills" items="${programmingSkills}"/><br>
    <form:checkbox path="hobbies" items="${hoobies}"/><br>
    <input type="submit" value="Wyślij">
</form>
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
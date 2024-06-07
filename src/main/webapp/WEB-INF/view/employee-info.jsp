<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Марат
  Date: 02.06.2024
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>>
<html>
<head>
    <title>Employee Info</title>
</head>
<body>
<form:form action="saveEmployee" modelAttribute="employee">
<%-- Эту форму мы не увидим, однако когда мы вызываем updateEmployee, то поле id не будет пустым. Так в метод saveEmployee мы отправим работника с конкретным id --%>
    <form:hidden path="id"/>
    
    Name <form:input path="name"/>
    <br>
    Surname <form:input path="surname"/>
    <br>
    Department <form:input path="department"/>
    <br>
    Salary <form:input path="salary"/>
    <br>
    <input type="submit" value="OK">
</form:form>
</body>
</html>

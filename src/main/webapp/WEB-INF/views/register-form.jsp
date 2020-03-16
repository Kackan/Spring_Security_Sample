<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration form</title>
</head>
<body>

<form:form action="${pageContext.request.contextPath}/register/saveUser" modelAttribute="user">

Name: <form:input path="username"/>

Password <form:password path="password"/>

<button type="submit">Register</button>
</form:form>

</body>
</html>
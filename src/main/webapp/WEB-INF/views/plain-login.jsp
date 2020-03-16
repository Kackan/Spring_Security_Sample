<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/authenticateTheUser">

	<c:if test="${param.error != null}">
	<i style="color:red;">Sorry! You entered invalid date.</i>
	</c:if>

	
	<p>User name: <input type="text" name="username"/> </p>
	<p>Password: <input type="password" name="password"/> </p>
	
	<input type="submit" value="Login"/>
</form:form>


Register here! <a href="${pageContext.request.contextPath}/register/showRegisterForm" > Register now!</a>


</body>
</html>
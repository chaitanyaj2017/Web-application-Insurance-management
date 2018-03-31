<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Life Insurence</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
<a href="${contextPath}/HomeController/signup.htm">Sign-Up</a><br/>
<h2>Sign-In</h2>
	<form action="${contextPath}/LogInController/authentication" method="post">
	
		<table>
		<tr>
		    <td>User Name:</td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
		</table>
		<br>
		<a href="${contextPath}/HomeController/forgotPassword.htm">Forgot Password</a>

	</form>


</body>
</html>


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add User Form</title>
<style>
table {
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid black;
}
</style>
</head>
<body>
	
	<h1>Logged out successfully !</h1>
	
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="policies" value="${sessionScope.allpolicies}" />
	<a href="${contextPath}">Login again</a>



</body>
</html>
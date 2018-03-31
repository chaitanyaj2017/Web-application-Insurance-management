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

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="policies" value="${sessionScope.allpolicies}" />
	<a href="${contextPath}/LogInController/toLogoutPage">logout</a>

	
	<br />

	<h2>Buy a new Policy :</h2>




	<form:form action="${contextPath}/LogInController/buyPolicyPage"
		commandName="policies" method="post">

		<h1>Select Policy</h1>
		<br>
		<br>
		
		
		<c:forEach var="policy" items="${policies}">
			<table border="1">
				<tr>
					<td><input type="radio" name="radio"
						value="${policy.policyName}"></td>
					<td>${policy.policyName}</td>
				</tr>
			</table>
		</c:forEach>
		<button type="submit">Submit</button>

	</form:form>




</body>
</html>
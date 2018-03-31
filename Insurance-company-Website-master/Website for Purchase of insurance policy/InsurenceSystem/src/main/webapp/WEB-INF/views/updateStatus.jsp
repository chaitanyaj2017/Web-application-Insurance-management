<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>UpdatePolicy Status</title>
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
<h3> ${errorAgeMax} </h3>
<h3> ${errorAgeMin} </h3>
<h3> ${msg} </h3>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="clientRequest" value="${sessionScope.clientRequest}" />
	<c:set var="userToprocess" value="${sessionScope.userToprocess}" />
	<a href="${contextPath}/LogInController/toLogoutPage">logout</a>
	<br>
<br><br><br>
	<form action="${contextPath}/LogInController/setStatus">
	<h3>	Client Details:</h3>
		<table border="1">
			<tr>
				<td><b>Client Id</b></td>
				<td><b>First Name</b></td>
				<td><b>Last Name</b></td>
				<td><b>Cell Phone</b></td>
				<td><b>Email Id</b></td>
				<td><b>Requested Policy</b></td>
				<td><b>Requested Policy Status</b></td>

			</tr>
			<tr>
				<td>${userToprocess.id}</td>
				<td>${userToprocess.fName}</td>
				<td>${userToprocess.lName}</td>
				<td>${userToprocess.cellPhone}</td>
				<td>${userToprocess.email}</td>
				<td>${clientRequest.policy.policyName}</td>
				<td>${clientRequest.status}</td>
			</tr>
		</table>

		Update the status Of the policy: <br>
		<br> <input type="radio" name="status" value="Approved" required="required">Approved<br>
		<input type="radio" name="status" value="Deny" required="required">Deny Or Terminate
		<button type="submit">Submit</button>
	</form>



	

</body>
</html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>employeHomePage</title>
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
	<c:set var="clientRequests" value="${sessionScope.clientRequests}" />
	<a href="${contextPath}/LogInController/toLogoutPage">logout</a><br><br>
	<a href="${contextPath}/PolicyController/addOrUpdatePolicy">Add or Update Policy</a>
	<br>
	<br>


	<form action="${contextPath}/LogInController/updateStatus">
		<table border="1">
			<tr>
				<td><b>Request Id</b></td>
				<td><b>Requested Policy</b></td>
				<td><b>Client Id</b></td>
				<td><b>Action</b></td>
			</tr>

			<c:forEach var="clientRequest" items="${clientRequests}">
				<tr>
					<td>${clientRequest.requestId}</td>
					<td>${clientRequest.policy.policyName}</td>
					<td>${clientRequest.client.clientId}</td>
					<td><a href="${contextPath}/LogInController/updateStatus?id=${clientRequest.requestId}">Update Status</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	
	
		
	



</body>
</html>
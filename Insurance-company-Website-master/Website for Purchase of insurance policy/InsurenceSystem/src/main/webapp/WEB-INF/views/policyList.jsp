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
	<%-- <h3> ${errorAgeMax} </h3>
<h3> ${errorAgeMin} </h3>
 --%>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="policies" value="${sessionScope.policies}" />
	<a href="${contextPath}/LogInController/toLogoutPage">logout</a>
	<br>
	<br>
	<br>
	<br> Policies :
	<br>

	<form action="${contextPath}/PolicyController/toAddPolicy">
		<table border="1">
			<tr>
				<td><b>Policy Id</b></td>
				<td><b>Policy Name</b></td>
				<td><b>Maturity Period</b></td>
				<td><b>Minimum Age</b></td>
				<td><b>Maximum Age</b></td>
				<td><b>Rate of Interest</b></td>
				<td><b>Premium</b></td>
			</tr>

			<c:forEach var="policy" items="${policies}">
				<tr>
					<td>${policy.policyId}</td>
					<td>${policy.policyName}</td>
					<td>${policy.maturityPeriod}</td>
					<td>${policy.minAge}</td>
					<td>${policy.maxAge}</td>
					<td>${policy.roi}</td>
					<td>${policy.premium}</td>
					<td>
				</tr>
			</c:forEach>
		</table>
		<button type="submit">Submit</button>
	</form>
</body>
</html>
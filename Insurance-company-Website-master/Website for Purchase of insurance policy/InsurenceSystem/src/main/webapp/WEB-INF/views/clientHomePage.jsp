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
	<c:set var="client" value="${sessionScope.client}" />
	<c:set var="clientRequests" value="${sessionScope.clientRequests}" />
	<a href="${contextPath}/LogInController/toLogoutPage">logout</a>

	<h2>Welcome ${user.fName} ${user.lName}</h2>

	<form action="${contextPath}/LogInController/showPolicyDetails"
		method="post">
		Your policies :
		<table>
			<tr>
				<td><b>Policy Name</b></td>
				<td><b>Maturity Period</b></td>
				<td><b>Minimum Age</b></td>
				<td><b>Maximum Age</b></td>
				<td><b>Rate of Interest<b></td>
			</tr>

			<c:forEach var="policy" items="${client.policies}">
				<tr>
					<td><input type="text" name="policyName"
						value=${policy.policyName } readonly></input></td>

					<td><input type="text" name="maturityPeriod"
						value=${policy.maturityPeriod } readonly></input></td>

					<td><input type="text" name="minAge" value=${policy.minAge
						} readonly></input></td>

					<td><input type="text" name="maxAge" value=${policy.maxAge
						} readonly></input></td>

					<td><input type="text" name="roi" value=${policy.roi
						} readonly></input></td>
				</tr>
			</c:forEach>
		</table>

		<br>
		<br>
		<br> <input type="radio" name="radio" value="allPolicies" checked>
		Show All<br> <input type="radio" name="radio" value="bestOfThree">Best
		Of Three<br> <br>

		<button type="submit" value="Submit">Submit</button>
<br><br>
Your Requests:
 <table>
			<tr>
				<td><b>Request Id</b></td>
				<td><b>Requested Policy</b></td>
				<td><b>Client Id</b></td>
				<td><b>Status</b></td>
			</tr>

			<c:forEach var="clRequest" items="${client.clientRequests}">
			<tr>
					
					<td>${clRequest.requestId}</td>
					<td>${clRequest.policy.policyName}</td>
					<td>${clRequest.client.clientId}</td>
					<td>${clRequest.status}</td>
		</tr>
			</c:forEach>
		</table>
 

	</form>

</body>
</html>


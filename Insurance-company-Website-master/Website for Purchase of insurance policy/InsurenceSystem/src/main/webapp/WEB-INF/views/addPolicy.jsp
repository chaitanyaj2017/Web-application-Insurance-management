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
<h2> ${error} </h2>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/LogInController/toLogoutPage">logout</a>
	<br><br><br><br>
	
	<form action="${contextPath}/PolicyController/addPolicy">
	
	<h3>Enter Policy Details:</h3>
	
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
			<tr>
				<td>
				<input type="text" name="policyId" " pattern="^\d{5}$" required="required"/>
				</td>
				<td>
				<input type="text" name="policyName" required="required" />
				</td>
				<td>
				<input type="text" name="matPeriod"  pattern="^\d{2}$"  required="required"/>
				</td>
				<td>
				<input type="text" name="minAge"     required="required"/>
				</td>
				<td>
				<input type="text" name="maxAge"  required="required" />
				</td>
				<td>
				<input type="text" name="roi" pattern="^\d{2}$"  required="required"/>
				</td>
				<td>
				<input type="text" name="premium"  required="required"/>
				</td>
				
			</tr>
			
		</table>
		<button type="submit">Submit</button>

	</form>

</body>
</html>
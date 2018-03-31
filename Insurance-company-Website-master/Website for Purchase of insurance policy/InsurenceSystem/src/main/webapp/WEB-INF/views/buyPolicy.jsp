<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add User Form</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="policy" value="${sessionScope.selectedpolicy}" />
	<c:set var="allEmployees" value="${sessionScope.all_user_employees}" />
	<a href="${contextPath}/LogInController/toLogoutPage">logout</a><br><br>
	<br />

	<h2>Buy a new Policy :</h2>

	<form action="${contextPath}/LogInController/requestPolicy"
		method="post">

		<%-- <h1>${selectedpolicy.policyName}</h1> --%>
		<br> <br>
		<table border="1">
			<tr>
				<td>Policy Name:</td>
				<td><input name="pname" value="${policy.policyName} " /></td>
			</tr>

			<tr>
				<td>Minimum Age:</td>
				<td><input name="minAge" value="${policy.minAge}" /></td>
			</tr>

			<tr>
				<td>Maximum Age:</td>
				<td><input name="maxAge" value="${policy.maxAge}" /></td>
			</tr>


			<tr>
				<td>Rate of Interest:</td>
				<td><input name="roi" value="${policy.roi}" /></td>
			</tr>
			
			<tr>
				<td>Premium:</td>
				<td><input name="premium" value="${policy.premium}" /></td>
			</tr>
			
		</table>
		<br> Select Agent :
		 <select name="selectedEmployee">
			<c:forEach var="user" items="${allEmployees}">	 
			<option value="${user.id}">${user.fName}  ${user.lName}</option>
			</c:forEach>
		 </select>

<%-- 
			<table>
				<tr>
					<td><input type="radio" name="radio"
						value="${policy.policyName}"> ${policy.policyName}</td>
				</tr>
			</table>
		
 --%>
 <br>
 <br>
		<input onclick="myFunction()"  type="submit" value="Request The Policy" />

	</form>


<script>
function myFunction() {
    alert("Policy requested successfully!");
}
</script>


</body>
</html>
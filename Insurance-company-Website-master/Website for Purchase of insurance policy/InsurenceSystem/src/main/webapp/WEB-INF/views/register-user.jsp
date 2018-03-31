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

	<a href="${contextPath}">Go Back</a>
	<h2> ${errorMessege} </h2>
	<br />

	<h2>Register a New User</h2>

	<form:form action="${contextPath}/HomeController/createUser.htm"
		commandName="user" method="post">

		<h1>Select the type of account:</h1>
		<br>
		<input type="radio" name="accountType" value="employee" checked> Employee<br>
		<input type="radio" name="accountType" value="client"> Client<br>
		<br>
		<table>
			<tr>
				<td>SSN:</td>
				<td><form:input path="ssn" size="30" type="number" /> <font
					color="red"><form:errors path="ssn" /></font></td>
			</tr>

			<tr>
				<td>First Name:</td>
				<td><form:input path="fName" size="30" /> <font color="red"><form:errors
							path="fName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lName" size="30" /> <font color="red"><form:errors
							path="lName" /></font></td>
			</tr>


			<tr>
				<td>Address:</td>
				<td><form:input path="address" size="30" /> <font
					color="red"><form:errors path="address" /></font></td>
			</tr>

			<tr>
				<td>CellPhone:</td>
				<td><form:input path="cellPhone" size="30" /> <font
					color="red"><form:errors path="cellPhone" /></font></td>
			</tr>

			<tr>
				<td>Type:</td>
				<td><form:input path="type" size="30" /> <font color="red"><form:errors
							path="type" /></font></td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><form:input path="userName" size="30" /> <font color="red"><form:errors
							path="userName" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30" /> <font
					color="red"><form:errors path="password" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email" size="30" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" /> <font
					color="red"><form:errors path="email" /></font></td>
			</tr>



			<tr>
				<td colspan="2"><input type="submit" value="Register User" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>
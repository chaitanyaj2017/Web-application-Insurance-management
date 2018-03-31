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

	<a href="${contextPath}">Go Back</a><br/>
		<h2>Enter Employee Information</h2>
		
		<form:form action="${contextPath}/HomeController/createInfoEmp.htm" commandName="employee" method="post">
		<table>
			<tr>
				<td>Status:</td>
				<td><form:input path="employmentStatus" size="30"
						 required="required" /> <font color="red"><form:errors
							path="employmentStatus" /></font></td>
		</tr>
		
			<tr>
				<td>Salary:</td>
				<td><form:input path="salary" size="30" pattern="^\d{5}$" 
						readonly="true" required="required" /> <font color="red"><form:errors
							path="salary" /></font></td>
		</tr>
		
		<tr>
				<td colspan="2"><input type="submit" value="CreateEmployee" /></td>
			</tr>
		
		</table>
		
		</form:form>

</body>
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
		<h2>Enter Client Information</h2>
		
		<form:form action="${contextPath}/HomeController/createInfo.htm" commandName="client" method="get">
		<table>
			<tr>
				<td>Age:</td>
				<td><form:input path="age" size="30"
						required="required" /> <font color="red"><form:errors
							path="age" /></font></td>
		</tr>
		<tr>
				<td>dob:</td>
				<td><form:input path="dob" size="30"
						required="required" /> <font color="red"><form:errors
							path="dob" /></font></td>
		</tr>
		
		
		<tr>
				<td>ocuupation:</td>
				<td><form:input path="ocuupation" size="30"
						required="required" /> <font color="red"><form:errors
							path="ocuupation" /></font></td>
		</tr>
		
		<tr>
				<td colspan="2"><input type="submit" value="CreateClient" /></td>
			</tr>
		
		</table>
		
		</form:form>

</body>
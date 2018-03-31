<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forgot Password Page</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form action="${contextPath}/HomeController/sendEmail.htm" method="get">

		<table>
			<tr>
				<td>User Name:</td>
				<td><input name="username" size="30" required="required" /></td>
			</tr>
			<tr>
				<input type="submit" value="Submit" id="button-1"/>
			</tr>
			
		</table>
		
		</form>

	

</body>
</html>


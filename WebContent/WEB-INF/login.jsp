<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Mosque Manager - Login Page</title>
</head>
<body bgcolor="black" text="white" onload='document.loginForm.masjidId.focus();'>

	<center>

	<br>	
	<h3><font face="Arial">Please login to continue:</font></h3>

	<div id="login-box">

		<br>
		<c:if test="${not empty error}">
			<div><font face="Arial" color="red">${error}</font></div>
		</c:if>
		<c:if test="${not empty msg}">
			<div><font face="Arial" color="red">${msg}</font></div>
		</c:if>

		<br>

		<form name='loginForm'
		  action="<c:url value='/login' />" method='POST'>

		<table>
			<tr>
				<td><font face="Arial">Masjid Id:</font></td>
				<td><input type='text' name='masjidId'></td>
			</tr>
			<tr>
				<td><font face="Arial">Password:</font></td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan='2' align="center"><input name="submit" type="submit"
				  value="submit" /></td>
			</tr>
		  </table>

		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		</form>
	</div>
	</center>
</body>
</html>
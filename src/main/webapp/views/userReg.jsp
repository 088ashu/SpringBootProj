<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
<style>
.error {
	color: #FF0000
}
</style>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>

<script>
	$(function() {

		$('form[id="Regform"]').validate({
			rules : {
				username : 'required',
				password : 'required',
				email : {
					required : true,
					email : true,
				},
				phno : {
					required : true,
					minlength : 10,
				}
			},
			messages : {
				username : 'You must Enter a UserName',
				password : ' Please Enter Password',
				email : 'Enter a valid email',
				phno : {
					minlength : 'PhoneNo must be at least 10 characters long'
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>
</head>
<body>

	<font color='green'>${Msg}</font>

	<h1>Register Here</h1>
	<form:form method="post" action="registerUser" modelAttribute="user"
		id="Regform">
		<table>
			<tr>
				<td>Username</td>
				<td><form:input path="username" /></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><form:input path="password" /></td>
			</tr>

			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
			</tr>

			<tr>
				<td>Phone No</td>
				<td><form:input path="phno" /></td>
			</tr>

			<tr>
				<td><input type="reset" value="Reset"></td>
				<td><input type="submit" value="Register"></td>
			</tr>
		</table>
	</form:form>
	<br>
	<br>
	<a href="showUser?pn=1">showUser</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="getEmails">usersEmail</a>
</body>
</html>
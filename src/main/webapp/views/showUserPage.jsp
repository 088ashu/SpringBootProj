<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration Form</title>
<script type="text/javascript">
	function onDelete() {
		return confirm("Are you sure, You want to delete it...")
	}

	$(document).ready(function() {
		$('#Regform').DataTable({
			"pagingType" : "full_numbers"
		});
	});
</script>
</head>
<body>
<h3>${responseMsg}</h3>
<br>
	<a href="register">+Add User</a>
	<br>
	<br>
	<br>
	<table border="1" class="display" style="width: 100%" id="Regform">
		<thead>
			<tr>
				<th>Sr no.</th>
				<th>UserName</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Action</th>
			</tr>
		</thead>
		<c:forEach items="${users}" var="user" varStatus="index">
			<tr>
				<td>${index.count}</td>
				<td>${user. username}</td>
				<td>${user.email }</td>
				<td>${user. phno}</td>
				<td><a href="deleteUser?userId=${user.uid}"
					onclick="return onDelete()">delete</a> &nbsp;&nbsp; <a
					href="editUser?userId=${user.uid}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${cp>1 }">
	<h4><a href="showUser?pn=${cp-1}">Previous</a></h4>
	</c:if>
	<c:forEach begin="1" end="${tp}" var="i">
		<c:choose>
			<c:when test="${cp == i }">
			<c:out value="${i }"/>
			</c:when>
			<c:otherwise>
<h4><a href="showUser?pn=${i}"><c:out value="${i}"></c:out></a></h4>
		</c:otherwise>
		</c:choose>	
	</c:forEach>
	<c:if test="${cp<tp }">
	<h4><a href="showUser?pn=${cp+1}">Next</a></h4>
	</c:if>
</body>
</html>
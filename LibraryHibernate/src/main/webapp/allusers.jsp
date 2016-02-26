<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<title>Library site</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
<script>
	function ConfirmDelete(delId){
		var x = confirm("Do you really want to delete this item?");
		if (x){
			window.location="userlist?delete="+delId;		
		}
		}
</script>
</head>

<t:userPage>

    <jsp:body>
		<span><b>All Users:</b></span><br/>		
		<c:if test="${empty users}">
		<span>You don't have any!</span>
		</c:if>
		<br/>
		<span><a href="adduser">Add new user...</a></span>
		<br/>		
		<c:if test="${not empty users}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th>
					<th>SSN</th>
					<th>Address</th>
					<th>Admin</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" varStatus="status" items="${users}">
						<tr>
							<td>${obj.name}</td>
							<td>${obj.ssn}</td>
							<td>${obj.address}</td>
							<td>${obj.isAdmin}</td>
							<td><a href="edituser?id=${obj.id}">edit</a></td>
							<td><input type="button" onClick="ConfirmDelete(${obj.id})" value="delete"></input></td>
							<td></td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>								
    </jsp:body>
</t:userPage>
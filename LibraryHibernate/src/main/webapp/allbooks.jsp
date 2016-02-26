<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<script>
	function ConfirmDelete(delId){
		var x = confirm("Do you really want to delete this item?");
		if (x){
			window.location="booklist?delete="+delId;		
		}
		}
</script>
<title>Library site</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
</head>

<t:userPage>
    <jsp:body>
		<span><b>All Books:</b></span><br/>		
		<c:if test="${empty books}">
		<span>You don't have any!</span>
		</c:if>
		<br/>
		<span><a href="addbook">Add new book...</a></span>
		<br/>
		<c:if test="${not empty books}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Title</th>
					<th>Author</th>
					<th>ISBN</th>
					<th>State</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" varStatus="status" items="${books}">
						<tr>
							<td>${obj.title}</td>
							<td>${obj.author}</td>
							<td>${obj.isbn}</td>
							<td>${obj.state}</td>
							<td><a href="editbook?id=${obj.id}">edit</a></td>
							<td><input type="button" onClick="ConfirmDelete(${obj.id})" value="delete"></input></td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>								
    </jsp:body>
</t:userPage>
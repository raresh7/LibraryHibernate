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
			window.location="lentbooks?delete="+delId;		
		}
		}
</script>
</head>

<t:userPage>

    <jsp:body>
		<span><b>All books ever lent:</b></span><br/>		
		<c:if test="${empty trans}">
		<span>There aren't any!</span>
		</c:if>
		<br/>
		<span><a href="addtrans">Lend a book to a user...</a></span>
		<br/>		
		<c:if test="${not empty trans}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>User</th>
					<th>Book title</th>
					<th>Date of borrowing</th>
					<th>Expected date of return</th>
					<th>Date of return</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" varStatus="status" items="${trans}">
						<tr>
							<td>${obj.user.name}</td>
							<td>${obj.book.title}</td>
							<td>${obj.dateOfBorrow}</td>
							<td>${obj.expectedDateOfReturn}</td>
							<td>${obj.dateOfReturn}</td>
							<td><a href="edittrans?id=${obj.id}">edit</a></td>
							<td><input type="button" onClick="ConfirmDelete(${obj.id})" value="delete"></input></td>
							<td></td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>								
    </jsp:body>
</t:userPage>
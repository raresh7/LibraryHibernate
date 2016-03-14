<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<title>Library site</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script>
 $(function() {
  $('#datepicker').datepicker({dateFormat: 'dd.mm.yy'});
  });
</script>
</head>

<t:userPage>

    <jsp:body>
		<span><b>Return books:</b></span><br/>		
		<c:if test="${empty trans}">
		<span>There aren't any!</span>
		</c:if>
		<br/>
		<c:if test="${not empty msg}">
			<strong><c:out value="${msg}"/></strong>
		</c:if>
		<br/>		
		<c:if test="${not empty trans}">
			<form action="returnbooks" method="post">
				<label>Date of return:</label>
				<input type="text" id="datepicker" name="dateOfReturn" required><br>	
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Selected</th>
							<th>User</th>
							<th>Book title</th>
							<th>Date of borrowing</th>
							<th>Expected date of return</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" varStatus="status" items="${trans}">
								<tr>
									<td><input type="checkbox" name="receive" value="${obj.id}"></td>
									<td>${obj.user.name}</td>
									<td>${obj.book.title}</td>
									<td>${obj.dateOfBorrow}</td>
									<td>${obj.expectedDateOfReturn}</td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="submit" value="Receive selected books">
			</form>
		</c:if>								
    </jsp:body>
</t:userPage>
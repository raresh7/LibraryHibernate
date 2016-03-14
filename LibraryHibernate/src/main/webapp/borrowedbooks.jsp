<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<title>Library site</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
</head>

<t:userPage>

    <jsp:body>
    		<c:if test="${all eq false}">
				<span><b>Books to return</b></span><br/>
			</c:if>
			<c:if test="${all eq true}">
				<span><b>All borrowed books</b></span><br/>
			</c:if>
			<ul class="nav navbar-nav">
				<c:if test="${empty borrowedBooks}">
				<span>You don't have any!</span>
				</c:if>
				<c:if test="${not empty borrowedBooks}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Date Borrowed</th>
							<th>Expected Return</th>
							
							<c:if test="${all eq true}">
								<th>Date of actual return</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" varStatus="status" items="${borrowedBooks}">
								<tr>
									<td>${obj.book.title}</td>
									<td>${obj.dateOfBorrow}</td>
									<td>${obj.expectedDateOfReturn}</td>
									<c:if test="${all eq true}">
										<td>${obj.dateOfReturn}</td>
									</c:if>
								</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>
			</ul>					
    </jsp:body>
</t:userPage>
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
    	<c:if test="${not empty msg}">
    		<c:out value="${msg}"></c:out>
    	</c:if>
		<c:if test="${empty loggedUser}">
			<b>Login:</b>
			<form action="login" method="POST">
				<label>User Name: </label> <input type="text" name="user"/>
				<input type="submit" value="login"/>
			</form>
		</c:if>
		<c:if test="${not empty loggedUser}">
			<b>Hello, ${loggedUser.name}, you may proceed now</b>
		</c:if>						
    </jsp:body>
</t:userPage>
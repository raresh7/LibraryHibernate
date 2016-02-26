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
		<span><b>Add new user:</b></span><br/>		
			<form action="adduser" method="post">
				<label style="width:80px">Name: </label><input type="text" name="name" value="${user.name}" required="required"/> <br/>
				<label style="width:80px">SSN: </label><input type="text" name="ssn" value="${user.ssn}" required="required"/> <br/>
				<label style="width:80px">Address: </label><input type="text" name="address" value="${user.address}" required="required"/> <br/>
				<label style="width:80px">Admin: </label><input type="checkbox" name="isAdmin" value="true"/> <br/>
				<input type="submit" value="Add user"/>
			</form>						
    </jsp:body>
</t:userPage>
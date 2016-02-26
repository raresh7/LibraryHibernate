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
		<ul>
			<li><label>User Name: </label> ${loggedUser.name}</li>
			<li><label>SSN: </label> ${loggedUser.ssn}</li>
			<li><label>Address: </label> ${loggedUser.address}</li>
		</ul>
					
    </jsp:body>
</t:userPage>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
	<head>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<link href="metro-bootstrap-master/css/metro-bootstrap.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
	  <div id="pageheader">
	    <%@ include file="//header.jsp" %>
	  </div>
	  <div id="body">
	    <jsp:doBody/>
	  </div>
	  <div id="pagefooter">
	    <br/><p>this is the footer<br/> Copyright and stuff</p>
	  </div>
	</body>
</html>
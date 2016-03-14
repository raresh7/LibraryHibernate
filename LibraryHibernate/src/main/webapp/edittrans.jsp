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
	  var stringDate = "${trans.dateOfBorrow}";
	  var dateParts = stringDate.match(/(\d+)/g);
	  $('#datepicker').datepicker({dateFormat: 'dd.mm.yy'});
	
	  $('#datepicker').datepicker('setDate', new Date(dateParts[0], dateParts[1] - 1, dateParts[2]));


	  stringDate = "${trans.expectedDateOfReturn}";
	  var dateParts = stringDate.match(/(\d+)/g);
	  $( "#datepicker2" ).datepicker({ dateFormat: 'dd.mm.yy' });
	  $('#datepicker2').datepicker('setDate', new Date(dateParts[0], dateParts[1] - 1, dateParts[2]));
  });
 </script>
</head>

<t:userPage>
    <jsp:body>
		<span><b>Edit transaction:</b></span><br/>		
		<c:if test="${empty trans}">
		<span>The book cannot be found!</span>
		</c:if>
		<c:if test="${not empty trans}">
			<div>
				<form action="edittrans?id=${trans.id}" method="post">
					<label style="width:80px">User: </label>
						<select name="user">
							<c:forEach var="obj" varStatus="status" items="${users}">
								<option value="${obj.id}" <c:if test="${obj.id == trans.user.id}"> <c:out value="selected"/> </c:if>>${obj.name}</option>							
							</c:forEach>
						</select>
						<br/>
						<label style="width:80px">Book: </label>
						<select name="book">
							<c:forEach var="obj" varStatus="status" items="${books}">
								<option value="${obj.id}" <c:if test="${obj.id == trans.book.id}"> <c:out value="selected"/> </c:if>>${obj.title}</option>							
							</c:forEach>
						</select>
						<br/>
						<label style="width:80px">Date of borrowing: </label>
						<input type="text" id="datepicker" name="dateOfBorrow" value="${obj.dateOfBorrow}"><br>
						<label style="width:80px">Expected date of return: </label>
						<input type="text" id="datepicker2" name="expectedDateOfReturn" value="${obj.expectedDateOfReturn}"><br>
					 <br/>
					<input type="submit" value="Save modifications"/>
				</form>
			</div>
		</c:if>								
    </jsp:body>
</t:userPage>
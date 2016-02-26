<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="clearfix">
	<nav class="navbar navbar-default" role="navigation">
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp">Home</a></li>
				<c:if test="${not empty sessionScope.appSettings}">
					<c:forEach var="obj" varStatus="status" items="${sessionScope.appSettings.tabs}">
						<c:if test="${(loggedUser.isAdmin || obj.isForAdmin == false) && (not empty loggedUser)}">
							<li class="active"><a href="${obj.url}">${obj.tabName}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="signout">Sign Out</a></li>
			</ul>
		</div>
	</nav>
</header>
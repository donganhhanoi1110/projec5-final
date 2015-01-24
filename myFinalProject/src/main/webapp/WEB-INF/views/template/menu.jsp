
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<div class="nav-outer-repeat">

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">
	<h2 style="color:white;">
		Welcome : <span style="color:yellow;">${pageContext.request.userPrincipal.name}</span> | <a style="color:white;"
			href="javascript:formSubmit()"> Logout</a> | <a style="color:orrange;"
			href="home"> Home</a>
	</h2>
</c:if>
</div>
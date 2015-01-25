
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>


<nav class="col-md-3 navigator">
	<ul>
		<li><a style="color: blue;" href="home"><span class="fa fa-user"></span>
				 Home</a></li>
		<li><a href="#dasfa"><span class="fa fa-info"></span>
				lakjsdfalkdjsf </a></li>
		<li><a href="#dasfa"><span class="fa fa-random"></span>
				lakjsdfalkdjsf </a></li>
		<li><a href="#dasfa"><c:url value="/j_spring_security_logout"
					var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form> <script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script> <c:if test="${pageContext.request.userPrincipal.name != null}">
					<div  class="fa fa-random  style="color: white;">
						Welcome : <span style="color: red;">${pageContext.request.userPrincipal.name}</span>
						| <a  href="javascript:formSubmit()">
							Logout</a> 
					</div>
				</c:if> </a></li>
	
	</ul>
</nav>
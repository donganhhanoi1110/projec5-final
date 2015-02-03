
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>


<nav class="col-md-3 navigator myMenu">
	<ul>
		<li><a href="home"><span class="fa fa-home"></span> Home</a></li>
		<li><a href="homeTransaction"><span class="fa fa-university"></span>
				Tranasaction</a></li>
		<li><a href="homeCustomer"><span class="fa fa-users"></span>
				Customer</a></li>
		<li><a href="homeSavingAccount"><span class="fa fa-suitcase"></span>
				Saving Account</a></li>

		<li><a href="homeInterestRate"><span class="fa fa-suitcase"></span>
				Interest Rate</a></li>
		<!-- 
	<li><a href="javascript:;" data-toggle="collapse"
		data-target="#demo" class="collapsed" aria-expanded="false"><i
			class="fa fa-fw fa-arrows-v"></i> SavingAccount <i
			class="fa fa-fw fa-caret-down"></i></a>
		<ul id="demo" class="collapse" aria-expanded="false"
			style="height: 0px;">
			<li><a href="homeSavingAccount">Show Hold Account</a></li>
			<li><a href="viewAllSavingAccount">Manage Account</a></li>


		</ul></li> -->

		<li><a href="home"> <c:if test="${pageContext.request.userPrincipal.name != null}">
					<div class="fa fa-chevron-circle-right  style="color:white;">
						Welcome : <span style="color: red;"><b>${pageContext.request.userPrincipal.name}</b></span>
					</div>
				</c:if> </a></li>
		<li><a href="#"><c:url value="/j_spring_security_logout"
					var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form> <script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script> <c:if test="${pageContext.request.userPrincipal.name != null}">
					<div class="fa fa-chevron-circle-right">
						<a href="javascript:formSubmit()"> Logout</a>
					</div>
				</c:if> </a></li>

	</ul>
</nav>
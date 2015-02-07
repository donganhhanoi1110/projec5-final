<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<!-- Custom Fonts -->
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/site.css" type="text/css" />
<link rel="stylesheet" href="css/screen.css">
<link rel="stylesheet" href="css/style_home.css">
<!-- jQuery -->
<script src="js/jquery/jquery.js"></script>

<script src="js/bootstrap.min.js"></script>

<style type="text/css">
body {
	background-image: url(images/myimage/nen25.jpg);
}

.error {
	color: red;
}
</style>
<body>
	<div id="page-top-outer">

		<div id="page-center" style="background-color: white;">

			<!-- start logo -->
			<div id="logo" style="padding-left: 50px">
				<a href="home"><img src="images/logo.png" width="229"
					height="74" alt="" /></a>
			</div>
		</div>
	</div>
	<h1 class="error">HTTP Status 405!! Internal Server Error!</h1>

	<c:choose>
		<c:when test="${empty username}">
			<h2>You Are Getting Error!</h2>
		</c:when>
		<c:otherwise>
			<h2>
				Username : ${username} <br />You Get Error 405!!!
			</h2>
			<h2>
				<a href="#"><c:url value="/j_spring_security_logout"
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
					</c:if> </a>
			</h2>
		</c:otherwise>
	</c:choose>
	<div class="myFooter1">

		<label><span class="fa fa-male"></span>Nguyen Anh Minh</label> <label><span
			class="fa fa-male"></span>Nguyen Xuan Phu</label> <label><span
			class="fa fa-female"></span>Nguyen Thi Ngoc Ngan</label>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<title></title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/site.css" type="text/css" />
	<link rel="stylesheet" href="css/screen.css">

	<!-- jQuery -->
    <script src="js/jquery/jquery.js"></script>
    
    <script src="js/bootstrap.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#show").click(function() {
			$("#hide").slideToggle();
		});

	});
</script>

</head>
<body onload='document.loginForm.username.focus();'>

	<div id="content-outer">
		<!-- start content -->
		<div id="content">
			<div style="margin: 24% auto" class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Login</h3>
						</div>
						<div class="panel-body">

							<form action="<c:url value='/j_spring_security_check' />" role="form" name='loginForm'
								method="post">
								<c:if test="${not empty error}">
									<div class="error">${error}</div>
								</c:if>
								<c:if test="${not empty msg}">
									<div class="msg">${msg}</div>
								</c:if>
								<div class="form-group">
									<label for="username" class="control-label">Username</label>
									<input type="text" name="username" class="form-control"
										id="username" placeholder="username">
								</div>
								<div class="form-group">
									<label for="password" class="control-label">Password</label>
									<input type="password" name="password" class="form-control"
										id="password" placeholder="Password">
								</div>


								<div class="form-group">
									<button type="submit" name="submit" class="btn btn-primary">Sign in</button>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div>

					</div>
				</div>
			</div>

		</div>

	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min yelti.css">
<link rel="stylesheet" type="text/css" href="css/site.css">
<script language="javascript" src="js/jquery-2.1.3.js"></script>
<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script language="javascript" src="js/jquery-2.1.3.js"></script>
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>

<script src="js/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#show").click(function() {
			$("#hide").slideToggle();
		});

	});
</script>

</head>
<body onload='document.loginForm.username.focus();'>
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>



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


							<form action="<c:url 

value='/j_spring_security_check' />"
								class="form-horizontal" role="form" name='loginForm'
								method="post">
								<div class="form-group">
									<label for="username" class="col-md-2 control-label">Username</label>
									<div class="col-

md-8">
										<input type="text" name="username" class="form-control"
											id="username" placeholder="username">
									</div>
								</div>
								<div class="form-group">
									<label for="password" class="col-md-2 control-label">Password</label>
									<div class="col-

md-8">
										<input type="password" name="password" class="form-control"
											id="password" placeholder="Password">
									</div>
								</div>


								<div class="form-group">
									<div class="col-

md-offset-2 col-md-8">
										<button type="submit" name="submit" class="btn btn-primary">Sign


											in</button>
									</div>
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
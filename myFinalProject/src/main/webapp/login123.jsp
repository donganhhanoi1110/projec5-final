<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min yelti.css">
<link rel="stylesheet" type="text/css" href="css/site.css">
<script language="javascript" src="js/jquery-2.1.3.js"></script>
<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#show").click(function() {
							$("#hide").slideToggle();
						});
						
						
					});
</script>

</head>
<body>

	<div style="margin: 24% auto" class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Login</h3>
				</div>
				<div class="panel-body" id="hide">


					<form action="login123.html" class="form-horizontal" role="form"
						method="post">
						<div class="form-group">
							<label for="username" class="col-md-2 control-label">Username</label>
							<div class="col-md-8">
								<input type="text" name="loginId" class="form-control"
									id="username" placeholder="username">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-md-2 control-label">Password</label>
							<div class="col-md-8">
								<input type="password" name="password" class="form-control"
									id="password" placeholder="Password">
							</div>
						</div>


						<div class="form-group">
							<div class="col-md-offset-2 col-md-8">
								<button type="submit" class="btn btn-primary">Sign in</button>
							</div>
						</div>
						
					</form>
				</div>
				<div id="show">show</div>
			</div>
		</div>
	</div>

</body>
</html>
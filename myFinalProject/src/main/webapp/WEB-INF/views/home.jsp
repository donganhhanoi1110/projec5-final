<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CSC Banking System</title>

</head>
<body>
	<%-- 	<c:if test="${loginSession == null}"><jsp:forward
			page="/login.jsp" /></c:if> --%>
	<div id="page-top-outer">

		<div id="page-center" style="background-color: white;">

			<!-- start logo -->
			<div id="logo" style="padding-left: 50px">
				<a href=""><img src="images/logo.png" width="229" height="74"
					alt="" /></a>
			</div>
		</div>
	</div>
	<div class="nav-outer-repeat"></div>

	<div id="content-outer">
		<!-- start content -->
		<div id="content">

			<!--  start page-heading -->
			<div id="page-heading">
				<h1>Account Management</h1>
				<h1 style="color: red">${minh}</h1>
			</div>
			<table border="0" width="100%" cellpadding="0" cellspacing="0"
				id="content-table">
				<tr>
					<th rowspan="3" class="sized"><img
						src="images/shared/side_shadowleft.jpg" width="20" height="300"
						alt="" /></th>
					<th class="topleft"></th>
					<td id="tbl-border-top">&nbsp;</td>
					<th class="topright"></th>
					<th rowspan="3" class="sized"><img
						src="images/shared/side_shadowright.jpg" width="20" height="300"
						alt="" /></th>
				</tr>
				<tr>
					<td id="tbl-border-left"></td>
					<td>
						<div id="content-table-inner">
							<div id="page-heading">
								<h1 style="color: red;">
									<c:if test="${!

empty ERROR_CODE }">
										<c:out value="${ERROR_CODE }"></c:out>
										<script>
											alert("You must have something...");
										</script>
									</c:if>
								</h1>
								<h1>Add Account Information</h1>

							</div>

							<div class="panel-body">


								<form action="homePost.html" class="form-horizontal" role="form"
									method="get">
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


											<button type="submit" class="btn btn-primary">Sign


												in</button>
										</div>
									</div>

								</form>
							</div>


						</div>
					</td>
					<td id="tbl-border-right"></td>
				</tr>
				<tr>
					<th class="sized bottomleft"></th>
					<td id="tbl-border-bottom">&nbsp;</td>
					<th class="sized bottomright"></th>
				</tr>
			</table>
			<br />
		</div>

	</div>


</body>
</html>
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


	<div id="content-outer">
		<!-- start content -->
		<div id="content">

			<!--  start page-heading -->
			<div id="page-heading">
			
				<h1 style="color: red">${message}</h1>
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
									<c:if test="${!empty ERROR_CODE }">
										<%-- <c:out value="${ERROR_CODE }"></c:out> --%>
										<c:if test="${ ERROR_CODE == '1'}">
											<script>
												alert("Successfully!!!");
											</script>
										</c:if>
										<c:if test="${ ERROR_CODE == '0'}">
											<script>
												alert("You are getting Error!!!");
											</script>
										</c:if>
									</c:if>
								</h1>
								<h1>Edit User Information</h1>

							</div>
							<table border="0" width="100%" cellpadding="0" cellspacing="0">
								<tr valign="top">
									<td>
										<form action="editUserProfile.html" method="post">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
											<table border="0" cellpadding="0" cellspacing="0"
												class="table table-striped table-bordered" id="id-form">

												<c:forEach var="user" items="${userProfile}">
													<tr>
														<th valign="top">ID :</th>
														<td><input type="hidden" class="inp-form"
															name="userId" value="${user.userId}" /> ${user.userId}</td>
													</tr>
													<tr>
														<th valign="top">User Name</th>
														<td><input type="text" class="form-control"
															name="userName" value="${user.userName}" /></td>
													</tr>
													<tr>
														<th valign="top">Password:</th>
														<td><input type="text" class="form-control"
															name="password" value="${user.password}" /></td>
										
													</tr>
													<tr>
														<th valign="top">Enable:</th>
														<td><input type="text" class="form-control"
															name="enable" value="${user.enable}" /></td>
											
													</tr>

													<tr>
														<td></td>
														<td><input type="submit" class="myButton"
															value="Update User" /></td>
												
													</tr>
												</c:forEach>
											</table>
										</form>
									</td>
								</tr>

							</table>






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
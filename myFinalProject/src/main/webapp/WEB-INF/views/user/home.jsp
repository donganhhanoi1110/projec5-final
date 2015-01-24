<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
				<h1>Account Management</h1>
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
												alert("You are getting Error when Create, Update or Delete!!!");
											</script>
										</c:if>
									</c:if>
								</h1>
								<h1>Add Account Information</h1>

							</div>

							<div id="hide">
								<div style="float: left;">
									<table border="0" width="100%" cellpadding="0" cellspacing="0">
										<tr valign="top">
											<td>
												<form action="createUser.html" method="post">
													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" />
													<table border="0" cellpadding="0" cellspacing="0"
														id="id-form">

														<tr>
															<th valign="top">User Name:</th>
															<td><input type="text" class="css-input"
																name="username" /></td>
														</tr>
														<tr>
															<th valign="top">Password:</th>
															<td><input type="text" class="css-input"
																name="password" /></td>
															<td></td>
														</tr>
														<tr>
															<th valign="top">Enable</th>
															<td><input type="text" class="css-input"
																name="enable" /></td>
															<td></td>
														</tr>
														<tr>
															<td></td>
															<td><input type="submit" class="myButton"
																value="Save" id="addAccount" /></td>
															<td></td>
														</tr>
													</table>
												</form>
											</td>
										</tr>

									</table>
								</div>
								<div style="float: right; margin-right: 20%;">


									<a class="myButton" href="addCard.html" style="margin: 5px;">
										Create Card</a> <a class="myButton" href="addNewTransaction.html">
										Create Transaction</a>


								</div>
							</div>


							<div id="show">
								<b> >>>Manage User<<< </b>
							</div>
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="mytable1" id="dataTables-example">
										<thead>
											<tr>
												<th>ID</th>
												<th>User Name</th>
												<th>Password</th>
												<th>Enable</th>
												<th>Delete User</th>
												<th>Edit User</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="user" items="${listUser}">
												<c:if test="${user.userName != null}">
													<tr>
														<td>${user.userId}</td>
														<td>${user.userName}</td>
														<td>${user.password}</td>
														<td>${user.enable}</td>
														<td><a href="deleteUser.html?userId=${user.userId}"
															class="myButton">Delete</a></td>
														<td><a href="editUser.html?userId=${user.userId}"
															class="myButton">Edit</a></td>

													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
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
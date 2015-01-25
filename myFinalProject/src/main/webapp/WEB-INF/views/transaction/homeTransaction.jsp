<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>CSC Banking System</title>


<script type="text/javascript">
	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	$(document)
			.ready(
					function() {
						$("#table").DataTable({
							responsive : true
						});
						$("#show").click(function() {
							$("#hide").slideToggle();
						});
						$(document)
								.on(
										"click",
										"#linkDeleteUser",
										function(e) {
											e.preventDefault();

											var confirmMessage = confirm("Do you date to delete this user?");
											if (confirmMessage) {
												//to delete user page
												var userId = $(this).attr(
														"user");
												/* 	$(this).closest("tr").remove(); */

												//if you want to reload the page
												//window.location.href = 'deleteUser?userId=' + userId;
												// if you user ajax
												$
														.ajax({
															type : 'post',
															url : 'deleteUserJson',
															data : 'userId='
																	+ userId,
															datatype : 'json',
															success : function(
																	data) {
																console
																		.log(data);
																if (data.login == true) {
																	if (data.success == true) {
																		alert("Delete successfully!");
																		if (data.error_code == '0') {
																			window.location.href = 'home?ERROR_CODE='
																					+ data.error_code;
																		} else {
																			window.location.href = 'home';
																		}

																	} else {
																		alert("Delete Failed");
																	}
																} else {
																	window.location.href = 'login';
																}
															}
														});

											} else {
												//do nothing
											}
										});
					});
</script>
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
												alert("You are getting Error when Create, Update or Delete!!!");
											</script>
										</c:if>
									</c:if>
								</h1>
								<h1>Add User Information</h1>

							</div>

							<div id="hide">
								<div style="float: left;">

									<form action="createUser.html" method="post">
										<table border="0" cellpadding="0" cellspacing="0" id="id-form"
											class="table table-striped table-bordered">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
											<tr>
												<th valign="top">User Name:</th>
												<td><input type="text" class="form-control"
													name="username" /></td>
											</tr>
											<tr>
												<th valign="top">Password:</th>
												<td><input type="text" class="form-control"
													name="password" /></td>
									
											</tr>
											<tr>
												<th valign="top">Enable</th>
												<td><input type="text" class="form-control"
													name="enable" /></td>
									
											</tr>
											<tr>
												<td></td>
												<td><input type="submit" class="myButton" value="Save"
													id="addAccount" /></td>
									
											</tr>
										</table>
									</form>

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
									<table class="mytable1 table table-striped table-bordered "
										id="table">
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
														<td><a user="${user.userId}" href=""
															id="linkDeleteUser" class="myButton"> Delete </a></td>
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
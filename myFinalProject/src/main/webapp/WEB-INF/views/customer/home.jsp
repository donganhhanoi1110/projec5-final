<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script src="js/popup/CreateCustomer.js"></script>
<title>CSC Banking System</title>
<style type="text/css">
.error {
	color: red;
}
</style>

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
										"#linkDeleteCustomer",
										function(e) {
											e.preventDefault();

											var confirmMessage = confirm("Do you date to delete this user?");
											if (confirmMessage) {
												//to delete user page
												var customerId = $(this).attr(
														"customer");
												/* 	$(this).closest("tr").remove(); */

												//if you want to reload the page
												//window.location.href = 'deleteUser?userId=' + userId;
												// if you user ajax
												$
														.ajax({
															type : 'post',
															url : 'deleteCustomerJson',
															data : 'customerId='
																	+ customerId,
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
			<!-- begin popup -->
	<div class="popupContainer">
		<div class="popup">
		<div class="headerPopup">
						<span> Add New Customer</span>
						<button class="popupCloseButton">X</button>
					</div>
			<div class="panel-body">
				<div>
					<div style="float: left;">

						<form:form action="createCustomer.html" method="POST"
							modelAttribute="customer">
							<table border="0" cellpadding="0" cellspacing="0" id="id-form"
								class="table table-striped table-bordered">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<tr>
									<th valign="top">Account Number:</th>
									<td><form:input class="form-control   "
											path="accountNumber" /></td>
									<td><form:errors path="accountNumber" cssClass="error" /></td>
									<th valign="top">Account Type</th>
									<td><form:input class="form-control   " path="accountType" /></td>
									<td><form:errors path="accountType" cssClass="error" /></td>

								</tr>
								<tr>
									<th valign="top">First Name</th>
									<td><form:input class="form-control  " path="firstName" /></td>
									<td><form:errors path="firstName" cssClass="error" /></td>
									<th valign="top">Last Name:</th>
									<td><form:input class="form-control  " path="lastName" /></td>
									<td><form:errors path="lastName" cssClass="error" /></td>
								</tr>

								<tr>
									<th valign="top">Mid Name:</th>
									<td><form:input class="form-control  " path="midName" /></td>
									<td><form:errors path="midName" cssClass="error" /></td>
									<th valign="top">ID Number</th>
									<td><form:input class="form-control  " path="idNumber" /></td>
									<td><form:errors path="idNumber" cssClass="error" /></td>
								</tr>

								<tr>
									<th valign="top">Phone 1</th>
									<td><form:input class="form-control  " path="phone1" /></td>
									<td><form:errors path="phone1" cssClass="error" /></td>
									<th valign="top">Phone 2</th>
									<td><form:input class="form-control  " path="phone2" /></td>
									<td><form:errors path="phone2" cssClass="error" /></td>
								</tr>

								<tr>
									<th valign="top">Address 1</th>
									<td><form:input class="form-control  " path="add1" /></td>
									<td><form:errors path="add1" cssClass="error" /></td>
									<th valign="top">Address 2</th>
									<td><form:input class="form-control  " path="add2" /></td>
									<td><form:errors path="add2" cssClass="error" /></td>

								</tr>

								<tr>
									<th valign="top">Mail:</th>
									<td><form:input class="form-control  " path="email" /></td>
									<td><form:errors path="email" cssClass="error" /></td>
									<th valign="top">State:</th>
									<td><form:input class="form-control  " path="state" /></td>
									<td><form:errors path="state" cssClass="error" /></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="submit" class="myButton" value="Save"
										id="addCustomer" /></td>
									<td></td>
									<td></td>

								</tr>
							</table>
						</form:form>

					</div>

				</div>
			</div>

		</div>
	</div>
	<!-- End popupContainer -->
	<button class="CreateCustomer myButton" value="CreateCustomer"><span class="fa fa-plus"></span> 
		Add New Saving Account</button>
	<div id="content-outer">
		<!-- start content -->
		<div id="content">

			<!--  start page-heading -->
			<div id="page-heading">

				<h1 style="color: red">${message}</h1>
			</div>

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
					<h1>Add customer Information</h1>

				</div>
				<div class="clearfix"></div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="mytable1 table table-striped table-bordered "
							id="table">
							<thead>
								<tr>
									<th>ID</th>
									<th>Account Number</th>
									<th>Account type</th>
									<th>Full Name</th>
									<th>ID Number</th>
									<th>Phone 1</th>

									<th>Address 1</th>

									<th>Email</th>
									<th>State</th>
									<th>Delete Customer</th>
									<th>Edit Customer</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="cus" items="${listCustomer}">
									<c:if test="${cus.id != null}">
										<tr>
											<td>${cus.id}</td>
											<td>${cus.accountNumber}</td>
											<td>${cus.accountType}</td>
											<td>${cus.firstName}${cus.midName}${cus.lastName}</td>
											<td>${cus.idNumber}</td>
											<td>${cus.phone1}</td>
											<td>${cus.add1}</td>
											<td>${cus.email}</td>
											<td>${cus.state}</td>
											<td><a customer="${cus.id}" href=""
												id="linkDeleteCustomer" class="myButton"> Delete </a></td>
											<td><a href="editCustomer.html?custID=${cus.id}"
												class="myButton">Edit</a></td>

										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</div>

			<br />
		</div>

	</div>


</body>
</html>
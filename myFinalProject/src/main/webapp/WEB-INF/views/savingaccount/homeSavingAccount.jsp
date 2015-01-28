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
										"#linkDeleteTransaction",
										function(e) {
											e.preventDefault();

											var confirmMessage = confirm("Do you want to delete this Transaction?");
											if (confirmMessage) {
												//to delete Transaction page
												var TransactionId = $(this)
														.attr("Transaction");
												/* 	$(this).closest("tr").remove(); */

												//if you want to reload the page
												//window.location.href = 'deleteTransaction?TransactionId=' + TransactionId;
												// if you Transaction ajax
												$
														.ajax({
															type : 'post',
															url : 'deleteTransactionJson',
															data : 'TransactionId='
																	+ TransactionId,
															datatype : 'json',
															success : function(
																	data) {
																console
																		.log(data);
																if (data.login == true) {
																	if (data.success == true) {
																		alert("Delete successfully!");
																		if (data.error_code == '0') {
																			window.location.href = 'homeTransaction?ERROR_CODE='
																					+ data.error_code;
																		} else {
																			window.location.href = 'homeTransaction';
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
								<h1>Add Saving Account Information</h1>

							</div>

							<div id="hide">
								<div style="float: left;">

									<form action="createSavingAccount.html" method="post">
										<table border="0" cellpadding="0" cellspacing="0" id="id-form"
											class="table table-striped table-bordered">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />

											<tr>
												<th valign="top">Customer ID:</th>
												<td><input type="text" class="form-control"
													name="customerId" /></td>
											</tr>
											<tr>
												<th valign="top"> Balance Amount:</th>
												<td><input type="text" class="form-control"
													name="balanceAmount" /></td>

											</tr>
											<tr>
												<th valign="top">Repeatable:</th>
												<td><input type="text" class="form-control"
													name="repeatable" /></td>

											</tr>

											<tr>
												<th valign="top">interest Rate Id:</th>
												<td><select class="form-control"
													name="interestRateId">
														<option value="noterm"> no term </option>
														<option value="3months">3 months</option>
														<option value="6months">6 months</option>
														<option value="12months">12 months</option>
												</select></td>

											</tr>
																						<tr>
												<th valign="top">State:</th>
												<td><select class="form-control"
													name="state">
														<option value="new"> New </option>
														<option value="hold">Hold</option>
														<option value="active">Active</option>
														<option value="done">Done</option>
												</select></td>

											</tr>
											<tr>
												<td></td>
												<td><input type="submit" class="myButton" value="Save"
													id="addAccount" /></td>

											</tr>
										</table>
									</form>

								</div>

							</div>


							<div id="show">
								<b> >>>Manage Transaction<<< </b>
							</div>
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="mytable1 table table-striped table-bordered "
										id="table">
										<thead>
											<tr>
												<th>Saving Account Id</th>
												<th>Customer ID</th>
												<th>Saving Balance Amount</th>
												<th>Repeatable</th>
												<th>Interest Rate</th>
												<th>State</th>
												<sec:authorize access="hasRole('admin')">
													<th>Approve Saving Account</th>

													<th>Deny Saving Account</th>

													<th>Delete Saving Account</th>
													<th>Edit Saving Account</th>
												</sec:authorize>
												<sec:authorize access="hasRole('support')">
													<th>Submit Saving Account</th>
												</sec:authorize>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="savingAccount" items="${listSavingAccount}">

												<tr>
													<td>${savingAccount.id}</td>
													<td>${savingAccount.customerId}</td>
													<td>${savingAccount.balanceAmount}</td>
													<td>${savingAccount.repeatable}</td>
													<td>${savingAccount.interestRateId}</td>
													<td>${savingAccount.state}</td>
													<sec:authorize access="hasRole('admin')">
														<td><a
															href="approveSavingAccount.html?SavingAccountId=${savingAccount.id}"
															class="myButton">Approve</a></td>
														<td><a
															href="denySavingAccount.html?SavingAccountId=${savingAccount.id}"
															class="myButton">Deny</a></td>
														<td><a Transaction="${savingAccount.id}" href=""
															id="linkDeleteSavingAccount" class="myButton"> Delete </a></td>
														<td><a
															href="editSavingAccount.html?TransactionId=${savingAccount.id}"
															class="myButton">Edit</a></td>
														<input type="hidden" name="${_csrf.parameterName}"
															value="${_csrf.token}" />
													</sec:authorize>
													<sec:authorize access="hasRole('support')">
														<td><a
															href="submitSavingAccount.html?TransactionId=${savingAccount.id}"
															class="myButton">Submit</a></td>
													</sec:authorize>

												</tr>

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
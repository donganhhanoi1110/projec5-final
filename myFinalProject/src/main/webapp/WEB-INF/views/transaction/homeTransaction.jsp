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

											var confirmMessage = confirm("Do you date to delete this Transaction?");
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

<div id="manu_main">
		<ul>

			<li><a href="homeTransaction.html">Home Transaction</a></li>
			<li><a href="viewTransaction.html">View All Transaction</a></li>
	<li>
				<form action="searchSavingAccount.html" method="post">
					<table>

						<tr>
							<td><input type="hidden" name="${_csrf.parameterName}"
								style="width: 15px; height: 30px;" value="${_csrf.token}" /> <input
								name="searchSavingAcount" type="text" /></td>
							<td><select name="searchType" class="searchBox">
									<option value="accountNumber">Account Number</option>
									<option value="idNumber">ID Number's Customer</option>
							</select></td>
							<td><input type="submit" value="Search" id="addAccount" /></td>
						</tr>

					</table>
				</form>

			</li>

		</ul>
	</div>
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
					<h1>Add Transaction Information</h1>

				</div>

				<div id="hide">
					<div style="float: left;">

						<form action="createTransaction.html" method="post">
							<table border="0" cellpadding="0" cellspacing="0" id="id-form"
								class="table table-striped table-bordered">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<tr>
									<th valign="top">Amount</th>
									<td><input type="text" class="form-control"
										name="transactionAmount" /></td>
								</tr>
								<tr>
									<th valign="top">Start Time:</th>
									<td><input type="text" class="form-control"
										name="transactionDateStart" /></td>

								</tr>
								<tr>
									<th valign="top">End Time:</th>
									<td><input type="text" class="form-control"
										name="transactionDateEnd" /></td>

								</tr>
								<tr>
									<th valign="top">Saving Account ID:</th>
									<td><input type="text" class="form-control"
										name="transactionSavingAccountId" /></td>
									<%-- 		<td><select class="form-control"
													name="transactionSavingAccountId">
														<c:forEach var="savingaccount"
															items="${listSavingAccount }">
															<option value="${savingaccount.id}">${savingaccount.id }
															</option>

														</c:forEach>

												</select></td> --%>

								</tr>

								<tr>
									<th valign="top">State:</th>
									<td><select class="form-control" name="transactionState">
											<option value="new">new</option>
											<option value="hold">hold</option>
											<option value="active">active</option>
											<option value="done">done</option>
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
									<th>ID</th>
									<th>Amount</th>
									<th>Start Time</th>
									<th>End Time</th>
									<th>Saving Account Id</th>
									<th>State</th>
									<sec:authorize access="hasRole('admin')">
										<th>Approve Transaction</th>

										<th>Deny Transaction</th>

										<th>Delete Transaction</th>
										<th>Edit Transaction</th>
									</sec:authorize>
									<sec:authorize access="hasRole('support')">
										<th>Submit Transaction</th>
									</sec:authorize>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="Transaction" items="${listTransaction}">
									<c:if test="${Transaction.id != null}">
										<tr>
											<td>${Transaction.id}</td>
											<td>${Transaction.amount}</td>
											<td>${Transaction.dateStart}</td>
											<td>${Transaction.dateEnd}</td>
											<td>${Transaction.savingAccountId}</td>
											<td class="${Transaction.state}">${Transaction.state}</td>
											<sec:authorize access="hasRole('admin')">
												<td><a
													href="approveTransaction.html?TransactionId=${Transaction.id}"
													class="myButton">Approve</a></td>
												<td><a
													href="denyTransaction.html?TransactionId=${Transaction.id}"
													class="myButton">Deny</a></td>
												<td><a Transaction="${Transaction.id}" href=""
													id="linkDeleteTransaction" class="myButton"> Delete </a></td>
												<td><a
													href="editTransaction.html?TransactionId=${Transaction.id}"
													class="myButton">Edit</a></td>
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}" />
											</sec:authorize>
											<sec:authorize access="hasRole('support')">
												<td><a
													href="submitTransaction.html?TransactionId=${Transaction.id}"
													class="myButton">Submit</a></td>
											</sec:authorize>

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
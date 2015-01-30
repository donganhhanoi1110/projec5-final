<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

						<form:form action="createTransactionAttribute.html" method="post"
							modelAttribute="transaction">
							<table border="0" cellpadding="0" cellspacing="0" id="id-form"
								class="table table-striped table-bordered">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<tr>
									<th valign="top"><form:label path="amount">Amount</form:label>
									</th>
									<td><form:input path="amount" class="form-control" /></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="dateStart">Date Start</form:label>
									</th>
									<td><form:input path="dateStart" class="form-control" /></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="dateEnd">Date End</form:label>
									</th>
									<td><form:input path="dateEnd" class="form-control" /></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="savingAccountId.id">Saving Account</form:label></th>
									<td><form:select path="savingAccountId.id"
											multiple="false" class="form-control">
											<form:options items="${savingaccountlist}" itemValue="id"
												itemLabel="savingAccountNumber" />
										</form:select></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="transactionType">Transaction Type</form:label></th>
									<td><form:select path="transactionType"
											class="form-control">
											<form:option value="deposit">new</form:option>
											<form:option value="withdraw">withdraw</form:option>

										</form:select></td>

								</tr>
								<tr>
									<td></td>
									<td><input type="submit" class="myButton" value="Save"
										id="addAccount" /></td>

								</tr>
							</table>
						</form:form>

					</div>

				</div>


				<div id="show">
					<b> >>>Manage Transaction<<< </b>
				</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="mytable1 table table-striped table-bordered"
							id="table">
							<thead>
								<tr>
									<th>ID</th>
									<th>Amount</th>
									<th>Start Time</th>
									<th>End Time</th>
									<th>Account Number</th>
									<th>State</th>
									<th>Transaction Type</th>
									<th>Current Balance</th>
									<th>After Balance</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="Transaction" items="${listDoneTransaction}">
									<c:if test="${Transaction.id != null}">
										<tr>
											<td>${Transaction.id}</td>
											<td>${Transaction.amount}</td>
											<td>${Transaction.dateStart}</td>
											<td>${Transaction.dateEnd}</td>
											<td>${Transaction.savingAccountId.savingAccountNumber}</td>
											<td>${Transaction.state}</td>
											<td>${Transaction.transactionType}</td>
											<td>${Transaction.currentBalance}</td>
											<td>${Transaction.afterBalance}</td>

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
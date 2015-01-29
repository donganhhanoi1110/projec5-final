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
										"#linkDeleteSavingAccount",
										function(e) {
											e.preventDefault();

											var confirmMessage = confirm("Do you  to delete this SavingAccount?");
											if (confirmMessage) {
												//to delete SavingAccount page
												var savingAccountId = $(this)
														.attr("SavingAccount");
												/* 	$(this).closest("tr").remove(); */

												//if you want to reload the page
												//window.location.href = 'deleteSavingAccount?SavingAccountId=' + SavingAccountId;
												// if you SavingAccount ajax
												$
														.ajax({
															type : 'post',
															url : 'deleteSavingAccountJson',
															data : 'savingAccountId='
																	+ savingAccountId,
															datatype : 'json',
															success : function(
																	data) {
																console
																		.log(data);
																if (data.login == true) {
																	if (data.success == true) {
																		alert("Delete successfully!");
																		if (data.error_code == '0') {
																			window.location.href = 'homeSavingAccount?ERROR_CODE='
																					+ data.error_code;
																		} else {
																			window.location.href = 'homeSavingAccount';
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

				</div>
				<div class="panel-body">
					<h1>Waiting SavingAccount</h1>
					<div class="dataTable_wrapper">
						<table class="mytable1 table table-striped table-bordered "
							id="table">
							<thead>
								<tr>
									<th>Saving Account Number</th>
									<th>Customer</th>
									<th>Saving Balance Amount</th>
									<th>Repeatable</th>
									<th>Interest Rate</th>
									<th>State</th>
									<th>Date Start</th>
									<th>Date End</th>
									<sec:authorize access="hasRole('admin')">
										<th>Approve Saving Account</th>
										<th>Deny Saving Account</th>
										<th>Delete Saving Account</th>
										<th>Edit Saving Account</th>
									</sec:authorize>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="savingAccount" items="${listHoldSavingAccount}">

									<tr>
										<td>${savingAccount.savingAccountNumber}</td>
										<td>${savingAccount.customerId.firstName}</td>
										<td>${savingAccount.balanceAmount}</td>
										<td>${savingAccount.repeatable}</td>
										<td>${savingAccount.interestRateId.savingAccountType}&nbsp;
														${savingAccount.interestRateId.interestRate}%&nbsp;
														(${savingAccount.interestRateId.currency})</td>
													<td>${savingAccount.state}</td>
										
										<td>${savingAccount.dateStart}</td>
										<td>${savingAccount.dateEnd}</td>
										<sec:authorize access="hasRole('admin')">
											<td><a
												href="approveSavingAccount.html?SavingAccountId=${savingAccount.id}"
												class="myButton">Approve</a></td>
											<td><a
												href="denySavingAccount.html?SavingAccountId=${savingAccount.id}"
												class="myButton">Deny</a></td>
											<td><a SavingAccount="${savingAccount.id}" href=""
												id="linkDeleteSavingAccount" class="myButton"> Delete </a></td>
											<td><a
												href="editSavingAccount.html?SavingAccountId=${savingAccount.id}"
												class="myButton">Edit</a></td>
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
										</sec:authorize>


									</tr>

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
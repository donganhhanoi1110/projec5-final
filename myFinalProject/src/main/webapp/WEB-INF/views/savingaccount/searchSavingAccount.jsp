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

						/*For Popup only  */
						$(".viewSavingAccount").bind("click", function(e) {
							e.preventDefault();
							$(".popupContainer").show();
						})

						$(".popupCloseButton").bind("click", function(e) {
							$(".popupContainer").hide();
						})
						$(".popupContainer").bind("click", function(e) {
							$(".popupContainer").hide();
						})
					});
</script>
</head>
<body>
	<%-- 	<c:if test="${loginSession == null}"><jsp:forward
			page="/login.jsp" /></c:if> --%>

	<div id="manu_main">
		<ul>

			<li><a href="homeSavingAccount.html">Home Saving Acccount</a></li>
			<li><a href="viewAllSavingAccount.html">View All Saving
					Account</a></li>

			<li>
				<form action="searchSavingAccount.html" method="post">
					<table>

						<tr>
							<td><input type="hidden" name="${_csrf.parameterName}"
								style="width: 15px; height: 30px;" value="${_csrf.token}" /> <input
								name="searchSavingAcount" type="text" /></td>
							<td><select name="searchType"
								style="width: 50px; height: 20px; margin-left: 3px; margin-right: 3px;">
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
				<c:if test="${message != null}">

					<h1 id="error">${message }</h1>
				</c:if>
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




				<h1>Search Saving Account Information</h1>
				<div class="panel-body">
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

										<th>Edit Saving Account</th>

									</sec:authorize>


									<th>View</th>



								</tr>
							</thead>
							<tbody>
								<c:forEach var="savingAccount" items="${listSavingAccount}">

									<tr>
										<td><a>${savingAccount.savingAccountNumber}</a></td>
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
												href="editSavingAccount.html?SavingAccountId=${savingAccount.id}"
												class="myButton">Edit</a></td>
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
										</sec:authorize>


										<td><a SavingAccount="${savingAccount.id}" href=""
											id="linkViewSavingAccount" class="myButton viewSavingAccount">
												View</a></td>



									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<!--End If check for Account Information, null => dont show  -->
				<table border="0" cellpadding="0" cellspacing="0" id="id-form"
					class="table table-striped table-bordered">


					<tr>
						<th valign="top">Account Number:</th>
						<td><span id="accountNumber"></span></td>

						<th valign="top">Account Type</th>
						<td><span id="accountType"></span></td>


					</tr>
					<tr>
						<th valign="top">Full Name</th>
						<td><span id="fullName"></span></td>

						<th valign="top">ID Number</th>
						<td><span id="idNumber"></span></td>
					</tr>

					<tr>
						<th valign="top">Phone 1</th>
						<td><span id="phone1"></span></td>
						<th valign="top">Phone 2</th>
						<td><span id="phone2"></span></td>
					</tr>

					<tr>
						<th valign="top">Address 1</th>
						<td><span id="add1"></span></td>
						<th valign="top">Address 2</th>
						<td><span id="add2"></span></td>

					</tr>

					<tr>
						<th valign="top">Mail:</th>
						<td><span id="mail"></span></td>
						<th valign="top"></th>
						<td><span id=""></span></td>

					</tr>



					<tr>
						<td><input type="button" class="myButton viewSavingAccount"
							value="Withdraw" id="withDraw" /></td>
						<td><input type="button" class="myButton"
							value="CreateTransaction" id="createTransaction" /></td>
						<td></td>
						<td></td>

					</tr>
				</table>

			</div>

			<br />
		</div>

	</div>

	<div class="popupContainer">
		<div class="popup">
			<button class="popupCloseButton">X</button>
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
						<td><form:select path="savingAccountId.id" multiple="false"
								class="form-control">
								<form:options items="${savingaccountlist}" itemValue="id"
									itemLabel="savingAccountNumber" />
							</form:select></td>
					</tr>
					<tr>
						<th valign="top"><form:label path="transactionType">Transaction Type</form:label></th>
						<td><form:select path="transactionType" class="form-control">
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
	<!--  End popup-->


</body>
</html>
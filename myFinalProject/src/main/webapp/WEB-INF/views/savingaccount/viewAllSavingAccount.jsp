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
						$("#popUpTable").DataTable({
							responsive : true
						});
						$("#show").click(function() {
							$("#hide").slideToggle();
						});
						$(document)
								.on(
										"click",
										"#addSavingAccountSubmit",
										function(e) {
											e.preventDefault();
											var mydata = $("#savingaccount")
													.serialize();
											// if you Transaction ajax
											$
													.ajax({
														type : 'post',
														url : 'createSavingAccountJson',
														data : mydata,
														datatype : 'json',
														success : function(data) {
															console.log(data);
															if (data.login == true) {
																if (data.success == true) {
																	alert("Create successfully!");
																	if (data.error_code == '0') {
																		window.location.href = 'viewAllSavingAccount?ERROR_CODE='
																				+ data.error_code;
																	} else {
																		/* window.location.href = 'viewAllSavingAccount'; */
																		var t = $(
																				'#table')
																				.DataTable();
																		t.row
																				.add(
																						[
																								data.savingAccount.savingAccountNumber,
																								data.savingAccount.customerId.lastName
																										+ ''
																										+ data.savingAccount.customerId.midName
																										+ ''
																										+ data.savingAccount.customerId.lastName,
																								data.savingAccount.balanceAmount,
																								data.savingAccount.repeatable,
																								data.savingAccount.interestRateId.savingAccountType
																										+ ' '
																										+ data.savingAccount.interestRateId.interestRate
																										+ ' '
																										+ data.savingAccount.interestRateId.currency,
																								data.savingAccount.state,
																								data.savingAccount.dateStart,
																								data.savingAccount.dateEnd ]

																				)
																				.draw();
																		$(
																				".popupContainer")
																				.hide();
																	}
																} else {
																	alert("Create Failed");
																}
															} else {
																window.location.href = 'login';
															}
														},
														error : function(a, b,
																c) {
															$("#errorPane")
																	.html(
																			a.responseText);
															console.log(a);
														}
													});

										});
						$(".addSavingAccount")
								.bind(
										"click",
										function(e) {
											$(".popupContainer")
													.fadeIn(
															"fast",
															function() {

																$
																		.ajax({
																			type : 'post',
																			url : 'getSavingAccountNumber',
																			data : "",
																			datatype : 'json',
																			success : function(
																					response) {
																				console
																						.log(response);
																				$(
																						".createSavingForm")
																						.trigger(
																								"reset");
																				$(
																						"#savingAccountNumber")
																						.val(
																								response);
																			},
																			error : function(
																					a,
																					b,
																					c) {
																				$(
																						"#errorPane")
																						.html(
																								a.responseText);
																				console
																						.log(a);
																			}
																		});

															});
										})
						$(".popupCloseButton").bind("click", function(e) {
							$(".popupContainer").hide();
						});
						$(".popupContainer").bind("click", function(e) {
							if (e.target == this) {
								$(this).hide();
							}
						})
						$(function() {
							$(".datepicker").datepicker({
								inline : true,
								dateFormat : 'dd/mm/yy'
							});
						});

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
			<div id="errorPane"></div>
			<!--  start page-heading -->


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
					<button class="addSavingAccount myButton" value="addSavingAccount">
						Add New Saving Account</button>
				</div>



				<div class="popupContainer">
					<div class="popup">
						<button class="popupCloseButton">X</button>
						<form:form action="createSavingAccount.html" method="post"
							modelAttribute="savingaccount" class="createSavingForm">
							<table class="mytable2">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<tr>
									<th valign="top"><form:label path="savingAccountNumber">Saving Account Number:</form:label>
									</th>
									<td><form:input readonly="true" path="savingAccountNumber"
											class="textox" id="savingAccountNumber"
											value="${savingAccountNumber }" /></td>
									<th valign="top"><form:label path="balanceAmount">Balance Amount:</form:label>
									</th>
									<td><form:input path="balanceAmount" class="textox" /></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="dateStart">Date Start:</form:label></th>
									<td><form:input path="dateStart" class="textox datepicker" /></td>
									<th valign="top"><form:label path="dateEnd">Date End:</form:label></th>
									<td><form:input path="dateEnd" class="textox datepicker" /></td>
								</tr>

								<tr>
									<th valign="top"><form:label path="customerId.id">Customer:</form:label></th>
									<td><form:select path="customerId.id" multiple="false"
											class="textox">
											<c:forEach var="customer" items="${customerList}">
												<form:option value="${customer.id}">
													<c:out
														value="${customer.lastName} ${customer.midName} ${customer.firstName}" />
												</form:option>
											</c:forEach>
										</form:select></td>
									<th valign="top"><form:label path="interestRateId.id">Period:</form:label></th>
									<td><form:select path="interestRateId.id" multiple="false"
											class="textox">
											<form:options items="${interestrateList}" itemValue="id"
												itemLabel="savingAccountType" />
										</form:select></td>


								</tr>
								<tr>
									<th valign="top"><form:label path="state">State:</form:label></th>
									<td><form:select path="state" class="textox">
											<form:options items="${states }" />
										</form:select></td>
									<th valign="top"><form:label path="repeatable">Repeatable:</form:label>
									</th>
									<td><form:input path="repeatable" class="textox" /></td>

								</tr>
								<tr>
									<td></td>
									<td><input type="submit" class="myButton" value="Save"
										id="addSavingAccountSubmit" /></td>

								</tr>
							</table>
						</form:form>

						<div class="panel-body">
							<div class="dataTable_wrapper">
								<div>
									<span> Interest Rate Information</span>
								</div>
								<table class=" table table-striped table-bordered table-hover "
									id="popUpTable">
									<thead>
										<tr>
											<th>ID</th>
											<th>Interest Rate Type</th>
											<th>Rate</th>
											<th>Currency</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="interest" items="${interestrateList}">
											<c:if test="${interest.id != null}">
												<tr>
													<td>${interest.id}</td>
													<td>${interest.savingAccountType}</td>
													<td>${interest.interestRate}</td>
													<td>${interest.currency}</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>
				<!-- End popupContainer -->


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
									<th>View Transactions</th>
									<sec:authorize access="hasRole('admin')">
										<th>Edit Saving Account</th>
									</sec:authorize>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="savingAccount" items="${listSavingAccount}">

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
										<td><a
											href="viewListTransaction.html?SavingAccountId=${savingAccount.id}"
											class="myButton">Transactions</a></td>
										<sec:authorize access="hasRole('admin')">

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
	<!-- 	<script>
		$(document).ready(function() {
			$("#linkPopup").bind("click", function(e) {
				e.preventDefault();
				$(".popupContainer").show();
			});
			$(".popupCloseButton").bind("click", function(e) {
				$(".popupContainer").hide();
			});
		});
	</script>
	<a href="dskaj" id="linkPopup">click to show popup</a>
	<div class="popupContainer">
		<div class="popup">
			<button class="popupCloseButton">X</button>
		</div>
	</div> -->


</body>
</html>
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
										"#linkDeleteSavingAccount",
										function(e) {
											e.preventDefault();

											var confirmMessage = confirm("Do you want to delete this Transaction?");
											if (confirmMessage) {
												//to delete Transaction page
												var savingAccountId = $(this)
														.attr("SavingAccount");
												/* 	$(this).closest("tr").remove(); */

												//if you want to reload the page
												//window.location.href = 'deleteTransaction?TransactionId=' + TransactionId;
												// if you Transaction ajax
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
						$(".addSavingAccount").bind("click",function(e){
							$(".popupContainer").show();
						})
						$(".popupCloseButton").bind("click",function(e){
							$(".popupContainer").hide();
						})
 						$(".popupContainer").bind("click",function(e){
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
					<h1>Add Saving Account Information</h1>

				</div>

				<%-- 				<div id="hide">
					<div style="float: left;">

						<form:form action="createSavingAccount.html" method="post"
							modelAttribute="savingaccount">
							<table border="0" cellpadding="0" cellspacing="0" id="id-form"
								class="table table-striped table-bordered">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<tr>
									<th valign="top"><form:label path="savingAccountNumber">Saving Account Number</form:label>
									</th>
									<td><form:input path="savingAccountNumber"
											class="form-control" /></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="balanceAmount">Balance Amount</form:label>
									</th>
									<td><form:input path="balanceAmount" class="form-control" /></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="repeatable">Repeatable</form:label>
									</th>
									<td><form:input path="repeatable" class="form-control" /></td>
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
									<th valign="top"><form:label path="customerId.id">Saving Account</form:label></th>
									<td><form:select path="customerId.id" multiple="false"
											class="form-control">
											<form:options items="${customerList}" itemValue="id"
												itemLabel="firstName" />
										</form:select></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="interestRateId.id">Saving Account</form:label></th>
									<td><form:select path="interestRateId.id" multiple="false"
											class="form-control">
											<form:options items="${interestrateList}" itemValue="id"
												itemLabel="savingAccountType" />
										</form:select></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="state">State</form:label></th>
									<td><form:select path="state" class="form-control">
											<form:options items="${states }" />
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
				</div> --%>

				<button class="addSavingAccount" value="addSavingAccount"> AddSavingAccount </button>	
				<div class="popupContainer">
					<div class="popup">
					<button class="popupCloseButton">X</button>
					
					<form:form action="createSavingAccount.html" method="post"
							modelAttribute="savingaccount">
							<table border="0" cellpadding="0" cellspacing="0" id="id-form"
								class="table table-striped table-bordered">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<tr>
									<th valign="top"><form:label path="savingAccountNumber">Saving Account Number</form:label>
									</th>
									<td><form:input path="savingAccountNumber"
											class="form-control" /></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="balanceAmount">Balance Amount</form:label>
									</th>
									<td><form:input path="balanceAmount" class="form-control" /></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="repeatable">Repeatable</form:label>
									</th>
									<td><form:input path="repeatable" class="form-control" /></td>
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
									<th valign="top"><form:label path="customerId.id">Saving Account</form:label></th>
									<td><form:select path="customerId.id" multiple="false"
											class="form-control">
											<form:options items="${customerList}" itemValue="id"
												itemLabel="firstName" />
										</form:select></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="interestRateId.id">Saving Account</form:label></th>
									<td><form:select path="interestRateId.id" multiple="false"
											class="form-control">
											<form:options items="${interestrateList}" itemValue="id"
												itemLabel="savingAccountType" />
										</form:select></td>
								</tr>
								<tr>
									<th valign="top"><form:label path="state">State</form:label></th>
									<td><form:select path="state" class="form-control">
											<form:options items="${states }" />
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
				</div><!-- End popupContainer -->
				
				
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
										<th>Approve Saving Account</th>

										<th>Deny Saving Account</th>

										<th>Delete Saving Account</th>
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
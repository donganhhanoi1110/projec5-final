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
<script src="js/popup/createSavingAccountOnSupportPage.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#table").DataTable({
			responsive : true
		});
		$(function() {
			$(".datepicker").datepicker({
				inline : true,
				dateFormat : 'dd/mm/yy'
			});
		});

		$("#popUpTable").DataTable({
			responsive : true
		});
		$("#show").click(function() {
			$("#hide").slideToggle();
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
					<table >

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
				<div>
					<h1>New Saving Account</h1>
					<sec:authorize access="hasRole('support')">
						<button class="addSavingAccount myButton " value="addSavingAccount">
							<span class="fa fa-plus"></span> Add New Saving Account</button>
					</sec:authorize>
				</div>
				<div class="panel-body">


					<div class="dataTable_wrapper">
						<table class="mytable1 table table-striped table-bordered "
							id="table">
							<thead>
								<tr>
									<th>Saving Account Number</th>
									<th>Customer</th>
									<th>Balance Amount</th>
									<th>Repeatable</th>
									<th>Interest Rate</th>
									<th>State</th>
									<th>Date Start</th>
									<th>Date End</th>
									<sec:authorize access="hasRole('support')">
										<th>Submit</th>
										<th>Edit</th>
									</sec:authorize>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="savingAccount" items="${listNewSavingAccount}">

									<tr>
										<td>${savingAccount.savingAccountNumber}</td>
										<td>${savingAccount.customerId.lastName}&nbsp;
											${savingAccount.customerId.midName} &nbsp;
											${savingAccount.customerId.firstName}</td>
										<td>${savingAccount.balanceAmount}</td>
										<td>${savingAccount.repeatable}</td>
										<td>${savingAccount.interestRateId.savingAccountType}&nbsp;
											${savingAccount.interestRateId.interestRate}%&nbsp;
											(${savingAccount.interestRateId.currency})</td>
										<td id="${savingAccount.savingAccountNumber}" class="${savingAccount.state}">${savingAccount.state}</td>

										<td>${savingAccount.dateStart}</td>
										<td>${savingAccount.dateEnd}</td>
										<sec:authorize access="hasRole('support')">
											<td><a
												href="submitSavingAccount.html?SavingAccountId=${savingAccount.id}"
												class="myButton">Submit</a></td>
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
	<div class="popupContainer">
		<div class="popup">
			<div class="headerPopup">
				<span> Add New Saving Account</span>
				<button class="popupCloseButton">X</button>
			</div>

			<form:form action="createSavingAccount.html" method="post"
				modelAttribute="savingaccount" class="createSavingForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<table class="mytable2 input-group">
					<tr>
						<th valign="top"><form:label path="savingAccountNumber">Saving Account Number:</form:label>
						</th>
						<td><form:input readonly="true" path="savingAccountNumber"
								class="textox" id="savingAccountNumber"
								value="${savingAccountNumber }" /></td>

						<th valign="top"><form:label path="balanceAmount">Balance Amount:</form:label>
						</th>
						<td><form:input path="balanceAmount" class="textox" id="myBalanceAmount"/></td>
					</tr>
					<tr>
						<th valign="top"><form:label path="dateStart">Date Start:</form:label></th>
						<td><form:input path="dateStart" class="textox datepicker" /></td>

						<th valign="top"><form:label path="state">State:</form:label></th>
						<td><form:select path="state" class="textox">
								<form:options items="${states }" />
							</form:select></td>
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

						<th valign="top"><form:label path="repeatable">Repeatable:</form:label>
						</th>
						<td> <form:select
								path="repeatable" class="textox">
								<form:option value="1">Yes</form:option>
								<form:option value="0">No</form:option>
							</form:select></td>
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

</body>
</html>
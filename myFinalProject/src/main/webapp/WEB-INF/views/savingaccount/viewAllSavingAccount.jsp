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

<script src="js/popup/getTransactionsPopup.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#table").DataTable({
		responsive : true
	});
});

</script>
</head>
<body>
	<jsp:include page="popup/getTransactionPopup.jsp"></jsp:include>

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
					<h1>Saving Account Information</h1>


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
									<th>View Transactions</th>
									<sec:authorize access="hasRole('admin')">
										<th>Edit</th>
									</sec:authorize>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="savingAccount" items="${listSavingAccount}">

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
										<td class="${savingAccount.state}">${savingAccount.state}</td>
										<td>${savingAccount.dateStart}</td>
										<td>${savingAccount.dateEnd}</td>
										<td><a
											SavingAccount=${savingAccount.savingAccountNumber } href=""
											class="myButton getMyTransactions">Transactions</a></td>
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



</body>
</html>
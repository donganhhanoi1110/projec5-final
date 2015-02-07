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
	<sec:authorize access="hasRole('admin')">
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
										<th>Balance Amount</th>
										<th>Repeatable</th>
										<th>Interest Rate</th>
										<th>State</th>
										<th>Date Start</th>
										<th>Date End</th>

										<th>Approve</th>
										<th>Deny</th>
										<th>Edit</th>


									</tr>
								</thead>
								<tbody>
									<c:forEach var="savingAccount" items="${listHoldSavingAccount}">

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
												href="approveSavingAccount.html?SavingAccountId=${savingAccount.id}"
												class="myButton">Approve</a></td>
											<td><a
												href="denySavingAccount.html?SavingAccountId=${savingAccount.id}"
												class="myButton">Deny</a></td>
											<td><a
												href="editSavingAccount.html?SavingAccountId=${savingAccount.id}"
												class="myButton">Edit</a></td>



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

	</sec:authorize>
</body>
</html>
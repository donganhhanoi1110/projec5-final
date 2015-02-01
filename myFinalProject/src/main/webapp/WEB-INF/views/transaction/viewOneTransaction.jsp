<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CSC Banking System</title>
<link href="css/ui-lightness/jquery-ui-1.9.2.custom.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="js/jquery/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			inline : true,
			format : 'dd-mm-yyyy hh:mm:ss '
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
												alert("You are getting Error!!!");
											</script>
										</c:if>
									</c:if>
								</h1>
								<h1>View Transaction Information</h1>

							</div>
							<table border="0" width="100%" cellpadding="0" cellspacing="0">
								<tr valign="top">
									<td>

										<table border="0" cellpadding="0" cellspacing="0"
											class="table table-striped table-bordered" id="id-form">

											<c:forEach var="Transaction" items="${TransactionProfile}">
												<tr>
													<th valign="top">ID :</th>
													<td><input type="text" disabled="disabled"
														class="form-control" name="transactionId"
														value="${Transaction.id}" /></td>
												</tr>
												<tr>
													<th valign="top">Amount</th>
													<td><input type="text" disabled="disabled"
														class="form-control" name="transactionAmount"
														value="${Transaction.amount}" /></td>
												</tr>
												<tr>
													<th valign="top">Start Time:</th>
													<td><input type="text" disabled="disabled"
														class="form-control" name="transactionDateStart"
														value="${Transaction.dateStart}" /></td>

												</tr>
												<tr>
													<th valign="top">End Time:</th>
													<td><input type="text" disabled="disabled"
														class="form-control" name="transactionDateEnd"
														value="${Transaction.dateEnd}" /></td>

												</tr>
												<tr>
													<th valign="top">Saving Account ID:</th>
													<td><input type="text" disabled="disabled"
														class="form-control" name="transactionSavingAccountId"
														value="${Transaction.savingAccountId.savingAccountNumber}" /></td>

												</tr>

												<tr>
													<th valign="top">State:</th>
													<td><input type="text" disabled="disabled"
														class="form-control" name="transactionState"
														value="${Transaction.state}" /></td>

												</tr>
													<tr>
													<th valign="top">Transaction Type:</th>
													<td><input type="text" disabled="disabled"
														class="form-control" name="transactionState"
														value="${Transaction.transactionType}" /></td>

												</tr>

											</c:forEach>
										</table>

									</td>
								</tr>

							</table>






						</div>
			
			<br />
		</div>

	</div>


</body>
</html>
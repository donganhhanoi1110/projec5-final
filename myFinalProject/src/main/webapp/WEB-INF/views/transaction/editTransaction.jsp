<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
					<h1>Edit Transaction Information</h1>

				</div>
				<table>
					<tr valign="top">
						<td><form:form action="editTransactionProfile.html"
								method="post" modelAttribute="transaction">
								<table id="id-form" class="table table-striped table-bordered">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<tr>
										<th valign="top"><form:label path="id">ID</form:label></th>
										<td><form:input path="id" class="form-control"
												readonly="true" value="${transaction.id }" /></td>
									</tr>
									<tr>
										<th valign="top"><form:label path="amount">Amount</form:label>
										</th>
										<td><form:input path="amount" class="form-control"
												value="${transaction.amount }" /></td>
									</tr>
									<tr>
										<th valign="top"><form:label path="dateStart">Date Start</form:label>
										</th>
										<td><form:input path="dateStart" class="form-control"
												value="${transaction.dateStart }" /></td>
									</tr>
									<tr>
										<th valign="top"><form:label path="dateEnd">Date End</form:label>
										</th>
										<td><form:input path="dateEnd" class="form-control"
												value="${transaction.dateEnd }" /></td>
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
										<th valign="top"><form:label path="state">State</form:label></th>
										<td><form:select path="state" class="form-control">

												<form:options items="${states }" />
											</form:select></td>

									</tr>
									<tr>
										<th valign="top"><form:label path="transactionType">Transaction Type</form:label></th>
										<td><form:select path="transactionType"
												class="form-control">
												<form:options items="${transactiontype }" />
											</form:select></td>

									</tr>
									<tr>
										<td></td>
										<td><input type="submit" class="myButton" value="Save"
											id="addAccount" /></td>

									</tr>
								</table>
							</form:form></td>
					</tr>

				</table>
			</div>

			<br />
		</div>

	</div>


</body>
</html>
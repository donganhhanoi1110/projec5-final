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
		$(".datepicker").datepicker({
			inline : true,
			dateFormat : 'dd/mm/yy'
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
					<h1>Edit Saving Account Information</h1>

				</div>
				<table border="0" width="100%" cellpadding="0" cellspacing="0">
					<tr valign="top">
						<td><form:form action="editSavingAccountProfile.html"
								method="post" modelAttribute="savingaccount">
								<table border="0" cellpadding="0" cellspacing="0" id="id-form"
									class="table table-striped table-bordered">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />

									<tr>
										<th valign="top"><form:label path="id">ID</form:label></th>
										<td><form:input path="id" class="form-control"
												value="${savingaccount.id }" readonly="true" /></td>
									</tr>
									<tr>
										<th valign="top"><form:label path="savingAccountNumber">Saving Account Number</form:label>
										</th>
										<td><form:input path="savingAccountNumber"
												class="form-control" readonly="true" /></td>
									</tr>
									<tr>
										<th valign="top"><form:label path="balanceAmount">Balance Amount</form:label>
										</th>
										<td><form:input path="balanceAmount" class="form-control"
												value="${savingaccount.balanceAmount }" /></td>
									</tr>
									<tr>
										<th valign="top"><form:label path="repeatable">Repeatable</form:label>
										</th>
										<td><form:input path="repeatable" class="form-control"
												value="${savingaccount.repeatable }" /></td>
									</tr>
									<tr>
										<th valign="top"><form:label path="dateStart">Date Start</form:label>
										</th>
										<td><form:input path="dateStart"
												class="form-control datepicker"
												value="${savingaccount.dateStart }" /></td>
									</tr>
									<tr>
										<th valign="top"><form:label path="dateEnd">Date End</form:label>
										</th>
										<td><form:input path="dateEnd"
												class="form-control datepicker"
												value="${savingaccount.dateEnd }" /></td>
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
										<th valign="top"><form:label path="interestRateId.id">Saving Account Type</form:label></th>
										<td><form:select path="interestRateId.id"
												multiple="false" class="form-control">
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
										<td><div class=" myButton ">
												<span class="fa fa-floppy-o"></span> <input type="submit"
													value="Save" id="addAccount" class=" myButtonNone"/>
											</div>
											
											</td>

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
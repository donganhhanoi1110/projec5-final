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


	<div id="content-outer">
		<!-- start content -->
		<div id="content">

			<!--  start page-heading -->
			<div id="page-heading">

				<h1 style="color: red">${message}</h1>
			</div>
			<table border="0" width="100%" cellpadding="0" cellspacing="0"
				id="content-table">
				<tr>
					<th rowspan="3" class="sized"><img
						src="images/shared/side_shadowleft.jpg" width="20" height="300"
						alt="" /></th>
					<th class="topleft"></th>
					<td id="tbl-border-top">&nbsp;</td>
					<th class="topright"></th>
					<th rowspan="3" class="sized"><img
						src="images/shared/side_shadowright.jpg" width="20" height="300"
						alt="" /></th>
				</tr>
				<tr>
					<td id="tbl-border-left"></td>
					<td>
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
														value="${Transaction.savingAccountId.customerId.accountNumber}" /></td>

												</tr>

												<tr>
													<th valign="top">State:</th>
													<td><input type="text" disabled="disabled"
														class="form-control" name="transactionState"
														value="${Transaction.state}" /></td>

												</tr>

											</c:forEach>
										</table>

									</td>
								</tr>

							</table>






						</div>
					</td>
					<td id="tbl-border-right"></td>
				</tr>
				<tr>
					<th class="sized bottomleft"></th>
					<td id="tbl-border-bottom">&nbsp;</td>
					<th class="sized bottomright"></th>
				</tr>
			</table>
			<br />
		</div>

	</div>


</body>
</html>
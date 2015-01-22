<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CSC Banking System</title>

</head>
<body>
<%-- 	<c:if test="${loginSession == null}"><jsp:forward
			page="/login.jsp" /></c:if> --%>
	<div id="page-top-outer">

		<div id="page-center" style="background-color: white;">

			<!-- start logo -->
			<div id="logo" style="padding-left: 50px">
				<a href=""><img src="images/logo.png" width="229" height="74"
					alt="" /></a>
			</div>
		</div>
	</div>
	<div class="nav-outer-repeat"></div>

	<div id="content-outer">
		<!-- start content -->
		<div id="content">

			<!--  start page-heading -->
			<div id="page-heading">
				<h1>Account Management</h1>
				
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
										<c:out value="${ERROR_CODE }"></c:out>
										<script>
											alert("You must have something...");
										</script>
									</c:if>
								</h1>
								<h1>Add Account Information</h1>

							</div>

							<div id="hide">
								<div style="float: left;">
									<table border="0" width="100%" cellpadding="0" cellspacing="0">
										<tr valign="top">
											<td>
												<form action="createAccount.html" method="post">
													<table border="0" cellpadding="0" cellspacing="0"
														id="id-form">
														<tr>
															<th valign="top">Account Type:</th>
															<td><select id="selectAccountType"
																name="accountType" class="css-input">
																	<option value="Deposit Account">Deposit
																		Account</option>
																	<option value="Saving Account">Saving Account</option>
																	<option value="Others">Others</option>
															</select></td>
															<td></td>
														</tr>
														<tr>
															<th valign="top">Account Number</th>
															<td><input type="text" class="css-input"
																name="accountNumber" /></td>
														</tr>
														<tr>
															<th valign="top">Account Name:</th>
															<td><input type="text" class="css-input"
																name="accountName" /></td>
															<td></td>
														</tr>
														<tr>
															<th valign="top">Account Amount:</th>
															<td><input type="text" class="css-input"
																name="accountAmount" /></td>
															<td></td>
														</tr>
														<tr>
															<td></td>
															<td><input type="submit" class="myButton"
																value="Save" id="addAccount" /></td>
															<td></td>
														</tr>
													</table>
												</form>
											</td>
										</tr>

									</table>
								</div>
								<div style="float: right; margin-right: 20%;">

									
									<a class="myButton" href="addCard.html" style="margin: 5px;"> Create Card</a>
									<a class="myButton" href="addNewTransaction.html" > Create Transaction</a>
								

								</div>
							</div>


							<div id="show">Add Account Information</div>
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="mytable1" id="dataTables-example">
										<thead>
											<tr>
												<th>Account Number</th>
												<th>Account Name</th>
												<th>Account Type</th>
												<th>Account Amount</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="account" items="${listAccount}">
												<c:if test="${account.accountName != null}">
													<tr>
														<td>${account.accountNumber}</td>
														<td>${account.accountName}</td>
														<td>${account.accountType}</td>
														<td>${account.availableNumber}</td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
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
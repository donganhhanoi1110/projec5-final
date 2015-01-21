<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CSC Banking System</title>
<link rel="stylesheet" href="css/screen.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui-min.css" type="text/css" />
<link rel="stylesheet" href="css/jquery.dataTables.css" type="text/css" />

<!-- SCRIPT -->
<script src="js/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>
<script src="js/jquery/jquery.tooltip.js" type="text/javascript"></script>
<script src="js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/jquery/jquery-ui.js" type="text/javascript"></script>
<script src="js/jquery/jquery.dataTables.js" type="text/javascript"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#mytable").dataTable();
		
	});
	
</script>
</head>
<body>
<c:if test="${loginSession == null}"><jsp:forward page="/login.jsp"/></c:if>
	<div id="page-top-outer">

		<div id="page-center" style="background-color: white;">

			<!-- start logo -->
			<div id="logo" style="padding-left: 50px">
				<a href=""><img src="images/logo.png" width="229" height="74"
					alt="" /></a>
			</div>
		</div>
	</div>
	<div class="nav-outer-repeat">	
	</div>

	<div id="content-outer">
		<!-- start content -->
		<div id="content">

			<!--  start page-heading -->
			<div id="page-heading">
				<h1>Transfer Management</h1>
				<h3 align="right">Welcome ${loginSession}, <a href="getCustomerProfile.html?loginId=${loginSession}" >Update Profile</a></h3>
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
			<h1>Transfer to Preregistered Account</h1>
		</div>
		
		<div id="content-table-inner" style="padding-left: 35px;">
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr valign="top">
					<td>
						<form action="TransferToPreAcc.html" method="post">
						<h2><span> ${message }</span></h2>
							<table border="0" cellpadding="0" cellspacing="0" id="id-form">
								<tr>
									<th valign="top">Account Number:</th>
									<td><select id="selectAccountType" name="accountNumber">
									<c:forEach var="acc" items="${listAccount }">
									<c:if test="${acc.accountName != null}" >
											<option value="${acc.number }">${acc.number }</option>
											</c:if>
									</c:forEach>
									</select></td>
									<td></td>
								</tr>
								<tr>
									<th valign="top">Preregistered Account</th>
									<td><select id="selectAccountType" name="accountTargetNumber">
									<c:forEach var="accPre" items="${listPreAcc }">
											<option value="${accPre.accountNumber }">${accPre.accountOwner }</option>
									</c:forEach>
									</select></td>
								</tr>
								<tr>
									<th valign="top">Amount:</th>
									<td><input type="text" class="inp-form" name="amount"/></td>

								</tr>
								<tr>
									<td></td>
									<td><input type="submit" class="btnAdd" value="Submit"
										id="transferMoneyAcc" /></td>
									<td></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
		</div>
		<h1>Transfer to Normal Account</h1>
		<div id="content-table-inner" style="padding-left: 35px;">
			<table border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr valign="top">
					<td>
						<form action="TransferToNormalAcc.html" method="post">
							<table border="0" cellpadding="0" cellspacing="0" id="id-form">
								<tr>
									<th valign="top" width="100px">Account Number:</th>
									<td><select id="selectAccountType" name="accountNumber">
									<c:forEach var="acc" items="${listAccount }">
									<c:if test="${acc.accountName != null}" >
											<option value="${acc.number }">${acc.number }</option>
											</c:if>
									</c:forEach>
									</select></td>
									<td></td>
								</tr>
								<tr>
									<th valign="top">Receive Account Number:</th>
									<td><input type="text" class="inp-form" name="accountTargetNumber"/></td>
								</tr>
								<tr>
									<th valign="top">Receive Account Name:</th>
									<td><input type="text" class="inp-form" name="accountTargetName"/></td>
								</tr>
								<tr>
									<th valign="top">Receive Account Bank:</th>
									<td><input type="text" class="inp-form" name="accountTargetBank"/></td>
								</tr>
								<tr>
									<th valign="top">Receive Account Bank Branch:</th>
									<td><input type="text" class="inp-form" name="accountTargetBranch"/></td>
								</tr>
								<tr>
									<th valign="top">Amount:</th>
									<td><input type="text" class="inp-form" name="amount"/></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="submit" class="btnAdd" value="Submit"
										id="transferMoneyAcc" /></td>
									<td></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
		</div>
							<table id="mytable">
								<thead>
									<tr>
										<th>No Transaction</th>
										<th>Account Name</th>
										<th>Receive Account</th>
										<th>Receive Account Number</th>
										<th>Receive Bank</th>
										<th>Receive Branch</th>
										<th>Amount</th>
										<th>Date</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="trans" items="${listTransfer}" >
										
										<tr>
											<td>${trans.transId}</td>
											<td>${trans.sourceAcc}</td>
											<td>${trans.targetAcc}</td>
											<td>${trans.targetAccNumber}</td>
											<td>${trans.targetBank}</td>
											<td>${trans.targetBranch}</td>
											<td>${trans.amount}</td>	
											<td>${trans.dateTrans}</td>
																				
										</tr>
									
									</c:forEach>
								</tbody>
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
		<div class="clear">&nbsp;</div>
	</div>

	<div class="clear">&nbsp;</div>

	<!-- start footer -->
	<div id="footer">
		<!--  start footer-left -->
		<div id="footer-left">
			&copy; Copyright 2014 DAOnJPA| Java Fresher Training Program. <span
				id="spanYear"></span> <a href="http://www.csc.com" target="_blank">www.csc.com</a>
		</div>
		<!--  end footer-left -->
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
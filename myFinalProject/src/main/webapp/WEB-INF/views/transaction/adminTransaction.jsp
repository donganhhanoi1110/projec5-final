<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
	$(document).ready(function() {
		$("#table").DataTable({
			responsive : true
		});
		$("#show").click(function() {
			$("#hide").slideToggle();
		});
		$(function() {
			$(".datepicker").datepicker({
				inline : true,
				dateFormat : 'dd/mm/yy'
			});
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
										alert("You are getting Error when Create, Update or Delete!!!");
									</script>
								</c:if>
							</c:if>
						</h1>

					</div>
					<div>
						<div>
							<h1>Waiting Transaction</h1>
							<!-- Start search -->
							<form action="searchTransaction.html" method="post"
								style="width: 400px; margin-bottom: 20px;">
								<input type="hidden" name="${_csrf.parameterName}"
									style="width: 15px; height: 30px;" value="${_csrf.token}" />
								<div class="form-group">
									<label>Date Start:</label> <input
										class="textox datepicker form-control" name="dateStart" />
								</div>
								<div class="form-group">
									<label>Date End:</label> <input
										class="textox datepicker form-control" name="dateEnd" />
								</div>

								<div class=" myButton ">
									<span class="fa fa-search"></span> <input type="submit"
										value="Search" id="searchTransaction" class=" myButtonNone" />
								</div>
							</form>
							<div class="clearfix"></div>
						</div>
						<!-- End search -->

						<div class="dataTable_wrapper">
							<table class="mytable1 table table-striped table-bordered "
								id="table">
								<thead>
									<tr>
										<th>ID</th>
										<th>Amount</th>
										<th>Start Time</th>
										<th>Account Number</th>
										<th>State</th>
										<th>Transaction Type</th>
										<th>Current Balance</th>
										<th>After Balance</th>

										<th>Approve Transaction</th>
										<th>Deny Transaction</th>

										<th>Edit Transaction</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach var="Transaction" items="${listHoldTransaction}">
										<c:if test="${Transaction.id != null}">
											<tr>
												<td>${Transaction.id}</td>
												<td>${Transaction.amount}</td>
												<td>${Transaction.dateStart}</td>
												<td>${Transaction.savingAccountId.savingAccountNumber}</td>
												<td class="${Transaction.state}">${Transaction.state}</td>
												<td>${Transaction.transactionType}</td>
												<td>${Transaction.currentBalance}</td>
												<td>${Transaction.afterBalance}</td>

												<td><a
													href="approveTransaction.html?TransactionId=${Transaction.id}"
													class="myButton">Approve</a></td>
												<td><a
													href="denyTransaction.html?TransactionId=${Transaction.id}"
													class="myButton">Deny</a></td>
												<td><a
													href="editTransaction.html?TransactionId=${Transaction.id}"
													class="myButton">Edit</a></td>
											</tr>
										</c:if>
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
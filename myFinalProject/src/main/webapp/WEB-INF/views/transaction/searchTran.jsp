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
									/* alert("You are getting Error when Create, Update or Delete!!!"); */
								</script>
							</c:if>
						</c:if>
					</h1>

				</div>
				<div class="panel-body">

					<h1>List Transaction:</h1>
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


								</tr>
							</thead>
							<tbody>
								<c:forEach var="Transaction" items="${listTrans}">
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


</body>
</html>
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
	$(document)
			.ready(
					function() {
						$("#table").DataTable({
							responsive : true
						});
						$("#show").click(function() {
							$("#hide").slideToggle();
						});
						$(document)
								.on(
										"click",
										"#linkDeleteCustomer",
										function(e) {
											e.preventDefault();

											var confirmMessage = confirm("Do you date to delete this user?");
											if (confirmMessage) {
												//to delete user page
												var customerId = $(this).attr(
														"customer");
												/* 	$(this).closest("tr").remove(); */

												//if you want to reload the page
												//window.location.href = 'deleteUser?userId=' + userId;
												// if you user ajax
												$
														.ajax({
															type : 'post',
															url : 'deleteCustomerJson',
															data : 'customerId='
																	+ customerId,
															datatype : 'json',
															success : function(
																	data) {
																console
																		.log(data);
																if (data.login == true) {
																	if (data.success == true) {
																		alert("Delete successfully!");
																		if (data.error_code == '0') {
																			window.location.href = 'home?ERROR_CODE='
																					+ data.error_code;
																		} else {
																			window.location.href = 'home';
																		}

																	} else {
																		alert("Delete Failed");
																	}
																} else {
																	window.location.href = 'login';
																}
															}
														});

											} else {
												//do nothing
											}
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
								<h1>Interest Rate Information</h1>

							</div>
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="mytable1 table table-striped table-bordered "
										id="table">
										<thead>
											<tr>
												<th>ID</th>
												<th>Interest Rate Type</th>
												<th>Rate</th>
												<th>Currency</th>


											</tr>
										</thead>
										<tbody>
											<c:forEach var="interest" items="${listInterestRate}">
												<c:if test="${interest.id != null}">
													<tr>
														<td>${interest.id}</td>
														<td>${interest.savingAccountType}</td>
														<td>${interest.interestRate}</td>
														<td>${interest.currency}</td>


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
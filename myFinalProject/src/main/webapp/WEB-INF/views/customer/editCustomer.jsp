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
					<h1>Edit Customer Information</h1>

				</div>
				<table border="0" width="100%" cellpadding="0" cellspacing="0">
					<tr valign="top">
						<td>
							<form action="editCustProfile.html" method="post">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table border="0" cellpadding="0" cellspacing="0"
									class="table table-striped table-bordered" id="id-form">

									<c:forEach var="cust" items="${customerProfile}">
										<tr>
											<th valign="top">ID :</th>
											<td><input type="hidden" class="inp-form" name="custID"
												value="${cust.id}" /> ${cust.id}</td>
										</tr>
										<tr>
											<th valign="top">Account Type</th>
											<td><input type="text" class="form-control"
												name="accountType" value="${cust.accountType}" /></td>
										</tr>
										<tr>
											<th valign="top">First Name</th>
											<td><input type="text" class="form-control"
												name="firstName" value="${cust.firstName}" /></td>
										</tr>
										<tr>
											<th valign="top">Last Name:</th>
											<td><input type="text" class="form-control"
												name="lastName" value="${cust.lastName}" /></td>
										</tr>
										<tr>
											<th valign="top">Mid Name:</th>
											<td><input type="text" class="form-control"
												name="midName" value="${cust.midName}" /></td>
										</tr>
										<tr>
											<th valign="top">ID Number</th>
											<td><input type="text" class="form-control"
												name="idNumber" value="${cust.idNumber}" /></td>
										</tr>
										<tr>
											<th valign="top">Phone 1</th>
											<td><input type="text" class="form-control"
												name="phone1" value="${cust.phone1}" /></td>
										</tr>
										<tr>
											<th valign="top">Phone 2</th>
											<td><input type="text" class="form-control"
												name="phone2" value="${cust.phone2}" /></td>
										</tr>
										<tr>
											<th valign="top">Address 1</th>
											<td><input type="text" class="form-control" name="add1"
												value="${cust.add1}" /></td>
										</tr>
										<tr>
											<th valign="top">Address 2</th>
											<td><input type="text" class="form-control" name="add2"
												value="${cust.add2}" /></td>
										</tr>
										<tr>
											<th valign="top">Mail:</th>
											<td><input type="text" class="form-control" name="email"
												value="${cust.email}" /></td>
										</tr>
										<tr>
											<th valign="top">State:</th>
											<td><input type="text" class="form-control" name="state"
												value="${cust.state}" /></td>
										</tr>
										<tr>
											<th valign="top">Account Number:</th>
											<td><input type="text" class="form-control"
												name="accountNumber" value="${cust.accountNumber}" /></td>
										</tr>
										<tr>
											<td></td>
											<td><input type="submit" class="myButton"
												value="Update User" /></td>

										</tr>
									</c:forEach>
								</table>
							</form>
						</td>
					</tr>

				</table>






			</div>

			<br />
		</div>

	</div>


</body>
</html>
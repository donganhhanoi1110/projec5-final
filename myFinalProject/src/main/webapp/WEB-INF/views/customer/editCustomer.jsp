<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
							<form:form action="editCustProfile.html" method="post" modelAttribute="customer">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<table border="0" cellpadding="0" cellspacing="0"
									class="table table-striped table-bordered" id="id-form">
										<tr>
											<th valign="top">ID :</th>
											<td><form:hidden class="inp-form" path="id"
												value="${customer.id}" /> ${customer.id}</td>
										</tr>
										<tr>
											<th valign="top">Account Type</th>
											<td><form:input class="form-control"
											  path="accountType" value="${customer.accountType}" /></td>
										</tr>
										<tr>
											<th valign="top">First Name</th>
											<td><form:input class="form-control"
												path="firstName" value="${customer.firstName}" /></td>
										</tr>
										<tr>
											<th valign="top">Last Name:</th>
											<td><form:input  class="form-control"
												path="lastName" value="${customer.lastName}" /></td>
										</tr>
										<tr>
											<th valign="top">Mid Name:</th>
											<td><form:input class="form-control"
												path="midName" value="${customer.midName}" /></td>
										</tr>
										<tr>
											<th valign="top">ID Number</th>
											<td><form:input  class="form-control"
												path="idNumber" value="${customer.idNumber}" /></td>
										</tr>
										<tr>
											<th valign="top">Phone 1</th>
											<td><form:input class="form-control"
												path="phone1" value="${customer.phone1}" /></td>
										</tr>
										<tr>
											<th valign="top">Phone 2</th>
											<td><form:input class="form-control"
												path="phone2" value="${customer.phone2}" /></td>
										</tr>
										<tr>
											<th valign="top">Address 1</th>
											<td><form:input class="form-control" path="add1"
												value="${customer.add1}" /></td>
										</tr>
										<tr>
											<th valign="top">Address 2</th>
											<td><form:input class="form-control" path="add2"
												value="${customer.add2}" /></td>
										</tr>
										<tr>
											<th valign="top">Mail:</th>
											<td><form:input class="form-control" path="email"
												value="${custtomer.email}" /></td>
										</tr>
										<tr>
											<th valign="top">State:</th>
											<td><form:input class="form-control" path="state"
												value="${customer.state}" /></td>
										</tr>
										<tr>
											<th valign="top">Account Number:</th>
											<td><form:input class="form-control"
												path="accountNumber" value="${customer.accountNumber}" /></td>
										</tr>
										<tr>
											<td></td>
											<td><input type="submit" class="myButton"
												value="Update User" /></td>

										</tr>
								</table>
							</form:form>
						</td>
					</tr>

				</table>






			</div>

			<br />
		</div>

	</div>


</body>
</html>
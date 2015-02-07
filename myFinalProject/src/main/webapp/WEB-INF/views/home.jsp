<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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



	<!-- start content -->
	<div>

		<!--  start page-heading -->
		<div>

			<h1 style="color: red">${message}</h1>
		</div>

		<div>
			<div>
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
				<h1>Welcome to CSC Banking</h1>

			</div>

			<div id="cover_all_box">
				<div class="cover_box">
					<div class="box_image">

						<a href="homeTransaction"><img
							src="images/myimage/managePayedReport.png" height="100px" /></a>
					</div>
					<div class="box_title">Manage Transaction</div>
				</div>
				<div class="cover_box">
					<div class="box_image">

						<a href="homeSavingAccount" title="title"><img
							src="images/myimage/manageBill.png" height="100px" /></a>
					</div>
					<div class="box_title">Manage Saving Account</div>
				</div>
				<div class="cover_box">
					<div class="box_image">

						<a href="homeCustomer" title="title"><img
							src="images/myimage/manageCustomer.png" height="100px" /></a>
					</div>
					<div class="box_title">Manage Customer</div>
				</div>
					<sec:authorize access="hasRole('admin')">
					<div class="cover_box">
						<div class="box_image">

							<a href="homeUser" title="title"><img
								src="images/myimage/manageuser.png" height="100px" /></a>
						</div>
						<div class="box_title">Manage User</div>
					</div>
				</sec:authorize>
				<div class="cover_box">
					<div class="box_image">

						<a href="homeInterestRate" title="title"><img
							src="images/myimage/manageInterestRate.png" height="100px" /></a>
					</div>
					<div class="box_title">Interest Rate</div>
				</div>
			</div>



			<br />
		</div>

	</div>
</body>
</html>
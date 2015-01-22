<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CSC Banking System</title>
<link rel="stylesheet" href="css/style_home.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/site.css">
<link rel="stylesheet" href="css/screen.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui-min.css" type="text/css" />
<link rel="stylesheet" href="css/jquery.dataTables.css" type="text/css" />

<script language="javascript" src="js/jquery-2.1.3.js"></script>
<!-- SCRIPT -->
<!-- <script src="js/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>
<script src="js/jquery/jquery.tooltip.js" type="text/javascript"></script>
<script src="js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/jquery/jquery-ui.js" type="text/javascript"></script> -->
<!-- <script src="js/jquery/jquery.dataTables.js" type="text/javascript"></script> -->
<script language="javascript" src="js/jquery-2.1.3.js"></script>
<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>

<script src="js/dataTables.bootstrap.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#show").click(function() {
			$("#hide").slideToggle();
		});
		$("#mytable").dataTable();
		$('#dataTables-example').DataTable({
			responsive : true
		});
	});
</script>
</head>
<body>
	<c:if test="${loginSession == null}"><jsp:forward
			page="/login.jsp" /></c:if>
	<div id="page-top-outer">

		<div id="page-center" style="background-color: white;">

			<!-- start logo -->
			<div id="logo" style="padding-left: 50px">
				<a href="home.html"><img src="images/logo.png" width="229" height="74"
					alt="" /></a>
			</div>
		</div>
	</div>
	<h1 style="color:red; margin: 100px;">Login Failed!!! Click here to go to <a href="/daonjpa/login.jsp" >Login Page</a></h1>

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

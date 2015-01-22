<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" /></title>
<!--[if IE]>
<link rel="stylesheet" href="resources/css/ie.css"
type="text/css" media="screen, projection">
<![endif]-->


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

		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
		

		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	
</body>
</html>
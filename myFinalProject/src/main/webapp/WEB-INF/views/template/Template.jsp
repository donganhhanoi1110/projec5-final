<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>CSC Banking System</title>
<!-- My CSS -->
<link href="css/style_home.css" rel="stylesheet">
<link href="css/screen.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link rel="stylesheet" href="css/screen.css" type="text/css" />
<style type="text/css">
body {
	background-image: url(images/myimage/nen25.jpg);
}
</style>
<!-- jQuery -->
<script src="js/jquery/jquery.js"></script>

<script src="js/bootstrap.min.js"></script>

<script src="js/jquery.dataTables.min.js"></script>

<script src="js/dataTables.bootstrap.min.js"></script>

<!-- Date Picker -->
<link href="css/ui-lightness/jquery-ui-1.9.2.custom.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="js/jquery/jquery-ui-1.9.2.custom.min.js"></script>
</head>

<body>
	<tiles:insertAttribute name="header" />
	<div id="cover_content">
		<tiles:insertAttribute name="menu" />
		<!-- Navigation -->


		<!-- Page Content -->
		<div id="content_right">

			<div>
				<div>
					<div>
						<!--                         <h1 class="page-header">Blank</h1> -->
						<tiles:insertAttribute name="body" />
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<tiles:insertAttribute name="footer" />

</body>

</html>

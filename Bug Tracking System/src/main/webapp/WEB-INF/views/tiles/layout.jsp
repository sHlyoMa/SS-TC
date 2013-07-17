<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />    
		<link href="<c:url value="/resources/css/bootstrap-select.css" />" rel="stylesheet"  type="text/css" />    
		<link href="<c:url value="/resources/css/select2.css" />" rel="stylesheet"  type="text/css" />
  		<link href="<c:url value="/resources/css/bootstrap-responsive.min.css" />" rel="stylesheet"  type="text/css" /> 	
  		<link href="<c:url value="/resources/css/bootstrap-switch.css" />" rel="stylesheet"  type="text/css" />
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/favicon.ico" />" />
		<!-- <style>
			html, body {
			    height: 100%;
			}
			* html .page-wrapper {

			}
			.page-wrapper {

				margin-bottom: -20px;
			}		
			.site-footer {
				height: 20px;
			}
		</style> -->
	</head>
	
	<body>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.9.1.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-pagination.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/custom.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/select2.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-switch.js" />"></script>
		
		<div class="page-wrapper">
			<table id="main-table" align="center" width="98%">
			    <tr>
			        <td>
			        	<tiles:insertAttribute name="header" />
			        </td>
			    </tr>
			    <tr>   
			        <td>
			        	<div class="container-fluid">
							<div class="row-fluid">
								<div class="span3">
									<div class="well sidebar-nav">
										<tiles:insertAttribute name="menu" />
									</div><!--/.well -->
	    						</div><!--/span-->
	    						<div class="span9">
									<div class="hero-unit">
			        					<tiles:insertAttribute name="body" />
			        				</div>
								</div>
							</div><!--/row-->
						</div>
			        </td>
			    </tr>
			</table>
		</div>
		<div class="site-footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Bug Tracking System | log in</title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/css/bootstrap-select.css" />" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/css/select2.css" />" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/css/bootstrap-responsive.min.css" />" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/favicon.ico" />" />
		<style type="text/css">
			.form-signin {
				max-width: 300px;
				padding: 19px 29px 29px;
				margin: 0 auto 20px;
				background-color: #fff;
				border: 1px solid #e5e5e5;
				-webkit-border-radius: 5px;
				-moz-border-radius: 5px;
				border-radius: 5px;
				-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
				-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
				box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
			}
			
			.form-signin .form-signin-heading,.form-signin .checkbox {
				margin-bottom: 10px;
			}
			
			.form-signin input[type="text"],.form-signin input[type="password"] {
				font-size: 16px;
				height: auto;
				margin-bottom: 15px;
				padding: 7px 9px;
			}
		</style>
	</head>

	<body>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.9.1.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-pagination.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/custom2.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-select.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/select2.js" />"></script>
			
		<div class="page-wrapper">
			<table align="center" width="98%">
				<tr>
					<td><tiles:insertAttribute name="header" /></td>
				</tr>
				<tr>
					<td>
						<div class="container-fluid">
							<div class="row-fluid">
								<div>
									<tiles:insertAttribute name="body" />
								</div>
							</div>
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
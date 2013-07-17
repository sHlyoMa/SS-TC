<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Bug Tracking System | <spring:message code="label.404.head" /></title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/favicon.ico" />" /> 
	</head>

	<body class="login-bg" style="padding-top: 300px;">
		<div class="row" align="center">
			<div class="span8" align="left" style=" color: #EEEEEE; text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25); padding-right: 30px;">
				<h1><spring:message code="label.access.denied" /></h1>
				<h4><spring:message code="label.access.credentials" /></h4>
			</div>
			<div class="span4"  align="right" style="font-size: 281.5px; color: #EEEEEE; text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);">
				403
			</div>
		</div>
	</body>
</html>










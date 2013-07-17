<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>Bug Tracking System | <spring:message code="label.404.head" /></title>
		
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />        
  		<link href="<c:url value="/resources/css/bootstrap-responsive.min.css" />" rel="stylesheet"  type="text/css" /> 
  			
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/favicon.ico" />" />
	</head>
	<body>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.9.1.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$('.exceptionRow').each(function(){
					var innerText = $(this).html();	
					var begin = innerText.indexOf("(");
					var middle = innerText.indexOf(":");
					var end = innerText.indexOf(")");
					if (middle > 0) {
						beginStr = innerText.substring(0, begin);
						middleStr = innerText.substring(begin, middle);
						endStr = innerText.substring(middle+1, end);
						$(this).html(beginStr + 
									'(<br><b style="font-weight: bold; color: #0088CC; padding-left: 30px;">' + 
											beginStr + '</b>' + 
									':<b style="font-weight: bold; color: red;">' + endStr + '</b><br>)');
					}
				});	
			});
		</script>
		
		<div class="container-fluid" style="width: 97%">
       		<div class="hero-unit">
       			<c:choose>
					<c:when test="${!empty stackTrace}">
						<div class="accordion" id="accordionException">
							<div class="accordion-group">
								<div class="accordion-heading">
									<h4>
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionException" href="#collapseStacktrace">
											${message}
										</a>
									</h4>
								</div>
								<div id="collapseStacktrace" class="accordion-body collapse">
									<div class="accordion-inner" style="line-height: 18px;">
										<c:forEach items="${stackTrace}" var="stack">
											<p class="exceptionRow" style="margin: 0px 0px 5px 15px;">${stack}</p>
											<br>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<h3>
							${message}
						</h3>
					</c:otherwise>
				</c:choose>
       			
       			<div class="row" style="padding-top:20px">
       				<a class="btn btn-warning btn-small" onclick="javascript: history.go(-1)">
						<i class="icon-chevron-left icon-white"></i> 
						<spring:message code="label.back" />
					</a>
       			</div>
			</div>
		</div>
	</body>
</html>





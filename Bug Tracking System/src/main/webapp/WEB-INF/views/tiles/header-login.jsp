<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import = "org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import = "org.springframework.security.core.Authentication" %>
<%@page import = "org.springframework.security.core.GrantedAuthority" %>
<%@page import = "java.util.List" %>



<div class="navbar navbar-inverse navbar-static-bottom">
	<div class="navbar-inner">
		<table width="100%">
			<tr>
				<td width="26%">
					<h4 style="color: rgb(238,238,238); text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);" class="pull-left">
						<i style="margin-right: 5px; margin-top: 3px;" class="icon-eye-open icon-white"></i> Bug Tracking System<%// <img src="<c:url value="/resources/img/logo.png"/>">%>
					</h4>
				</td>
				
				<td width="74%" align="right">
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.language" /><b class="caret"></b></a>
							<ul class="dropdown-menu" style="text-align: left;">
								<li><a class="language" href="#myModal" url="?lang=en" data-toggle="modal"><img src="<c:url value="/resources/img/languageicon/en.png"/>"> <spring:message code="label.english" /></a></li>
								<li><a class="language" href="#myModal" url="?lang=ru" data-toggle="modal"><img src="<c:url value="/resources/img/languageicon/ru.png"/>"> <spring:message code="label.russian" /></a></li>
								<li><a class="language" href="#myModal" url="?lang=ua" data-toggle="modal"><img src="<c:url value="/resources/img/languageicon/ua.png"/>"> <spring:message code="label.ukrainian" /></a></li>
							</ul>
						</li>
						
						
						
					
					</ul>
				</td>
			</tr>
		</table>
	</div>
</div>
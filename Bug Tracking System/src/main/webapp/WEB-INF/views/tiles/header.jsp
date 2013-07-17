<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page import = "org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import = "org.springframework.security.core.Authentication" %>
<%@page import = "org.springframework.security.core.GrantedAuthority" %>
<%@page import = "java.util.List" %>

 <%

String currentURL = null;
if( request.getAttribute("javax.servlet.forward.request_uri") != null ) {
    currentURL = (String)request.getAttribute("javax.servlet.forward.request_uri");
	}
if( currentURL != null && request.getAttribute("javax.servlet.include.query_string") != null ) {
    currentURL += "?" + request.getQueryString();
	}
%> 

<div class="modal hide fade" tabindex="-1" role="dialog" id="confirmModal">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">×</a>
		<h3>Confirm</h3>
	</div>
	<div class="modal-body">
		&nbsp;
	</div>
	<div class="modal-footer">
		<a href="#" class="btn btn-danger">Ok</a>
		<a href="#" class="btn btn-cancel" data-dismiss="modal">Cancel</a>
	</div>
</div>

<div class="navbar navbar-inverse navbar-static-bottom">
	<div class="navbar-inner">
		<table width="100%">
			<tr>
				<td width="26%">
					<h4 style="color: rgb(238,238,238); text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);" class="pull-left">
						<i style="margin-right: 5px; margin-top: 3px;" class="icon-eye-open icon-white"></i> Bug Tracking System<%// <img src="<c:url value="/resources/img/logo.png"/>">%>
					</h4>
				</td>
				
				<td width="30%" align="left">
					<ul class="nav">
						<c:forEach var="actionList" items="${info.role.actionList}">
							<c:if test="${actionList.action == 'View issues'}">
								<li 
									<%if (currentURL.toLowerCase().contains("issue")) {%>
										class="active"
									<%} %>
								>
									<a href="<c:url value="/issue" />">
										<spring:message code="label.issue" />
									</a>
								</li>
							</c:if>
							<c:if test="${actionList.action == 'View projects'}">
								<li 
									<%if ((currentURL.toLowerCase().endsWith("project"))||
										  (currentURL.toLowerCase().contains("project/detail"))||
										  (currentURL.toLowerCase().contains("project/add"))||
										  (currentURL.toLowerCase().contains("project/edit"))||
										  (currentURL.toLowerCase().contains("project/delete"))) {%>
										class="active"
									<%} %>
								>
									<a href="<c:url value="/project" />">
										<spring:message code="label.project" />
									</a>
								</li>
							</c:if>
							<c:if test="${actionList.action == 'View users'}">
								<li 
									<%if (currentURL.toLowerCase().contains("user") || currentURL.toLowerCase().contains("user/role")) {%>
										class="active"
									<%} %>
								>
									<a href="<c:url value="/user" />">
										<spring:message code="label.user" />
									</a>
								</li>
							</c:if>
							<c:if test="${actionList.action == 'View roles'}">
								<li 
									<%if (currentURL.toLowerCase().endsWith("/role") ||  currentURL.toLowerCase().contains("/role/") ) {%>
										class="active"
									<%} %>
								>
									<a href="<c:url value="/role" />">
										<spring:message code="label.role" />
									</a>
								</li>
							</c:if>
						</c:forEach>	
					</ul>
				</td>
				
				<td width="44%" align="right">
					<ul class="nav pull-right" align="left">
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="label.language" /><b class="caret"></b></a>
							<ul class="dropdown-menu" style="text-align: left;">
								<li><a class="language" href="#languageModal" url="?lang=en" data-toggle="modal"><img src="<c:url value="/resources/img/languageicon/en.png"/>"> <spring:message code="label.english" /></a></li>
								<li><a class="language" href="#languageModal" url="?lang=ru" data-toggle="modal"><img src="<c:url value="/resources/img/languageicon/ru.png"/>"> <spring:message code="label.russian" /></a></li>
								<li><a class="language" href="#languageModal" url="?lang=ua" data-toggle="modal"><img src="<c:url value="/resources/img/languageicon/ua.png"/>"> <spring:message code="label.ukrainian" /></a></li>
							</ul>
						</li>
						
						<li class="divider-vertical"></li>
						
						<li>
							<span style="color: #999999; text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25); float: none; padding: 10px 15px; text-decoration: none; display: block;">
								<spring:message code="label.hi" />, ${info.userInfo.firstName} ${info.userInfo.secondName} (${info.role.roleName})
							</span>
						</li>
						
						<li class="divider-vertical"></li>
						
						<li><a href="<c:url value='/logout'/>"><spring:message code="label.logout" /></a></li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
</div>





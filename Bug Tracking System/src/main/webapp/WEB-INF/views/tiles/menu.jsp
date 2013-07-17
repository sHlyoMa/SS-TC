<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import = "org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import = "org.springframework.security.core.Authentication" %>
<%@page import = "org.springframework.security.core.GrantedAuthority" %>
<%@page import = "java.util.List" %>
<%
String currentURL = null;
if( request.getAttribute("javax.servlet.forward.request_uri") != null ){
    currentURL = (String)request.getAttribute("javax.servlet.forward.request_uri");
}
if( currentURL != null && request.getAttribute("javax.servlet.include.query_string") != null ){
    currentURL += "?" + request.getQueryString();
} 	
%>

<div id="leftmenu" style="max-height: 700px; overflow: auto">
	<ul class="nav nav-list">
		<%if ((currentURL.toLowerCase().endsWith("/project"))||(currentURL.toLowerCase().contains("/project/details/"))
				||(currentURL.toLowerCase().contains("/project/edit/"))||(currentURL.toLowerCase().contains("/project/add"))) {%>
			<li <%if (currentURL.toLowerCase().endsWith("/project")) {%>
				class="active"
			<%} %>><a href="<c:url value="/project" />"><spring:message code="label.project.my"/></a></li>
		<%} else if ((currentURL.toLowerCase().contains("/issue/sort=")) || (currentURL.toLowerCase().contains("/issue/project")) ||
				(currentURL.toLowerCase().contains("/issue/details/"))) { %>
			<li <%if (currentURL.toLowerCase().contains("/issue/sort=")) {%>
				class="active"
			<%} %>><a href="<c:url value="/issue" />"><spring:message code="label.issue.my"/></a></li>
		<%} else if ((currentURL.toLowerCase().endsWith("/role")) || (currentURL.toLowerCase().contains("/role/details/")) ||
				(currentURL.toLowerCase().contains("/role/edit/")) || (currentURL.toLowerCase().contains("/role/add"))) { %>
			<li <%if (currentURL.toLowerCase().endsWith("/role")) {%>
				class="active"
			<%} %>><a href="<c:url value="/role" />"><spring:message code="label.role.all"/></a></li>
		<%} else if ((currentURL.toLowerCase().contains("/user/sort=")) || (currentURL.toLowerCase().contains("/user/add")) || (currentURL.toLowerCase().contains("/user/role"))) { %>
			<li <%if (currentURL.toLowerCase().contains("/user/sort=")) {%>
				class="active"
			<%} %>><a href="<c:url value="/user" />"><spring:message code="label.user.all"/></a></li>
		<%} %>
		<li class="divider"></li>
		
		<%if (currentURL.toLowerCase().contains("issue")) {%>
			<c:forEach items="${projectList}" var="project">
				<li <c:if test="${project.projectId == projectId }">
					class="active"
				</c:if>><a href="<c:url value="/issue/projectId=${project.projectId}/sort=8-1&page=1&limit=10" />">${project.name}</a></li>
			</c:forEach>
		<%} else if (currentURL.toLowerCase().contains("project")) { %>
			<c:forEach items="${projectList}" var="project">
				<li <c:if test="${project.projectId == projectId }">
					class="active"
				</c:if>><a project-id-ajax="${project.projectId}" href="<c:url value="/project/details/${project.projectId}" />">${project.name}</a></li>
			</c:forEach>
		<%} else if (currentURL.toLowerCase().endsWith("/role")||currentURL.toLowerCase().contains("/role/")) {%>
			<c:forEach items="${roleList}" var="role">
				<li  <c:if test="${role.roleId == roleId }">
					class="active"
				</c:if>><a href="<c:url value="/role/details/${role.roleId}" />">${role.roleName}</a></li>
			</c:forEach>
		<%} else if (currentURL.toLowerCase().contains("/user")) {%>
			<c:forEach items="${roleList}" var="role">
				<li <c:if test="${role.roleId == roleId }">
					class="active"
				</c:if>><a href="<c:url value="/user/roleId=${role.roleId}/sort=1-1/page=1&limit=20" />">${role.roleName}</a></li>
			</c:forEach>
		<%} %>
		
		<c:forEach var="actionList" items="${info.role.actionList}">
			<c:if test="${actionList.action == 'Add role'}">
				<c:set var="addRole" value="${actionList.action}" />
			</c:if>
			
			<c:if test="${actionList.action == 'Add project'}">
				<c:set var="addProject" value="${actionList.action}" />
			</c:if>
		</c:forEach>
	
	<c:if test="${!empty addProject}">
		<%if (currentURL.toLowerCase().endsWith("/project") || currentURL.toLowerCase().contains("/project/details")) {%>
			<li class="divider"></li>
				<input class="btn btn-info" type="button" onclick="location.href='<c:url value="/project/add" />'" 
					value="<spring:message code="label.project.add"/>">
		<%} %>
	</c:if>
		
	<c:if test="${!empty addRole}">
		<%if (currentURL.toLowerCase().endsWith("/role") 
				|| currentURL.toLowerCase().contains("/role/add") 
				|| currentURL.toLowerCase().contains("/role/edit") 
				|| currentURL.toLowerCase().contains("/role/details")) {%>
			<li class="divider"></li>
				<input type="button" class="btn btn-info" onclick="location.href='<c:url value="/role/add" />'" 
					value="<spring:message code='label.role.addrole'/>" />
		<%} %>
	</c:if>
	
	</ul>

</div>
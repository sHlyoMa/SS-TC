<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="actionList" items="${info.role.actionList}">
	<c:if test="${actionList.action == 'Edit project'}">
		<c:set var="editProject" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Delete project'}">
		<c:set var="deleteProject" value="${actionList.action}" />
	</c:if>
</c:forEach>

<h4 align="center">
	<spring:message code="label.project.detail" />
</h4>

<div class="row">
	<div class="span6">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right:3px">
				<spring:message code="label.project.name" />
			</span>:
		</div>
		<div class=span8>${project.name}</div>
	</div>
</div>

<div class="row">
	<ul class="nav nav-list">
		<li class="divider"></li>
	</ul>
</div>

<div class="row">
	<div class="span6">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right:3px">
				<spring:message code="label.project.description" />
			</span>:
		</div>
		<div class=span8>${project.description}</div>
	</div>
	
	<div class="span6">
		<div class="row">
			<div class="span4" align="right">
				<span class="label label-info" style="margin-right:3px">
					<spring:message code="label.project.pm" />
				</span>:
			</div>
			<div class="span8">
				${project.pmUserFullName.firstName} ${project.pmUserFullName.secondName}
			</div>
		</div>
	</div>
</div>

<div class="row">
	<ul class="nav nav-list">
		<li class="divider"></li>
	</ul>
</div>

<div class="row">
	<div class="span12" align="right">
		<div class="span6">
			<div class="span4" align="right">
				<span class="label label-info" style="margin-right:3px">
					<spring:message code="label.project.team" />
				</span>:
			</div>
	
			<div class="span8" style="max-height: 300px; overflow: auto;" align="left">
				<c:if test="${!empty team}">
					<c:forEach items="${team}" var="team">
						${team.userFullName.firstName} ${team.userFullName.secondName} (${team.userRole.roleName})<br/>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div class="span6"></div>
	</div>
</div>

<c:if test="${!empty editProject || !empty deleteProject}">
	<div class="row">
		<ul class="nav nav-list">
			<li class="divider"></li>
		</ul>
	</div>
</c:if>

<div class="row">
	<div class="span6" align="left">
		<a class="btn btn-warning btn-small" onclick="history.go(-1)">
			<i class="icon-chevron-left icon-white"></i> 
			<spring:message code="label.back" />
		</a>
	</div>
	
	<div class="span5" align="right">
		<c:if test="${!empty deleteProject}">
			<input class="btn btn-danger" type="button" id="confirmRemoveDetail" url-attr="<c:url value="/project/delete/${project.projectId}"/>" value="<spring:message code="label.delete" />">
		</c:if>
	</div>
	<div class="span1" align="right">
		<c:if test="${!empty editProject}">
			<input class="btn btn-info" type="button" onclick="location.href='<c:url value="/project/edit/${project.projectId}"/>'" value="<spring:message code="label.edit" />">
		</c:if>
	</div>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/remove.confirm.js" />"></script>


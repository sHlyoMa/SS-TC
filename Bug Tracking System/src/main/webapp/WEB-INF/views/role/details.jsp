<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="actionList" items="${info.role.actionList}">
	<c:if test="${actionList.action == 'Edit role'}">
		<c:set var="editRole" value="${actionList.action}" />
	</c:if>
</c:forEach>

<h4 align="center">
	<spring:message code="label.role.detail" />
</h4>

<div class=row>
	<div class=span3 align="right">
		<spring:message code="label.role.name" />
		:
	</div>
	<div class=span6 align="left">${role.roleName}</div>
</div>


<div class=row>
	<div class=span3 align="right">
		<spring:message code="label.role.description" />
		:
	</div>
	<div class=span6 align="left">${role.description}</div>
</div>

<div class=row>
	<div class=span3 align="right">
		<spring:message code="label.role.action" />
		:
	</div>
	<div class=span6 align="left">
		<c:forEach items="${role.actionList}" var="actionList">
					${actionList.action} &nbsp;
				</c:forEach>
	</div>
</div>

<div class="row">
	<ul class="nav nav-list">
		<li class="divider"></li>
	</ul>
</div>
	
<div class=row>
	<div class="span6" align="left">
		<a class="btn btn-warning btn-small" onclick="history.go(-1)">
			<i class="icon-chevron-left icon-white"></i> 
			<spring:message code="label.back" />
		</a>
	</div>
	<div class=span6 align="right">
		<c:if test="${!empty editRole}">
			<input type="button" class="btn btn-info"
				onclick="location.href='<c:url value="/role/edit/${role.roleId}" />'"
				value="<spring:message code='label.edit'/>" />
		</c:if>
	</div>
</div>

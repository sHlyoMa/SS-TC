<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
label.valid {
	width: 24px;
	height: 24px;
	display: inline-block;
	text-indent: -9999px;
}

label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}
</style>
<script type="text/javascript"
	src="<c:url value="/resources/js/messages_${locale}.js" />"></script>

<c:forEach var="actionList" items="${info.role.actionList}">
	<c:if test="${actionList.action == 'Assign to'}">
		<c:set var="assignTo" value="${actionList.action}" />
	</c:if>
</c:forEach>

<title><spring:message code="label.title" /></title>

<c:choose>
	<c:when test="${addOrEdit == null}">
		<spring:message code="label.issueadd.title" var="title" />
		<c:set var="formAction" value="add" />
	</c:when>
	<c:otherwise>
		<spring:message code="label.issueupdate.title" var="title" />
		<c:set var="formAction" value="${issue.issueId}" />
	</c:otherwise>
</c:choose>
<h4 align="center">${title}</h4>
<form:form method="post" action="${formAction}" commandName="issue"
	id="issue-form">
	<div class=row>
		<div class=span2 align="right">
			<spring:message code="label.project.name" />
			:
		</div>
		<div class=span4>${projectName}</div>
	</div>

	<c:choose>
		<c:when test="${addOrEdit == null}">
			<div class=row>
				<div class=span2 align="right">
					<form:label path="typeId">
						<spring:message code="label.issue.type" />:
			</form:label>
				</div>
				<div class=span4>
					<form:select path="typeId" var="Type">
						<form:options items="${typeList}" itemValue="typeId"
							itemLabel="type" />
					</form:select>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class=row>
				<div class=span2 align="right">
					<spring:message code="label.issue.type" />
					:
				</div>
				<div class=span4>${issue.type.type}</div>
			</div>
		</c:otherwise>
	</c:choose>

	<div class="row control-group">
		<div class=span2 align="right">
			<form:label class="control-label" path="name">
				<spring:message code="label.issue.summary" />*:
			</form:label>
		</div>
		<div class="span4 controls">
			<form:input path="name" type="text" />
		</div>
		<div class=span6>
			<form:errors path="name" element="div" class="alert" />
		</div>
	</div>

	<div class="row control-group">
		<div class=span2 align="right">
			<form:label class="control-label" path="description">
				<spring:message code="label.issue.description" />*:
			</form:label>
		</div>
		<div class="span4 controls">
			<form:textarea type="text" class="input-large" rows="5"
				path="description" />
		</div>
		<div class=span6>
			<form:errors path="description" element="div" class="alert" />
		</div>
	</div>
	<c:if test="${!empty assignTo}">
		<div class=row>
			<div class=span2 align="right">
				<form:label path="assignerId">
					<spring:message code="label.issue.assigner" />:
			</form:label>
			</div>
			<div class=span4>
				<form:select path="assignerId">
					<form:option value="">None</form:option>
					<c:forEach var="team" items="${team}">
						<form:option value="${team.userId}">
							<c:out
								value="${team.userFullName.firstName} ${team.userFullName.secondName} (${team.userRole.roleName})" />
						</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
	</c:if>

	<div class=row>
		<div class=span2 align="right">
			<form:label path="priorityId">
				<spring:message code="label.issue.priority" />:
			</form:label>
		</div>
		<div class=span4>
			<form:select path="priorityId" var="Priority">
				<form:options items="${priorityList}" itemValue="priorityId"
					itemLabel="priority" />
			</form:select>
		</div>
	</div>
	<form:input path="statusId" type="hidden" />
	<div class=row>
		<div class=span2 align="right">
			<form:label path="environmentId">
				<spring:message code="label.issue.environment" />:
			</form:label>
		</div>
		<div class=span4>
			<form:select path="environmentId" var="Environment">
				<form:options items="${environmentList}" itemValue="environmentId"
					itemLabel="environment" />
			</form:select>
		</div>
	</div>



	<div class="row">
		<ul class="nav nav-list">
			<li class="divider"></li>
		</ul>
	</div>

	<div class="row control-group">
		<c:choose>
			<c:when test="${addOrEdit == null}">
				<div class="span6" align="left">
					<input type="button" class="btn btn-warning"
						onclick="history.go(-1)"
						value="<spring:message code="label.cancel" />" />
				</div>
				<div class="span5" align="right">
					<input type="button" class="btn btn-info" id="AddAndSave"
						value="<spring:message code="label.issue.save&add" />" />
				</div>
				<div class="span1" align="right">
					<input type="button" class="btn btn-info" id="Add"
						value="<spring:message code="label.add" />" />

				</div>
				<input type="hidden" id="hiddenAction" name="action" value="" />
			</c:when>
			<c:otherwise>
				<div class="span9" align="left">
					<input type="button" class="btn btn-warning"
						onclick="history.go(-1)"
						value="<spring:message code="label.cancel" />" />
				</div>
				<div class=span3 align="right">
					<input type="submit" class="btn btn-info"
						value="<spring:message code="label.save" />" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</form:form>

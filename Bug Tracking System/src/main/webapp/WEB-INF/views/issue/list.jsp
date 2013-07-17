<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
label.valid {
  width: 0px;
  height: 0px;
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

<script type="text/javascript">
	var count = parseInt("${issueCount}");
	var page = parseInt("${pageNumber}");
	var limit = parseInt("${recordsLimit}");
</script>

<script type="text/javascript"
	src="<c:url value="/resources/js/paginator_sort.js" />"></script>

<c:forEach var="actionList" items="${info.role.actionList}">
	<c:if test="${actionList.action == 'Add issue'}">
		<c:set var="addIssue" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Edit issue'}">
		<c:set var="editIssue" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Delete issue'}">
		<c:set var="deleteIssue" value="${actionList.action}" />
	</c:if>
</c:forEach>

<div class="row">
	<ul class="nav nav-list">
		<li class="divider"></li>
	</ul>
</div>
<c:url value="/" var="filterAction" />
<form:form method="get" action="${filterAction}${filterFormAction}"
	modelAttribute="issueFilter" >
	<table>
		<tr>
			<td width="80" align="right"><form:label path="typeId">
					<spring:message code="label.issue.type" /> &nbsp;
				</form:label></td>
			<td width="260"><form:select path="typeId" var="Type">
					<form:option value="">All</form:option>
					<form:options items="${typeList}" itemValue="typeId"
						itemLabel="type" />
				</form:select></td>
			<td align="right"><form:label path="statusId">
					<spring:message code="label.issue.status" /> &nbsp;
				</form:label></td>
			<td width="260"><form:select path="statusId" var="Status">
					<form:option value="">All</form:option>
					<form:options items="${statusList}" itemValue="statusId"
						itemLabel="status" />
				</form:select></td>
			<td align="right"><form:label path="blocked">
					<spring:message code="label.issue.block" /> &nbsp;
				</form:label></td>
			<td width="260"><form:select path="blocked" var="blocked">
					<form:option value="All">All</form:option>
					<form:option value="(blocked)">Blocked</form:option>
					<form:option value="(unblocked)">Unblocked</form:option>
				</form:select></td>
		</tr>
		<tr>
			<td width="80" align="right"><form:label path="priorityId">
					<spring:message code="label.issue.priority" /> &nbsp;
				</form:label></td>
			<td width="260"><form:select path="priorityId" var="Priority">
					<form:option value="">All</form:option>
					<form:options items="${priorityList}" itemValue="priorityId"
						itemLabel="priority" />
				</form:select></td>

			<td align="right"><form:label path="environmentId">
					<spring:message code="label.issue.environment" /> &nbsp;
				</form:label></td>
			<td width="260"><form:select path="environmentId"
					var="Environment">
					<form:option value="">All</form:option>
					<form:options items="${environmentList}" itemValue="environmentId"
						itemLabel="environment" />
				</form:select></td>
			<td align="right"><form:label path="search">
					<spring:message code="label.issue.search" /> &nbsp;
				</form:label></td>
			<td width="260" control-group><div class="span4 controls">
					<form:input path="search" type="text" />
				</div></td>
			<td>
			<div class="btn-group">
				<a type="button" class="btn btn-info" id="acceptFilter">
					<spring:message code="label.filter" />
				</a>
				<a type="button" title="<spring:message code="label.filter.reset" />"  class="btn btn-info" id="resetFilter">
					&nbsp;<i class="icon-remove icon-white"></i>
				</a>
			</div>
			</td>
		</tr>
	</table>
	<div class="row">
		<ul class="nav nav-list">
			<li class="divider"></li>
		</ul>
	</div>
</form:form>

<c:choose>
	<c:when test="${!empty issueList}">

		<div class="row" align="right">
			<select id="recordsLimit" style="width: 60px; align: right">
				<option id="limit10">10</option>
				<option id="limit20">20</option>
				<option id="limit50">50</option>
			</select>
		</div>
		<table class="table table-hover table-condensed">
			<thead>
				<tr>
					<th class="clicksort" id="1"></th>
					<th class="clicksort" id="2" style="text-align: center;"><spring:message
							code="label.issue.key" /></th>
					<th class="clicksort" id="3"><spring:message
							code="label.issue.summary" /></th>
					<th><spring:message code="label.issue.project" /></th>
					<th class="clicksort" id="4"><spring:message
							code="label.issue.assigned" /></th>
					<th class="clicksort" id="5" style="text-align: center;"><spring:message
							code="label.issue.status" /></th>
					<th class="clicksort" id="6" style="text-align: center;"><spring:message
							code="label.issue.priority" /></th>
					<th class="clicksort" id="7" style="text-align: center;"><spring:message
							code="label.issue.environment" /></th>
					<th class="clicksort" id="8"><spring:message
							code="label.issue.created" /></th>
					<c:if test="${!empty editIssue}">
						<th></th>
					</c:if>
					<c:if test="${!empty deleteIssue}">
						<th></th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${issueList}" var="issue">
					<tr url="<c:url value="/issue/details/${issue.issueId}"/>"
						style="background-color: #FFFFFF">
						<td class="clickrow" width="7" style="text-align: center;"><c:choose>
								<c:when test="${issue.typeId == '1' }">
									<i class="icon-leaf"></i>
								</c:when>

								<c:otherwise>
									<i class="icon-fire"></i>
								</c:otherwise>
							</c:choose></td>
						<td class="clickrow" width="60" style="text-align: center;">${issue.key}</td>
						<td class="clickrow" width="90">${issue.name}</td>
						<td class="clickrow" width="100">${issue.project.name}</td>
						<td class="clickrow" width="130">${issue.userAssigner.secondName}
							${issue.userAssigner.firstName}</td>

						<td class="clickrow" width="100" style="text-align: center;">${issue.status.status}
							${issue.blocked}</td>
						<td class="clickrow" width="80" style="text-align: center;">
							<c:choose>
								<c:when test="${issue.priorityId == 2}">
									<span class="label label-important label-center">${issue.priority.priority}</span>
								</c:when>
								<c:when test="${issue.priorityId == 3}">
									<span class="label label-warning">${issue.priority.priority}</span>
								</c:when>
								<c:when test="${issue.priorityId == 4}">
									<span class="label label-success">${issue.priority.priority}</span>
								</c:when>
								<c:when test="${issue.priorityId == 5}">
									<span class="label label-info">${issue.priority.priority}</span>
								</c:when>
								<c:when test="${issue.priorityId == 6}">
									<span class="label">${issue.priority.priority}</span>
								</c:when>
								<c:otherwise>
									<span class="label label-inverse">${issue.priority.priority}</span>
								</c:otherwise>
							</c:choose>
						</td>

						<td class="clickrow" width="80" style="text-align: center;">${issue.environment.environment}</td>
						<td class="clickrow" width="100" align="center">${issue.created}</td>
						<c:if test="${!empty editIssue}">
							<td width="5" style="text-align: center;"><a
								href="<c:url value="/issue/projectId=${issue.projectId}/edit/${issue.issueId}"/>"><i
									class="icon-pencil"></i></a></td>
						</c:if>
						<c:if test="${!empty deleteIssue}">
							<td width="5" style="text-align: center;">
								<a class="removeConfirmCall" url-attr="<c:url value="/issue/delete/${issue.issueId}"/>">
									<i class="icon-remove"></i>
								</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<div class="row"
			style="text-align: center; font-size: 28.5px; text-shadow: #EEEEEE; color: #999999;"">

			<spring:message code="label.issue.emptylist" />

		</div>
	</c:otherwise>
</c:choose>

<c:if test="${!empty addIssue}">
	<c:if test="${!empty projectId}">
		<input type="button" class="btn btn-info"
			onclick="location.href='<c:url value="/issue/projectId=${projectId}/add" />'"
			value="<spring:message code="label.issue.add"/>">
	</c:if>
</c:if>
<script type="text/javascript"
	src="<c:url value="/resources/js/remove.confirm.js" />"></script>
	
<div id="pagination" class="pagination pagination-centered"></div>

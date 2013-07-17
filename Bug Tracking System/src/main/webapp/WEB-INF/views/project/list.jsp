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
	<spring:message code="label.project.my" />
</h4>

<c:if test="${!empty projectList}">
	<table class="table table-hover table-condensed">
		<thead>
			<tr>
				<th width="160"><spring:message code="label.project.name" /></th>
				<th width="140"><spring:message code="label.project.pm" /></th>
				<th width="400"><spring:message code="label.project.description" /></th>
				<c:if test="${!empty editProject}">
					<th></th>
				</c:if>
				<c:if test="${!empty deleteProject}">
					<th></th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${projectList}" var="project">
				<tr url="project/details/${project.projectId}" style="background-color: #FFFFFF">
					<td class="clickrow">${project.name}</td>
					<td class="clickrow">${project.pmUserFullName.firstName} ${project.pmUserFullName.secondName}</td>
					<td class="clickrow">${project.description}</td>
					
					<c:if test="${!empty editProject}">
						<td width="5" style="text-align: center;">
							<a href="<c:url value="project/edit/${project.projectId}"/>">
								<i class="icon-pencil"></i>
							</a>
						</td>
					</c:if>
					
					<c:if test="${!empty deleteProject}">
						<td width="5" style="text-align: center;">
							<a class="removeConfirmCallAjax" 
									url-attr="<c:url value="project/delete"/>" 
									project-id="${project.projectId}">
								<i class="icon-remove"></i>
							</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

<script type="text/javascript" src="<c:url value="/resources/js/remove.confirm.js" />"></script>

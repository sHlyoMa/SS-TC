<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="actionList" items="${info.role.actionList}">
	
	<c:if test="${actionList.action == 'Edit role'}">
		<c:set var="editRole" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Delete role'}">
		<c:set var="deleteRole" value="${actionList.action}" />
	</c:if>
</c:forEach>

<c:if test="${!empty roleList}">
	<div class="bs-docs-example">
		<table class="table table-hover table-condensed" >
			<thead>
				<tr>		
					<th class="clicksort" id="1" width="20%"><spring:message
							code="label.role.name" /></th>			
					<th class="clicksort" id="2"><spring:message
							code="label.role.description" /></th>					
					<c:if test="${!empty editRole}">
						<th></th>
					</c:if>
					<c:if test="${!empty deleteRole}">
						<th></th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roleList}" var="role">
					<tr url="<c:url value="role/details/${role.roleId}"/>"
					style="background-color: #FFFFFF">						
						<td class="clickrow" align="center">${role.roleName}</td>
						<td class="clickrow" align="center">${role.description}</td>						
						<c:if test="${!empty editRole}">
							<td class="clickcell" url="<c:url value="role/edit/${role.roleId}" />" style="text-align: center; width: 15px;">
								<i class="icon-pencil"></i>
							</td>
						</c:if>
						<c:if test="${!empty deleteRole}">
							<td class="removeConfirmCall" url-attr="<c:url value="role/delete/${role.roleId}" />" style="text-align: center; width: 15px;">
								 <i class="icon-remove"></i>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>

<script type="text/javascript"
	src="<c:url value="/resources/js/remove.confirm.js" />"></script>
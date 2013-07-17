<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<script type="text/javascript" src="<c:url value="/resources/js/messages_${locale}.js" />"></script>

<c:choose>
	<c:when test="${showEditView == null}">
		<spring:message code="label.project.add" var="title" />
		<spring:message code="label.add" var="buttonTitle" />
		<c:set var="formAction" value="add" />
	</c:when>
	<c:otherwise>
		<spring:message code="label.project.edit" var="title" />
		<spring:message code="label.save" var="buttonTitle" />
		<c:set var="formAction" value="${project.projectId}" />
	</c:otherwise>
</c:choose>

<h4 align="center">
	${title}
</h4>

<form:form id="projectAddForm" method="post" action="${formAction}" commandName="project">
	<div class="row control-group">
		<div class="span2" align="right" style="width: 16%">
			<form:label class="control-label" path="name" style="margin-right: 8px;">
				<spring:message code="label.project.name" />*:
			</form:label>
		</div>
		<div class="span5 controls" align="left">
			<form:input style="width: 100%" path="name" />
		</div>
		<div class="span4" align="left" style="width: 38.5%">
			<form:errors path="name" element="div" cssClass="alert" />
		</div>
	</div>
	
	<div class="row control-group">
		<div class="span2" align="right" style="width: 16%">
			<form:label class="control-label" path="description" style="margin-right: 8px;">
				<spring:message code="label.project.description" />*:
			</form:label>
		</div>
		<div class="span5 controls" align="left">
			<form:textarea style="width: 100%" rows="5" path="description" />
		</div>
		<div class="span4" align="left" style="width: 38.5%">
			<form:errors path="description" element="div" cssClass="alert" />
		</div>
	</div>
	
	<div class="row">
		<div class="span2" align="right" style="width: 16%">
			<form:label path="team" style="margin-right: 8px;">
				<spring:message code="label.project.team" />:
			</form:label>
		</div>
		<div class="span5" align="left">
			<c:set var="first" value="1" />
			<select class="selectTeam" multiple>
				<c:forEach items="${userProject}" var="userProject" varStatus="loop">
					<c:if test="${first == 1}">
						<c:set var="first" value="2" />
						<c:set var="changeRole" value="${userProject.userRole.roleName}" />
					    <optgroup label="${userProject.userRole.roleName}">
					</c:if>
					<c:if test="${changeRole != userProject.userRole.roleName}">
						</optgroup>
						<c:set var="changeRole" value="${userProject.userRole.roleName}" />
					    <optgroup label="${userProject.userRole.roleName}">
					</c:if>
					
					<option value="${userProject.userInfoId}">${userProject.firstName} ${userProject.secondName} (${userProject.userRole.roleName})</option>						
				</c:forEach>
				
				</optgroup>
			</select>
			<div id="selectedInTeam" style="display: none">
			</div>
		</div>
		<div class="span4" align="left" style="width: 38.5%">
			<form:errors path="userId" element="div" cssClass="alert" />
		</div>
	</div>
	
	<div class="row">
		<ul class="nav nav-list">
			<li class="divider"></li>
		</ul>
	</div>
	
	<div class="row">
		<div class="span6" align="left">
			<input class="btn btn-warning" type="button" onclick="history.go(-1)" value="<spring:message code="label.cancel" />">
		</div>
		<div class="span6" align="right">
			<input class="btn btn-info" type="button" id="submitProject" value="${buttonTitle}" />
		</div>
	</div>
</form:form>

<script type="text/javascript">
	$('.selectTeam').select2();
	
	$('#submitProject').click(function(){
		$('#selectedInTeam').html('');
		if ($(".selectTeam").select2("val").length > 0) {
			$(".selectTeam").select2("val").forEach(function(userId) {
				var oldHtml = '';
				oldHtml = $('#selectedInTeam').html();
				$('#selectedInTeam').html(oldHtml + '<input type="hidden" name="userId" value="' + userId + '"/>');
			});	
		} else {
			$('#selectedInTeam').html('<input type="hidden" name="userId" value=""/>');
		}	
		$('#projectAddForm').submit();
	});
</script>

<c:if test="${showEditView != null}">
	<script type="text/javascript">
		currentUsers = new Array();
	</script>
	<c:forEach items="${userProjectCurrent}" var="userProjectCurrent" varStatus="loop">
		<script type="text/javascript">
			currentUsers.push({"id": "${userProjectCurrent.userInfoId}", "text": "${userProjectCurrent.firstName}"+" "+"${userProjectCurrent.secondName}"+" "+"(${userProjectCurrent.userRole.roleName})"});
		</script>
	</c:forEach>
	<script type="text/javascript">
		$('.selectTeam').select2("data", currentUsers);
	</script>
</c:if>



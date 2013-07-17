<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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


<script type="text/javascript">
	selectedArray = new Array();
</script>

<c:forEach items="${role.actionList}" var="actionList">
	<script type="text/javascript">
		selectedArray.push({
			"id" : "${actionList.actionId}",
			"text" : "${actionList.action}"
		});
	</script>
</c:forEach>

<c:url value="/role/actions" var="postURL" />
<script type="text/javascript">
	var URL = "${postURL}";
	$(document).ready(function() {
		$("#actionSelect").select2();
		$('#actionSelect').select2("data", selectedArray);
			
		if (typeof($("#actionSelect").select2("val")[0]) === 'undefined' || $("#actionSelect").select2("val") == null || $(this).select2("val").length == 0) {
			$('#submitRole').attr('disabled', 'disabled');	
		} 
		
		$('#submitRole').click(function() {
			$.ajax({
				type : "POST",
				url : URL,
				data : {
					actionsId : $("#actionSelect").select2("val")
				},
				success : function(response) {
					$('#roleForm').submit();
				}
			});
		});
		
		$("#actionSelect").change(function(element){
			console.log($("#actionSelect").select2("val"));
			//adding view projects action if one of project action was selected
			if (($("#actionSelect").select2("val").indexOf('1') >= 0 || $("#actionSelect").select2("val").indexOf('2') >= 0 || $("#actionSelect").select2("val").indexOf('3') >= 0 ) && 
					($("#actionSelect").select2("val").indexOf('25') < 0 )) {
				var selectValues = $("#actionSelect").select2("val");
				selectValues.push('25');
				$("#actionSelect").select2("val", selectValues);
			}
			//adding view issues action if one of issues action was selected
			if (($("#actionSelect").select2("val").indexOf('4') >= 0 || $("#actionSelect").select2("val").indexOf('5') >= 0 || $("#actionSelect").select2("val").indexOf('6') >= 0 ) && 
					($("#actionSelect").select2("val").indexOf('24') < 0 )) {
				var selectValues = $("#actionSelect").select2("val");
				selectValues.push('24');
				$("#actionSelect").select2("val", selectValues);
			}
			//adding view users action if one of users action was selected
			if (($("#actionSelect").select2("val").indexOf('7') >= 0 || $("#actionSelect").select2("val").indexOf('8') >= 0 || $("#actionSelect").select2("val").indexOf('9') >= 0 ) && 
					($("#actionSelect").select2("val").indexOf('26') < 0 )) {
				var selectValues = $("#actionSelect").select2("val");
				selectValues.push('26');
				$("#actionSelect").select2("val", selectValues);
			}
			//adding view roles action if one of roles action was selected
			if (($("#actionSelect").select2("val").indexOf('10') >= 0 || $("#actionSelect").select2("val").indexOf('11') >= 0 || $("#actionSelect").select2("val").indexOf('12') >= 0 ) && 
					($("#actionSelect").select2("val").indexOf('27') < 0 )) {
				var selectValues = $("#actionSelect").select2("val");
				selectValues.push('27');
				$("#actionSelect").select2("val", selectValues);
			}
			if (typeof($("#actionSelect").select2("val")[0]) === 'undefined' || $("#actionSelect").select2("val") == null || $(this).select2("val").length == 0) {
				$('#submitRole').attr('disabled', 'disabled');	
			} else {
				$('#submitRole').removeAttr('disabled');
			}	
		});

	});
	
</script>

<c:choose>
	<c:when test="${addOrEdit == null}">
		<spring:message code="label.role.addrole" var="title" />
		<c:set var="formAction" value="add" />
		<spring:message code="label.add" var="submitLabel" />
	</c:when>
	<c:otherwise>
		<spring:message code="label.role.editrole" var="title" />
		<c:set var="formAction" value="${role.roleId}" />
		<spring:message code="label.save" var="submitLabel" />
	</c:otherwise>
</c:choose>

<h4 align="center">${title}</h4>

<form:form method="post" action="${formAction}" id="roleForm"
	commandName="role">
	<div class="row control-group">
		<div class=span3 align="right">
			<form:label class="control-label" path="roleName">
				<spring:message code="label.role.name" />* :
			</form:label>
		</div>
		<div class="span4 controls">
			<form:input path="roleName" class="input-xlarge" />
		</div>
		<div class=span5>
			<form:errors path="roleName" element="div" class="alert" />
		</div>
	</div>
	<div class="row control-group">
		<div class=span3 align="right">
			<form:label class="control-label" path="description">
				<spring:message code="label.role.description" />* :
			</form:label>
		</div>
		<div class="span4 controls">
			<form:textarea type="text" class="input-xxlarge" rows="5"
				path="description" />
		</div>
		<div class=span5>
			<form:errors path="description" element="div" class="alert" />
		</div>
	</div>
	<div class=row>
		<div class=span3 align="right">
			<spring:message code="label.role.action" />
			:
		</div>
		<div class=span5 >
			<select id="actionSelect" multiple>
				<c:forEach items="${actionList}" var="actionList" varStatus="loop">
					<option value="${actionList.actionId}">${actionList.action}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="row">
		<ul class="nav nav-list">
			<li class="divider"></li>
		</ul>
	</div>

	<div class=row>
		<div class="span6" align="left">
			<input type="button" class="btn btn-warning" onclick="history.go(-1)"
				value="<spring:message code="label.cancel" />" />
		</div>
		<div class=span6 align="right">
			<input type="button" class="btn btn-info" id="submitRole"
				value="${submitLabel}" />
		</div>
	</div>
</form:form>
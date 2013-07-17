<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#comments').animate({
			scrollTop : $('#comments')[0].scrollHeight
		});
		$("#assignSelect").select2();
	});
</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/messages_${locale}.js" />"></script>

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

<c:forEach var="actionList" items="${info.role.actionList}">
	<c:if test="${actionList.action == 'Assign to'}">
		<c:set var="assignTo" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Assign to me'}">
		<c:set var="assignToMe" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Start progress'}">
		<c:set var="startProgress" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Resolve issue'}">
		<c:set var="resolveIssue" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Close issue'}">
		<c:set var="closeIssue" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Reopen issue'}">
		<c:set var="reopenIssue" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Add comment'}">
		<c:set var="addComment" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Reset issue'}">
		<c:set var="resetIssue" value="${actionList.action}" />
	</c:if>
	<c:if test="${actionList.action == 'Blocked'}">
		<c:set var="blockeIssue" value="${actionList.action}" />
		<spring:message code="label.issue.details.block" var="blockLabel"/>
	</c:if>
</c:forEach>

<c:if test="${issue.blocked != null}">
	<c:set var="assignToDisabled" value="disabled" />
	<c:set var="startProgressDisabled" value="disabled" />
	<c:set var="resolveIssueDisabled" value="disabled" />
	<c:set var="closeIssueDisabled" value="disabled" />
	<c:set var="reopenIssueDisabled" value="disabled" />
	<c:set var="resetIssueDisabled" value="disabled" />
	<spring:message code="label.issue.details.unblock" var="blockLabel"/>
</c:if>
<c:if
	test="${issue.assignerId != null && (issue.status.status != 'Open' || issue.status.status != 'Reopened')}">
	<c:set var="assignToDisabled" value="disabled" />
</c:if>
<c:if
	test="${issue.assignerId != sessionUserId ||  (issue.status.status != 'Open' && issue.status.status != 'Reopened')  }">
	<c:set var="startProgressDisabled" value="disabled" />
</c:if>
<c:if
	test="${issue.status.status != 'In Progress' || issue.assignerId != sessionUserId}">
	<c:set var="resolveIssueDisabled" value="disabled" />
</c:if>
<c:if
	test="${issue.status.status != 'Resolved' || issue.assignerId == null}">
	<c:set var="closeIssueDisabled" value="disabled" />
</c:if>
<c:if test="${issue.status.status != 'Closed'}">
	<c:set var="reopenIssueDisabled" value="disabled" />
</c:if>

<h4 align="center">
	<spring:message code="label.issue.detail" />
</h4>

<div class="row" align="center">
	<c:if test="${!empty assignTo}">
		<select id="assignSelect" ${assignToDisabled}>
			<option value="0">None</option>
			<c:forEach var="team" items="${team}">
				<option value="${team.userId}">${team.userFullName.firstName}
					${team.userFullName.secondName} (${team.userRole.roleName})</option>
			</c:forEach>
		</select>
	</c:if>
	<div class="btn-group" style="margin-left: 0px;">
		<c:if test="${!empty assignTo}">
			<input type="button" id="assign"
				style="border-bottom-left-radius: 0px; border-top-left-radius: 0px; margin-left: 0px; margin-right: 0px;"
				value="<spring:message
					code="label.issue.details.assign" />"
				class="btn" ${assignToDisabled} />
		</c:if>
		<c:if test="${!empty assignToMe}">
			<input type=button id="assignToMe" class="btn"
				value="<spring:message
			code="label.issue.details.assignToMe" />"
				${assignToDisabled} />
		</c:if>
		<c:if test="${!empty startProgress}">
			<input type=button id="startProgress" class="btn"
				value="<spring:message
			code="label.issue.details.start" />"
				${startProgressDisabled} />
		</c:if>
		<c:if test="${!empty resolveIssue}">
			<input type=button id="resolveIssue" class="btn"
				value="<spring:message
			code="label.issue.details.resolve" />"
				${resolveIssueDisabled} />
		</c:if>
		<c:if test="${!empty closeIssue}">
			<input type=button id="closeIssue" class="btn"
				value="<spring:message
			code="label.issue.details.close" />"
				${closeIssueDisabled} />
		</c:if>
		<c:if test="${!empty reopenIssue}">
			<input type=button id="reopenIssue" class="btn"
				value="<spring:message
			code="label.issue.details.reopen" />"
				${reopenIssueDisabled} />
		</c:if>
		<c:if test="${!empty resetIssue}">
			<input type=button id="resetIssue" class="btn"
				value="<spring:message
			code="label.issue.details.reset" />" ${resetIssueDisabled}/>
		</c:if>
		<c:if test="${!empty blockeIssue}">
			<input type=button id="blockIssue" class="btn"
				value="${blockLabel}" />
		</c:if>
	</div>
	<div class="row" align="center"></div>
</div>


<div class="row">
	<h4 align="center">${issue.name}</h4>
</div>
<div class=row>
	<div class="span9" align="left">
		<span class="label" style="margin-right: 3px"><spring:message
				code="label.project.name" /></span>: ${issue.project.name}
	</div>
	<div class=span3 align="right">
		<span class="label " style="margin-right: 3px"><spring:message
				code="label.issue.key" /></span>: # ${issue.key}
	</div>
</div>
<div class="row">
	<ul class="nav nav-list">
		<li class="divider"></li>
	</ul>
</div>

<div class="row">
	<div class="span4">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.type" /></span>:
		</div>
		<div class="span8" align="left">${issue.type.type}</div>
	</div>
	<div class="span4">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.priority" /></span>:
		</div>
		<div class="span8" align="left">${issue.priority.priority}</div>
	</div>
	<div class="span4">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.status" /></span>:
		</div>
		<div class="span8" align="left">${issue.status.status} ${issue.blocked}</div>
	</div>
</div>

<div class="row">
	<div class="span4">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.environment" /></span>:
		</div>
		<div class="span8" align="left">
			${issue.environment.environment}</div>
	</div>
	<div class="span4">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.reporter" /></span>:
		</div>
		<div class="span8" align="left">${issue.userReporter.firstName}
			${issue.userReporter.secondName}</div>
	</div>
	<div class="span4">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.assigner" /></span>:
		</div>
		<div class="span8" align="left">${issue.userAssigner.firstName}
			${issue.userAssigner.secondName}</div>
	</div>
</div>
<div class="row">
	<div class="span4">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.created" /></span>:
		</div>
		<div class="span8" align="left">${issue.created}</div>
	</div>
	<div class="span4">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.updated" /></span>:
		</div>
		<div class="span8" align="left">${issue.updated}</div>
	</div>
	<div class="span4">
		<div class="span4" align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.closedate" /></span>:
		</div>
		<div class="span8" align="left">${issue.closeDate}</div>
	</div>
</div>


<div class="row">
	<ul class="nav nav-list">
		<li class="divider"></li>
	</ul>
</div>

<div class="row">
	<div class="span6">

		<div class=span3 align="right">
			<span class="label label-info" style="margin-right: 3px"><spring:message
					code="label.issue.description" /></span>:
		</div>
		<div class="span9" align="left">${issue.description}</div>
	</div>
	<div class=span6 align="left">
		<div id="comments" class="span12"
			style="max-height: 300px; overflow: auto">
			<c:forEach items="${commentList}" var="comment">
				<div>
					<div class="span12 alert alert-info">
						<div class="span12" align="left">
							<span class="label " style="margin-right: 3px">${comment.userName.firstName}
								${comment.userName.secondName}</span> added a comment -
							${comment.creatingDate}
						</div>
						<div class="span11">${comment.value}</div>
					</div>
					<div class="span11">
						<ul class="nav nav-list">
							<li class="divider"></li>
						</ul>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:if test="${!empty addComment}">
			<form:form method="post" action="${issue.issueId}" commandName="comment" id="comment-form">
				<div class="span12" align="left">
					<div id="control-group-comment" class="control-group">
						<div>
							<form:label path="value" class="control-label">
								<spring:message code="label.comment.value" />&nbsp;:
							</form:label>
						</div>
						<div class="span12 controls">
							<form:textarea type="text" id="comment-value" class="span12" rows="4" path="value" />
						</div>
					</div>
					<form:errors path="value" element="div" class="alert" />
					<a id="dynamicCommentAdd" class="btn btn-info btn-small" ><spring:message code="label.comment.commentadd" /></a>
				</div>
			</form:form>
			<script type="text/javascript">
				$('#dynamicCommentAdd').click(function() {
					if (($('#comment-value').val() != null) && 
						($('#comment-value').val() != "") && 
						($('#comment-value').val().length < 240)) {
						$.ajax({
							url : "<c:url value='/issue/details/addcomment' />",
							type: 'POST',
							dataType: 'json',
							contentType: 'application/x-www-form-urlencoded',
							data : ({
									value: $('#comment-value').val(),
									issueId: $('#comment-form').attr('action')
									}),
							success: function (comment) { 
										$('<div><div class="span12 alert alert-info">' + 
												'<button class="close" data-dismiss="alert" type="button">×</button>' + 
												'<div class="span12" align="left">'+
												'<span class="label " style="margin-right: 3px">' +
													comment.userName.firstName + ' ' + comment.userName.secondName +
												'</span>' +
												'added a comment - ' + comment.creatingDate +
										  '</div>' +
										  '<div class="span11">' + comment.value + '</div></div>' + 
										  '<div class="span11">' + 
										  	'<ul class="nav nav-list">' + 
										  		'<li class="divider"></li>' + 
										  	'</ul>' + 
										  '</div></div>').appendTo($('#comments'));
										
										$('#comments').animate({
											scrollTop : $('#comments')[0].scrollHeight
										});
										
										$('#comment-value').blur();
										$('#comment-value').val("");
										$('#control-group-comment').removeClass("error");
										$('#control-group-comment').removeClass("success");
										$('#comment-value').removeClass("valid");
										$('label.error').remove();
									 }
						});
					}
				}); 
			</script>
		</c:if>
	</div>
	<div class=span12>
		<div class=span6 align="left">
		</div>
		<div class=span6 align="right">
			<a class="btn btn-warning btn-small" onclick="location.href='${backPath}'">
				<i class="icon-chevron-left icon-white"></i> 
				<spring:message code="label.back" />
			</a>
		</div>
	</div>
</div>
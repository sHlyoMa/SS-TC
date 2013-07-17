<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="span7">
	<h3>
		<spring:message code="label.user.title" />
	</h3>
</div>
<div align="right">
	<h3>
		<spring:message code="label.user.overall" />
		- ${count} -
	</h3>
</div>
<%
	String pmHaveProject = (String) request.getSession().getAttribute(
			"PmHaveProjects");
	if (pmHaveProject == "true") {
%><script>
	var countOfPmProjects = "${countOfPmProjects}";
	confirm("Cannot delete PM assigned to " + countOfPmProjects + " projects");
</script>
<%
	request.getSession().setAttribute("PmHaveProjects", "none");
	};
%>

<%
	String userInTeam = (String) request.getSession().getAttribute(
			"userInTeam");
	if (userInTeam == "true") {
%><script>
	
	confirm("Cannot delete user assigned to team");
</script>
<%
	request.getSession().setAttribute("userInTeam", "none");
	};
%>

<script type="text/javascript">
	var count = parseInt("${count}");
	var page = parseInt("${page}");
	var limit = parseInt("${recordsLimit}");
</script>

<script type="text/javascript"
	src="<c:url value="/resources/js/paginator_sort.js" />"></script>

<c:if test="${!empty userList}">
	<div class="row" align="right">
		<select id="recordsLimit" style="width: 60px; align: right">
			<option id="limit10">10</option>
			<option id="limit20">20</option>
			<option id="limit50">50</option>
		</select>
	</div>
	<div class="bs-docs-example">
		<table class="table table-hover table-condensed">
			<thead>
				<tr>
					<th class="clicksort" id="1" width="90" style="text-align: center;"><spring:message
							code="label.user.secondname" /></th>
					<th class="clicksort" id="2" width="100"
						style="text-align: center;"><spring:message
							code="label.user.firstname" /></th>
					<th class="clicksort" id="3" width="120"
						style="text-align: center;"><spring:message
							code="label.user.role" /></th>
					<th class="clicksort" id="4" width="90" style="text-align: center;"><spring:message
							code="label.user.login" /></th>
					<th class="clicksort" id="5" width="200" style="text-align: center;"><spring:message
							code="label.user.email" /></th>
					<th class="clicksort" id="6" width="8%" style="text-align: center;"><spring:message
							code="label.user.active" /></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user" >
					<tr url="<c:url value="/user/edit/${user.userId}"/>" style="background-color: #FFFFFF">
						<td class="clickrow" url="<c:url value="/user/edit/${user.userId}"/>"
							 align="center">${user.userInfo.secondName}</td>
						<td class="clickrow" url="<c:url value="/user/edit/${user.userId}"/>"
							 align="center">${user.userInfo.firstName}</td>
						<td class="clickrow" url="<c:url value="/user/edit/${user.userId}"/>"
							 align="center">${user.role.roleName}</td>
						<td class="clickrow" url="<c:url value="/user/edit/${user.userId}"/>"
							 align="center">${user.login}</td>
						<td class="clickrow" url="<c:url value="/user/edit/${user.userId}"/>"
							 align="center">${user.userInfo.email}</td>

						<td><c:if
								test="${user.enabled == 1}">

								<div class="switch switch-mini" data-off="danger"
									custom-id-bun="bun" user-id="${user.userId}">
									<input type="checkbox" CHECKED>
								</div>

							</c:if> <c:if test="${user.enabled == 0}">

								<div class="switch switch-mini" data-off="danger"
								custom-id-unban="unBun"
									user-id="${user.userId}">
									<input type="checkbox">
								</div>

							</c:if> <!-- <div class="switch switch-small">  switch-large, switch-small or switch-mini</div> --></td>


						<td width="5"
							text-align: center;"><a
							href="<c:url value="/user/edit/${user.userId}"/>"> <i
								class="icon-pencil"></i>
						</a></td>
						<td width="5"
							text-align: center;"><a
							class="removeConfirmCall"
							url-attr="<c:url value="/user/delete/${user.userId}" />"> <i
								class="icon-remove"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class=row>
		<div class=span6 align="left">
			<input type="button" class="btn btn-info"
				onclick="location.href='<c:url value="/user/add" />'"
				value="<spring:message code='label.user.adduser'/>" />
		</div>
	</div>
</c:if>
	<script type="text/javascript">
			$('div[custom-id-bun="bun"]').each(function(){
				$(this).click(function() {
					
						$.ajax({
							url : "<c:url value='/user/ban' />",
							type: 'GET',
							dataType: 'json',
							contentType: 'application/x-www-form-urlencoded',
							data : ({
								userId: $(this).attr('user-id')
								}),
							success: function () { 
										console.log('changed');
									 }
						});
					
				}); 
	});
			</script>
			<script type="text/javascript">
			$('div[custom-id-unban="unBun"]').each(function(){
				$(this).click(function() {
					
						$.ajax({
							url : "<c:url value='/user/unBan' />",
							type: 'GET',
							dataType: 'json',
							contentType: 'application/x-www-form-urlencoded',
							data : ({
								userId: $(this).attr('user-id')
								}),
							success: function () { 
										
									 }
						});
					
				}); 
	});
			</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/remove.confirm.js" />"></script>
<div id="pagination" class="pagination pagination-centered"></div>

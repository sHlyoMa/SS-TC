<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<h4 align="center">
	<spring:message code="label.useradd.title" />
</h4>

<form:form name="myForm" method="post" action="add" commandName="user">
	<table>
		<tr>
			<td><form:label path="login">
					<spring:message code="label.user.login" />
				</form:label></td>
			<td><form:input path="login" /></td>
			<td><form:errors path="login" element="div" cssClass="alert" /></td>
		</tr>
		<tr>
			<td><form:label path="password">
					<spring:message code="label.user.password" />
				</form:label></td>
			<td><form:input path="password" /></td>
			<td><form:errors path="password" element="div" cssClass="alert" /></td>
		</tr>
		<tr>
			<td><form:label path="userInfo.firstName">
					<spring:message code="label.user.firstname" />
				</form:label></td>
			<td><form:input path="userInfo.firstName" /></td>
			<td><form:errors path="userInfo.firstName" element="div"
					cssClass="alert" /></td>
		</tr>
		<tr>
			<td><form:label path="userInfo.secondName">
					<spring:message code="label.user.secondname" />
				</form:label></td>
			<td><form:input path="userInfo.secondName" /></td>
			<td><form:errors path="userInfo.secondName" element="div"
					cssClass="alert" /></td>
		</tr>
		<tr>
			<td><form:label path="userInfo.email">
					<spring:message code="label.user.email" />
				</form:label></td>
			<td><form:input path="userInfo.email" /></td>
			<td><form:errors path="userInfo.email" element="div"
					cssClass="alert" /></td>
		</tr>
		<tr>
			<td><form:label path="roleId">
					<spring:message code="label.user.role" />
				</form:label></td>
			<td><form:select path="roleId" var="Role">
					<form:options items="${roleList}" itemValue="roleId"
						itemLabel="roleName" />
				</form:select></td>
		</tr>
	</table>

	<div class="row">
		<ul class="nav nav-list">
			<li class="divider"></li>
		</ul>
	</div>

	<div class=row>
		<div class="span6" align="left">
			<input type="button" class="btn btn-warning"
				onclick="location.href='<c:url value="/user/sort=1-1/page=${page}&limit=${recordsLimit}"/>'"
				value="<spring:message code="label.cancel" />" />			
		</div>
		<div class=span6 align="right">
			<input type="submit" class="btn btn-info"
				value="<spring:message code="label.add"/>" />
		</div>
	</div>
</form:form>

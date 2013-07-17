<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<form name="myForm" method="POST"
		class="form-signin" action="login">
		<h3 class="form-signin-heading" align="center">
			<spring:message code="label.login.pleaseenter" />
		</h3>

		<input type="text"
			placeholder="<spring:message code="label.login.login" />"
			class="input-block-level" name="username" /> <input
			type="password" name="password"
			placeholder="<spring:message code="label.login.password" />"
			class="input-block-level" />

		<button type="submit" value="Login" class="btn btn-info">


			<spring:message code="label.login.enter" />
		</button>
		<%
		String state = (String) request.getSession().getAttribute("login");
		if (state == null) {
			state = "1";
		}
		if (state.equals("error")) {%>
			&nbsp;&nbsp; 
			<font color="blue"> 
				<spring:message code="label.error" />
			</font>
		<%
		request.getSession().setAttribute("login", null);
		};%>
	</form>
</div>
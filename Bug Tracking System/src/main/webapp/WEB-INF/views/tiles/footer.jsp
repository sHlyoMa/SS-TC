<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Modal -->
<div id="languageModal" class="hide fade" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="progress progress-striped active" style="z-index: 1042; 
														width: 80px; 
														position: fixed;
													    top: 50%;
   														left: 50%;
   														margin-left: -40px;
   														margin-top: -5px;">
    	<div class="bar" style="width: 100%;"></div>
    </div>
</div>

<div id="deleteConfirmation" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true" style="position: fixed;
													    top: 35%;
   														left: 50%;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">×</button>
		<h3 id="myModalLabel">&nbsp;</h3>
	</div>
	<div class="modal-body">
		<spring:message code="label.dialog.text" />&nbsp;
	</div>
	<div class="modal-footer">
		<button class="btn btn-warning" id="urlToRemoveAction"><spring:message code="label.dialog.yes" /></button>
		<button class="btn btn-info" data-dismiss="modal" aria-hidden="true"><spring:message code="label.dialog.no" /></button>
	</div>
</div>

<!-- <div style="float: right; color: #FFFFFF; padding-bottom: 15px; padding-right: 15px;">© SoftServe Ltd., Ch-010, 2013</div> -->


$(document).ready(function() {
	
	//click on rows in issue/project/user list
	$(".clickrow").each(function() {
		$(this).click(function() {
			window.location = $(this).parent().attr('url');
		});
	});
	
	//click on cell in issue/project/user list
	$(".clickcell").each(function() {
		$(this).click(function() {
			window.location = $(this).attr('url');
		});
	});
	
	//language change
	$(".language").each(function() {
		$(this).click(function() {
			window.location = $(this).attr('url');
		});
	});

	$('.selectpicker').selectpicker();
	
	//issue detail top buttons functions
	var url = document.location.href;
	$('#assignToMe').click(function(){
		self.location = url+"/assignToMe";
	});
	$('#startProgress').click(function(){
		self.location = url+"/startProgress";
	});
	$('#resolveIssue').click(function(){
		self.location = url+"/resolveIssue";
	});
	$('#closeIssue').click(function(){
		self.location = url+"/closeIssue";
	});
	$('#reopenIssue').click(function(){
		self.location = url+"/reopenIssue";
	});
	$('#resetIssue').click(function(){
		self.location = url+"/resetIssue";
	});
	$('#blockIssue').click(function(){
		self.location = url+"/blockIssue";
	});
	 
	//disabling "assign" button on issue detail view
	$("#assign").attr("disabled", "true");
	
	//issue detail view "assignTo" select
	$("#assignSelect").select2({
		 placeholder: "Select a State",
       allowClear: true
	});
	$('#assignSelect').change(function(){
		var selectId = $("#assignSelect").select2("val");
		if(selectId != 0){
			$('#assign').prop('disabled',false);
		} else{
			$('#assign').prop('disabled',true);
		}
		$('#assign').click(function(){
			self.location = url+"/assignTo?assignId="+selectId;
		});
	});
	
	//reset filter for issue button
	$('#resetFilter').click(function(){
		    $('#typeId').prop('selectedIndex',0);
		    $('#statusId').prop('selectedIndex',0);
		    $('#priorityId').prop('selectedIndex',0);
		    $('#environmentId').prop('selectedIndex',0);
		    $('#blocked').prop('selectedIndex',0);
		    $('#issueFilter').submit();
	});
	//submit filter form
	$('#acceptFilter').click(function(){
		$('#issueFilter').submit();	
	});
	
	//disable assignTo for Product owner users
	$('#typeId').change(function(){
		if ($("#typeId").val() == 2) $('#assignerId').prop('disabled', false); else {
			$('#assignerId').prop('disabled', true);
			$('#assignerId').prop('selectedIndex', 0);
		}
	});
	
	//add and save issue button
	$('#AddAndSave').click(function(){
		$('#hiddenAction').val($('#AddAndSave').attr('id'));
		$('#issue-form').submit();
	});
	
	//add issue button
	$('#Add').click(function(){
		$('#hiddenAction').val($('#Add').attr('id'));
		$('#issue-form').submit();
	});
	
	//add close icon to all alerts
	$('.alert').each(function(){
		var oldHtml = $(this).html();
		$(this).html("<button type='button' class='close' data-dismiss='alert'>&times;</button>" + oldHtml);
	});
	
	
	//client-based validation begin
	$('#issue-form').validate({
		rules: {
			name: {
				minlength: 2,
				maxlength: 50,
				required: true
			},
			description: {
			   	minlength: 10,
			   	maxlength: 240,
				required: true
			}
		},
		highlight: function(element) {
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
			.text('OK!').addClass('valid')
			.closest('.control-group').removeClass('error').addClass('success');
		}
	});

	$('#issueFilter').validate({
		rules: {
			search: {
				minlength: 1,
				maxlength: 50
			}
		},
		highlight: function(element) {
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
			.addClass('valid')
			.closest('.control-group').removeClass('error').addClass('success');
			element.closest('label').remove();
		}
	});

	$('#comment-form').validate({
		rules: {
				    value: {
				    	minlength: 1,
				    	maxlength: 240,
						required: true
				    }
		},
		highlight: function(element) {
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
			.addClass('valid')
			.closest('.control-group').removeClass('error').addClass('success');
			element.closest('label').remove();
		}
	});

	$('#projectAddForm, #projectEditForm').validate({
		rules: {
			name: {
				minlength: 1,
				maxlength: 45,
				required: true
			},
			description: {
			   	minlength: 1,
			   	maxlength: 240,
				required: true
			}
		},
		highlight: function(element) {
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
			.addClass('valid')
			.closest('.control-group').removeClass('error').addClass('success');
			element.closest('label').remove();
		}
	});
	
	$('#roleForm').validate({
		rules: {
			roleName: {
				minlength: 2,
				maxlength: 50,
				required: true
			},
			description: {
			   	minlength: 2,
			   	maxlength: 240,
				required: true
			}
		},
		highlight: function(element) {
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
			.addClass('valid')
			.closest('.control-group').removeClass('error').addClass('success');
			element.closest('label').remove();
		}
	});
	//client-based validation end
	
});







	





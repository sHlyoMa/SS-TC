$(".removeConfirmCall, #confirmRemoveDetail").each(function() {
	$(this).click(function() {
		$('#deleteConfirmation').modal();
		var removeAction = $(this).attr('url-attr');
		$('#urlToRemoveAction').attr('onclick', 'self.location="'+removeAction+'"');
		$('#deleteConfirmation').modal('show');
	});
});
ajaxDeleteFunction = function(urlAction, projectId) {
	$.ajax({
		url : urlAction,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded',
		data : ({
				projectId: projectId
				}),
		success: function (deletedProjectId) { 
					$('a[project-id="'+deletedProjectId+'"]').parent().parent().remove();
					$('a[project-id-ajax="'+deletedProjectId+'"]').parent().remove();
					$('#deleteConfirmation').modal('hide');
				 }
	});
};
$(".removeConfirmCallAjax").each(function() {
	$(this).click(function() {
		$('#deleteConfirmation').modal();
		var urlAction = $(this).attr('url-attr');
		var projectId = $(this).attr('project-id');
		$('#urlToRemoveAction').attr('onclick', 'ajaxDeleteFunction("'+urlAction+'",'+projectId+')');
		$('#deleteConfirmation').modal('show');
	});
});
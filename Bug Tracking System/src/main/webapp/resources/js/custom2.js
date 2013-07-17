$(document).ready(function() {
	$(".clickrow").each(function() {
		$(this).click(function() {
			window.location = $(this).parent().attr('url');
		});
	});
	
	$(".clicksort").each(function() {
		$(this).click(function() {
			
			var columnId = $(this).attr('id');
			var url = document.location.href;
			var sortId = url.match(/\d-\d/)[0].split('-');
			if(sortId[0]==columnId){
				if(sortId[1]==1){
				self.location= url.replace(sortId[0]+"-"+sortId[1], columnId+"-"+2);
				} else{
					self.location= url.replace(sortId[0]+"-"+sortId[1], columnId+"-"+1);
				}
				}else {
					self.location= url.replace(sortId[0]+"-"+sortId[1], columnId+"-"+1);
			}
		});
	});
	
	
	$(".language").each(function() {
		$(this).click(function() {
			window.location = $(this).attr('url');
		});
	});

	$('.selectpicker').selectpicker();
	
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
	$("#assignSelect").change(function() {
		  var selectId = $(this).children(":selected").attr("id");
		  $('#assign').click(function(){
			  self.location = url+"/assign?assignId="+selectId;
		  });
		});
	
	
	$('#resetFilter').click(function(){
		    $('#typeId').prop('selectedIndex',0);
		    $('#statusId').prop('selectedIndex',0);
		    $('#priorityId').prop('selectedIndex',0);
		    $('#environmentId').prop('selectedIndex',0);
	});
	
	
	
	/*$(function() {		
		var url = document.location.href;
		if ((count < 20)||(count == null)) return;
		else if(count%20 > 0){
			count = count/20 + 1;
		}else{
			count = count/20;
		}
		
		var target = $('.pagination'),
 		options = {
				prev : '«',
				next : '»',
				left : 3,
				right : 3,
				page : parseInt(page),
				lastPage : parseInt(count),
				click : function(i) {
					self.location = url.replace(/page=\d/,"page="+i);
				}
	      };
		console.log(options);
		target.pagination(options);
	});
	
	var url = document.location.href;
	var sortId = url.match(/\d-\d/)[0].split('-');
	var tdElement = $('.clicksort').get(sortId[0]-1);
	var oldText = $(tdElement).text();
	if(sortId[1] == 1) {
		$(tdElement).html(oldText+'<i class="icon-chevron-down"></i>');
	} else {
		$(tdElement).html(oldText+'<i class="icon-chevron-up"></i>');
	}*/

});



	





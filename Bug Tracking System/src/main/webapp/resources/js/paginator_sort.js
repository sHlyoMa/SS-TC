$(document).ready(function() {
	$(function() {		
			var url = document.location.href;
			if ((count < limit)||(count == null)) return;
			else if (count == limit) return;
			else if(count%limit > 0){
				count = count/limit + 1;
			}else{
				count = count/limit;
			}
			var target = $('.pagination'),
	 		options = {
					prev : '«',
					next : '»',
					left : 3,
					right : 3,
					page : page,
					lastPage : count,
					click : function(i) {
						self.location = url.replace(/page=\d+/,"page="+i);
					}
		      };
			console.log(options);
			target.pagination(options);
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

	var url = document.location.href;
	var sortId = url.match(/\d-\d/)[0].split('-');
	var tdElement = $('.clicksort').get(sortId[0]-1);
	var oldText = $(tdElement).text();
	if(sortId[1] == 1) {
		$(tdElement).html(oldText+'<i class="icon-chevron-down"></i>');
	} else {
		$(tdElement).html(oldText+'<i class="icon-chevron-up"></i>');
	}
	
	$('#recordsLimit').change(function(){
		self.location = url.replace(/limit=\d+/,"limit="+$("#recordsLimit").val());
	});

	$("#recordsLimit").val(limit);
});
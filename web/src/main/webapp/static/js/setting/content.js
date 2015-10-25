define(function(require, exports, module) {
	require('jquery');
	
	var Scrollspy = require('js/component/scrollspy.js');
	var spy = Scrollspy(".anchor");
	spy.scroll(function(showId){
		$(".sidebar-ul>li").each(function(){
			var module = $(this).attr("module");
			if(showId==module) $(this).addClass("active");
			else $(this).removeClass("active");
		});
	}).scroll();
});
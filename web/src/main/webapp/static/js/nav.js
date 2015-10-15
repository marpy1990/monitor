define(function(require, exports, module) {
	require('jquery');
	
	var top = $(".main-nav").offset().top;

	$(window).scroll(function() {
		var offset = $(document).scrollTop();
		if (offset > top) {
			$(".main-nav").addClass("position-fixed");
		} else {
			$(".main-nav").removeClass("position-fixed");
		}
	});

});
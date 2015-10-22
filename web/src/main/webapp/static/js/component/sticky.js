/**
 * 屏幕滑动时固定效果的控件
 */
define(function(require, exports, module) {
	require('jquery');

	module.exports = sticky;

	function sticky(node, top) {
		return new Sticky(node, top);
	}

	function Sticky(node, top) {
		var nodeTop = $(node).offset().top;
		var nodeLeft = $(node).offset().left;

		$(window).scroll(function() {
			var offset = $(document).scrollTop();
			if (offset + top > nodeTop) {
				$(node).css("position", "fixed");
				$(node).css("top", top+"px");
				$(node).css("left", nodeLeft);
			} else {
				$(node).css("position", "");
				$(node).css("top", "");
				$(node).css("left", "");
			}
		}).scroll();
	}

});
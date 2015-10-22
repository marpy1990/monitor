define(function(require, exports, module) {
	require('jquery');
	
	var Sticky = require('js/component/sticky.js');

	Sticky('.sidebar', $('.sidebar').offset().top-80);

});
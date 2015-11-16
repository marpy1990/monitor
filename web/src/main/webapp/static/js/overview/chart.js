define(function(require, exports, module){
	require('jquery');
	var Chart = require('js/component/chart.js');
	
	Chart('#container',{
		type: 'line',
		options:{
//			title: {
//	        	text: 'Test',
//	        }
		}
	});
});
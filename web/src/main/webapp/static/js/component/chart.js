/**
 * 曲线图插件
 */
define(function(require, exports, module) {

	module.exports = chart;

	function chart(node, setting) {
		switch (setting.type) {
		case 'line':
			var LineChart = require('js/component/charts/lineChart.js');
			return LineChart(node, setting);
		}
	}

});
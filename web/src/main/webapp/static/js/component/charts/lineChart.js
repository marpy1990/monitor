/**
 * 折线图
 */
define(function(require, exports, module) {

	require('highcharts');

	module.exports = lineChart;

	function lineChart(node, setting) {
		return new LineChart(node, setting);
	}

	function LineChart(node, setting) {
		var self = this;
		var chart;
		var _setting = {
			title : 'Line Chart',
			realtime : true,
			interval : 60000,
			from : now - this.interval,
			to : now,
			freq : 1000,
			series : [],
			options : {
				chart : {
					type : 'line',
					animation : false
				},
				xAxis : {
					type : 'datetime',
					tickPixelInterval : 150
				},
				tooltip : {
					formatter : function() {
						return '<b>'
								+ this.series.name
								+ '</b>: '
								+ Highcharts.numberFormat(this.y, 2)
								+ this.series.unit
								+ '<br/>'
								+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S',
										this.x);

					}
				},
				credits : {
					enabled : false
				}
			}
		}
		$.extend(true, _setting, setting);

		(function init() {
			$(node).highcharts(_setting.options);
			chart = $(node).highcharts();
			var from, to;
			var now = (new Date()).getTime();
			if (_setting.realtime) {
				from = now - _setting.interval;
				to = now;
			} else {
				from = _setting.from;
				to = _setting.to;
			}
			setSeries();
			setData(from, to);
		})();

		var lastRefresh = 0;
		(function refresh() {
			var now = (new Date()).getTime();
			if (_setting.realtime && now - lastRefresh >= _setting.freq) {
				lastRefresh = now;
				setData(now - _setting.interval, now);
			}
			setTimeout(refresh, 1000);
		})();

		function setSeries() {
			var seriesIds = [];
			$.each(_setting.series, function() {
				seriesIds.push(this.id);
			});
			$.ajax({
				type : 'POST',
				url : "/rpc/chart/getSeries.json",
				data : {
					seriesIds : seriesIds,
				},
				success : function(msg) {
					var labels = [];
					$.each(_setting.series, function(i, series) {
						$.extend(true, series, msg[i]);
						if (labels.indexOf(series.type.id) == -1) {
							labels.push(series.type.id);
							chart.addAxis({
								id : series.type.id,
								title : {
									text : series.type.name + '(' + series.unit
											+ ")"
								}
							}, false);
						}
						chart.addSeries({
							yAxis : series.type.id,
							name : series.name == undefined ? series.source.name
									: series.name,
							color : series.color == 'auto' ? undefined
									: series.color
						}, false);
					});
					chart.redraw();
				}
			});
		}

		function setData(from, to) {
			var seriesIds = [];
			$.each(_setting.series, function() {
				seriesIds.push(this.id);
			});
			$.ajax({
				type : 'POST',
				url : "/rpc/chart/getPoints.json",
				data : {
					seriesIds : seriesIds,
					from : from,
					to : to
				},
				success : setPoints
			});
		}

		function setPoints(lines) {
			$.each(chart.series, function(i, series) {
				var line=[];
				$.each(lines[i], function(j, p) {
					line.push([p.x, p.y]);
				});
				series.setData(line, false);
			});
			chart.redraw();
		}
	}
});
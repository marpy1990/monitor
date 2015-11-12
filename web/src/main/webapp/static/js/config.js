seajs.config({

	// 别名配置
	alias : {
		'jquery' : 'lib/jquery-1.11.3/jquery-1.11.3.min.js',
		'ztree' : 'lib/zTree_v3/js/jquery.ztree.all-3.5.min.js',
		'bootstrap' :  'lib/bootstrap-3.3.5-dist/js/bootstrap.min.js',
		'vis' : 'lib/vis/dist/vis.min.js',
		'highcharts' : 'lib/highcharts-4.1.9/js/highcharts.js'
	},

	// 路径配置
	paths : {
		'lib' : '/static/lib',
		'js' : '/static/js'
	},

	// 变量配置
	vars : {
		'locale' : 'zh-cn'
	},

	// 调试模式
	debug : true,

	// 文件编码
	charset : 'utf-8'

});
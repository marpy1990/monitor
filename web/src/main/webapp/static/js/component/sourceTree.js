define(function(require, exports, module) {
	require('jquery');
	require('ztree');

	var setting = {
		async : {
			enable : true,
			url : "/rpc/source_tree/getNodes.json",
			autoParam : [ "id", "name", "level" ],
			otherParam : {
				"otherParam" : "zTreeAsyncTest"
			},
		}
	};

	module.exports = {
		init : function(node) {
			$(document).ready(function() {
				$.fn.zTree.init(node, setting);
			});
		}
	}

});
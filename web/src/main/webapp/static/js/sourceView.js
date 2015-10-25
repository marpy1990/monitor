define(function(require, exports, module) {
	require('jquery');

	var Tree = require('js/component/sourceTree.js');

	var tree = Tree('#right-source-tree', {
		callback:{
			onClick : function(event, treeId, treeNode){
				
			}
		}
	});

});
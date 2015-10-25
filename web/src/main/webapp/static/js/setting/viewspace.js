define(function(require, exports, module) {
	require('jquery');

	var Tree = require('js/component/sourceTree.js');

	var setting = {
		edit : {
			enable : true,
		},
//		callback : {
//			beforeDrag : beforeDrag,
//			beforeDrop : beforeDrop
//		}
	};
	var treeLeft = Tree('#view-tree-left', setting);
	var treeRight = Tree('#view-tree-right', setting);
});
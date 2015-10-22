define(function(require, exports, module) {
	require('jquery');

	var Tree = require('js/component/sourceTree.js');

	var tree = Tree('#right-source-tree');
	tree.click(function(sourceId) {
		location.href = "/overview.html?sourceId=" + sourceId;
	});

});
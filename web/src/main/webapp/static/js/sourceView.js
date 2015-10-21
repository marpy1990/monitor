define(function(require, exports, module) {
	require('jquery');

	var Tree = require('js/component/sourceTree.js');

	var tree = Tree('#right-source-tree');
	tree.click(function(sourceId) {
		if (sourceId > 0)
			location.href = "/overview/detail.html?sourceId=" + sourceId;
		else
			location.href = "/overview.html";
	});

});
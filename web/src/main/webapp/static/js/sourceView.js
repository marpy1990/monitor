define(function(require, exports, module) {
	require('jquery');
	
	var tree = require('./component/sourceTree.js');

	tree.init($('.right-tree .ztree'));
});
define(function(require, exports, module) {
	require('jquery');
	require('ztree');

	module.exports = tree;

	function tree(node) {
		return new Tree(node);
	}

	function Tree(node) {
		var self = this;
		
		$(document).ready(function() {

			var setting = {
				async : {
					enable : true,
					url : "/rpc/source_tree/getNodes.json",
					autoParam : [ "id", "name", "level" ],
					otherParam : [ "treeId", $(node).attr('id') ]
				},

				callback : {
					onExpand : onExpand,
					onCollapse : onCollapse,
					onClick : onClick
				}
			};

			$.fn.zTree.init($(node), setting, null);
		});

		this.click = function(callback) {
			self._onClick = callback;
		}

		function onClick(event, treeId, treeNode) {
			self._onClick(treeNode.id);
		}

		function onExpand(event, treeId, treeNode) {
			$.ajax({
				type : 'POST',
				url : "/rpc/source_tree/expandNode.json",
				data : {
					id : treeNode.id,
					treeId : treeId,
				}
			});
		}

		function onCollapse(event, treeId, treeNode) {
			$.ajax({
				type : 'POST',
				url : "/rpc/source_tree/collapseNode.json",
				data : {
					id : treeNode.id,
					treeId : treeId,
				}
			});
		}

	}

});
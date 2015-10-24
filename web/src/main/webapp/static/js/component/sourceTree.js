/**
 * 生成source tree的控件
 */
define(function(require, exports, module) {
	require('jquery');
	require('ztree');

	module.exports = tree;

	function tree(node) {
		return new Tree(node);
	}

	function Tree(node) {
		var self = this;
		var treeId = $(node).attr('id');
		var spaceId;
		var rMenu = $("<div class='rMenu'><ul></ul></div>");
		this._onClick = null;

		$(node).after(rMenu);
		init();

		function init(msg) {
			$.ajax({
				type : 'POST',
				url : "/rpc/source_tree/getViewSpaces.json",
				data : {
					treeId : treeId
				},
				success : function(msg) {
					rMenuUl = rMenu.children('ul');
					rMenuUl.empty();
					spaceId = msg.current;
					msg.spaces.each(function(){
						var li = $("<li/>");
						li.attr("spaceId", this.id);
						li.text(this.name);
						rMenuUl.append(li);
					});
					$.fn.zTree.init($(node), setting(), null);
				}
			});
		}

		this.click = function(callback) {
			self._onClick = callback;
		}
		
		function setting(){
			return {
				async : {
					enable : true,
					url : "/rpc/source_tree/getNodes.json",
					autoParam : [ "id" ],
					otherParam : [ "treeId", treeId, "spaceId", spaceId ]
				},

				view : {
					dblClickExpand : false
				},

				callback : {
					onExpand : onExpand,
					onCollapse : onCollapse,
					onClick : onClick,
					onRightClick : onRightClick
				}
			};
		}

		function onClick(event, treeId, treeNode) {
			if (self._onClick != null)
				self._onClick(treeNode.id);
		}

		function onExpand(event, treeId, treeNode) {
			$.ajax({
				type : 'POST',
				url : "/rpc/source_tree/expandNode.json",
				data : {
					id : treeNode.id,
					treeId : treeId,
					spaceId : spaceId
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
					spaceId : spaceId
				}
			});
		}

		function OnRightClick(event, treeId, treeNode) {
			zTree.cancelSelectedNode();
			showRMenu(event.clientX, event.clientY);
		}

		function showRMenu(x, y) {
			rMenu.show();
			rMenu.css({
				"top" : y + "px",
				"left" : x + "px",
				"visibility" : "visible"
			});
			$("body").bind("mousedown", onBodyMouseDown);
		}

		function onBodyMouseDown(event) {
			rMenu.css({
				"visibility" : "hidden"
			});
		}

	}

});
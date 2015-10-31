define(function(require, exports, module) {
	require('jquery');

	var Tree = require('js/component/sourceTree.js');

	var setting = {
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom
		},
		edit : {
			enable : true,
			editNameSelectAll: true
		},
		callback : {
			// beforeDrag : beforeDrag,
			// beforeDrop : beforeDrop,
			beforeRemove : beforeRemove,
			onRename : onRename
		}
	};
	var trees = [];
	trees["view-tree-left"] = Tree('#view-tree-left', setting);
	trees["view-tree-right"] = Tree('#view-tree-right', setting);

	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
			return;
		var btn = $("<span class='button' onfocus='this.blur();' "
				+ "style='margin-left:2px; "
				+ "margin-right: -1px; background-position:-144px 0;"
				+ "vertical-align:top; *vertical-align:middle'></span>");
		btn.attr("id", "addBtn_" + treeNode.tId);
		btn.attr("sourceId", treeNode.id);
		sObj.after(btn);
		if (btn)
			btn.bind("click", function() {
				$.ajax({
					type : 'POST',
					url : "/rpc/setting/view_space/addSource.json",
					data : {
						parentId : treeNode.id,
						treeId : treeId
					},
					success : function() {
						trees[treeId].refresh();
					}
				});
			});
	}

	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.tId).unbind().remove();
	}

	function onRename(event, treeId, treeNode, isCancel) {
		if (isCancel)
			return;
		$.ajax({
			type : 'POST',
			url : "/rpc/setting/view_space/renameSource.json",
			data : {
				sourceId : treeNode.id,
				name : treeNode.name,
			},
			success : function() {
				$.fn.zTree.getZTreeObj(treeId).cancelSelectedNode();
			}
		});
	}

	function beforeRemove(treeId, treeNode) {
		if (!confirm("你确定移除 " + treeNode.name + " 吗？（我才懒得写回滚呢）"))
			return false;
		var ret = $.ajax({
			type : 'POST',
			url : "/rpc/setting/view_space/removeSource.json",
			async : false,
			data : {
				sourceId : treeNode.id,
				name : treeNode.name,
			}
		});

		return ret.responseJSON == true;
	}

});
define(function(require, exports, module) {
	require('jquery');

	var Modal = require('js/component/modal.js');
	var modal = Modal('#create-view-space');
	modal.title.text("新建资源视图");
	modal.body.html("<div class='form-group'>"
			+ "<label for='add-space'>视图名称</label>"
			+ "<input class='form-control' id='add-space'/></div>");
	modal.ok.click(function() {
		var name = $("#add-space").val();
		if (name == "")
			return;
		$.ajax({
			type : 'POST',
			url : "/rpc/setting/viewspace/addViewSpace.json",
			data : {
				name : name
			},
			success : function() {
				location.reload();
			}
		});
	});

	var Tree = require('js/component/sourceTree.js');

	var setting = {
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom
		},
		edit : {
			enable : true,
			editNameSelectAll : true,
			showRemoveBtn : showRemoveBtn
		},
		callback : {
			beforeDrag : beforeDrag,
			beforeDragOpen : beforeDragOpen,
			beforeDrop : beforeDrop,
			onDrop : onDrop,
			beforeRemove : beforeRemove,
			beforeRename : beforeRename
		}
	};
	var trees = {};
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
					url : "/rpc/setting/viewspace/addSource.json",
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

	function beforeRename(treeId, treeNode, newName, isCancel) {
		if (isCancel)
			return;
		var ok = false;
		$.ajax({
			type : 'POST',
			url : "/rpc/setting/viewspace/renameSource.json",
			async : false,
			data : {
				sourceId : treeNode.id,
				name : newName,
			},
			success : function(msg) {
				ok = (msg == true);
			}
		});
		$.fn.zTree.getZTreeObj(treeId).cancelSelectedNode();
		return ok;
	}

	function showRemoveBtn(treeId, treeNode) {
		if (treeNode.id == 0)
			return false;
		else
			return true;
	}

	function beforeRemove(treeId, treeNode) {
		var ok = false;
		$.ajax({
			type : 'POST',
			url : "/rpc/setting/viewspace/removeSource.json",
			async : false,
			data : {
				sourceId : treeNode.id,
				parentId : treeNode.getParentNode().id,
				treeId : treeId,
			},
			success : function(msg) {
				ok = (msg == true);
			}
		});

		return ok;
	}

	function beforeDrag(treeId, treeNodes) {
		if (treeNodes[0].id == 0)
			return false;

		treeNodes[0].fromTreeId = treeId;
		treeNodes[0].fromParentId = treeNodes[0].getParentNode().id;
		return true;
	}

	function beforeDragOpen(treeId, treeNode) {
		$.ajax({
			type : 'POST',
			url : "/rpc/source_tree/expandNode.json",
			data : {
				id : treeNode.id,
				treeId : treeId
			}
		});
		return true;
	}

	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		if (targetNode == null || (targetNode.id == 0 && moveType != "inner"))
			return false;
		else {
			return true;
		}
	}

	function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
		var parentId = 0;
		if (moveType == "inner")
			parentId = targetNode.id;
		else {
			if (targetNode == null || targetNode.getParentNode() == null)
				return;
			parentId = targetNode.getParentNode().id;
		}
		var sourceIds = [];
		$.each(treeNodes, function(i, e) {
			sourceIds[i] = e.id;
		});

		$.ajax({
			type : 'POST',
			url : "/rpc/setting/viewspace/moveSources.json",
			data : {
				parentId : parentId,
				fromParentId : treeNodes[0].fromParentId,
				sourceIds : JSON.stringify(sourceIds),
				isCopy : isCopy,
				fromTreeId : treeNodes[0].fromTreeId,
				treeId : treeId
			},

			success : function() {
				$.each(trees, function() {
					this.refresh();
				});
			}
		});
	}

});
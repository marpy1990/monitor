define(function(require, exports, module) {
	require('jquery');

	var Tree = require('js/component/sourceTree.js');

	var setting = {
		view : {
			addHoverDom : addHoverDom
		},
		edit : {
			enable : true,
		},
		callback : {
//			beforeDrag : beforeDrag,
//			beforeDrop : beforeDrop
		}
	};
	var treeLeft = Tree('#view-tree-left', setting);
	var treeRight = Tree('#view-tree-right', setting);
	
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
//		if (btn) btn.bind("click", function(){
//			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//			zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
//			return false;
//		});
	};

});
/**
 * 生成source tree的控件
 */
define(function(require, exports, module) {
	require('jquery');
	require('ztree');

	module.exports = tree;

	function tree(node, setting) {
		return new Tree(node, setting);
	}

	function Tree(node, setting) {
		var self = this;
		var treeId = $(node).attr('id');
		var spaceId;
		var rMenu = $("<ul class='dropdown-menu rMenu' style='position:fixed'></ul>");
		var _setting = {
			async : {
				enable : true,
				url : "/common/source_tree/getNodes.json",
				autoParam : [ "id" ],
				otherParam : [ "treeId", treeId, "spaceId", spaceId ]
			},

			view : {
				dblClickExpand : false
			},

			callback : {
				onExpand : onExpand,
				onCollapse : onCollapse,
				onRightClick : onRightClick
			}
		};
		
		$.extend(true, _setting, setting);
		$(node).after(rMenu);
		init();

		function init(msg) {
			$.ajax({
				type : 'POST',
				url : "/common/source_tree/getViewSpaces.json",
				data : {
					treeId : treeId
				},
				success : function(msg) {
					rMenu.empty();
					spaceId = msg.current;
					rMenu.append("<li class='text-center'><small>切换资源视图</small></li>");
					rMenu.append("<li role='separator' class='divider'></li>");
					$.each(msg.spaces, function() {
						var li=$("<li/>")
						var a=$("<a class='text-center' href='javascript:void(0)'/>");
						var small=$("<small/>");
						a.append(small);
						li.append(a);
						li.attr("spaceId", this.id);
						small.text(this.name + "域");
						if(this.id==spaceId) li.addClass("active");
						rMenu.append(li);
					});

					rMenu.css({
						"min-width" : "50px",
					});
					$.fn.zTree.init($(node), exSetting(), null);
				}
			});
		}
		
		function exSetting(){
			var asynP = {
					async :{
						otherParam : [ "treeId", treeId, "spaceId", spaceId ]
					}
			};
			return $.extend(true, {}, _setting, asynP);
			
		}

		function onExpand(event, treeId, treeNode) {
			$.ajax({
				type : 'POST',
				url : "/common/source_tree/expandNode.json",
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
				url : "/common/source_tree/collapseNode.json",
				data : {
					id : treeNode.id,
					treeId : treeId,
					spaceId : spaceId
				}
			});
		}

		function onRightClick(event, treeId, treeNode) {
			$.fn.zTree.getZTreeObj(treeId).cancelSelectedNode();
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
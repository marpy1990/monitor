/**
 * 生成source tree的控件
 */
define(function(require, exports, module) {
	require('jquery');
	require('ztree');

	module.exports = tree;

	function tree(node, setting) {
		$(node).css({
			"min-height" : "100%",
			"min-width" : "100%"
		});
		return new Tree(node, setting);
	}

	function Tree(node, setting) {
		var self = this;
		if (undefined == $(node).attr('id'))
			$(node).attr('id', 'default');
		var treeId = $(node).attr('id');
		var spaceId;
		var rMenu = $("<ul class='dropdown-menu rMenu' style='position:fixed;'></ul>");
		var _setting = {
			async : {
				enable : true,
				url : "/rpc/source_tree/getNodes.json",
				autoParam : [ "id" ],
				otherParam : [ "treeId", treeId ]
			},

			view : {
				dblClickExpand : false
			},

			callback : {
				onExpand : onExpand,
				onCollapse : onCollapse,
				onRightClick : onRightClick,
				onMouseDown : onMouseDown
			}
		};

		$.extend(true, _setting, setting);
		$(node).after(rMenu);

		this.refresh = init;

		init();

		function init() {
			$.ajax({
				type : 'POST',
				url : "/rpc/source_tree/getViewSpaces.json",
				data : {
					treeId : treeId
				},
				success : build
			});
		}

		function build(msg) {
			rMenu.empty();
			spaceId = msg.current;
			rMenu.append("<li class='text-center'><small>切换资源视图</small></li>");
			rMenu.append("<li role='separator' class='divider'></li>");
			$.each(msg.spaces, function() {
				var li = $("<li/>")
				var a = $("<a class='text-center' "
						+ "href='javascript:void(0)'/>");
				var small = $("<small/>");
				a.append(small);
				li.append(a);
				li.attr("spaceId", this.id);
				small.text(this.name + "视图");
				if (this.id == spaceId)
					li.addClass("active");
				rMenu.append(li);
			});

			rMenu.children('li').on('click', switchSpace);

			rMenu.css({
				"min-width" : "50px",
			});

			$.fn.zTree.init($(node), exSetting(), null);
		}

		function exSetting() {
			var asynP = {
				async : {
					otherParam : [ "treeId", treeId, "spaceId", spaceId ]
				}
			};
			return $.extend(true, {}, _setting, asynP);

		}

		function onExpand(event, treeId, treeNode) {
			$.ajax({
				type : 'POST',
				url : "/rpc/source_tree/expandNode.json",
				data : {
					id : treeNode.id,
					treeId : treeId
				}
			});
		}

		function onCollapse(event, treeId, treeNode) {
			$.ajax({
				type : 'POST',
				url : "/rpc/source_tree/collapseNode.json",
				data : {
					id : treeNode.id,
					treeId : treeId
				}
			});
		}

		function onRightClick(event, treeId, treeNode) {
			$.fn.zTree.getZTreeObj(treeId).cancelSelectedNode();
			showRMenu(event.clientX, event.clientY);
		}

		function onMouseDown(event, treeId, treeNode) {
			if (treeNode == null)
				$.fn.zTree.getZTreeObj(treeId).cancelSelectedNode();
		}

		function showRMenu(x, y) {
			rMenu.show();
			rMenu.css({
				"top" : y + "px",
				"left" : x + "px",
				"visibility" : "visible"
			});
			$("body").bind("mousedown", hideRMenu);
		}

		function hideRMenu(event) {
			if (!($(event.target).hasClass("rMenu") || $(event.target).parents(
					".rMenu").length > 0)) {
				rMenu.hide();
			}
		}

		function switchSpace() {
			$("body").unbind("mousedown", hideRMenu);
			var spaceId = $(this).attr('spaceId');
			if (undefined != spaceId) {
				$.ajax({
					type : 'POST',
					url : "/rpc/source_tree/switchSpace.json",
					data : {
						treeId : treeId,
						spaceId : spaceId
					},
					success : function() {
						rMenu.hide();
						init();
					},
					error : function() {
						$("body").bind("mousedown", hideRMenu);
					}
				});
			} else {
				$("body").bind("mousedown", hideRMenu);
			}
		}

	}

});
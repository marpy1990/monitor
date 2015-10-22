/**
 * 屏幕滚动时触发效果的控件
 */
define(function(require, exports, module){
	require('jquery');

	module.exports = scrollspy;

	function scrollspy(nodes) {
		return new Scrollspy(nodes);
	}
	
	function Scrollspy(nodes){
		var list=[];
		var self=this;
		this._scroll=null;
		$(nodes).each(function(){
			var id=$(this).attr("id");
			var height=$(this).offset().top;
			var obj={
				id : id,
				height : height
			};
			list.push(obj);
		});
		
		list.sort(function(a, b){
			return a.height - b.height;
		});
		
		this.scroll = function(callback) {
			if(callback==undefined)
				onScroll();
			else
				self._scroll = callback;
			return self;
		}
		
		$(window).scroll(onScroll);
		
		function onScroll(){
			var top = $(document).scrollTop();
			for(var i=list.length-1; i>=0; i--){
				var d = list[i].height - top;
				if(d<=0 || i==0){
					if(self._scroll!=null)
						self._scroll(list[i].id);
					break;
				}
			}
		}
		
	}

});
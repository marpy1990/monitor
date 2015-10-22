define(function(require, exports, module){
	require('jquery');
	
	module.exports = table;

	function table(node) {
		return new Table(node);
	}
	
	function Table(node){
		
		$(node).append('<thead><tr/>/thead><tbody/>');
		
		this.setHead = function(list){
			var tr = $(node).children('thread>tr');
			list.each(function(){
				tr.append('<th/>');
				tr.children('th').last().val(this);
			});
		}
		
		this.setBody = function(mat){
			var tbody = $(node).children('tbody');
			tbody.empty();
			mat.each(function(){
				tbody.append('<tr/>');
				var tr=tbody.children('tr').last();
				this.each(function(){
					tr.append('<th/>');
					tr.children('th').last().val(this);
				});
			});
		}
	}
});
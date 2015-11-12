/**
 * 弹窗控件
 */
define(function(require, exports, module) {
	require('jquery');
	require('bootstrap');

	module.exports = modal;
	
	function modal() {
		return new Modal();
	}

	function Modal() {
		var div_fade = $("<div class='modal fade' />");
		var div_dialog = $("<div class='modal-dialog' />");
		var div_content = $("<div class='modal-content'>");
		var div_header = $("<div class='modal-header'/>");
		var div_close = $("<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>");
		var div_title = $("<h4 class='modal-title'></h4>");
		var div_body = $("<div class='modal-body' />");
		var div_foot = $("<div class='modal-footer' />");
		var div_cancel = $("<button type='button' class='btn btn-default' data-dismiss='modal'>取消</button>");
		var div_ok = $("<button type='button' class='btn btn-primary'>确定</button>");
		
		div_header.append(div_close);
		div_header.append(div_title);
		
		div_foot.append(div_cancel);
		div_foot.append(div_ok);
		
		div_content.append(div_header);
		div_content.append(div_body);
		div_content.append(div_foot);
		
		div_dialog.append(div_content);
		div_fade.append(div_dialog);
		
		this.title = div_title;
		this.body = div_body;
		this.foot = div_foot;
		this.ok = div_ok;
		this.cancel = div_cancel;
		this.close = div_close;
		
		this.show = function(){
			div_fade.modal();
		}
	}

});
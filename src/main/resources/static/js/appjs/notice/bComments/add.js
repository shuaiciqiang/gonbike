$().ready(function() {
	$('.summernote').summernote({
		height:'220px',
		lang : 'zh-CN'
	});
	validateRule();
});
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var content_sn = $("#content_sn").code();
	$("#content").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/notice/bComments/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.statusCode ==200) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.message)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			title : {
				required : true
			}
		},
		messages : {
			title : {
				required : icon + "请输入标题"
			}
		}
	})
}
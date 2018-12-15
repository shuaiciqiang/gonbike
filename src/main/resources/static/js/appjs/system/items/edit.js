$().ready(function() {
	validateRule();
    init();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function init(){
    var itemId=$("#id").val();
    $.ajax({
        type: "GET",
        url: "/system/label/allList?itemId="+itemId,
        success: function(data){
            var html="";
            $("#shopItemLabelList").html("");
            if(data.statusCode == 200){
                for(var i=0;i<data.list.length;i++){
                    html=html+'<li style="float:left;width:100px;">'+data.list[i].labelName+'<input type="checkbox" id="labelId'+data.list[i].id+'" data="shopItemLabel" value="'+data.list[i].labelCode+'" /></li>';
                }
            }
            $("#shopItemLabelList").html("<ul style='width:500px'>"+html+"</ul>");

            if(data.statusCode == 200){
                for(var i=0;i<data.itemLabelList.length;i++){
                	$("#labelId"+data.itemLabelList[i].id).attr("checked",true);
                }
            }


        }
    });
}
function update() {
    var itemLabels="";
    $("input[data='shopItemLabel']").each(function(){
    	if(this.checked) {
            itemLabels = itemLabels + this.id.replace("labelId", "") + ",";
        }
    })
    itemLabels=itemLabels.substring(0,itemLabels.length-1);
    $("#shopItemLabels").val(itemLabels);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/items/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.statusCode == 200) {
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}
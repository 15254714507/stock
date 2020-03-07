//表单验证提交
$("#form-deliveryOrder-update").Validform({

    tiptype: 2,

    callback: function (data) {
        if (data.status == 1) {
            layer.msg(data.info, {icon: data.status, time: 1000}, function () {
                location.reload();//刷新页面
            });
        } else {
            layer.msg(data.info, {icon: data.status, time: 3000});
        }
        var index = parent.$("#iframe").attr("src");
        parent.layer.close(index);
        //
    }


});
//修改药品信息
function updateClick() {
    $.ajax({
        url: "/updateDeliveryOrder.do",
        type: "POST",
        cache: false,
        data: $("#form-deliveryOrder-update").serialize(),
        dataType: "json",
        success: function (result) {
            if (result.code === 200) {
                layer.alert(result.msg, {
                    title: '成功框',
                    icon: 1
                }, function(){
                    //刷新当前页面
                    parent.window.location.reload();
                });
            } else {
                layer.alert(result.msg, {
                    title: '错误框',
                    icon: 2,
                });
            }

        },
        error: function () {
            alert("连接服务器异常，请刷新后重试")
        }
    });
}
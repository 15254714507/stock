laydate({
    elem: '#start',
    event: 'focus'
});
$(function() {
    var oTable1 = $('#sample-table').dataTable( {
        "aaSorting": [[0, "asc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable":false,"aTargets":[2,3,4]}// 制定列不参与排序
        ]});
})
/*发布*/
function member_publish(obj, id) {
    $.ajax({
        url: "/publishPurchaseOrder.do",
        type: "POST",
        cache: false,
        data: {
            id: id
        },
        dataType: "json",
        success: function (result) {
            if (result.code === 200) {
                layer.alert(result.msg, {
                        title: '成功框',
                        icon: 1
                    },function(){
                        //刷新当前页面
                        window.location.reload();
                    }
                );
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
/*编辑*/
function member_edit(obj, id) {
    layer.open({
        type: 2,
        title: '修改入库单信息',
        closeBtn: 1, //显示关闭按钮
        area: ['600px', '200px'],
        shadeClose: false,
        content: ['/gotoUpdatePurchaseOrder.do?id=' + id, 'yes'],
    });
}

/*删除*/
function member_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            url: "/deletePurchaseOrder.do",
            type: "POST",
            cache: false,
            data: {
                id: id
            },
            dataType: "json",
            success: function (result) {
                if (result.code === 200) {
                    layer.alert(result.msg, {
                            title: '成功框',
                            icon: 1
                        },function(){
                            //刷新当前页面
                            window.location.reload();
                        }
                    );
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

    });
}
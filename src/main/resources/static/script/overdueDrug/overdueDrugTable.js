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
            {"orderable":false,"aTargets":[1,2,3,4,5,6,7,8]}// 制定列不参与排序
        ]});
})
/*处理*/
function member_process(obj, id) {
    $.ajax({
        url: "/processOverdueDrug.do",
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
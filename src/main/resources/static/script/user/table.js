jQuery(function ($) {
    var oTable1 = $('#sample_table').dataTable({
        "aaSorting": [[1, "desc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable": false, "aTargets": [0, 2, 3, 4, 5, 6, 8,]}// 制定列不参与排序
        ]
    });


    $('table th input:checkbox').on('click', function () {
        var that = this;
        $(this).closest('table').find('tr > td:first-child input:checkbox')
            .each(function () {
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });

    });


    $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});

    function tooltip_placement(context, source) {
        var $source = $(source);
        var $parent = $source.closest('table');
        var off1 = $parent.offset();
        var w1 = $parent.width();

        var off2 = $source.offset();
        var w2 = $source.width();

        if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
        return 'left';
    }
});

/*编辑*/
function member_edit(obj, id) {
    layer.open({
        type: 2,
        title: '修改信息',
        closeBtn: 1, //显示关闭按钮
        area: ['600px', '550px'],
        shadeClose: false,
        content: ['/gotoUpdateUser.do?id=' + id, 'yes'],
    });
}

/*删除*/
function member_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            url: "/deleteUser.do",
            type: "POST",
            cache: false,
            data: {
                id: id
            },
            dataType: "json",
            success: function (result) {
                if (result.code === 200) {
                    layer.alert("删除成功", {
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
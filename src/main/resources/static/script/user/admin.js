$("#testIframe").load("/gotoUserTable.do");
jQuery(function ($) {
    var oTable1 = $('#sample_table').dataTable({
        "aaSorting": [[1, "desc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable": false, "aTargets": [0, 2, 3, 4, 5, 7, 8,]}// 制定列不参与排序
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
$(function () {
    $("#administrator").fix({
        float: 'left',
        //minStatue : true,
        skin: 'green',
        durationTime: false,
        spacingw: 50,//设置隐藏时的距离
        spacingh: 270,//设置显示时间距
    });
});

//字数限制
function checkLength(which) {
    var maxChars = 100; //
    if (which.value.length > maxChars) {
        layer.open({
            icon: 2,
            title: '提示框',
            content: '您输入的字数超过限制!',
        });
        // 超过限制的字数了就将 文本框中的内容按规定的字数 截取
        which.value = which.value.substring(0, maxChars);
        return false;
    } else {
        var curr = maxChars - which.value.length; //250 减去 当前输入的
        document.getElementById("sy").innerHTML = curr.toString();
        return true;
    }
};
//初始化宽度、高度
$(".widget-box").height($(window).height() - 215);
$(".table_menu_list").width($(window).width() - 260);
$(".table_menu_list").height($(window).height() - 215);
//当文档窗口发生改变时 触发
$(window).resize(function () {
    $(".widget-box").height($(window).height() - 215);
    $(".table_menu_list").width($(window).width() - 260);
    $(".table_menu_list").height($(window).height() - 215);
})
laydate({
    elem: '#start',
    event: 'focus'
});

/*用户-停用*/
function member_stop(obj, id) {
    layer.confirm('确认要停用吗？', function (index) {
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="fa fa-close bigger-120"></i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
        $(obj).remove();
        layer.msg('已停用!', {icon: 5, time: 1000});
    });
}

/*用户-启用*/
function member_start(obj, id) {
    layer.confirm('确认要启用吗？', function (index) {
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="fa fa-check  bigger-120"></i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
        $(obj).remove();
        layer.msg('已启用!', {icon: 6, time: 1000});
    });
}

/*产品-编辑*/
function member_edit(title, url, id, w, h) {
    layer_show(title, url, w, h);
}

/*产品-删除*/
function member_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        $(obj).parents("tr").remove();
        layer.msg('已删除!', {icon: 1, time: 1000});
    });
}

/*添加管理员*/
$('#administrator_add').on('click', function () {
    layer.open({
        type: 1,
        title: '添加管理员',
        area: ['700px', ''],
        shadeClose: false,
        content: $('#add_administrator_style'),

    });
})
//表单验证提交
$("#form-admin-add").Validform({

    tiptype: 2,

    callback: function (data) {
        //form[0].submit();
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
$("#testIframe").load("/gotoUserTable.do");
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
$(".table_menu_list").height($(window).height()-68);
//当文档窗口发生改变时 触发
$(window).resize(function () {
    $(".widget-box").height($(window).height() - 215);
    $(".table_menu_list").width($(window).width() - 260);
    $(".table_menu_list").height($(window).height()-68);
});
/*添加管理员*/
$('#administrator_add').on('click', function () {
    layer.open({
        type: 2,
        title: '添加管理员',
        closeBtn: 1, //不显示关闭按钮
        area: ['600px', '550px'],
        shadeClose: false,
        content: ['/gotoAddAdmin.do', 'yes'],
    });
});

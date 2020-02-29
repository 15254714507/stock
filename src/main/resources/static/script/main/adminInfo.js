//按钮点击事件
function modify() {
    $('.text_info').attr("disabled", false);
    $('.text_info').addClass("add");
    $('#Personal').find('.xinxi').addClass("hover");
    $('#Personal').find('.btn-success').css({'display': 'block'});
};

function save_info() {
    let num = 0;
    let str = "";
    if ($("#name").val() === "") {
        layer.alert('姓名不能为空!', {
            title: '提示框',
            icon: 0,
        });
        return false;
    }
    if ($("#phone").val() === "") {
        layer.alert('手机号码不能为空!', {
            title: '提示框',
            icon: 0,

        });
        return false;
    }
    if ($("#email").val() === "") {
        layer.alert('邮箱地址不能为空!', {
            title: '提示框',
            icon: 0,
        });
        return false;
    }
    $.ajax({
        url: "/changeInformation.do",
        type: "POST",
        cache: false,
        data: {
            userAccount: $("#account").val(),
            name: $("#name").val(),
            phone: $("#phone").val(),
            email: $("#email").val()
        },
        dataType: "json",
        success: function (result) {
            if (result.code === 200) {
                layer.alert(result.msg, {
                    title: '成功框',
                    icon: 1,
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
    $('#Personal').find('.xinxi').removeClass("hover");
    $('#Personal').find('.text_info').removeClass("add").attr("disabled", true);
    $('#Personal').find('.btn-success').css({'display': 'none'});
    layer.close(index);


};
//初始化宽度、高度
$(".admin_modify_style").height($(window).height());
$(".recording_style").width($(window).width() - 400);
//当文档窗口发生改变时 触发
$(window).resize(function () {
    $(".admin_modify_style").height($(window).height());
    $(".recording_style").width($(window).width() - 400);
});

//修改密码
function change_Password() {
    layer.open({
        type: 1,
        title: '修改密码',
        area: ['300px', '300px'],
        shadeClose: true,
        content: $('#change_Pass'),
        btn: ['确认修改'],
        yes: function (index, layero) {
            if ($("#password").val() == "") {
                layer.alert('原密码不能为空!', {
                    title: '提示框',
                    icon: 0,

                });
                return false;
            }
            if ($("#Nes_pas").val() == "") {
                layer.alert('新密码不能为空!', {
                    title: '提示框',
                    icon: 0,

                });
                return false;
            }

            if ($("#c_mew_pas").val() == "") {
                layer.alert('确认新密码不能为空!', {
                    title: '提示框',
                    icon: 0,

                });
                return false;
            }
            if (!$("#c_mew_pas").val || $("#c_mew_pas").val() != $("#Nes_pas").val()) {
                layer.alert('密码不一致!', {
                    title: '提示框',
                    icon: 0,

                });
                return false;
            } else {
                $.ajax({
                    url: "/changePassword.do",
                    type: "POST",
                    cache: false,
                    data: $("#changePasswordForm").serialize(),
                    dataType: "json",
                    success: function (result) {
                        if (result.code === 200) {
                            //顶层页面跳转到登录页重新登录
                            top.location.href = "/";
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
                layer.close(index);
            }
        }
    });
}

jQuery(function ($) {
    let oTable1 = $('#sample-table').dataTable({
        "aaSorting": [[1, "desc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable": false, "aTargets": [0, 2, 3, 4, 5, 6]}// 制定列不参与排序
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
});
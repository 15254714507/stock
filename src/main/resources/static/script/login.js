$('#login_btn').on('click', function () {
    debugger;
    var num = 0;
    var str = "";
    $("input[type$='text'],input[type$='password']").each(function (n) {
        if ($(this).val() == "") {
            layer.alert(str += "" + $(this).attr("name") + "不能为空！\r\n", {
                title: '提示框',
                icon: 0,
            });
            num++;
            return false;
        }
    });
    if (num > 0) {
        return false;
    } else {
        // layer.alert('登陆成功！', {
        //     title: '提示框',
        //     icon: 1,
        // });
        // location.href = "index.html";
        document.getElementById("form").submit();
    }

});
$(document).ready(function () {
    $("input[type='text'],input[type='password']").blur(function () {
        var $el = $(this);
        var $parent = $el.parent();
        $parent.attr('class', 'frame_style').removeClass(' form_error');
        if ($el.val() == '') {
            $parent.attr('class', 'frame_style').addClass(' form_error');
        }
    });
    $("input[type='text'],input[type='password']").focus(function () {
        var $el = $(this);
        var $parent = $el.parent();
        $parent.attr('class', 'frame_style').removeClass(' form_errors');
        if ($el.val() == '') {
            $parent.attr('class', 'frame_style').addClass(' form_errors');
        } else {
            $parent.attr('class', 'frame_style').removeClass(' form_errors');
        }
    });
})
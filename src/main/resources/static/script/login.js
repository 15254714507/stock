$('#login_btn').on('click', function () {
    debugger;
    //验证账号不能为空，长度要符合要求 验证密码不能为空，长度要符合要求
    if (!validateAccount() || !validatePassword()) {
        return false;
    }
    document.getElementById("form").submit();
});

/**
 * 校验账号
 * @returns {boolean}
 */
function validateAccount() {
    let userAccountDom = $("#username");
    if (userAccountDom.val() === "") {
        layer.alert( "账号不能为空！\r\n", {
            title: '提示框',
            icon: 0,
        });
        return false;
    }
    let userAccountLength = userAccountDom.val().length;
    if (userAccountLength < 6 || userAccountLength.length > 16) {
        layer.alert( "账号长度要大于6小于16！\r\n", {
            title: '提示框',
            icon: 0,
        });
        return false;
    }
    return true;
}

/**
 * 校验密码
 * @returns {boolean}
 */
function validatePassword() {
    let userPasswordDom = $("#userpwd");
    if (userPasswordDom.val() === "") {
        layer.alert( "密码不能为空！\r\n", {
            title: '提示框',
            icon: 0,
        });
        return false;
    }
    let userPasswordLength = userPasswordDom.val().length;
    if (userPasswordLength < 2 || userPasswordLength.length > 12) {
        layer.alert( "密码长度要大于1小于12！\r\n", {
            title: '提示框',
            icon: 0,
        });
        return false;
    }
    return true;
}

$(document).ready(function () {
    let user = $("#username,#userpwd");
    user.blur(function () {
        let $el = user;
        let $parent = $el.parent();
        $parent.attr('class', 'frame_style').removeClass(' form_error');
        if ($el.val() === '') {
            $parent.attr('class', 'frame_style').addClass(' form_error');
        }
    });
    user.focus(function () {
        let $el = user;
        let $parent = $el.parent();
        $parent.attr('class', 'frame_style').removeClass(' form_errors');
        if ($el.val() === '') {
            $parent.attr('class', 'frame_style').addClass(' form_errors');
        } else {
            $parent.attr('class', 'frame_style').removeClass(' form_errors');
        }
    });
})
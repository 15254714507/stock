<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link href="assets/css/codemirror.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="font/css/font-awesome.min.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript"></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>

    <title></title>
</head>

<body>
<div class="clearfix">
    <div class="admin_info_style">
        <div class="admin_modify_style" id="Personal">
            <div class="type_title">管理员信息</div>
            <div class="xinxi">
                <div class="form-group"><label class="col-sm-3 control-label no-padding-right"
                                               for="form-field-1">账号： </label>
                    <div class="col-sm-9">
                        <input type="text" name="userAccount" class="col-xs-7" style="border: 0px;"
                               disabled="disabled" value="${(user.account)!}"/>
                        &nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="change_Password()"
                                             class="btn btn-warning btn-xs">修改密码</a>
                    </div>

                </div>
                <div class="form-group"><label class="col-sm-3 control-label no-padding-right"
                                               for="form-field-1">姓名： </label>
                    <div class="col-sm-9"><input type="text" name="userName" id="website-title"
                                                 value="${(user.name)!}"
                                                 class="col-xs-7 text_info" disabled="disabled">
                    </div>
                </div>
                <div class="form-group"><label class="col-sm-3 control-label no-padding-right"
                                               for="form-field-1">性别： </label>
                    <div class="col-sm-9">
                            <span>
                                <#if (user.sex == 0)>
                                    女
                                <#else>
                                    男
                                </#if>
                            </span>
                    </div>
                </div>
                <div class="form-group"><label class="col-sm-3 control-label no-padding-right"
                                               for="form-field-1">年龄： </label>
                    <div class="col-sm-9"><span>${(user.age)!}</span></div>
                </div>
                <div class="form-group"><label class="col-sm-3 control-label no-padding-right"
                                               for="form-field-1">移动电话： </label>
                    <div class="col-sm-9"><input type="text" name="移动电话" id="website-title" value="${(user.phone)!}"
                                                 class="col-xs-7 text_info" disabled="disabled"></div>
                </div>
                <div class="form-group"><label class="col-sm-3 control-label no-padding-right"
                                               for="form-field-1">电子邮箱： </label>
                    <div class="col-sm-9"><input type="text" name="电子邮箱" id="website-title" value="${(user.email)!}"
                                                 class="col-xs-7 text_info" disabled="disabled"></div>
                </div>
                <div class="form-group"><label class="col-sm-3 control-label no-padding-right"
                                               for="form-field-1">权限： </label>
                    <div class="col-sm-9"><span>
                            <#if user.superAdmin>
                                超级管理员
                            <#else>
                                普通管理员
                            </#if>
                        </span></div>
                </div>
                <div class="form-group"><label class="col-sm-3 control-label no-padding-right"
                                               for="form-field-1">注册时间： </label>
                    <div class="col-sm-9"><span>${(user.createTime)?string("yyyy-MM-dd") !}</span></div>
                </div>
                <div class="Button_operation clearfix">
                    <button onclick="modify();" class="btn btn-danger radius" type="submit">修改信息</button>
                    <button onclick="save_info();" class="btn btn-success radius" type="button">保存修改</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--修改密码的弹出框-->
<div class="change_Pass_style" id="change_Pass">
    <form action="" id="changePasswordForm">
        <input name="userAccount" value="${(user.account)!}" style="display:none"/>
        <ul class="xg_style">
            <li><label class="label_name">原&nbsp;&nbsp;密&nbsp;码</label><input name="oldPassword" type="password"
                                                                              id="password"></li>
            <li><label class="label_name">新&nbsp;&nbsp;密&nbsp;码</label><input name="newPassword" type="password"
                                                                              id="Nes_pas"></li>
            <li><label class="label_name">确认密码</label><input name="" type="password"
                                                             id="c_mew_pas"></li>
        </ul>
    </form>
</div>
<script type="text/javascript" src="script/main/adminInfo.js"></script>
</body>
</html>
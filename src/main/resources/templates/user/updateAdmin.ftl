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
    <script src="assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="Widget/Validform/5.3.2/Validform.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript"></script>
    <script src="js/lrtk.js" type="text/javascript"></script>
    <script src="assets/layer/layer.js" type="text/javascript"></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
    <title></title>
</head>

<body>
<div id="add_administrator_style" class="add_menber">
    <form action="" method="post" id="form-admin-update">
        <input name="id" value="${(user.id)!} " style="display:none"/>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>账号：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(user.account)!}" readonly  id="userAccount"
                       name="userAccount"
                >
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>姓名：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(user.name)!}" id="name" name="name"
                       datatype="*2-16" nullmsg="姓名">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>年龄：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(user.age)!}" id="age" name="age"
                       datatype="*1-3" nullmsg="年龄">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label "><span class="c-red">*</span>性别：</label>
            <div class="formControls  skin-minimal">
                <#if user.sex ==0>
                    <label><input id="sex" name="sex" type="radio" class="ace" value="1"><span
                                class="lbl">男</span></label>&nbsp;&nbsp;
                    <label><input id="sex" name="sex" type="radio" class="ace" checked="checked" value="0"><span
                                class="lbl">女</span></label>
                <#else>
                    <label><input id="sex" name="sex" type="radio" class="ace" checked="checked" value="1"><span
                                class="lbl">男</span></label>&nbsp;&nbsp;
                    <label><input id="sex" name="sex" type="radio" class="ace" value="0"><span
                                class="lbl">女</span></label>
                </#if>
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label "><span class="c-red">*</span>手机：</label>
            <div class="formControls ">
                <input type="text" class="input-text" value="${(user.phone)}" id="phone" name="phone"
                       datatype="m" nullmsg="手机号不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls ">
                <input type="text" class="input-text" value="${(user.email)!}" name="email" id="email" datatype="e"
                       nullmsg="请输入邮箱！">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label">角色：</label>
            <div class="formControls "> <span class="select-box" style="width:150px;">
				<select class="select" name="superAdmin" size="1">
                    <#if user.superAdmin>
                        <option value="1">超级管理员</option>
                        <option value="0">普通管理员</option>
                    <#else>
                        <option value="0">普通管理员</option>
                        <option value="1">超级管理员</option>
                    </#if>
				</select>
				</span></div>
        </div>
        <div align="center">
            <input class="btn btn-primary radius" type="button" id="Add_Administrator" onclick="updateClick();"
                   value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
        </div>
    </form>
</div>
<script type="text/javascript" src="script/user/updateAdmin.js"></script>
</body>
</html>
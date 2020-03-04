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
    <form action="" method="post" id="form-provider-update">
        <input name="id" value="${(provider.id)!}" style="display: none"/>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>编码：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(provider.code)!}" id="code" name="code" readonly>
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>公司名称：</label>
            <div class="formControls">
                <input type="text" name="company" autocomplete="off" value="${(provider.company)!}"
                       class="input-text" datatype="*4-20" nullmsg="公司名称不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>负责人：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(provider.name)!}" id="name" name="name"
                       datatype="*2-16" nullmsg="负责人不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>联系方式：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(provider.phone)!}" id="phone" name="phone"
                       datatype="n" nullmsg="联系方式不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(provider.email)!}" id="email" name="email"
                       datatype="e" nullmsg="邮箱不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>所在城市：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(provider.city)!}" id="city" name="city"
                       datatype="*2-8" nullmsg="所在城市不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>地址：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(provider.address)!}" id="address" name="address"
                       datatype="*1-16" nullmsg="地址不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div align="center">
            <input class="btn btn-primary radius" type="button" onclick="updateClick();"
                   value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
        </div>
    </form>
</div>
<script type="text/javascript" src="script/provider/updateProvider.js"></script>
</body>
</html>
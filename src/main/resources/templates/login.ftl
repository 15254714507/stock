<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!--禁止缓存，禁止刷新重新提交表单-->
     <META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
     <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
     <META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">

    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
    <script src="assets/js/ace-extra.min.js"></script>
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript"></script>
    <title>登陆页面</title>
</head>

<body class="login-layout Reg_log_style">
<div class="logintop">
    <ul>
        <li><a href="#">返回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>
<div class="loginbody">
    <div class="login-container">
        <div class="center">
            <h4 class="header white lighter bigger">
                药品库存管理系统

            </h4>
        </div>

        <div class="space-6"></div>

        <div class="position-relative">
            <div id="login-box" class="login-box widget-box no-border visible">
                <div class="widget-body">
                    <div class="widget-main">
                        <h4 class="header blue lighter bigger">
                            <i class="icon-coffee green"></i>
                            用户登录页面
                        </h4>

                        <div class="login_icon"><img src="images/login.png"/></div>

                        <form id="form" action="/Login.do" method="post">
                            <fieldset>
                                <ul>
                                    <li class="frame_style form_error"><label class="user_icon"></label><input
                                                name="userAccount" type="text" id="username" value="${(account) !}" placeholder="用户名"/></li>

                                    <li class="frame_style form_error"><label class="password_icon"></label><input
                                                name="password" type="password" id="userpwd" value="${(password) !}" placeholder="密码"/></li>

                                </ul>
                                <div>
                                    <span style="color: red">${(accountError) !}</span>
                                    <span style="color: red">${(passwordError) !}</span>
                                    <span style="color: red">${(systemError) !}</span>
                                </div>
                                <div class="space"></div>
                                <div class="clearfix">
<#--                                    <label class="inline">-->
<#--                                        <input type="checkbox" class="ace">-->
<#--                                        <span class="lbl">保存密码</span>-->
<#--                                    </label>-->

                                    <button type="button" class="width-35 pull-right btn btn-sm btn-primary"
                                            id="login_btn">
                                        <i class="icon-key"></i>
                                        登陆
                                    </button>
                                </div>

                                <div class="space-4"></div>
                            </fieldset>
                        </form>

                        <div class="social-or-login center">
                            <span class="bigger-110">通知</span>
                        </div>

                        <div class="social-login center">
                            本网站系统不再对IE8以下浏览器支持，请见谅。
                        </div>
                    </div><!-- /widget-main -->

                    <div class="toolbar clearfix">


                    </div>
                </div><!-- /widget-body -->
            </div><!-- /login-box -->
        </div><!-- /position-relative -->
    </div>
</div>
<div class="loginbm">版权所有 <a href="">郑文菊</a></div>
<strong></strong>
<script type="text/javascript" src="script/login.js"></script>
</body>
</html>

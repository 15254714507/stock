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
<div class="page-content clearfix">
    <div class="administrator">
        <div class="d_Confirm_Order_style">
            <div class="search_style">

                <ul class="search_content clearfix">
                    <li><label class="l_f">管理员名称</label><input name="" type="text" class="text_add" placeholder=""
                                                               style=" width:400px"/></li>
                    <li><label class="l_f">添加时间</label><input class="inline laydate-icon" id="start"
                                                              style=" margin-left:10px;"></li>
                    <li style="width:90px;">
                        <button type="button" class="btn_search"><i class="fa fa-search"></i>查询</button>
                    </li>
                </ul>
            </div>
            <!--操作-->
            <div class="border clearfix">
       <span class="l_f">
        <a href="javascript:void(0)" id="administrator_add" class="btn btn-warning"><i class="fa fa-plus"></i> 添加管理员</a>
        <a href="javascript:void(0)" class="btn btn-danger"><i class="fa fa-trash"></i> 批量删除</a>
       </span>
                <span class="r_f">共：<b>${(adminNum+superAdminNum)!}</b>人</span>
            </div>
            <!--管理员列表-->
            <div class="clearfix administrator_style" id="administrator">
                <div class="left_style">
                    <div id="scrollsidebar" class="left_Treeview">
                        <div class="show_btn" id="rightArrow"><span></span></div>
                        <div class="widget-box side_content">
                            <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
                            <div class="side_list">
                                <div class="widget-header header-color-green2"><h4 class="lighter smaller">管理员分类列表</h4>
                                </div>
                                <div class="widget-body">
                                    <ul class="b_P_Sort_list">
                                        <li><i class="fa fa-users green"></i> <a href="#">全部管理员（${(adminNum+superAdminNum)!}）</a></li>
                                        <li><i class="fa fa-users orange"></i> <a href="#">超级管理员（${(superAdminNum)!}
                                                ）</a></li>
                                        <li><i class="fa fa-users orange"></i> <a href="#">普通管理员（${(adminNum)!}）</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="table_menu_list" id="testIframe">
                </div>
            </div>
        </div>
    </div>
    <!--添加管理员-->
    <div id="add_administrator_style" class="add_menber" style="display:none">
        <form action="" method="post" id="form-admin-add">
            <div class="form-group">
                <label class="form-label"><span class="c-red">*</span>管理员：</label>
                <div class="formControls">
                    <input type="text" class="input-text" value="" placeholder="" id="user-name" name="user-name"
                           datatype="*2-16" nullmsg="用户名不能为空">
                </div>
                <div class="col-4"><span class="Validform_checktip"></span></div>
            </div>
            <div class="form-group">
                <label class="form-label"><span class="c-red">*</span>初始密码：</label>
                <div class="formControls">
                    <input type="password" placeholder="密码" name="userpassword" autocomplete="off" value=""
                           class="input-text" datatype="*6-20" nullmsg="密码不能为空">
                </div>
                <div class="col-4"><span class="Validform_checktip"></span></div>
            </div>
            <div class="form-group">
                <label class="form-label "><span class="c-red">*</span>确认密码：</label>
                <div class="formControls ">
                    <input type="password" placeholder="确认新密码" autocomplete="off" class="input-text Validform_error"
                           errormsg="您两次输入的新密码不一致！" datatype="*" nullmsg="请再输入一次新密码！" recheck="userpassword"
                           id="newpassword2" name="newpassword2">
                </div>
                <div class="col-4"><span class="Validform_checktip"></span></div>
            </div>
            <div class="form-group">
                <label class="form-label "><span class="c-red">*</span>性别：</label>
                <div class="formControls  skin-minimal">
                    <label><input name="form-field-radio" type="radio" class="ace" checked="checked"><span class="lbl">保密</span></label>&nbsp;&nbsp;
                    <label><input name="form-field-radio" type="radio" class="ace"><span class="lbl">男</span></label>&nbsp;&nbsp;
                    <label><input name="form-field-radio" type="radio" class="ace"><span class="lbl">女</span></label>
                </div>
                <div class="col-4"><span class="Validform_checktip"></span></div>
            </div>
            <div class="form-group">
                <label class="form-label "><span class="c-red">*</span>手机：</label>
                <div class="formControls ">
                    <input type="text" class="input-text" value="" placeholder="" id="user-tel" name="user-tel"
                           datatype="m" nullmsg="手机不能为空">
                </div>
                <div class="col-4"><span class="Validform_checktip"></span></div>
            </div>
            <div class="form-group">
                <label class="form-label"><span class="c-red">*</span>邮箱：</label>
                <div class="formControls ">
                    <input type="text" class="input-text" placeholder="@" name="email" id="email" datatype="e"
                           nullmsg="请输入邮箱！">
                </div>
                <div class="col-4"><span class="Validform_checktip"></span></div>
            </div>
            <div class="form-group">
                <label class="form-label">角色：</label>
                <div class="formControls "> <span class="select-box" style="width:150px;">
				<select class="select" name="admin-role" size="1">
					<option value="0">超级管理员</option>
					<option value="1">管理员</option>
					<option value="2">栏目主辑</option>
					<option value="3">栏目编辑</option>
				</select>
				</span></div>
            </div>
            <div class="form-group">
                <label class="form-label">备注：</label>
                <div class="formControls">
                    <textarea name="" cols="" rows="" class="textarea" placeholder="说点什么...100个字符以内" dragonfly="true"
                              onkeyup="checkLength(this);"></textarea>
                    <span class="wordage">剩余字数：<span id="sy" style="color:Red;">100</span>字</span>
                </div>
                <div class="col-4"></div>
            </div>
            <div>
                <input class="btn btn-primary radius" type="submit" id="Add_Administrator"
                       value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="script/user/admin.js"></script>
</body>
</html>


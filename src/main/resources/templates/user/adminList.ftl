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
                    <!--这里是load的table.ftl里的表格-->
                </div>
            </div>
        </div>
    </div>
    <!--添加管理员-->

</div>
<script type="text/javascript" src="script/user/admin.js"></script>
</body>
</html>


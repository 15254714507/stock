<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/style.css"/>
    <link href="assets/css/codemirror.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="font/css/font-awesome.min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
    <![endif]-->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="assets/layer/layer.js" type="text/javascript" ></script>
    <script src="assets/laydate/laydate.js" type="text/javascript"></script>
</head>

<body>
<div class="margin clearfix">
    <div id="system_style">
        <div class="search_style" style="margin-bottom:0;margin-top: 0">
            <ul class="search_content clearfix">
                <li><label class="l_f">编码:</label><input id="code" type="text" class="text_add" style="width:250px"></li>
                <li><label class="l_f">名称:</label><input id="name" type="text" class="inline" style="width:250px"></li>
                <li style="width:90px;"><button type="button" id="search_button" class="btn_search"><i class="fa fa-search"></i>查询</button></li>
                <!--无用的，为了样式规整-->
                <li> <input id ="start" type="hidden"/></li>
                <li>
                    <a href="javascript:void(0)" id="drug_add" class="btn btn-warning"><i class="fa fa-plus"></i>
                        添加药品</a>
                </li>
            </ul>
        </div>
        <!--药品表格-->
        <div id="drugTableIframe" style="height: 500px;margin-top: 0" class="Columns_style margin">
        </div>
    </div>
</div>
<script type="text/javascript" src="script/drug/drugList.js"></script>
</body>
</html>
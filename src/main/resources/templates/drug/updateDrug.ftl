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
    <form action="" method="post" id="form-drug-update">
        <input value="${(drug.id)!}" name="id" style="display: none"/>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>编码：</label>
            <div class="formControls">
                <input type="text" name="code" value="${(drug.code)!}" readonly/>
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>名称：</label>
            <div class="formControls">
                <input type="text" name="name" autocomplete="off" value="${drug.name}"
                       class="input-text" datatype="*4-20" nullmsg="名称不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>规格：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(drug.specs)!}" placeholder="" id="specs" name="specs"
                       datatype="*2-16" nullmsg="规格不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>剂型：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(drug.dosageForm)!}" id="dosageForm" name="dosageForm"
                       datatype="*1-5" nullmsg="剂型不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>批准文号：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(drug.approvalNumber)!}"  id="approvalNumber" name="approvalNumber"
                       datatype="n" nullmsg="批准文号不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>库存：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(drug.number)!}"  id="number" name="number"
                       datatype="n" nullmsg="库存不能为空，可以为0">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>价格：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(drug.price)!}" id="price" name="price"
                       datatype="*1-10" nullmsg="价格不能为空，可以为0" placeholder="保留两位小数" >
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>贮藏：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(drug.storage)!}"  id="storage" name="storage"
                       datatype="*1-5" nullmsg="贮藏不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>包装：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(drug.packaging)!}"  id="packaging" name="packaging"
                       datatype="*1-5" nullmsg="包装不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>库房号：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(drug.wareHouse)}" id="wareHouse" name="wareHouse"
                       datatype="n" nullmsg="库房号不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div align="center">
            <input class="btn btn-primary radius" type="button" id="Add_Administrator" onclick="updateClick();"
                   value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
        </div>
    </form>
</div>
<script type="text/javascript" src="script/drug/updateDrug.js"></script>
</body>
</html>
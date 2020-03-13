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
    <form action="" method="post" id="form-purchaseOrderDrug-update">
        <input style="display: none" name="id" value="${(purchaseOrderDrug.getId())!}"/>
        <input style="display: none" name="code" value="${(purchaseOrderDrug.getCode())!}"/>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>药品编码：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(purchaseOrderDrug.getDrugCode())!}" id="drug_code"
                       readonly name="drugCode">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>药品名称：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(purchaseOrderDrug.getDrugName())!}" id="drug_name"
                       readonly name="drugName">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>单价：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(purchaseOrderDrug.price)?string("0.##")!}" id="price"
                       name="price"
                       datatype="*1-10" nullmsg="单价不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>入库数量：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(purchaseOrderDrug.number)}" id="number"
                       name="number"
                       datatype="n" nullmsg="数量不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>生产批号：</label>
            <div class="formControls">
                <input type="text" class="input-text" value="${(purchaseOrderDrug.productionLotNumber)}"
                       id="production_lot_number"
                       name="productionLotNumber"
                       datatype="n" nullmsg="生产批号不能为空">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>截止日期：</label>
            <div class="formControls">
                <input class="inline laydate-icon" value="${(purchaseOrderDrug.expireDate)?string("yyyy-MM-dd")!}"
                       id="start" name="expireDate">
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>
        <div class="form-group">
            <label class="form-label"><span class="c-red">*</span>供应商：</label>
            <div class="formControls">
                <select name="providerId">
                    <option value="${(purchaseOrderDrug.providerId)}">${(purchaseOrderDrug.providerName)!}</option>
                    <#list providerList as provider>
                        <#if provider.getId()!= purchaseOrderDrug.getId()>
                            <option value="${(provider.getId())!}">${(provider.getCompany())!}</option>
                        </#if>
                    </#list>
                </select>
            </div>
            <div class="col-4"><span class="Validform_checktip"></span></div>
        </div>

        <div align="center">
            <input class="btn btn-primary radius" type="button" onclick="updateClick();"
                   value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
        </div>
    </form>
</div>
<script type="text/javascript" src="script/purchaseOrderDrug/updatePurchaseOrderDrug.js"></script>
</body>
</html>
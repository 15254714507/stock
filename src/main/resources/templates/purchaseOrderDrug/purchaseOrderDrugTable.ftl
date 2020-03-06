<table class="table table-striped table-bordered table-hover" id="sample-table">
    <thead>
    <tr>
        <th width="50px">序号</th>
        <th width="70px">药品编码</th>
        <th width="70px">药品名称</th>
        <th width="70px">生产批号</th>
        <th width="100px">供应商名称</th>
        <th width="50px">单价</th>
        <th width="50px">进货量</th>
        <th width="70px">有效期</th>
        <th width="100px">操作</th>
    </tr>
    </thead>
    <tbody>
    <#assign num = page.total>
    <#list page.list as purchaseOrderDrug>
        <tr>
            <td>${(num)!}</td>
            <#assign num = num-1>
            <td>${(purchaseOrderDrug.drugCode)!}</td>
            <td>${(purchaseOrderDrug.drugName)!}</td>
            <td>${(purchaseOrderDrug.productionLotNumber)!}</td>
            <td>${(purchaseOrderDrug.providerName)!}</td>
            <td>${(purchaseOrderDrug.price)?string("0.##")}</td>
            <td>${(purchaseOrderDrug.number)!}</td>
            <td>${(purchaseOrderDrug.expireDate)!}</td>
            <td class="td-manage">
                <a title="编辑" onclick="member_edit(this,'${purchaseOrderDrug.getId()}')"
                   href="javascript:;" class="btn btn-xs btn-info"><i class="fa fa-edit bigger-120"></i></a>
                <a title="删除" href="javascript:;" onclick="member_del(this,'${purchaseOrderDrug.getId()}')"
                   class="btn btn-xs btn-warning"><i class="fa fa-trash  bigger-120"></i></a>
            </td>

        </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/purchaseOrderDrug/purchaseOrderDrugTable.js"></script>
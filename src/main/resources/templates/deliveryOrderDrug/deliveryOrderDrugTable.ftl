<table class="table table-striped table-bordered table-hover" id="sample-table">
    <thead>
    <tr>
        <th width="50px">序号</th>
        <th width="70px">药品编码</th>
        <th width="70px">药品名称</th>
        <th width="50px">单价</th>
        <th width="50px">进货量</th>
        <th width="50px">金额</th>
        <th width="100px">操作</th>
    </tr>
    </thead>
    <tbody>
    <#assign num = page.total>
    <#list page.list as deliveryOrderDrug>
        <tr>
            <td>${(num)!}</td>
            <#assign num = num-1>
            <td>${(deliveryOrderDrug.drugCode)!}</td>
            <td>${(deliveryOrderDrug.drugName)!}</td>
            <td>${(deliveryOrderDrug.price)?string("0.##")}</td>
            <td>${(deliveryOrderDrug.number)!}</td>
            <td>${(deliveryOrderDrug.number) *(deliveryOrderDrug.price) !}</td>
            <td class="td-manage">
                <a title="编辑" onclick="member_edit(this,'${deliveryOrderDrug.getId()}')"
                   href="javascript:;" class="btn btn-xs btn-info"><i class="fa fa-edit bigger-120"></i></a>
                <a title="删除" href="javascript:;" onclick="member_del(this,'${deliveryOrderDrug.getId()}')"
                   class="btn btn-xs btn-warning"><i class="fa fa-trash  bigger-120"></i></a>
            </td>

        </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/deliveryOrderDrug/deliveryOrderDrugTable.js"></script>
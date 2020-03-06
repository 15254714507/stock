<table class="table table-striped table-bordered table-hover" id="sample-table">
    <thead>
    <tr>
        <th width="50px">序号</th>
        <th width="130px">编码</th>
        <th width="100px">负责人账号</th>
        <th width="70px">负责人姓名</th>
        <th width="150px">说明</th>
        <th width="100px">日期</th>
        <th width="50px">状态</th>
        <th width="150px">操作</th>
    </tr>
    </thead>
    <tbody>
    <#assign num = purchaseOrderPage.total>
    <#list purchaseOrderPage.list as purchaseOrder>
    <tr>
        <td>${(num)!}</td>
        <#assign num = num-1>
        <td>${(purchaseOrder.code)!}</td>
        <td>${(purchaseOrder.userAccount)!}</td>
        <td>${(purchaseOrder.userName)!}</td>
        <td>${(purchaseOrder.description)!}</td>
        <td>${(purchaseOrder.createTime)?string("yyyy-MM-dd HH:mm")}</td>
        <td>
            <#if purchaseOrder.status>
                <span class="label label-success radius">已发布</span>
            <#else>
                <span class="label label-danger radius">未发布</span>
            </#if>
        </td>
        <td class="td-manage">
            <#if purchaseOrder.status == false>
                <a onClick="member_publish(this,'${purchaseOrder.getId()}')" href="#" title="发布"
                   class="btn btn-xs btn-success"><i
                            class="fa fa-check  bigger-120"></i></a>
            </#if>
            <a title="编辑" onclick="member_edit(this,'${purchaseOrder.getId()}')"
               href="#" class="btn btn-xs btn-info"><i class="fa fa-edit bigger-120"></i></a>
            <#if purchaseOrder.status == false>
                <a title="删除" href="#" onclick="member_del(this,'${purchaseOrder.getId()}')"
                   class="btn btn-xs btn-warning"><i class="fa fa-trash  bigger-120"></i></a>
            </#if>

            <a href="javascript:void(0)" name="" class="btn btn-xs btn-pink ads_link"
               onclick="gotoPurchaseOrderDrugList('${(purchaseOrder.code)!}')" title="入库单药品信息"><i class="fa  fa-bars  bigger-120"></i></a>
        </td>

    </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/purchaseOrder/purchaseOrderTable.js"></script>

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
    <#assign num = deliveryOrderPage.total>
    <#list deliveryOrderPage.list as deliveryOrder>
        <tr>
            <td>${(num)!}</td>
            <#assign num = num-1>
            <td>${(deliveryOrder.code)!}</td>
            <td>${(deliveryOrder.userAccount)!}</td>
            <td>${(deliveryOrder.userName)!}</td>
            <td>${(deliveryOrder.description)!}</td>
            <td>${(deliveryOrder.createTime)?string("yyyy-MM-dd HH:mm")}</td>
            <td>
                <#if deliveryOrder.status>
                    <span class="label label-success radius">已发布</span>
                <#else>
                    <span class="label label-danger radius">未发布</span>
                </#if>
            </td>
            <td class="td-manage">
                <#if deliveryOrder.status == false>
                    <a onClick="member_publish(this,'${deliveryOrder.getId()}')" href="#" title="发布"
                       class="btn btn-xs btn-success"><i
                                class="fa fa-check  bigger-120"></i></a>
                </#if>
                <#if deliveryOrder.status>
                    <a  href="/downDeliveryOrder.do?id=${deliveryOrder.getId()}" title="导出"
                       class="btn btn-xs btn-primary"><i
                                class="fa fa-download  bigger-120"></i></a>
                </#if>
                <a title="编辑" onclick="member_edit(this,'${deliveryOrder.getId()}')"
                   href="#" class="btn btn-xs btn-info"><i class="fa fa-edit bigger-120"></i></a>
                <#if deliveryOrder.status == false>
                    <a title="删除" href="#" onclick="member_del(this,'${deliveryOrder.getId()}')"
                       class="btn btn-xs btn-warning"><i class="fa fa-trash  bigger-120"></i></a>
                </#if>

                <a href="javascript:void(0)" name="" class="btn btn-xs btn-pink ads_link"
                   onclick="gotoDeliveryOrderDrugList('${(deliveryOrder.code)!}')" title="出库单药品信息"><i
                            class="fa  fa-bars  bigger-120"></i></a>
            </td>

        </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/deliveryOrder/deliveryOrderTable.js"></script>

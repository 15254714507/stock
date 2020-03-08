<table class="table table-striped table-bordered table-hover" id="sample-table">
    <thead>
    <tr>
        <th width="50px">序号</th>
        <th width="100px">药品编码</th>
        <th width="100px">药品名称</th>
        <th width="70px">药品规格</th>
        <th width="70px">数量</th>
        <th width="100px">处理方式</th>
        <th width="100px">过期时间</th>
        <th width="70px">状态</th>
        <th width="100px">操作</th>
    </tr>
    </thead>
    <tbody>
    <#assign num = overdueDrugPage.total>
    <#list overdueDrugPage.list as overdueDrug>
        <tr>
            <td>${(num)!}</td>
            <#assign num = num-1>
            <td>${(overdueDrug.drugCode)!}</td>
            <td>${(overdueDrug.drugName)!}</td>
            <td>${(overdueDrug.drugSpecs)!}</td>
            <td>${(overdueDrug.number)!}</td>
            <td>${(overdueDrug.processMode)!}</td>
            <td>${(overdueDrug.expireDate)?string("yyyy-MM-dd HH:mm")}</td>
            <td>
                <#if overdueDrug.status>
                    <span class="label label-success radius">已处理</span>
                <#else>
                    <span class="label label-danger radius">未处理</span>
                </#if>
            </td>
            <td class="td-manage">
                <#if overdueDrug.status==false>
                    <a onClick="member_process(this,'${overdueDrug.getId()}')" href="#" title="处理"
                       class="btn btn-xs btn-success"><i
                                class="fa fa-check  bigger-120"></i></a>
                </#if>


            </td>

        </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/overdueDrug/overdueDrugTable.js"></script>

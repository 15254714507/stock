<table class="table table-striped table-bordered table-hover" id="sample-table">
    <thead>
    <tr>
        <th width="50px" >序号</th>
        <th width="100px">编码</th>
        <th width="100px">名称</th>
        <th width="100px">规格</th>
        <th width="50px">剂型</th>
        <th width="100px">批准文号</th>
        <th width="50px">库存</th>
        <th width="50px">贮藏</th>
        <th width="50px">包装</th>
        <th width="50px">库房号</th>
        <th width="100px">操作</th>
    </tr>
    </thead>
    <tbody>
    <#assign num = drugPage.startRow>
    <#list drugPage.list as drug>
        <tr>
            <td>${(num)!}</td>
            <#assign num = num+1>
            <td>${(drug.code)!}</td>
            <td>${(drug.name)!}</td>
            <td>${(drug.specs)!}</td>
            <td>${(drug.dosageForm)!}</td>
            <td>${(drug.approvalNumber)!}</td>
            <td>${(drug.number)!}</td>
            <td>${(drug.storage)!}</td>
            <td>${(drug.packaging)!}</td>
            <td>${(drug.wareHouse)!}</td>
            <td class="td-manage">
                <a title="编辑" onclick="member_edit(this,'${drug.getId()}')"
                   href="javascript:;" class="btn btn-xs btn-info"><i class="fa fa-edit bigger-120"></i></a>
                <a title="删除" href="javascript:;" onclick="member_del(this,'${drug.getId()}')"
                   class="btn btn-xs btn-warning"><i class="fa fa-trash  bigger-120"></i></a>
            </td>

        </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/drug/drugTable.js"></script>
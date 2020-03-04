<table class="table table-striped table-bordered table-hover" id="sample-table">
    <thead>
    <tr>
        <th width="50px">序号</th>
        <th width="70px">编码</th>
        <th width="150px">公司名称</th>
        <th width="100px">负责人</th>
        <th width="70px">联系电话</th>
        <th width="100px">邮箱</th>
        <th width="70px">所在城市</th>
        <th width="150px">地址</th>
        <th width="100px">操作</th>
    </tr>
    </thead>
    <tbody>
    <#assign num = providePage.startRow>
    <#list providePage.list as provider>
        <tr>
            <td>${(num)!}</td>
            <#assign num = num+1>
            <td>${(provider.code)!}</td>
            <td>${(provider.company)!}</td>
            <td>${(provider.name)!}</td>
            <td>${(provider.phone)!}</td>
            <td>${(provider.email)!}</td>
            <td>${(provider.city)!}</td>
            <td>${(provider.address)!}</td>
            <td class="td-manage">
                <a title="编辑" onclick="member_edit(this,'${provider.getId()}')"
                   href="javascript:;" class="btn btn-xs btn-info"><i class="fa fa-edit bigger-120"></i></a>
                <a title="删除" href="javascript:;" onclick="member_del(this,'${provider.getId()}')"
                   class="btn btn-xs btn-warning"><i class="fa fa-trash  bigger-120"></i></a>
            </td>

        </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/provider/providerTable.js"></script>
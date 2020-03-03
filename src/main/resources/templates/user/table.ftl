<table class="table table-striped table-bordered table-hover" id="sample_table">
    <thead>
    <tr>
        <th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label>
        </th>
        <th width="80px">序号</th>
        <th width="100px">账号</th>
        <th width="100px">姓名</th>
        <th width="150px">手机</th>
        <th width="150px">邮箱</th>
        <th width="150px">角色</th>
        <th width="150px">添加时间</th>
        <th width="150px">操作</th>
    </tr>
    </thead>
    <tbody>
    <#assign num = page.startRow>
    <#list page.list as user>
        <tr>
            <td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
            <td>${(num)!}</td>
            <#assign num = num+1>
            <td>${(user.account)!}</td>
            <td>${(user.name)!}</td>
            <td>${(user.phone)!}</td>
            <td>${(user.email)!}</td>
            <td>
                <#if user.superAdmin>
                    超级管理员
                <#else>
                    普通管理员
                </#if>
            </td>
            <td>${(user.createTime)?string("yyyy-MM-dd HH:mm")!}</td>
            <td class="td-manage">
                <a title="编辑" onclick="member_edit(this,'${user.getId()}')"
                href="javascript:;" class="btn btn-xs btn-info"><i class="fa fa-edit bigger-120"></i></a>
                <a title="删除" href="javascript:;" onclick="member_del(this,'${user.getId()}')"
                   class="btn btn-xs btn-warning"><i class="fa fa-trash  bigger-120"></i></a>
            </td>

        </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/user/table.js"></script>
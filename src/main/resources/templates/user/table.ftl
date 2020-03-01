<table class="table table-striped table-bordered table-hover" id="sample_table">
    <thead>
    <tr>
        <th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label>
        </th>
        <th width="80px">序号</th>
        <th width="250px">账号</th>
        <th width="250px">姓名</th>
        <th width="100px">手机</th>
        <th width="100px">邮箱</th>
        <th width="100px">角色</th>
        <th width="180px">加入时间</th>
        <th width="200px">操作</th>
    </tr>
    </thead>
    <tbody>
    <#list page as user>
        <tr>
            <td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
            <td>0</td>
            <td>${(user.account)!}</td>
            <td>${(user.name)!}</td>
            <td>${(user.phone)!}</td>
            <td>${(user.email)!}</td>
            <td>${(user.phone)!}</td>
            <td>
                <#if user.superAdmin>
                    超级管理员
                <#else>
                    普通管理员
                </#if>
            </td>
            <td>${(user.createTime)?string("yyyy-MM-dd")!}</td>
            <td class="td-manage">
                <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')"
                   href="javascript:;" class="btn btn-xs btn-info"><i class="fa fa-edit bigger-120"></i></a>
                <a title="删除" href="javascript:;" onclick="member_del(this,'1')"
                   class="btn btn-xs btn-warning"><i class="fa fa-trash  bigger-120"></i></a>
            </td>

        </tr>
    </#list>
    </tbody>
</table>
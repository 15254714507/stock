<table class="table table-striped table-bordered table-hover" id="sample-table">
    <thead>
    <tr>
        <th width="50px">序号</th>
        <th width="100px">药品编码</th>
        <th width="100px">药品名称</th>
        <th width="70px">贮藏</th>
        <th width="70px">库房号</th>
        <th width="100px">滞料风险</th>
    </tr>
    </thead>
    <tbody>
    <#assign num = riskAssessmentPage.total>
    <#list riskAssessmentPage.list as riskAssessment>
        <tr>
            <td>${(num)!}</td>
            <#assign num = num-1>
            <td>${(riskAssessment.drugCode)!}</td>
            <td>${(riskAssessment.drugName)!}</td>
            <td>${(riskAssessment.drugStorage)!}</td>
            <td>${(riskAssessment.drugWarehouseNumber)!}</td>
            <td>
                <#if riskAssessment.delayedMaterialRisk ==0>
                    <span class="label label-default radius">无风险</span>
                </#if>
                <#if riskAssessment.delayedMaterialRisk ==1>
                    <span class="label label-grey radius">Ⅰ级</span>
                </#if>
                <#if riskAssessment.delayedMaterialRisk ==2>
                    <span class="label label-warning radius">Ⅱ级</span>
                </#if>
                <#if riskAssessment.delayedMaterialRisk ==3>
                    <span class="label label-info radius"> Ⅲ级</span>
                </#if>
                <#if riskAssessment.delayedMaterialRisk ==4>
                    <span class="label label-danger radius">已滞料</span>
                </#if>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/riskAssessment/riskAssessmentTable.js"></script>

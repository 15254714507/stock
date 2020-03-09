<table class="table table-striped table-bordered table-hover" id="sample-table">
    <thead>
    <tr>
        <th width="50px">序号</th>
        <th width="100px">药品编码</th>
        <th width="100px">药品名称</th>
        <th width="100px">库存量</th>
        <th width="100px">半年用量</th>
        <th width="100px">平均月用量</th>
        <th width="100px">预估月用量</th>
        <th width="100px">预估使用用</th>
        <th width="100px">请购量</th>
    </tr>
    </thead>
    <tbody>
    <#assign num = drugNumberAnalysisPage.total>
    <#list drugNumberAnalysisPage.list as drugNumberAnalysis>
        <tr>
            <td>${(num)!}</td>
            <#assign num = num-1>
            <td>${(drugNumberAnalysis.drugCode)!}</td>
            <td>${(drugNumberAnalysis.drugName)!}</td>
            <td>${(drugNumberAnalysis.number)!}</td>
            <td>${(drugNumberAnalysis.halfTotal)!}</td>
            <td>${(drugNumberAnalysis.avgDosage)!}</td>
            <td>${(drugNumberAnalysis.estimationDosage)!}</td>
            <td>
                <#if drugNumberAnalysis.estimationMonth gt 6>
                    ${(drugNumberAnalysis.estimationMonth)?string("0.#")!}
                <#elseif drugNumberAnalysis.estimationMonth gt 2>
                    <span class="label label-warning radius">${(drugNumberAnalysis.estimationMonth)?string("0.#")!}</span>
                <#else>
                    <span class="label label-danger radius">${(drugNumberAnalysis.estimationMonth)?string("0.#")!}</span>
                </#if>
            </td>
            <td>${(drugNumberAnalysis.requisitionQuantity)!}</td>
        </tr>
    </#list>
    </tbody>
</table>
<script type="text/javascript" src="script/drugNumberAnalysis/drugNumberAnalysisTable.js"></script>

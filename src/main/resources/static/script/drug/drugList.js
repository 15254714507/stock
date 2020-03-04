$("#drugTableIframe").load("/gotoDrugTable.do");
/*添加管理员*/
$('#drug_add').on('click', function () {
    layer.open({
        type: 2,
        title: '添加药品',
        closeBtn: 1, //不显示关闭按钮
        area: ['550px', '550px'],
        shadeClose: false,
        content: ['/gotoAddDrug.do', 'yes'],
    });
});
$("#search_button").on("click",function () {
    $("#drugTableIframe").load("/gotoDrugTable.do?code="+$("#code").val()+"&name="+$("#name").val());
});


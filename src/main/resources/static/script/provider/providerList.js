$("#providerTableIframe").load("/gotoProviderTable.do");
/*添加管理员*/
$('#provider_add').on('click', function () {
    layer.open({
        type: 2,
        title: '添加供应商',
        closeBtn: 1, //不显示关闭按钮
        area: ['550px', '500px'],
        shadeClose: false,
        content: ['/gotoAddProvider.do', 'yes'],
    });
});
$("#search_button").on("click",function () {
    $("#providerTableIframe").load("/gotoProviderTable.do?code="+$("#code").val()+"&company="+$("#company").val());
});


$("#deliveryOrderDrugTableIframe").load("/gotoDeliveryOrderDrugTable.do?code=" + $("#start").val());
/*添加管理员*/
$('#delivery_order_drug_add').on('click', function () {
    layer.open({
        type: 2,
        title: '添加入库药品',
        closeBtn: 1, //不显示关闭按钮
        area: ['550px', '550px'],
        shadeClose: false,
        content: ['/gotoAddPurchaseOrderDrug.do', 'yes'],
    });
});
$("#search_button").on("click", function () {
    $("#deliveryOrderDrugTableIframe").load("/gotoDeliveryOrderDrugTable.do?code=" + $("#start").val()
        + "drugCode=" + $("#drug_code").val() + "&drugName=" + $("#drug_name").val());
});

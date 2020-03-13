$("#purchaseOrderDrugTableIframe").load("/gotoPurchaseOrderDrugTable.do?code=" + $("#start").val());
/*添加管理员*/
$('#purchase_order_drug_add').on('click', function () {
    layer.open({
        type: 2,
        title: '添加入库药品',
        closeBtn: 1, //不显示关闭按钮
        area: ['550px', '400px'],
        shadeClose: false,
        content: ['/gotoAddPurchaseOrderDrug.do?code=' + $("#start").val(), 'yes'],
    });
});
$("#search_button").on("click", function () {
    $("#purchaseOrderDrugTableIframe").load("/gotoPurchaseOrderDrugTable.do?code=" + $("#start").val()
        + "&drugCode=" + $("#drug_code").val() + "&drugName=" + $("#drug_name").val());
});

$("#purchaseOrderTableIframe").load("/gotoPurchaseOrderTable.do");
/*添加管理员*/
$('#purchase_order_add').on('click', function () {
    layer.open({
        type: 2,
        title: '添加入库单',
        closeBtn: 1, //不显示关闭按钮
        area: ['550px', '200px'],
        shadeClose: false,
        content: ['/gotoAddPurchaseOrder.do', 'yes'],
    });
});
$("#search_button").on("click",function () {
    $("#purchaseOrderTableIframe").load("/gotoPurchaseOrderTable.do?code="+$("#code").val());
});


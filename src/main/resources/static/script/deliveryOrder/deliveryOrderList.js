$("#deliveryOrderTableIframe").load("/gotoDeliveryOrderTable.do");
/*添加管理员*/
$('#delivery_order_add').on('click', function () {
    layer.open({
        type: 2,
        title: '添加出库单',
        closeBtn: 1, //不显示关闭按钮
        area: ['550px', '200px'],
        shadeClose: false,
        content: ['/gotoAddDeliveryOrder.do', 'yes'],
    });
});
$("#search_button").on("click",function () {
    $("#deliveryOrderTableIframe").load("/gotoDeliveryOrderTable.do?code="+$("#code").val());
});


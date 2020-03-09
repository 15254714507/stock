laydate({
    elem: '#start',
    event: 'focus'
});
$(function() {
    var oTable1 = $('#sample-table').dataTable( {
        "aaSorting": [[0, "asc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable":false,"aTargets":[2,3,4]}// 制定列不参与排序
        ]});
})
$("#OverdueDrugTableIframe").load("/gotoOverdueDrugTable.do");
$("#search_button").on("click",function () {
    $("#OverdueDrugTableIframe").load("/gotoOverdueDrugTable.do?drugCode="+$("#drug_code").val());
});


$("#drugNumberAnalysisTableIframe").load("/gotoDrugNumberAnalysisTable.do");
$("#search_button").on("click", function () {
    $("#drugNumberAnalysisTableIframe").load("/gotoDrugNumberAnalysisTable.do?drugCode=" + $("#drug_code").val()
        + "&drugName=" + $("#drug_name").val());
});


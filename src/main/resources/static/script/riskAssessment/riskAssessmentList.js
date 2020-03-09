$("#riskAssessmentTableIframe").load("/gotoRiskAssessmentTable.do");
$("#search_button").on("click", function () {
    $("#riskAssessmentTableIframe").load("/gotoRiskAssessmentTable.do?drugCode=" + $("#drug_code").val()
        + "&delayedMaterialRisk=" + $("#delayed_material_risk").val());
});


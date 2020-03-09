package com.drug.stock.controller;

import com.drug.stock.entity.condition.OverdueDrugCondition;
import com.drug.stock.entity.condition.RiskAssessmentCondition;
import com.drug.stock.entity.domain.OverdueDrug;
import com.drug.stock.entity.domain.RiskAssessment;
import com.drug.stock.service.RiskAssessmentService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author lenovo
 */
@Slf4j
@Controller
public class RiskAssessmentController {
    @Resource
    RiskAssessmentService riskAssessmentService;

    @RequestMapping(value = "/gotoRiskAssessmentList.do")
    public String gotoRiskAssessmentList() {
        return "riskAssessment/riskAssessmentList";
    }

    @RequestMapping(value = "/gotoRiskAssessmentTable.do")
    public String gotoRiskAssessmentTable(Model model, String drugCode, Integer delayedMaterialRisk) {
        RiskAssessmentCondition riskAssessmentCondition = new RiskAssessmentCondition();
        riskAssessmentCondition.setDrugCode(drugCode);
        riskAssessmentCondition.setDelayedMaterialRisk(delayedMaterialRisk);
        PageInfo<RiskAssessment> pageInfo = null;
        try {
            pageInfo = riskAssessmentService.findRiskAssessmentPage(riskAssessmentCondition);
        } catch (Exception e) {
            log.error("跳转到药品风险评估页面时获取RiskAssessment的分页数据发生异常 drugCode：{}", drugCode);
            return "error/404";
        }
        model.addAttribute("riskAssessmentPage", pageInfo);
        return "riskAssessment/riskAssessmentTable";
    }
}

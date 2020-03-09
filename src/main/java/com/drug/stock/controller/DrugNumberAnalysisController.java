package com.drug.stock.controller;

import com.drug.stock.entity.condition.DrugNumberAnalysisCondition;
import com.drug.stock.entity.condition.OverdueDrugCondition;
import com.drug.stock.entity.domain.DrugNumberAnalysis;
import com.drug.stock.entity.domain.OverdueDrug;
import com.drug.stock.service.DrugNumberAnalysisService;
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
public class DrugNumberAnalysisController {
    @Resource
    DrugNumberAnalysisService drugNumberAnalysisService;

    @RequestMapping(value = "/gotoDrugNumberAnalysisList.do")
    public String gotoDrugNumberAnalysisList() {
        return "drugNumberAnalysis/drugNumberAnalysisList";
    }

    @RequestMapping(value = "/gotoDrugNumberAnalysisTable.do")
    public String gotoDrugNumberAnalysisTable(Model model, String drugCode, String drugName) {
        DrugNumberAnalysisCondition drugNumberAnalysisCondition = new DrugNumberAnalysisCondition();
        drugNumberAnalysisCondition.setDrugCode(drugCode);
        drugNumberAnalysisCondition.setDrugName(drugName);
        PageInfo<DrugNumberAnalysis> pageInfo = null;
        try {
            pageInfo = drugNumberAnalysisService.findDrugNumberAnalysisPage(drugNumberAnalysisCondition);
        } catch (Exception e) {
            log.error("跳转到药品库存页面时获取DrugNumberAnalysis的分页数据发生异常 drugCode：{}，drugName：{}", drugCode, drugName);
            return "error/404";
        }
        model.addAttribute("drugNumberAnalysisPage", pageInfo);
        return "drugNumberAnalysis/drugNumberAnalysisTable";
    }
}

package com.drug.stock.controller;

import com.drug.stock.entity.condition.OverdueDrugCondition;
import com.drug.stock.entity.domain.OverdueDrug;
import com.drug.stock.service.OverdueDrugService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
public class OverdueDrugController {
    @Resource
    OverdueDrugService overdueDrugService;

    @RequestMapping(value = "/gotoOverdueDrugList.do")
    public String gotoOverdueDrugList() {
        return "overdueDrug/overdueDrugList";
    }

    @RequestMapping(value = "/gotoOverdueDrugTable.do")
    public String gotoOverdueDrugTable(Model model, String drugCode) {
        OverdueDrugCondition overdueDrugCondition = new OverdueDrugCondition();
        overdueDrugCondition.setDrugCode(drugCode);
        PageInfo<OverdueDrug> pageInfo = null;
        try {
            pageInfo = overdueDrugService.findOverdueDrug(overdueDrugCondition);
        } catch (Exception e) {

        }
        model.addAttribute("overdueDrugPage", pageInfo);
        return "overdueDrug/overdueDrugTable";
    }

}

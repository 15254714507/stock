package com.drug.stock.controller;

import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.service.PurchaseOrderDrugService;
import com.drug.stock.service.PurchaseOrderService;
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
public class PurchaseOrderDrugController {
    @Resource
    PurchaseOrderService purchaseOrderService;
    @Resource
    PurchaseOrderDrugService purchaseOrderDrugService;

    /**
     * @param model
     * @param code
     * @return
     */
    @RequestMapping(value = "/gotoPurchaseOrderDrugList.do")
    public String gotoPurchaseOrderDrugList(Model model, String code) {
        PurchaseOrder purchaseOrder = null;
        try {
            purchaseOrder = purchaseOrderService.getPurchaseOrderByCode(code);
        } catch (Exception e) {
            log.error("跳转到入库单药品信息获取订单信息异常 code：{}", code, e);
            return "error/404";
        }
        if (purchaseOrder == null) {
            log.warn("跳转到purchaseOrderDrugList，根据code{} 获得的purchaseOrder为空", code);
            return "error/404";
        }
        model.addAttribute("purchaseOrder", purchaseOrder);
        return "purchaseOrderDrug/purchaseOrderDrugList";
    }

    /**
     * @param model
     * @param code
     * @param drugCode
     * @param drugName
     * @return
     */
    @RequestMapping(value = "/gotoPurchaseOrderDrugTable.do")
    public String gotoPurchaseOrderDrugTable(Model model, String code, String drugCode, String drugName) {
        PurchaseOrderDrugCondition purchaseOrderDrugCondition = new PurchaseOrderDrugCondition();
        purchaseOrderDrugCondition.setCode(code);
        purchaseOrderDrugCondition.setDrugCode(drugCode);
        purchaseOrderDrugCondition.setDrugName(drugName);
        PageInfo<PurchaseOrderDrug> pageInfo = null;
        try {
            pageInfo = purchaseOrderDrugService.findPurchaseOrderDrugPage(purchaseOrderDrugCondition);
        } catch (Exception e) {
            log.error("跳转到订单的药品信息页面获取药品的分页信息出现异常 purchaseOrderDrugCondition：{}", purchaseOrderDrugCondition, e);
            return "error/404";
        }
        model.addAttribute("page",pageInfo);
        return "purchaseOrderDrug/purchaseOrderDrugTable";
    }
}

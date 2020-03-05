package com.drug.stock.controller;

import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
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
public class PurchaseOrderController {
    @Resource
    PurchaseOrderService purchaseOrderService;

    @RequestMapping("/gotoPurchaseOrderList.do")
    public String gotoPurchaseOrderList() {
        return "purchaseOrder/purchaseOrderList";
    }

    @RequestMapping(value = "/gotoPurchaseOrderTable.do")
    public String gotoPurchaseOrderTable(Model model, String code) {
        PurchaseOrderCondition purchaseOrderCondition = new PurchaseOrderCondition();
        purchaseOrderCondition.setCode(code);
        PageInfo<PurchaseOrder> pageInfo = null;
        try {
            pageInfo = purchaseOrderService.findPurchaseOrderPage(purchaseOrderCondition);
        } catch (Exception e) {
            log.error("跳转到入库订单表获取 PageInfo<PurchaseOrder> 出现异常 code：{}",code,e);
            return "error/404";
        }
        model.addAttribute("purchaseOrderPage",pageInfo);
        return "purchaseOrder/purchaseOrderTable";
    }
}

package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.service.DeliveryOrderDrugService;
import com.drug.stock.service.DeliveryOrderService;
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
public class DeliveryOrderDrugController {
    @Resource
    DeliveryOrderService deliveryOrderService;
    @Resource
    DeliveryOrderDrugService deliveryOrderDrugService;

    /**
     * @param model
     * @param code
     * @return
     */
    @RequestMapping(value = "/gotoDeliveryOrderDrugList.do")
    public String gotoDeliveryOrderDrugList(Model model, String code) {
        DeliveryOrder deliveryOrder = null;
        try {
            deliveryOrder = deliveryOrderService.getDeliveryOrderByCode(code);
        } catch (Exception e) {
            log.error("跳转到出库单药品信息获取订单信息异常 code：{}", code, e);
            return "error/404";
        }
        if (deliveryOrder == null) {
            log.warn("跳转到DeliveryOrderDrugList，根据code{} 获得的deliveryOrder为空", code);
            return "error/404";
        }
        model.addAttribute("deliveryOrder", deliveryOrder);
        return "deliveryOrderDrug/deliveryOrderDrugList";
    }

    /**
     * @param model
     * @param code
     * @param drugCode
     * @param drugName
     * @return
     */
    @RequestMapping(value = "/gotoDeliveryOrderDrugTable.do")
    public String gotoDeliveryOrderDrugTable(Model model, String code, String drugCode, String drugName) {
        DeliveryOrderDrugCondition deliveryOrderDrugCondition = new DeliveryOrderDrugCondition();
        deliveryOrderDrugCondition.setCode(code);
        deliveryOrderDrugCondition.setDrugCode(drugCode);
        deliveryOrderDrugCondition.setDrugName(drugName);
        PageInfo<DeliveryOrderDrug> pageInfo = null;
        try {
           pageInfo = deliveryOrderDrugService.findDeliveryOrderDrugPage(deliveryOrderDrugCondition);
        } catch (Exception e) {
            log.error("跳转到出库单的药品信息页面获取药品的分页信息出现异常 deliveryOrderDrugCondition：{}", JSON.toJSONString(deliveryOrderDrugCondition), e);
            return "error/404";
        }
        model.addAttribute("page",pageInfo);
        return "deliveryOrderDrug/deliveryOrderDrugTable";
    }
}

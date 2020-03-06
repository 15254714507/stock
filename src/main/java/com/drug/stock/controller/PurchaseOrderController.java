package com.drug.stock.controller;

import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.service.PurchaseOrderService;
import com.drug.stock.sumbit.PurchaseOrderForm;
import com.drug.stock.until.Result;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author lenovo
 */
@Slf4j
@Controller
public class PurchaseOrderController {
    @Resource
    PurchaseOrderService purchaseOrderService;

    /**
     * @return
     */
    @RequestMapping("/gotoPurchaseOrderList.do")
    public String gotoPurchaseOrderList() {
        return "purchaseOrder/purchaseOrderList";
    }

    /**
     * @param model
     * @param code
     * @return
     */
    @RequestMapping(value = "/gotoPurchaseOrderTable.do")
    public String gotoPurchaseOrderTable(Model model, String code) {
        PurchaseOrderCondition purchaseOrderCondition = new PurchaseOrderCondition();
        purchaseOrderCondition.setCode(code);
        PageInfo<PurchaseOrder> pageInfo = null;
        try {
            pageInfo = purchaseOrderService.findPurchaseOrderPage(purchaseOrderCondition);
        } catch (Exception e) {
            log.error("跳转到入库订单表获取 PageInfo<PurchaseOrder> 出现异常 code：{}", code, e);
            return "error/404";
        }
        model.addAttribute("purchaseOrderPage", pageInfo);
        return "purchaseOrder/purchaseOrderTable";
    }

    /**
     * @param id
     * @return
     */
    @PostMapping(value = "/publishPurchaseOrder.do")
    @ResponseBody
    public Result publishPurchaseOrder(Long id) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(id);
        purchaseOrder.setStatus(true);
        Result result = null;
        try {
            Long isSuc = purchaseOrderService.updatePurchaseOrder(purchaseOrder);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.PURCHASE_ORDER_PUBLISH_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.PURCHASE_ORDER_NOT);
            }
        } catch (Exception e) {
            log.error("入库单修改状态时发生异常 id：{}", id, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/gotoUpdatePurchaseOrder.do")
    public String gotoUpdatePurchaseOrder(Model model, Long id) {
        PurchaseOrder purchaseOrder = null;
        try {
            purchaseOrder = purchaseOrderService.getPurchaseOrder(id);
        } catch (Exception e) {
            log.error("跳转到修改入库单页面获取入库单信息时出错");
            return "error/404";
        }
        if (purchaseOrder == null) {
            return "error/404";
        }
        model.addAttribute("purchaseOrder", purchaseOrder);
        return "purchaseOrder/updatePurchaseOrder";
    }


    /**
     * @param purchaseOrderForm
     * @param session
     * @return
     */
    @PostMapping(value = "/updatePurchaseOrder.do")
    @ResponseBody
    public Result updatePurchaseOrder(@Valid PurchaseOrderForm purchaseOrderForm, HttpSession session) {
        if (purchaseOrderForm == null || purchaseOrderForm.getId() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.PURCHASE_ORDER_FORM_ERROR);
        }
        PurchaseOrder purchaseOrder = fillPurchaseOrder(purchaseOrderForm, (String) session.getAttribute(session.getId()));
        purchaseOrder.setCreateUser(null);
        Result result = null;
        try {
            Long isSuc = purchaseOrderService.updatePurchaseOrder(purchaseOrder);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE,SuccessConstant.UPDATE_PURCHASE_ORDER_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE,ErrorConstant.PURCHASE_ORDER_NOT);
            }
        } catch (Exception e) {
            log.error("修改入库订单状态时发生异常，purchaseOrderForm：{}", purchaseOrder, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    private PurchaseOrder fillPurchaseOrder(PurchaseOrderForm purchaseOrderForm, String createUser) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(purchaseOrderForm.getId());
        purchaseOrder.setDescription(purchaseOrderForm.getDescription());
        purchaseOrder.setCreateUser(createUser);
        purchaseOrder.setUpdateUser(createUser);
        return purchaseOrder;
    }
}

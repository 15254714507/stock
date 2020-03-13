package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.DeliveryOrderCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.drug.stock.service.DeliveryOrderService;
import com.drug.stock.sumbit.DeliveryOrderForm;
import com.drug.stock.until.Result;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class DeliveryOrderController {
    @Resource
    DeliveryOrderService deliveryOrderService;

    /**
     * @return
     */
    @RequestMapping("/gotoDeliveryOrderList.do")
    public String gotoPurchaseOrderList() {
        return "deliveryOrder/deliveryOrderList";
    }

    /**
     * @param model
     * @param code
     * @return
     */
    @RequestMapping(value = "/gotoDeliveryOrderTable.do")
    public String gotoDeliveryOrderTable(Model model, String code) {
        DeliveryOrderCondition deliveryOrderCondition = new DeliveryOrderCondition();
        deliveryOrderCondition.setCode(code);
        PageInfo<DeliveryOrder> pageInfo = null;
        try {
            pageInfo = deliveryOrderService.findDeliveryOrderPage(deliveryOrderCondition);
        } catch (Exception e) {
            log.error("跳转到出库订单表获取 PageInfo<DeliveryOrder> 出现异常 code：{}", code, e);
            return "error/404";
        }
        model.addAttribute("deliveryOrderPage", pageInfo);
        return "deliveryOrder/deliveryOrderTable";
    }

    /**
     * @param id
     * @return
     */
    @PostMapping(value = "/publishDeliveryOrder.do")
    @ResponseBody
    public Result publishDeliveryOrder(Long id, HttpSession session) {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(id);
        deliveryOrder.setStatus(true);
        deliveryOrder.setUpdateUser((String) session.getAttribute(session.getId()));
        Result result = null;
        try {
            result = deliveryOrderService.publishDeliveryOrder(deliveryOrder);
        } catch (Exception e) {
            log.error("出库单修改状态时发生异常 id：{}", id, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/gotoUpdateDeliveryOrder.do")
    public String gotoUpdatePurchaseOrder(Model model, Long id) {
        DeliveryOrder deliveryOrder = null;
        try {
            deliveryOrder = deliveryOrderService.getDeliveryOrder(id);
        } catch (Exception e) {
            log.error("跳转到修改出库单页面获取出库单信息时出错");
            return "error/404";
        }
        if (deliveryOrder == null) {
            return "error/404";
        }
        model.addAttribute("deliveryOrder", deliveryOrder);
        return "deliveryOrder/updateDeliveryOrder";
    }


    /**
     * @param deliveryOrderForm
     * @param session
     * @return
     */
    @PostMapping(value = "/updateDeliveryOrder.do")
    @ResponseBody
    public Result updateDeliveryOrder(@Valid DeliveryOrderForm deliveryOrderForm, HttpSession session) {
        if (deliveryOrderForm == null || deliveryOrderForm.getId() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ORDER_FORM_ERROR);
        }
        DeliveryOrder deliveryOrder = fillDeliveryOrder(deliveryOrderForm, (String) session.getAttribute(session.getId()));
        deliveryOrder.setCreateUser(null);
        Result result = null;
        try {
            Long isSuc = deliveryOrderService.updateDeliveryOrder(deliveryOrder);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.UPDATE_DELIVERY_ORDER_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ORDER_NOT);
            }
        } catch (Exception e) {
            log.error("修改出库订单状态时发生异常，deliveryOrder：{}", JSON.toJSONString(deliveryOrder), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * 填充DeliveryOrder
     *
     * @param deliveryOrderForm
     * @param createUser
     * @return
     */
    private DeliveryOrder fillDeliveryOrder(DeliveryOrderForm deliveryOrderForm, String createUser) {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(deliveryOrderForm.getId());
        deliveryOrder.setDescription(deliveryOrderForm.getDescription());
        deliveryOrder.setCreateUser(createUser);
        deliveryOrder.setUpdateUser(createUser);
        return deliveryOrder;
    }

    /**
     * 前往添加出库单页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoAddDeliveryOrder.do")
    public String gotoAddDeliveryOrder(Model model) {
        return "deliveryOrder/addDeliveryOrder";
    }

    @PostMapping("/saveDeliveryOrder.do")
    @ResponseBody
    public Result saveDeliveryOrder(@Valid DeliveryOrderForm deliveryOrderForm, HttpSession session) {
        if (deliveryOrderForm == null || deliveryOrderForm.getDescription() == null || deliveryOrderForm.getId() != null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ORDER_FORM_ERROR);
        }
        DeliveryOrder deliveryOrder = fillDeliveryOrder(deliveryOrderForm, (String) session.getAttribute(session.getId()));
        deliveryOrder.setUserAccount(deliveryOrder.getCreateUser());
        Result result = null;
        try {
            Long isSuc = deliveryOrderService.insertDeliveryOrder(deliveryOrder);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.SAVE_DELIVERY_ORDER_SUCCESS);
            } else {
                //这里返回的要是系统异常，因为编码是生成的，所以就算一样也是因为生成的编码一样了，返回系统异常
                result = new Result(ErrorConstant.ERROR_CODE, SystemConstant.SYSTEM_ERROR);
            }
        } catch (Exception e) {
            log.error("创建新出库单时发生异常，deliveryOrder：{}", JSON.toJSONString(deliveryOrder), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    @PostMapping(value = "/deleteDeliveryOrder.do")
    @ResponseBody
    public Result deleteDeliveryOrder(Long id) {
        Result result = null;
        try {
            Long isSuc = deliveryOrderService.deleteDeliveryOrder(id);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.DELETE_DELIVERY_ORDER_SUCCESS);
            } else {
                //非法的不考虑，如果删除返回为0，说明没有没有此出库单
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.DELETE_ORDER_NOT);
            }
        } catch (Exception e) {
            log.error("删除出库单时发生系统异常 id{}", id, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }


}

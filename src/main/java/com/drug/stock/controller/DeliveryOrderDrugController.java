package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.*;
import com.drug.stock.entity.domain.*;
import com.drug.stock.service.DeliveryOrderDrugService;
import com.drug.stock.service.DeliveryOrderService;
import com.drug.stock.sumbit.DeliveryOrderDrugForm;
import com.drug.stock.sumbit.PurchaseOrderDrugForm;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
        DeliveryOrder deliveryOrder = null;
        try {
            pageInfo = deliveryOrderDrugService.findDeliveryOrderDrugPage(deliveryOrderDrugCondition);
            deliveryOrder = deliveryOrderService.getDeliveryOrderByCode(code);
        } catch (Exception e) {
            log.error("跳转到出库单的药品信息页面获取药品的分页信息出现异常 deliveryOrderDrugCondition：{}", JSON.toJSONString(deliveryOrderDrugCondition), e);
            return "error/404";
        }
        model.addAttribute("page", pageInfo);
        model.addAttribute("deliveryOrder", deliveryOrder);
        return "deliveryOrderDrug/deliveryOrderDrugTable";
    }

    /**
     * 跳转到入库单添加药品页面
     *
     * @param model
     * @param code  入库单的编码
     * @return
     */
    @RequestMapping(value = "/gotoAddDeliveryOrderDrug.do")
    public String gotoAddDeliveryOrderDrug(Model model, String code) {
        DeliveryOrderCondition deliveryOrderCondition = new DeliveryOrderCondition();
        deliveryOrderCondition.setCode(code);
        List<DeliveryOrder> list = deliveryOrderService.listDeliveryOrder(deliveryOrderCondition);
        if (list == null || list.size() < 1) {
            return "error/404";
        }
        model.addAttribute("deliveryOrderCode", list.get(0).getCode());
        return "deliveryOrderDrug/addDeliveryOrderDrug";
    }

    /**
     * 保存入库单药品
     *
     * @param deliveryOrderDrugForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping(value = "/saveDeliveryOrderDrug.do")
    @ResponseBody
    public Result saveDeliveryOrderDrug(@Valid DeliveryOrderDrugForm deliveryOrderDrugForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.DELIVERY_ORDER_DRUG_ERROR);
        }
        DeliveryOrderDrug deliveryOrderDrug = createDeliveryOrderDrug(deliveryOrderDrugForm, (String) session.getAttribute(session.getId()));
        Result result = null;
        try {
            result = deliveryOrderDrugService.insertDeliveryOrderDrug(deliveryOrderDrug);
        } catch (Exception e) {
            log.error("往入库单添加药品出现系统异常,deliveryOrderDrug:{}", JSON.toJSONString(deliveryOrderDrug), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * 根据提交的表单创建出库单药品
     *
     * @param deliveryOrderDrugForm
     * @param createUser
     * @return
     */
    private DeliveryOrderDrug createDeliveryOrderDrug(DeliveryOrderDrugForm deliveryOrderDrugForm, String createUser) {
        DeliveryOrderDrug deliveryOrderDrug = new DeliveryOrderDrug();
        deliveryOrderDrug.setCode(deliveryOrderDrugForm.getCode());
        deliveryOrderDrug.setDrugCode(deliveryOrderDrugForm.getDrugCode());
        deliveryOrderDrug.setNumber(deliveryOrderDrugForm.getNumber());
        deliveryOrderDrug.setCreateUser(createUser);
        deliveryOrderDrug.setUpdateUser(createUser);
        return deliveryOrderDrug;

    }

    /**
     * 前往入库单药品修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/gotoUpdateDeliveryOrderDrug.do")
    public String gotoUpdateDeliveryOrderDrug(Model model, Long id) {
        DeliveryOrderDrug deliveryOrderDrug = null;
        try {
            deliveryOrderDrug = deliveryOrderDrugService.getDeliveryOrderDrug(id);
        } catch (Exception e) {
            log.error("跳转到出库单药品修改页面时出现系统异常 id{}",id,e);
            return "error/404";
        }
        if (deliveryOrderDrug == null) {
            return "error/404";
        }
        model.addAttribute("deliveryOrderDrug", deliveryOrderDrug);
        return "deliveryOrderDrug/updateDeliveryOrderDrug";
    }

    @PostMapping(value = "/updateDeliveryOrderDrug.do")
    @ResponseBody
    public Result updateDeliveryOrderDrug(@Valid DeliveryOrderDrugForm deliveryOrderDrugForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors() || deliveryOrderDrugForm.getId() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.PURCHASE_ORDER_FORM_ERROR);
        }
        DeliveryOrderDrug deliveryOrderDrug = createDeliveryOrderDrug(deliveryOrderDrugForm, (String) session.getAttribute(session.getId()));
        deliveryOrderDrug.setId(deliveryOrderDrugForm.getId());
        deliveryOrderDrug.setPrice(deliveryOrderDrugForm.getPrice());
        Result result = null;
        try {
            result = deliveryOrderDrugService.updateDeliveryOrderDrug(deliveryOrderDrug);
        } catch (Exception e) {
            log.error("修改出库单中药品信息时发生系统异常，deliveryOrderDrug：{}", JSON.toJSONString(deliveryOrderDrug), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    @PostMapping(value = "/deleteDeliveryOrderDrug.do")
    @ResponseBody
    public Result deleteDeliveryOrderDrug(Long id) {
        Result result = null;
        try {
            Long isSuc = deliveryOrderDrugService.deleteDeliveryOrderDrug(id);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.DELETE_DELIVERY_ORDER_DRUG_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.NOT_DELIVERY_ORDER_DRUG);
            }
        } catch (Exception e) {
            log.error("删除出库单中药品时发生系统异常，id：{}", id, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }
}

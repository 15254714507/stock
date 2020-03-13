package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.ProviderCondition;
import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.Provider;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.service.ProviderService;
import com.drug.stock.service.PurchaseOrderDrugService;
import com.drug.stock.service.PurchaseOrderService;
import com.drug.stock.sumbit.PurchaseOrderDrugForm;
import com.drug.stock.until.Result;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import java.util.Date;
import java.util.List;

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
    @Resource
    ProviderService providerService;

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
        PurchaseOrder purchaseOrder = null;
        try {
            pageInfo = purchaseOrderDrugService.findPurchaseOrderDrugPage(purchaseOrderDrugCondition);
            purchaseOrder = purchaseOrderService.getPurchaseOrderByCode(code);
        } catch (Exception e) {
            log.error("跳转到订单的药品信息页面获取药品的分页信息出现异常 purchaseOrderDrugCondition：{}", purchaseOrderDrugCondition, e);
            return "error/404";
        }
        model.addAttribute("page", pageInfo);
        model.addAttribute("purchaseOrder", purchaseOrder);
        return "purchaseOrderDrug/purchaseOrderDrugTable";
    }

    /**
     * 跳转到入库单添加药品页面
     *
     * @param model
     * @param code  入库单的编码
     * @return
     */
    @RequestMapping(value = "/gotoAddPurchaseOrderDrug.do")
    public String gotoAddPurchaseOrderDrug(Model model, String code) {
        PurchaseOrderCondition purchaseOrderCondition = new PurchaseOrderCondition();
        purchaseOrderCondition.setCode(code);
        List<PurchaseOrder> list = purchaseOrderService.listPurchaseOrder(purchaseOrderCondition);
        if (list == null || list.size() < 1) {
            return "error/404";
        }
        ProviderCondition providerCondition = new ProviderCondition();
        List<Provider> providerList = providerService.listProvider(providerCondition);
        model.addAttribute("purchaseOrderCode", list.get(0).getCode());
        model.addAttribute("providerList", providerList);
        return "purchaseOrderDrug/addPurchaseOrderDrug";
    }

    /**
     * 保存入库单药品
     *
     * @param purchaseOrderDrugForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping(value = "/savePurchaseOrderDrug.do")
    @ResponseBody
    public Result savePurchaseOrderDrug(@Valid PurchaseOrderDrugForm purchaseOrderDrugForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.PURCHASE_ORDER_FORM_ERROR);
        }
        PurchaseOrderDrug purchaseOrderDrug = createPurchaseOrderDrug(purchaseOrderDrugForm, (String) session.getAttribute(session.getId()));
        Result result = fillExpireDate(purchaseOrderDrug, purchaseOrderDrugForm.getExpireDate());
        if (result != null) {
            return result;
        }
        try {
            result = purchaseOrderDrugService.insertPurchaseOrderDrug(purchaseOrderDrug);
        } catch (Exception e) {
            log.error("往入库单添加药品出现系统异常,purchaseOrderDrug:{}", JSON.toJSONString(purchaseOrderDrug), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * 根据提交的表单创建入库单药品
     *
     * @param purchaseOrderDrugForm
     * @param createUser
     * @return
     */
    private PurchaseOrderDrug createPurchaseOrderDrug(PurchaseOrderDrugForm purchaseOrderDrugForm, String createUser) {
        PurchaseOrderDrug purchaseOrderDrug = new PurchaseOrderDrug();
        purchaseOrderDrug.setCode(purchaseOrderDrugForm.getCode());
        purchaseOrderDrug.setDrugCode(purchaseOrderDrugForm.getDrugCode());
        purchaseOrderDrug.setProviderId(purchaseOrderDrugForm.getProviderId());
        purchaseOrderDrug.setProductionLotNumber(purchaseOrderDrugForm.getProductionLotNumber());
        purchaseOrderDrug.setPrice(purchaseOrderDrugForm.getPrice());
        purchaseOrderDrug.setNumber(purchaseOrderDrugForm.getNumber());
        purchaseOrderDrug.setCreateUser(createUser);
        purchaseOrderDrug.setUpdateUser(createUser);
        return purchaseOrderDrug;
    }

    /**
     * 提交的表单上的日期是字符串，所以需要转换成DATE格式
     *
     * @param purchaseOrderDrug
     * @param expireDate
     * @return
     */
    private Result fillExpireDate(PurchaseOrderDrug purchaseOrderDrug, String expireDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            purchaseOrderDrug.setExpireDate(format.parse(expireDate));
        } catch (ParseException e) {
            log.error("添加入库单药品时有效期转换失败,purchaseOrderDrug:{},expireDate:{}", JSON.toJSONString(purchaseOrderDrug), expireDate);
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.EXPIRE_DATE_TO_DATE);
        }
        return null;
    }

    /**
     * 前往入库单药品修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/gotoUpdatePurchaseOrderDrug.do")
    public String gotoUpdatePurchaseOrderDrug(Model model, Long id) {
        PurchaseOrderDrug purchaseOrderDrug = null;
        try {
            purchaseOrderDrug = purchaseOrderDrugService.getPurchaseOrderDrug(id);
        } catch (Exception e) {
            log.error("跳转到入库单药品修改页面时出现系统异常 id{}",id,e);
            return "error/404";
        }
        if (purchaseOrderDrug == null) {
            return "error/404";
        }
        model.addAttribute("purchaseOrderDrug", purchaseOrderDrug);

        ProviderCondition providerCondition = new ProviderCondition();
        List<Provider> providerList = providerService.listProvider(providerCondition);
        model.addAttribute("providerList", providerList);
        return "purchaseOrderDrug/updatePurchaseOrderDrug";
    }

    @PostMapping(value = "/updatePurchaseOrderDrug.do")
    @ResponseBody
    public Result updatePurchaseOrderDrug(@Valid PurchaseOrderDrugForm purchaseOrderDrugForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors() || purchaseOrderDrugForm.getId() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.PURCHASE_ORDER_FORM_ERROR);
        }
        PurchaseOrderDrug purchaseOrderDrug = createPurchaseOrderDrug(purchaseOrderDrugForm, (String) session.getAttribute(session.getId()));
        purchaseOrderDrug.setId(purchaseOrderDrugForm.getId());
        fillExpireDate(purchaseOrderDrug, purchaseOrderDrugForm.getExpireDate());
        Result result = null;
        try {
            result = purchaseOrderDrugService.updatePurchaseOrderDrug(purchaseOrderDrug);
        } catch (Exception e) {
            log.error("修改入库单中药品信息时发生系统异常，purchaseOrderDrug：{}", JSON.toJSONString(purchaseOrderDrug), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    @PostMapping(value = "/deletePurchaseOrderDrug.do")
    @ResponseBody
    public Result deletePurchaseOrderDrug(Long id) {
        Result result = null;
        try {
            Long isSuc = purchaseOrderDrugService.deletePurchaseOrderDrug(id);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.DELETE_PURCHASE_ORDER_DRUG_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.NOT_PURCHASE_ORDER_DRUG);
            }
        } catch (Exception e) {
            log.error("删除入库单中药品时发生系统异常，id：{}", id, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }
}

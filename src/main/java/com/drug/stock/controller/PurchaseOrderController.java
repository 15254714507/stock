package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.service.PurchaseOrderDrugService;
import com.drug.stock.service.PurchaseOrderService;
import com.drug.stock.sumbit.PurchaseOrderForm;
import com.drug.stock.until.Result;
import com.drug.stock.until.WordUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 */
@Slf4j
@Controller
public class PurchaseOrderController {
    @Resource
    PurchaseOrderService purchaseOrderService;
    @Resource
    PurchaseOrderDrugService purchaseOrderDrugService;

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
    public Result publishPurchaseOrder(Long id, HttpSession session) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(id);
        purchaseOrder.setStatus(true);
        purchaseOrder.setUpdateUser((String) session.getAttribute(session.getId()));
        Result result = null;
        try {
            result = purchaseOrderService.publishPurchaseOrder(purchaseOrder);
        } catch (Exception e) {
            log.error("入库单发布时发生异常 purchaseOrder：{}", JSON.toJSONString(purchaseOrder), e);
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
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ORDER_FORM_ERROR);
        }
        PurchaseOrder purchaseOrder = fillPurchaseOrder(purchaseOrderForm, (String) session.getAttribute(session.getId()));
        purchaseOrder.setCreateUser(null);
        Result result = null;
        try {
            Long isSuc = purchaseOrderService.updatePurchaseOrder(purchaseOrder);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.UPDATE_PURCHASE_ORDER_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ORDER_NOT);
            }
        } catch (Exception e) {
            log.error("修改入库订单状态时发生异常，purchaseOrderForm：{}", purchaseOrder, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * 填充PurchaseOrder
     *
     * @param purchaseOrderForm
     * @param createUser
     * @return
     */
    private PurchaseOrder fillPurchaseOrder(PurchaseOrderForm purchaseOrderForm, String createUser) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(purchaseOrderForm.getId());
        purchaseOrder.setDescription(purchaseOrderForm.getDescription());
        purchaseOrder.setCreateUser(createUser);
        purchaseOrder.setUpdateUser(createUser);
        return purchaseOrder;
    }

    /**
     * 前往添加订单页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoAddPurchaseOrder.do")
    public String gotoAddPurchaseOrder(Model model) {
        return "purchaseOrder/addPurchaseOrder";
    }

    @PostMapping("/savePurchaseOrder.do")
    @ResponseBody
    public Result savePurchaseOrder(@Valid PurchaseOrderForm purchaseOrderForm, HttpSession session) {
        if (purchaseOrderForm == null || purchaseOrderForm.getDescription() == null || purchaseOrderForm.getId() != null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ORDER_FORM_ERROR);
        }
        PurchaseOrder purchaseOrder = fillPurchaseOrder(purchaseOrderForm, (String) session.getAttribute(session.getId()));
        purchaseOrder.setUserAccount(purchaseOrder.getCreateUser());
        Result result = null;
        try {
            Long isSuc = purchaseOrderService.insertPurchaseOrder(purchaseOrder);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.SAVE_PURCHASE_ORDER_SUCCESS);
            } else {
                //这里返回的要是系统异常，因为编码是生成的，所以就算一样也是因为生成的编码一样了
                result = new Result(ErrorConstant.ERROR_CODE, SystemConstant.SYSTEM_ERROR);
            }
        } catch (Exception e) {
            log.error("创建新入库单时发生异常，purchaseOrder：{}", JSON.toJSONString(purchaseOrder), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    @PostMapping(value = "/deletePurchaseOrder.do")
    @ResponseBody
    public Result deletePurchaseOrder(Long id) {
        Result result = null;
        try {
            Long isSuc = purchaseOrderService.deletePurchaseOrder(id);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.DELETE_PURCHASE_ORDER_SUCCESS);
            } else {
                //非法的不考虑，如果删除返回为0，说明没有没有此入库单
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.DELETE_ORDER_NOT);
            }
        } catch (Exception e) {
            log.error("删除入库单时发生系统异常 id{}", id, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/downPurchaseOrder.do")
    @ResponseBody
    public ResponseEntity<byte[]> downPurchaseOrder(Long id) {
        Map<String, Object> dataMap = new HashMap<String, Object>(2);
        byte[] fileByte = null;
        try {
            PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrder(id);
            dataMap.put("purchaseOrder", purchaseOrder);
            PurchaseOrderDrugCondition purchaseOrderDrugCondition = new PurchaseOrderDrugCondition();
            purchaseOrderDrugCondition.setCode(purchaseOrder.getCode());
            List<PurchaseOrderDrug> purchaseOrderDrugList = purchaseOrderDrugService.listPurchaseOrderDrug(purchaseOrderDrugCondition);
            dataMap.put("purchaseOrderDrugList", purchaseOrderDrugList);
            fileByte = WordUtil.createWordByte(dataMap, "purchaseOrder.ftl", "入库单.doc");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(purchaseOrder.getCode()+"入库单.doc", "utf-8"));
            return new ResponseEntity<byte[]>(fileByte, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("导出入库单失败 id:{}", id, e);
        }
        return null;
    }

}

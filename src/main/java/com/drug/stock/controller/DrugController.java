package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.entity.domain.User;
import com.drug.stock.service.DrugService;
import com.drug.stock.sumbit.DrugForm;
import com.drug.stock.sumbit.UserForm;
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
import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author lenovo
 */
@Slf4j
@Controller
public class DrugController {
    @Resource
    DrugService drugService;

    /**
     * 前往药品信息管理页面
     *
     * @return
     */
    @RequestMapping(value = "/gotoDrugList.do")
    public String gotoUserList() {
        return "drug/drugList";
    }

    @RequestMapping(value = "/gotoDrugTable.do")
    public String gotoDrugTable(Model model) {
        DrugCondition drugCondition = new DrugCondition();
        PageInfo<Drug> page = null;
        try {
            page = drugService.findDrugPage(drugCondition);
        } catch (Exception e) {
            log.error("跳转到药品表格页面时获取药品分页对象报错", e);
            return "error/404";
        }
        model.addAttribute("drugPage", page);
        return "drug/drugTable";
    }

    @RequestMapping(value = "/gotoAddDrug.do")
    public String gotoAddDrug() {
        return "drug/addDrug";
    }

    @PostMapping(value = "/saveDrug.do")
    @ResponseBody
    public Result saveDrug(@Valid DrugForm drugForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.SAVE_DRUG_FORM_ERROR);
        }
        Drug drug = fillDrug(drugForm, (String) session.getAttribute(session.getId()));
        //创建药品时应该没有价格和库存
        drug.setPrice(null);
        drug.setNumber(null);
        Result result = null;
        try {
            Long isSuc = drugService.insertDrug(drug);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.INSERT_DRUG_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.SAVE_DRUG_CODE_EXIST);
            }
        } catch (Exception e) {
            log.error("添加药品信息时发生异常 drugForm:{}", JSON.toJSONString(drugForm), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    private Drug fillDrug(DrugForm drugForm, String createUser) {
        Drug drug = new Drug();
        drug.setCode(drugForm.getCode());
        drug.setName(drugForm.getName());
        drug.setSpecs(drugForm.getSpecs());
        drug.setDosageForm(drugForm.getDosageForm());
        drug.setApprovalNumber(drugForm.getApprovalNumber());
        drug.setStorage(drugForm.getStorage());
        drug.setPackaging(drugForm.getPackaging());
        drug.setWareHouse(drugForm.getWareHouse());
        drug.setNumber(drugForm.getNumber());
        drug.setPrice(drugForm.getPrice());
        drug.setCreateUser(createUser);
        drug.setUpdateUser(createUser);
        return drug;
    }

    /**
     * 前往修改药品页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoUpdateDrug.do")
    public String gotoUpdateDrug(@Valid @NotNull Long id, Model model) {
        Drug drug = null;
        try {
            drug = drugService.getDrug(id);
        } catch (Exception e) {
            log.error("跳转到修改药品信息页面时获取Drug数据发生错误 id:{}", id, e);
        }
        if (drug == null) {
            return "error/404";
        }
        model.addAttribute("drug", drug);
        return "drug/updateDrug";
    }

    /**
     * 修改用户信息
     *
     * @param drugForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping(value = "/updateDrug.do")
    @ResponseBody
    public Result updateDrug(@Valid DrugForm drugForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors() || drugForm.getId() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ACCOUNT_DIFFERENCE_ERROR);
        }
        Drug drug = fillDrug(drugForm, (String) session.getAttribute(session.getId()));
        //这里需要id才加上
        drug.setId(drugForm.getId());
        //修改信息，不应该修改创建人
        drug.setCreateUser(null);
        //药品的编码在创建的时候就确了，不允许修改
        drug.setCode(null);
        Result result = null;
        try {
            Long isSuc = drugService.updateDrug(drug);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.CHANGE_INFORMATION_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.UPDATE_DRUG_CODE_NOT);
            }
        } catch (Exception e) {
            log.error("修改药品信息出错 drug:{}", JSON.toJSONString(drug), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    @PostMapping(value = "/deleteDrug.do")
    @ResponseBody
    public Result deleteDrug(@Valid @NotNull Long id) {
        Result result = null;
        try {
            Long isSuc = drugService.deleteDrug(id);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.DELETE_DRUG_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.DELETE_DRUG_ERROR_NOT_CODE);
            }
        } catch (Exception e) {
            log.error("删除药品出错 id:{}", id, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }
}

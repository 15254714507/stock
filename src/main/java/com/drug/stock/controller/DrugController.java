package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.service.DrugService;
import com.drug.stock.sumbit.DrugForm;
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
        Result result = null;
        try {
            Long isSuc = drugService.insertDrug(drug);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.INSERT_DRUG_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE,ErrorConstant.SAVE_DRUG_CODE_EXIST);
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
        drug.setCreateUser(createUser);
        drug.setUpdateUser(createUser);
        return drug;
    }

}

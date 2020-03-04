package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.ProviderCondition;
import com.drug.stock.entity.domain.Provider;
import com.drug.stock.service.ProviderService;
import com.drug.stock.sumbit.ProviderForm;
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
import javax.validation.constraints.NotNull;

/**
 * @author lenovo
 */
@Slf4j
@Controller
public class ProviderController {
    @Resource
    ProviderService providerService;

    /**
     * 跳转到供应商页面
     *
     * @return
     */
    @RequestMapping(value = "/gotoProviderList.do")
    public String gotoProviderList() {
        return "provider/providerList";
    }

    @RequestMapping(value = "/gotoProviderTable.do")
    public String gotoProviderTable(Model model, String name, String company) {
        ProviderCondition providerCondition = new ProviderCondition();
        providerCondition.setName(name);
        providerCondition.setCompany(company);
        PageInfo<Provider> pageInfo = null;
        try {
            pageInfo = providerService.findProviderPage(providerCondition);
        } catch (Exception e) {
            log.error("跳转到供应商表格页面获取供应商列表时报错 providerCondition：{}", providerCondition, e);
            return "error/404";
        }
        model.addAttribute("providePage", pageInfo);
        return "provider/providerTable";
    }

    /**
     * 跳转到添加供应商页面
     *
     * @return
     */
    @RequestMapping(value = "/gotoAddProvider.do")
    public String gotoAddProvider() {
        return "provider/addProvider";
    }

    @PostMapping(value = "/saveProvider.do")
    @ResponseBody
    public Result saveProvider(@Valid ProviderForm providerForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.PROVIDER_FORM_ERROR);
        }
        Provider provider = fillProvider(providerForm, (String) session.getAttribute(session.getId()));
        Result result = null;
        try {
            Long isSuc = providerService.insertProvider(provider);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.INSERT_PROVIDER_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.SAVE_PROVIDER_CODE_EXIST);
            }
        } catch (Exception e) {
            log.error("添加供应商信息时出现异常，providerForm：{}", JSON.toJSONString(providerForm), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * 把providerForm的信息填充到Provider
     *
     * @param providerForm
     * @param createUser
     * @return
     */
    private Provider fillProvider(ProviderForm providerForm, String createUser) {
        Provider provider = new Provider();
        provider.setCode(providerForm.getCode());
        provider.setCompany(providerForm.getCompany());
        provider.setName(providerForm.getName());
        provider.setPhone(providerForm.getPhone());
        provider.setEmail(providerForm.getEmail());
        provider.setCity(providerForm.getCity());
        provider.setAddress(providerForm.getAddress());
        provider.setCreateUser(createUser);
        provider.setUpdateUser(createUser);
        return provider;
    }

    @RequestMapping(value = "/gotoUpdateProvider.do")
    public String gotoUpdateProvider(Model model, Long id) {
        Provider provider = null;
        try {
            provider = providerService.getProvider(id);
        } catch (Exception e) {
            log.error("跳转到修改供应商页面获取供应商信息出现错误 id：{}", id, e);
            return "error/404";
        }
        if (provider == null) {
            return "error/404";
        }
        model.addAttribute("provider", provider);
        return "provider/updateProvider";
    }

    @PostMapping(value = "/updateProvider.do")
    @ResponseBody
    public Result updateProvider(@Valid ProviderForm providerForm, BindingResult bindingResult, HttpSession session) {
        if (providerForm.getId() == null || bindingResult.hasErrors()) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.UPDATE_PROVIDER_FORM_ERROR);
        }
        Provider provider = fillProvider(providerForm, (String) session.getAttribute(session.getId()));
        //修改供应商信息需要id，创建者为空
        provider.setId(providerForm.getId());
        provider.setCreateUser(null);
        Result result = null;
        try {
            Long isSuc = providerService.updateProvider(provider);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.UPDATE_PROVIDER_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.UPDATE_PROVIDER_CODE_NOT);
            }
        } catch (Exception e) {
            log.error("修改供应商信息出现系统异常 providerForm:{}", JSON.toJSONString(providerForm), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    @PostMapping(value = "/deleteProvider.do")
    @ResponseBody
    public Result deleteProvider(@Valid @NotNull Long id) {
        Result result = null;
        try {
            Long isSuc = providerService.deleteProvider(id);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.DELETE_PROVIDER_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.DELETE_PROVIDER_NOT);
            }
        } catch (Exception e) {
            log.error("删除供应商出错 id:{}", id, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

}

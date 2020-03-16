package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.*;
import com.drug.stock.entity.domain.*;
import com.drug.stock.service.*;
import com.drug.stock.sumbit.ChangePasswordForm;
import com.drug.stock.sumbit.UserForm;
import com.drug.stock.until.Result;
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
import java.util.List;
import java.util.Objects;

/**
 * @author lenovo
 */
@Slf4j
@Controller()
public class MainController {

    @Resource
    private UserService userService;
    @Resource
    private PurchaseOrderService purchaseOrderService;
    @Resource
    private DeliveryOrderService deliveryOrderService;
    @Resource
    private DrugService drugService;
    @Resource
    private ProviderService providerService;
    @Resource
    private OverdueDrugService overdueDrugService;
    @Resource
    private RiskAssessmentService riskAssessmentService;

    /**
     * 进入home页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home.do")
    public String init(Model model, HttpSession session) {
        UserCondition userCondition = new UserCondition();
        List<User> userList = userService.listUser(userCondition);
        model.addAttribute("users",userList.size());
        PurchaseOrderCondition purchaseOrderCondition = new PurchaseOrderCondition();
        purchaseOrderCondition.setStatus(true);
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.listPurchaseOrder(purchaseOrderCondition);
        model.addAttribute("purchaseOrders",purchaseOrderList.size());
        DeliveryOrderCondition deliveryOrderCondition = new DeliveryOrderCondition();
        deliveryOrderCondition.setStatus(true);
        List<DeliveryOrder> deliveryOrderList = deliveryOrderService.listDeliveryOrder(deliveryOrderCondition);
        model.addAttribute("deliveryOrders",deliveryOrderList.size());
        DrugCondition drugCondition = new DrugCondition();
        List<Drug> drugList = drugService.listDrug(drugCondition);
        model.addAttribute("drugs",drugList.size());
        ProviderCondition providerCondition = new ProviderCondition();
        List<Provider> providerList = providerService.listProvider(providerCondition);
        model.addAttribute("providers",providerList.size());
        OverdueDrugCondition overdueDrugCondition = new OverdueDrugCondition();
        overdueDrugCondition.setStatus(false);
        List<OverdueDrug> overdueDrugList = overdueDrugService.listOverdueDrug(overdueDrugCondition);
        model.addAttribute("overdueDrugs",overdueDrugList.size());
        RiskAssessmentCondition riskAssessmentCondition = new RiskAssessmentCondition();
        List<RiskAssessment> allRiskAssessment = riskAssessmentService.listRiskAssessment(riskAssessmentCondition);
        riskAssessmentCondition = new RiskAssessmentCondition();
        riskAssessmentCondition.setDelayedMaterialRisk(0);
        List<RiskAssessment> riskAssessmentList = riskAssessmentService.listRiskAssessment(riskAssessmentCondition);
        model.addAttribute("riskAssessments",allRiskAssessment.size()-riskAssessmentList.size());
        return "main/home";
    }

    /**
     * 面包屑的首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/main.do")
    public String main(Model model, HttpSession session) {
        String account = (String) session.getAttribute(session.getId());
        User user = userService.getUserByAccount(account);
        model.addAttribute("user", user);
        return "main/main";
    }

    /**
     * 前往个人信息页面
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/gotoAdminInformation.do")
    public String gotoAdminInformation(Model model, HttpSession session) {
        //如果session中没有信息，拦截器就会拦截住
        String account = (String) session.getAttribute(session.getId());
        User user = null;
        try {
            user = userService.getUserByAccount(account);
        } catch (Exception e) {
            log.error("获取用户信息失败，账号 account：{}", account, e);
        }
        if (user == null) {
            return "error/404";
        }
        model.addAttribute("user", user);
        return "main/adminInfo";
    }

    /**
     * @param changePasswordForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping(value = "/changePassword.do")
    @ResponseBody
    public Result changePassword(@Valid ChangePasswordForm changePasswordForm, BindingResult bindingResult, HttpSession session) {
        Result result = validateChangePassword(changePasswordForm, bindingResult, (String) session.getAttribute(session.getId()));
        if (result != null) {
            return result;
        }
        User user = fillUserByChangePasswordForm(changePasswordForm);
        try {
            Long isSuccess = userService.updateUser(user);
            if (isSuccess.intValue() != 1) {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.CHANGE_PASSWORD_ERROR);
            } else {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.CHANGE_PASSWORD_SUCCESS);
            }
        } catch (Exception e) {
            log.error("修改密码时发生异常 user:{}", JSON.toJSONString(user), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }

        return result;
    }

    /**
     * 验证修改密码前的操作
     *
     * @param changePasswordForm
     * @param bindingResult
     * @param account
     * @return
     */
    private Result validateChangePassword(ChangePasswordForm changePasswordForm, BindingResult bindingResult, String account) {
        if (bindingResult.hasErrors()) {
            log.warn("提交的修改密码的表单不符合规则 changePasswordForm {}", JSON.toJSONString(changePasswordForm));
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.CHANGE_PASSWORD_LENGTH_ERROR);
        }
        if (!Objects.equals(account, changePasswordForm.getUserAccount())) {
            log.error("请求修改密码的账号和实际修改密码的账号不一致，session 中的account:{},请求修改密码的account：{}", account, changePasswordForm.getUserAccount());
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ACCOUNT_DIFFERENCE_ERROR);
        }
        User user = null;
        try {
            user = userService.getUserByAccount(changePasswordForm.getUserAccount());
        } catch (Exception e) {
            log.error("修改密码时，获取User出错，account:{}", account);
            return new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        if (user == null) {
            log.error("修改密码时根据account {},没有找到User", account);
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.CHANGE_PASSWORD_NOT_ACCOUNT);
        }
        if (!user.getPassword().equals(changePasswordForm.getOldPassword())) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.CHANGE_PASSWORD_PASSWORD_DIFFERENCE);
        }
        //这里是把数据库里的id给changePasswordForm
        changePasswordForm.setId(user.getId());
        return null;
    }

    /**
     * 填充User
     *
     * @param changePasswordForm
     * @return
     */
    private User fillUserByChangePasswordForm(ChangePasswordForm changePasswordForm) {
        User user = new User();
        user.setId(changePasswordForm.getId());
        user.setPassword(changePasswordForm.getNewPassword());
        return user;
    }

    @PostMapping(value = "/changeInformation.do")
    @ResponseBody
    public Result changeInformation(@Valid UserForm userForm, BindingResult bindingResult, HttpSession session) {
        Result result = validateChangeInformation(userForm, bindingResult, (String) session.getAttribute(session.getId()));
        if (result != null) {
            return result;
        }
        User user = fillUserByUserForm(userForm);
        try {
            Long isSuccess = userService.updateUserByAccount(user);
            if (isSuccess.intValue() != 1) {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.CHANGE_INFORMATION_ERROR);
            } else {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.CHANGE_INFORMATION_SUCCESS);
            }
        } catch (Exception e) {
            log.error("修改个人信息时发生异常 user:{}", JSON.toJSONString(user), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    private Result validateChangeInformation(UserForm userForm, BindingResult bindingResult, String account) {
        if (bindingResult.hasErrors()) {
            log.warn("提交的修改个人信息的表单不符合规则 userForm {}", JSON.toJSONString(userForm));
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.CHANGE_INFORMATION_FORM_ERROR);
        }
        if (!Objects.equals(account, userForm.getUserAccount())) {
            log.error("请求修改密码的账号和实际修改密码的账号不一致，session 中的account:{},请求修改信息的account：{}", account, userForm.getUserAccount());
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ACCOUNT_DIFFERENCE_ERROR);
        }
        return null;
    }

    private User fillUserByUserForm(UserForm userForm) {
        User user = new User();
        user.setAccount(userForm.getUserAccount());
        user.setPhone(userForm.getPhone());
        user.setEmail(userForm.getEmail());
        return user;
    }
}

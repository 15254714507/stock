package com.drug.stock.controller;

import com.alibaba.fastjson.JSON;
import com.drug.stock.constant.ErrorConstant;
import com.drug.stock.constant.SuccessConstant;
import com.drug.stock.constant.SystemConstant;
import com.drug.stock.entity.condition.UserCondition;
import com.drug.stock.entity.domain.User;
import com.drug.stock.service.UserService;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author lenovo
 */
@Slf4j
@Controller
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 前往用户信息管理页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoUserList.do")
    public String gotoUserList(Model model) {
        try {
            Long superAdminNum = userService.countUserBySuperAdmin(true);
            model.addAttribute("superAdminNum", superAdminNum);
            Long adminNum = userService.countUserBySuperAdmin(false);
            model.addAttribute("adminNum", adminNum);
        } catch (Exception e) {
            log.error("跳转到管理员列表页面时获得管理员的数量报错", e);
            return "error/404";
        }
        return "user/adminList";
    }

    @RequestMapping(value = "/gotoUserTable.do")
    public String getUserTable(Model model) {
        UserCondition userCondition = new UserCondition();
        try {
            PageInfo<User> page = userService.findUserPage(userCondition);
            model.addAttribute("page", page);
        } catch (Exception e) {
            log.error("获期用户信息列表出现错误", e);
            return "error/404";
        }
        return "user/table";
    }

    /***
     * 跳转到添加管理员页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoAddAdmin.do")
    public String gotoAddAdmin(Model model) {
        return "user/addAdmin";
    }

    /**
     * @param userForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping(value = "/saveUser.do")
    @ResponseBody
    public Result saveUser(@Valid UserForm userForm, BindingResult bindingResult, HttpSession session) {
        Result result = validateUserForm(userForm, bindingResult);
        if (result != null) {
            return result;
        }
        User user = fillUser(userForm, (String) session.getAttribute(session.getId()));
        try {
            Long isSuc = userService.insertUser(user);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.INSERT_USER_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ACCOUNT_EXIST_ERROR);
            }
        } catch (Exception e) {
            log.error("添加用户时发生异常 user:{}", JSON.toJSONString(user), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    /**
     * 校验提交的用户表单不能为空
     *
     * @param userForm
     * @param bindingResult
     * @return
     */
    private Result validateUserForm(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.INSERT_USER_FORM_ERROR);
        }
        //对userForm中没有加@notNull的加校验，不能为空
        if (userForm.getPassword() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.INSERT_USER_FORM_ERROR);
        }
        if (userForm.getAge() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.INSERT_USER_FORM_ERROR);
        }
        if (userForm.getSex() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.INSERT_USER_FORM_ERROR);
        }
        if (userForm.getSuperAdmin() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.INSERT_USER_FORM_ERROR);
        }
        return null;
    }

    /**
     * 组装user
     *
     * @param userForm
     * @param createUser
     * @return
     */
    private User fillUser(UserForm userForm, String createUser) {
        User user = new User();
        user.setAccount(userForm.getUserAccount());
        user.setPassword(userForm.getPassword());
        user.setName(userForm.getName());
        user.setAge(userForm.getAge());
        user.setSex(userForm.getSex());
        user.setPhone(userForm.getPhone());
        user.setEmail(userForm.getEmail());
        user.setSuperAdmin(userForm.getSuperAdmin());
        user.setCreateUser(createUser);
        user.setUpdateUser(createUser);
        return user;
    }

    /**
     * 前往修改用户页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoUpdateUser.do")
    public String gotoUpdateUser(@Valid @NotNull Long id, Model model) {
        User user = null;
        try {
            user = userService.getUser(id);
        } catch (Exception e) {
            log.error("跳转到修改用户信息页面时获取User数据发生错误 id:{}", id, e);
        }
        if (user == null) {
            return "error/404";
        }
        model.addAttribute("user", user);
        return "user/updateAdmin";
    }

    /**
     * 修改用户信息
     * @param userForm
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping(value = "/updateUser.do")
    @ResponseBody
    public Result updateUser(@Valid UserForm userForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors() || userForm.getId() == null) {
            return new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ACCOUNT_DIFFERENCE_ERROR);
        }
        User user = fillUser(userForm, (String) session.getAttribute(session.getId()));
        //这里需要id才加上
        user.setId(userForm.getId());
        //修改信息，不应该修改创建人
        user.setCreateUser(null);
        Result result = null;
        try {
            Long isSuc = userService.updateUser(user);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.CHANGE_INFORMATION_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.ACCOUNT_DIFFERENCE_ERROR);
            }
        } catch (Exception e) {
            log.error("修改用户信息出错 user:{}", JSON.toJSONString(user), e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }

    @PostMapping(value = "/deleteUser.do")
    @ResponseBody
    public Result deleteUser(@Valid @NotNull Long id) {
        Result result = null;
        try {
            Long isSuc = userService.deleteUser(id);
            if (isSuc == 1) {
                result = new Result(SuccessConstant.SUCCESS_CODE, SuccessConstant.DELETE_USER_SUCCESS);
            } else {
                result = new Result(ErrorConstant.ERROR_CODE, ErrorConstant.DELETE_USER_ERROR);
            }
        } catch (Exception e) {
            log.error("删除用户出错 id:{}", id, e);
            result = new Result(SystemConstant.SYSTEM_CODE, SystemConstant.SYSTEM_ERROR);
        }
        return result;
    }
}

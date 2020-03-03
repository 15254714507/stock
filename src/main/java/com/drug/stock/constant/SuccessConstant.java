package com.drug.stock.constant;

/**
 * 成功的状态码和有关成功的信息常量
 *
 * @author lenovo
 */
public interface SuccessConstant {
    /**
     * 成功的状态码
     */
    Integer SUCCESS_CODE = 200;
    /***
     * 修改密码成功的提示信息
     */
    String CHANGE_PASSWORD_SUCCESS = "修改密码成功";
    /***
     * 修改个人信息成功的提示信息
     *
     */
    String CHANGE_INFORMATION_SUCCESS = "修改信息成功";
    /**
     *
     */
    String INSERT_USER_SUCCESS = "添加用户成功";
    /**
     * 删除用户成功的提示信息
     */
    String DELETE_USER_SUCCESS = "删除用户成功";
}

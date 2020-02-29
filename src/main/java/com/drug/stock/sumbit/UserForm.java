package com.drug.stock.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加/修改用户信息提交的表单
 *
 * @author lenovo
 */
@Data
public class UserForm {
    /**
     * 账号
     */
    @NotNull
    private String userAccount;
    /**
     * 姓名
     */
    @NotNull
    private String name;
    /**
     * 手机号
     */
    @NotNull
    private String phone;
    /***
     * 邮箱
     */
    @NotNull
    private String email;

}

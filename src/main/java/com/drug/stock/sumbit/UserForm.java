package com.drug.stock.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加/修改用户信息提交的表单
 *
 * @author lenovo
 */
@Data
public class UserForm implements Serializable {
    /**
     * 账号
     */
    @NotNull
    private String userAccount;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    @NotNull
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别，0女 1男
     */
    private Integer sex;
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
    /***
     * 是否是超级管理员
     *
     * */
    private Boolean superAdmin;
}

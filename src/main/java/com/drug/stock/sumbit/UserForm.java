package com.drug.stock.sumbit;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 登录提交的表单
 *
 * @author lenovo
 */
@Data
public class UserForm {
    /**
     * 账号，限制长度
     */
    @NotNull
    @Size(min=6, max=10)
    private String userAccount;
    /**
     * 密码，限制长度
     */
    @NotNull
    @Size(min=2, max=16)
    private String password;
}

package com.drug.stock.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 修改密码提交的表单
 *
 * @author lenovo
 */
@Data
public class ChangePasswordForm implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 要修改密码的账号
     */
    @NotNull
    private String userAccount;
    /**
     * 旧密码
     */
    @NotNull
    @Size(min = 6,max = 12)
    private String oldPassword;
    /***
     * 新密码
     */
    @NotNull
    @Size(min = 6,max = 12)
    private String newPassword;
}

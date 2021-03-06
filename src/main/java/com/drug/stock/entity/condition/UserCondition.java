package com.drug.stock.entity.condition;

import com.drug.stock.entity.page.BasePage;
import lombok.Data;

import java.util.Date;

/**
 * 账号的搜索条件
 *
 * @author lenovo
 */
@Data
public class UserCondition extends BasePage {
    /**
     * 主键
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     **/
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别，0 女  1 男
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /***
     * 是否是超级管理员
     *
     * */
    private Boolean superAdmin;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 修改者
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /***
     *是否删除
     * */
    private Boolean delete;
    /**
     * 版本
     */
    private Integer version;
}

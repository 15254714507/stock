package com.drug.stock.entity.condition;

import lombok.Data;

import java.util.Date;

/**
 * @author lenovo
 */
@Data
public class ProviderCondition {
    /**
     * 主键
     */
    private Long id;
    /***
     * 企业编码
     * */
    private String code;
    /**
     * 供应商企业名称
     * */
    private String company;
    /***
     * 负责人姓名
     * */
    private String name;
    /***
     * 联系方式
     * */
    private String phone;
    /**
     * email
     * */
    private String email;
    /**
     * 所在的城市
     * */
    private String city;
    /**
     * 详细地址
     * */
    private String address;
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

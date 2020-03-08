package com.drug.stock.entity.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author lenovo
 */
@Data
public class RiskAssessment {
    /**
     * id
     */
    private Long id;
    /**
     * 药品编码
     */
    private String drugCode;
    /**
     * 药品名称
     */
    private String drugName;
    /**
     * 贮藏方式
     */
    private String drugStorage;
    /***
     * 库房号
     */
    private Integer drugWarehouseNumber;
    /**
     * 滞料等级，0,1,2,3,4
     */
    private Integer delayedMaterialRisk;
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

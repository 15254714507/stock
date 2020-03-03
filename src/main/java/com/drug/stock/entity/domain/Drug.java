package com.drug.stock.entity.domain;

import com.drug.stock.entity.page.BasePage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 药品信息
 *
 * @author lenovo
 */
@Data
public class Drug extends BasePage implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 药品编码
     */
    private String code;
    /**
     * 药品名称
     */
    private String name;
    /**
     * 药品规格
     */
    private String specs;
    /**
     * 药品剂型
     */
    private String dosageForm;
    /***
     *  批准文号
     * */
    private String approvalNumber;
    /***
     *
     * 库存数量
     * */
    private Integer number;
    /**
     * 贮藏方式
     */
    private String storage;
    /***
     * 包装方式
     * */
    private String packaging;
    /**
     * 库房号
     */
    private Integer wareHouse;
    /**
     * 价格
     */
    private Double price;
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

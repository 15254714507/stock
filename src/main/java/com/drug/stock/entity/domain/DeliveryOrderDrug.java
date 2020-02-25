package com.drug.stock.entity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 出库单药品信息
 * @author lenovo
 */
@Data
public class DeliveryOrderDrug implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 出库单编码
     * */
    private String code;
    /**
     * 药品编码
     * */
    private String drugCode;
    /**
     * 生产批号
     * */
    private String productionLotNumber;
    /**
     * 单价
     * */
    private Double price;
    /**
     * 出货数量
     * */
    private Integer number;
    /**
     * 有效期
     * */
    private Date expireDate;
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

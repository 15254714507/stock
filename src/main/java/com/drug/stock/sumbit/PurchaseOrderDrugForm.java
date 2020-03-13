package com.drug.stock.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author lenovo
 */
@Data
public class PurchaseOrderDrugForm {

    /**
     * 主键
     */
    private Long id;
    /**
     * 进库单编码
     */
    @NotNull
    private String code;
    /**
     * 药品编码
     */
    @NotNull
    private String drugCode;
    /**
     * 药品名称
     */
    private String drugName;
    /**
     * 生产批号
     */
    @NotNull
    private String productionLotNumber;
    /**
     * 供货商的id
     */
    @NotNull
    private Long providerId;
    /**
     * 供应商名称
     */
    private String providerName;
    /**
     * 单价
     */
    @NotNull
    private Double price;
    /**
     * 进货数量
     */
    @NotNull
    private Integer number;
    /**
     * 有效期
     */
    @NotNull
    private String expireDate;
}

package com.drug.stock.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author lenovo
 */
@Data
public class DeliveryOrderDrugForm {

    /**
     * 主键
     */
    private Long id;
    /**
     * 出库单编码
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
     * 单价
     */
    private Double price;
    /**
     * 出货数量
     */
    @NotNull
    private Integer number;
}

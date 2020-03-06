package com.drug.stock.sumbit;

import lombok.Data;

/**
 * @author lenovo
 */
@Data
public class PurchaseOrderForm {
    /**
     * id
     */
    private Long id;
    /**
     * 订单的说明
     */
    private String description;
}

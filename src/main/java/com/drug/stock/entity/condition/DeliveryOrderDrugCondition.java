package com.drug.stock.entity.condition;

import com.drug.stock.entity.page.BasePage;
import lombok.Data;

import java.util.Date;

/**
 * @author lenovo
 */
@Data
public class DeliveryOrderDrugCondition extends BasePage {

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
     * 药品名称
     * */
    private String drugName;
    /**
     * 单价
     * */
    private Double price;
    /**
     * 出货数量
     * */
    private Integer number;
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

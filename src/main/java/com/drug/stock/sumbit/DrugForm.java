package com.drug.stock.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 添加药品提交的表单
 *
 * @author lenovo
 */
@Data
public class DrugForm implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 药品编码
     */
    @NotNull
    private String code;
    /**
     * 药品名称
     */
    @NotNull
    private String name;
    /**
     * 药品规格
     */
    @NotNull
    private String specs;
    /**
     * 药品剂型
     */
    @NotNull
    private String dosageForm;
    /***
     *  批准文号
     * */
    @NotNull
    private String approvalNumber;
    /***
     *
     * 库存数量
     * */
    private Integer number;
    /**
     * 贮藏方式
     */
    @NotNull
    private String storage;
    /***
     * 包装方式
     * */
    @NotNull
    private String packaging;
    /**
     * 库房号
     */
    @NotNull
    private Integer wareHouse;
}

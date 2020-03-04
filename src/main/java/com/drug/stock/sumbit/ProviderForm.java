package com.drug.stock.sumbit;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lenovo
 */
@Data
public class ProviderForm implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /***
     * 企业编码
     * */
    @NotNull
    private String code;
    /**
     * 供应商企业名称
     */
    @NotNull
    private String company;
    /***
     * 负责人姓名
     * */
    @NotNull
    private String name;
    /***
     * 联系方式
     * */
    @NotNull
    private String phone;
    /**
     * email
     */
    @NotNull
    private String email;
    /**
     * 所在的城市
     */
    @NotNull
    private String city;
    /**
     * 详细地址
     */
    @NotNull
    private String address;

}

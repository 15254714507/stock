package com.drug.stock.entity.condition;

import com.drug.stock.entity.page.BasePage;
import lombok.Data;

import java.util.Date;

/**
 * @author lenovo
 */
@Data
public class OverdueDrugCondition extends BasePage {
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
     * 药品规格
     */
    private String drugSpecs;
    /**
     * 过期时间
     */
    private Date expireDate;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 处理方式
     */
    private String processMode;
    /**
     * 状态
     */
    private Boolean status;
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

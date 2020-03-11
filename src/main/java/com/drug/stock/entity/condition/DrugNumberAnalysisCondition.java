package com.drug.stock.entity.condition;

import com.drug.stock.entity.page.BasePage;
import lombok.Data;

import java.util.Date;

/**
 * @author lenovo
 */
@Data
public class DrugNumberAnalysisCondition extends BasePage {
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
     * 库存
     */
    private Integer number;
    /**
     * 请购量
     */
    private Integer requisitionQuantity;
    /**
     * 一个月前的用量
     */
    private Integer oneAgoMonthTotal;
    /**
     * 两个月前的用量
     */
    private Integer twoAgoMonthTotal;
    /**
     * 三个月前的用量
     */
    private Integer threeAgoMonthTotal;
    /**
     * 四个月前的用量
     */
    private Integer fourAgoMonthTotal;
    /**
     * 五个月前的用量
     */
    private Integer fiveAgoMonthTotal;
    /**
     * 六个月前的用量
     */
    private Integer sixAgoMonthTotal;
    /**
     * 半年总量
     */
    private Integer halfTotal;
    /**
     * 平均月用量
     */
    private Integer avgDosage;
    /**
     * 预估月用量
     */
    private Integer estimationDosage;
    /**
     * 预估使用月
     */
    private Double estimationMonth;
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

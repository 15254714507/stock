package com.drug.stock.entity.condition;

import com.drug.stock.entity.page.BasePage;
import lombok.Data;

import java.util.Date;

/**
 * @author lenovo
 */
@Data
public class DeliveryOrderCondition extends BasePage {

    /**
     * 主键
     */
    private Long id;
    /**
     * 编码
     */
    private String code;
    /**
     * 负责人账号
     */
    private String userAccount;
    /**
     * 负责人姓名
     * */
    private String userName;
    /**
     * 说明
     */
    private String description;
    /**
     * 是否已发布的状态 ，0未发布 1已发布
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

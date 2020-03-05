package com.drug.stock.entity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 出库单信息
 *
 * @author lenovo
 */
@Data
public class DeliveryOrder implements Serializable {

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
     */
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

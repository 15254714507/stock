package com.drug.stock.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 时间段
 *
 * @author lenovo
 */
@Data
public class BetweenTime {
    /**
     * 开始时间
     */
    private Date startTime;
    /***
     * 结束时间
     */
    private Date endTime;
}

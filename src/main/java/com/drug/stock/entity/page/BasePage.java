package com.drug.stock.entity.page;

import lombok.Data;
/**
 * @author lenovo
 */
@Data
public class BasePage {
    /**
     * 主键
     */
    private Long id;

    /**
     * 页数，首页是第一页
     */
    private Integer page = 1;
    /**
     * 每一页有多少条数据，设置为最大值，所有数据都放前端，让前端分页去
     */
    private Integer rows = Integer.MAX_VALUE;
}

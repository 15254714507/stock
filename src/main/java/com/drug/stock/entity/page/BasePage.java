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
     * 每一页有多少条数据，默认10条
     */
    private Integer rows = 10;
}

package com.drug.stock.until;

import lombok.Data;

/**
 * 封装的返回信息对象
 *
 * @author lenovo
 */
@Data
public class Result {
    /***
     * 是否成功的标志
     */
    private Boolean success;
    /**
     * 返回前端的信息提示
     */
    private String msg;

    /***
     * 返回的对象
     */
    private Object object;
}
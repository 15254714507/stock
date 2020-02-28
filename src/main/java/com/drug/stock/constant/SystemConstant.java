package com.drug.stock.constant;

/**
 * 服务端在处理问题时发生异常(出现报错)
 *
 * @author lenovo
 */

public interface SystemConstant {
    /**
     * 服务端发生异常返回的状态码
     */
    Integer SYSTEM_CODE = 500;
    /**
     * 返回的错误信息
     */
    String SYSTEM_ERROR = "系统错误，请刷新后重试";
}

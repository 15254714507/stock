package com.drug.stock.constant;


/**
 * 错误的状态码与错误信息常量
 *
 * @author lenovo
 */
public interface ErrorConstant {
    /**
     * 服务端接收了请求，但是因为种种原因不能不能执行此次请求
     */
    Integer ERROR_CODE = 403;
    /**
     * 修改的密码长度不符合要求
     */
    String CHANGE_PASSWORD_LENGTH_ERROR = "请确认密码的长度是否符合要求";
    /**
     * 修改密码时如果请求修改的账号和当前账号不一致，返回此错误信息
     */
    String CHANGE_PASSWORD_ACCOUNT_DIFFERENCE_ERROR = "要修改密码的账号不一致，请刷新后重试";
    /***
     * 修改密码时没有在数据库中找到此账号
     */
    String CHANGE_PASSWORD_NOT_ACCOUNT = "没有此账号，请联系运营人员";
    /**
     * 修改密码时输入了旧密码，当旧密码不一致时出现和这个错误
     */
    String CHANGE_PASSWORD_PASSWORD_DIFFERENCE = "输入旧密码错误，请重新输入";

    /**
     * 修改密码过程中如果出现问题就会提示
     */
    String CHANGE_PASSWORD_ERROR = "修改密码失败,请重新操作";
}

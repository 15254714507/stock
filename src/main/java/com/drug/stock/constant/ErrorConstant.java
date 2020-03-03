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
     * 当提交的表单和session中的account不一致时返回下列错误
     */
    String ACCOUNT_DIFFERENCE_ERROR = "要修改账号出现错误，请刷新后重试";
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
    /**
     * 当修改个人信息时提交表单不符合规则时
     */
    String CHANGE_INFORMATION_FORM_ERROR = "修改的信息不符合规则，请重新修改";
    /**
     * 修改数据库失败，返回结果为0
     */
    String CHANGE_INFORMATION_ERROR = "修改个人信息失败，请重新操作";
    /**
     * 添加user时对提交的表单进行校验不符合规则的返回错误信息
     */
    String INSERT_USER_FORM_ERROR = "添加用户的信息填写错误，请认真填写";
    /**
     * 添加失败，账号已存在
     */
    String ACCOUNT_EXIST_ERROR = "添加失败，请确认账号是否唯一";
    /**
     * 删除用户失败的信息
     */
    String DELETE_USER_ERROR = "删除用户失败，请刷新重试";
    /**
     * 当添加药品时提交的表单不符合规则，返回的错误信息
     */
    String SAVE_DRUG_FORM_ERROR = "添加药品信息不符合规则，请重新提交";
    /**
     * 添加药品时查到数据库中有此编码了，返回的错误信息
     */
    String SAVE_DRUG_CODE_EXIST = "药品编码已存在，请修改后重试";
}

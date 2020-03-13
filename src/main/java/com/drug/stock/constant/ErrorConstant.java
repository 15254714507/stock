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
    /**
     * 当修改药品时，查到数据库没有此药品
     */
    String UPDATE_DRUG_CODE_NOT = "要修改的药品不存在，请刷新后重试";
    /**
     * 删除药品时没有查到数据库中有此药品
     */
    String DELETE_DRUG_ERROR_NOT_CODE = "删除药品失败，请确认此药品存在";
    /**
     * 提交添加供应商的信息form表单没有通过校验
     */
    String PROVIDER_FORM_ERROR = "保存的供应商信息不符合要求";
    /**
     * 添加供应商时查到已经有此供应商编码了
     */
    String SAVE_PROVIDER_CODE_EXIST = "供应商编码已存在，请修改后重试";

    /**
     * 提交修改供应商的信息form表单没有通过校验
     */
    String UPDATE_PROVIDER_FORM_ERROR = "修改的供应商信息不符合要求";
    /**
     * 修改供应商信息时没有查到此供应商
     */
    String UPDATE_PROVIDER_CODE_NOT = "要修改的供应商不存在，请刷新后重试";
    /**
     * 删除供应商时没有在数据库中查到
     */
    String DELETE_PROVIDER_NOT = "要删除的供应商不存在,请刷新后重试";
    /**
     * 要发布的订单不存在
     */
    String ORDER_NOT = "要发布的的订单不存在,请刷新后重试";
    /**
     * 当提交的修改出入库单不存在或者id不存在时返回的错误信息
     */
    String ORDER_FORM_ERROR = "提交的不符合规则，请重新提交";
    /**
     * 删除订单但是没有重找到此订单
     */
    String DELETE_ORDER_NOT = "没有此订单，请刷新后重试";
    /**
     * 入库药品添加提交的表单
     */
    String PURCHASE_ORDER_FORM_ERROR = "入库药品填写的格式不正确";
    /**
     * 查询药品时返回为空时
     */
    String NOT_DRUG = "没有此药品";
    /**
     * 查询供应商返回为空时
     */
    String NOT_PROVIDER = "没有此供应商";
    /**
     * 添加入库单药品时查到此入库单已存在此药品
     */
    String PURCHASE_ORDER_DRUG_EXIST = "此药品在此入库单中已存在";
    /**
     * 当字符串日期转换成日期格式时出现问题就会返回这个错误
     */
    String EXPIRE_DATE_TO_DATE = "日期格式不正确";
    /**
     * 入库单没有查询到此药品返回的信息
     */
    String NOT_PURCHASE_ORDER_DRUG = "此入库单中没有此药品信息";
}

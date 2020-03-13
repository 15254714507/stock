package com.drug.stock.constant;

/**
 * 成功的状态码和有关成功的信息常量
 *
 * @author lenovo
 */
public interface SuccessConstant {
    /**
     * 成功的状态码
     */
    Integer SUCCESS_CODE = 200;
    /***
     * 修改密码成功的提示信息
     */
    String CHANGE_PASSWORD_SUCCESS = "修改密码成功";
    /***
     * 修改个人信息成功的提示信息
     *
     */
    String CHANGE_INFORMATION_SUCCESS = "修改信息成功";
    /**
     *
     */
    String INSERT_USER_SUCCESS = "添加用户成功";
    /**
     * 删除用户成功的提示信息
     */
    String DELETE_USER_SUCCESS = "删除用户成功";
    /**
     * 添加药品成功的提示信息
     */
    String INSERT_DRUG_SUCCESS = "添加药品成功";
    /***
     * 删除药品成功
     */
    String DELETE_DRUG_SUCCESS = "删除药品成功";
    /**
     * 添加供应商成功返回的信息
     */
    String INSERT_PROVIDER_SUCCESS = "添加供应商成功";
    /**
     * 修改供应商信息成功后返回的信息
     */
    String UPDATE_PROVIDER_SUCCESS = "修改供应商信息成功";
    /***
     * 删除供应商成功返回的信息
     */
    String DELETE_PROVIDER_SUCCESS = "删除供应商成功";
    /**
     * 修改订单信息成功后返回的
     */
    String UPDATE_PURCHASE_ORDER_SUCCESS = "修改入库单信息成功";
    /**
     * 修改入库单状态为已发布
     */
    String PURCHASE_ORDER_PUBLISH_SUCCESS = "此入库单状态已修改为已发布";
    /**
     * 添加入库单成功后返回的信息
     */
    String SAVE_PURCHASE_ORDER_SUCCESS = "创建新入库单成功";
    /**
     * 删除入库单成功
     */
    String DELETE_PURCHASE_ORDER_SUCCESS = "删除入库单成功";
    /**
     * 修改出库单信息成功后返回的
     */
    String UPDATE_DELIVERY_ORDER_SUCCESS = "修改出库单信息成功";
    /**
     * 修改出库单状态为已发布
     */
    String DELIVERY_ORDER_PUBLISH_SUCCESS = "此出库单状态已修改为已发布";
    /**
     * 添加出库单成功后返回的信息
     */
    String SAVE_DELIVERY_ORDER_SUCCESS = "创建新出库单成功";
    /**
     * 删除出库单成功
     */
    String DELETE_DELIVERY_ORDER_SUCCESS = "删除出库单成功";
    /**
     * 入库单添加药品成功返回的信息
     */
    String SAVE_PURCHASE_ORDER_DRUG_SUCCESS = "入库单添加药品成功";
    /**
     *  修改入库单药品成功返回的信息
     */
    String UPDATE_PURCHASE_ORDER_DRUG_SUCCESS = "修改药品成功";
}

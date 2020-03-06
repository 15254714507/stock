package com.drug.stock.until;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成订单编号的工厂类
 *
 * @author lenovo
 */
public class OrderCodeFactory {
    /**
     * 入库单的首字母
     */
    private static final String PURCHASE_ORDER_CODE = "P";
    /**
     * 出库单的首字母
     */
    private static final String DELIVERY_ORDER_CODE = "D";

    /**
     * 获得入库单的编码
     *
     * @return 首字母+日期+两位的随机数
     */
    public static String getPurchaseOrderCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return PURCHASE_ORDER_CODE + sdf.format(new Date()) + (int) (1 + Math.random() * (10)) + (int) (1 + Math.random() * (10));
    }

    /**
     * 获得出库单编码
     *
     * @return 首字母+日期+两位的随机数
     */
    public static String getDeliveryOrderCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return DELIVERY_ORDER_CODE + sdf.format(new Date()) + (int) (1 + Math.random() * (10)) + (int) (1 + Math.random() * (10));
    }
}

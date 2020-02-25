package com.drug.stock.dao;

import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.exception.DaoException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface PurchaseOrderDao {
    /**
     * 根据id获得订单信息
     *
     * @param id
     * @return
     */
    public PurchaseOrder getPurchaseOrder(Long id);

    /**
     * 添加订单信息
     *
     * @param purchaseOrder
     * @return
     */
    public Long insertPurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 修改订单上的信息
     *
     * @param purchaseOrder
     * @return
     */
    public Long updatePurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 删除订单信息
     *
     * @param id
     * @return
     */
    public Long deletePurchaseOrder(Long id);

    /**
     * 根据订单编码获得订单信息
     *
     * @param code
     * @return
     */
    public PurchaseOrder getPurchaseOrderByCode(String code);

    /**
     * 获得订单的集合
     *
     * @param purchaseOrderCondition
     * @return
     */
    public List<PurchaseOrder> listPurchaseOrder(PurchaseOrderCondition purchaseOrderCondition);

    /**
     * 根据code查询订单是否存在(一般用于查询此订单是否存在)
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public Long countPurchaseOrderByCode(String code);

}

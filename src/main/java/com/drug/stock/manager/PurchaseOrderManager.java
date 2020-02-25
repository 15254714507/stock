package com.drug.stock.manager;

import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.exception.DaoException;

import java.util.List;

/**
 * @author lenovo
 */
public interface PurchaseOrderManager {
    /**
     * 根据id获得订单信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public PurchaseOrder getPurchaseOrder(Long id) throws DaoException;

    /**
     * 添加订单信息
     *
     * @param purchaseOrder
     * @return
     * @throws DaoException
     */
    public Long insertPurchaseOrder(PurchaseOrder purchaseOrder) throws DaoException;

    /**
     * 修改订单上的信息
     *
     * @param purchaseOrder
     * @return
     * @throws DaoException
     */
    public Long updatePurchaseOrder(PurchaseOrder purchaseOrder) throws DaoException;

    /**
     * 删除订单信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deletePurchaseOrder(Long id) throws DaoException;

    /**
     * 根据订单编码获得订单信息
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public PurchaseOrder getPurchaseOrderByCode(String code) throws DaoException;

    /**
     * 获得订单的集合
     *
     * @param purchaseOrderCondition
     * @return
     * @throws DaoException
     */
    public List<PurchaseOrder> listPurchaseOrder(PurchaseOrderCondition purchaseOrderCondition) throws DaoException;

    /**
     * 查询code的订单的数量(一般用于查询此订单是否存在)
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public Long countPurchaseOrderByCode(String code) throws DaoException;
}

package com.drug.stock.manager;

import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.exception.DaoException;

import java.util.List;

/**
 * @author lenovo
 */
public interface PurchaseOrderDrugManager {
    /**
     * 获得入库单药品信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public PurchaseOrderDrug getPurchaseOrderDrug(Long id) throws DaoException;

    /**
     * 添加入库单药品信息
     *
     * @param purchaseOrderDrug
     * @return
     * @throws DaoException
     */
    public Long insertPurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug) throws DaoException;

    /**
     * 修改入库单药品信息
     *
     * @param purchaseOrderDrug
     * @return
     * @throws DaoException
     */
    public Long updatePurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug) throws DaoException;

    /**
     * 删除入库单药品信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deletePurchaseOrderDrug(Long id) throws DaoException;

    /***
     * 根据code获得入库单药品信息
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public PurchaseOrderDrug getPurchaseOrderDrugByCode(String code) throws DaoException;

    /**
     * 获得入库单药品的集合
     *
     * @param purchaseOrderDrugCondition
     * @return
     * @throws DaoException
     */
    public List<PurchaseOrderDrug> listPurchaseOrderDrug(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException;

    /***
     * 根据code统计数量
     * @param code
     * @return
     * @throws DaoException
     */
    public Long countPurchaseOrderDrugByCode(String code) throws DaoException;

}

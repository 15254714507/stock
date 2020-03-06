package com.drug.stock.service;

import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.exception.DaoException;

import java.util.List;

/**
 * @author lenovo
 */
public interface PurchaseOrderDrugService {
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
     * 根据code和drug为唯一索引获得入库单药品信息
     *
     * @param code
     * @param drugCode
     * @return
     * @throws DaoException
     */
    public PurchaseOrderDrug getPurchaseOrderDrugByCodeAndDrugCode(String code,String drugCode) throws DaoException;

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
    public Long countPurchaseOrderDrugByCodeAndDrugCode(String code,String drugCode) throws DaoException;
}

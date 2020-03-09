package com.drug.stock.manager;

import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.exception.DaoException;
import com.github.pagehelper.PageInfo;

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
     * 根据code和drug为唯一索引获得入库单药品信息
     *
     * @param code
     * @param drugCode
     * @return
     * @throws DaoException
     */
    public PurchaseOrderDrug getPurchaseOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException;

    /**
     * 获得入库单药品的集合
     *
     * @param purchaseOrderDrugCondition
     * @return
     * @throws DaoException
     */
    public List<PurchaseOrderDrug> listPurchaseOrderDrug(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException;

    /***
     * 根据code和drugCode为唯一索引统计数量
     * @param code
     * @param drugCode
     * @return
     * @throws DaoException
     */
    public Long countPurchaseOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException;

    /**
     * 获得订单表药品的分页信息
     *
     * @param purchaseOrderDrugCondition
     * @return
     * @throws DaoException
     */
    public PageInfo<PurchaseOrderDrug> findPurchaseOrderDrugPage(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException;

    /**
     * 查询没过期的药品的数量
     *
     * @param purchaseOrderDrugCondition
     * @return
     * @throws DaoException
     */
    public List<PurchaseOrderDrug> listNotOverdueDrug(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException;

}

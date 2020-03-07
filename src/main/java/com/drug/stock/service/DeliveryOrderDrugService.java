package com.drug.stock.service;

import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lenovo
 */
public interface DeliveryOrderDrugService {
    /**
     * 获得出库单药品信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public DeliveryOrderDrug getDeliveryOrderDrug(Long id) throws DaoException;

    /**
     * 添加出库单药品信息
     *
     * @param deliveryOrderDrug
     * @return
     * @throws DaoException
     */
    public Long insertDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug) throws DaoException;

    /**
     * 修改出库单药品信息
     *
     * @param deliveryOrderDrug
     * @return
     * @throws DaoException
     */
    public Long updateDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug) throws DaoException;

    /**
     * 删除出库单药品信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteDeliveryOrderDrug(Long id) throws DaoException;

    /***
     * 根据code获得出库单药品信息
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public DeliveryOrderDrug getDeliveryOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException;

    /**
     * 获得出库单药品的集合
     *
     * @param deliveryOrderDrugCondition
     * @return
     * @throws DaoException
     */
    public List<DeliveryOrderDrug> listDeliveryOrderDrug(DeliveryOrderDrugCondition deliveryOrderDrugCondition) throws DaoException;

    /***
     * 根据code统计数量
     * @param code
     * @param drugCode
     * @return
     * @throws DaoException
     */
    public Long countDeliveryOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException;

    /**
     * 获得订单药品信息的分页数据
      * @param deliveryOrderDrugCondition
     * @return
     * @throws DaoException
     */
    public PageInfo<DeliveryOrderDrug> findDeliveryOrderDrugPage(DeliveryOrderDrugCondition deliveryOrderDrugCondition) throws DaoException;
}

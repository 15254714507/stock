package com.drug.stock.service;

import com.drug.stock.entity.condition.DeliveryOrderCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.drug.stock.entity.dto.BetweenTime;
import com.drug.stock.exception.DaoException;
import com.drug.stock.exception.ServiceException;
import com.drug.stock.until.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lenovo
 */
public interface DeliveryOrderService {
    /**
     * 根据id获得出库单信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public DeliveryOrder getDeliveryOrder(Long id) throws DaoException;

    /**
     * 添加出库单信息
     *
     * @param deliveryOrder
     * @return
     * @throws DaoException
     */
    public Long insertDeliveryOrder(DeliveryOrder deliveryOrder) throws DaoException;

    /**
     * 修改出库单上的信息
     *
     * @param deliveryOrder
     * @return
     * @throws DaoException
     */
    public Long updateDeliveryOrder(DeliveryOrder deliveryOrder) throws DaoException;

    /**
     * 删除出库单信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteDeliveryOrder(Long id) throws DaoException;

    /**
     * 根据出库单编码获得订单信息
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public DeliveryOrder getDeliveryOrderByCode(String code) throws DaoException;

    /**
     * 获得出库单的集合
     *
     * @param deliveryOrderCondition
     * @return
     * @throws DaoException
     */
    public List<DeliveryOrder> listDeliveryOrder(DeliveryOrderCondition deliveryOrderCondition) throws DaoException;

    /**
     * 根据code查询出库单是否存在(一般用于查询此订单是否存在)
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public Long countDeliveryOrderByCode(String code) throws DaoException;

    /**
     * DeliveryOrder的分页数据
     *
     * @param deliveryOrderCondition
     * @return
     * @throws DaoException
     */
    public PageInfo<DeliveryOrder> findDeliveryOrderPage(DeliveryOrderCondition deliveryOrderCondition) throws DaoException;

    /**
     * 根据时间的范围返回出库单集合
     *
     * @param betweenTime 时间的范围
     * @return
     * @throws DaoException
     */
    public List<DeliveryOrder> listStartTimeToEndTime(BetweenTime betweenTime) throws DaoException;

    /**
     * 出库单发布服务
     *
     * @param deliveryOrder
     * @return
     * @throws ServiceException
     */
    public Result publishDeliveryOrder(DeliveryOrder deliveryOrder) throws ServiceException;
}

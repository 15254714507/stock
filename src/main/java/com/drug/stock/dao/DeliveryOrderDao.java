package com.drug.stock.dao;

import com.drug.stock.entity.condition.DeliveryOrderCondition;
import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.entity.dto.BetweenTime;
import com.drug.stock.exception.DaoException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface DeliveryOrderDao {
    /**
     * 根据id获得出库单信息
     *
     * @param id
     * @return
     */
    public DeliveryOrder getDeliveryOrder(Long id);

    /**
     * 添加出库单信息
     *
     * @param deliveryOrder
     * @return
     */
    public Long insertDeliveryOrder(DeliveryOrder deliveryOrder);

    /**
     * 修改出库单上的信息
     *
     * @param deliveryOrder
     * @return
     */
    public Long updateDeliveryOrder(DeliveryOrder deliveryOrder);

    /**
     * 删除出库单信息
     *
     * @param id
     * @return
     */
    public Long deleteDeliveryOrder(Long id);

    /**
     * 根据出库单编码获得订单信息
     *
     * @param code
     * @return
     */
    public DeliveryOrder getDeliveryOrderByCode(String code);

    /**
     * 获得出库单的集合
     *
     * @param deliveryOrderCondition
     * @return
     */
    public List<DeliveryOrder> listDeliveryOrder(DeliveryOrderCondition deliveryOrderCondition);

    /**
     * 根据code查询出库单是否存在(一般用于查询此订单是否存在)
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public Long countDeliveryOrderByCode(String code);

    /**
     * 根据时间的范围返回出库单集合
     *
     * @param betweenTime 时间的范围
     * @return
     */
    public List<DeliveryOrder> listStartTimeToEndTime(BetweenTime betweenTime);
}

package com.drug.stock.dao;

import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface DeliveryOrderDrugDao {
    /**
     * 获得出库单药品信息
     *
     * @param id
     * @return
     */
    public DeliveryOrderDrug getDeliveryOrderDrug(Long id);

    /**
     * 添加出库单药品信息
     *
     * @param deliveryOrderDrug
     * @return
     */
    public Long insertDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug);

    /**
     * 修改出库单药品信息
     *
     * @param deliveryOrderDrug
     * @return
     */
    public Long updateDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug);

    /**
     * 删除出库单药品信息
     *
     * @param id
     * @return
     */
    public Long deleteDeliveryOrderDrug(Long id);

    /***
     * 根据code获得出库单药品信息
     *
     * @param code
     * @return
     */
    public DeliveryOrderDrug getDeliveryOrderDrugByCode(String code);

    /**
     * 获得出库单药品的集合
     *
     * @param deliveryOrderDrugCondition
     * @return
     */
    public List<DeliveryOrderDrug> listDeliveryOrderDrug(DeliveryOrderDrugCondition deliveryOrderDrugCondition);

    /***
     * 根据code统计数量
     * @param code
     * @return
     */
    public Long countDeliveryOrderDrugByCode(String code);
}

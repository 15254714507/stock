package com.drug.stock.dao;

import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface PurchaseOrderDrugDao {
    /**
     * 获得入库单药品信息
     *
     * @param id
     * @return
     */
    public PurchaseOrderDrug getPurchaseOrderDrug(Long id);

    /**
     * 添加入库单药品信息
     *
     * @param purchaseOrderDrug
     * @return
     */
    public Long insertPurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug);

    /**
     * 修改入库单药品信息
     *
     * @param purchaseOrderDrug
     * @return
     */
    public Long updatePurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug);

    /**
     * 删除入库单药品信息
     *
     * @param id
     * @return
     */
    public Long deletePurchaseOrderDrug(Long id);

    /***
     * 根据code和drug为唯一索引获得入库单药品信息
     *
     * @param code
     * @param drugCode
     * @return
     */
    public PurchaseOrderDrug getPurchaseOrderDrugByCodeAndDrugCode(String code, String drugCode);

    /**
     * 获得入库单药品的集合
     *
     * @param purchaseOrderDrugCondition
     * @return
     */
    public List<PurchaseOrderDrug> listPurchaseOrderDrug(PurchaseOrderDrugCondition purchaseOrderDrugCondition);

    /***
     * 根据code和drugCode为唯一统计数量
     * @param code
     * @param drugCode
     * @return
     */
    public Long countPurchaseOrderDrugByCodeAndDrugCode(String code, String drugCode);


}

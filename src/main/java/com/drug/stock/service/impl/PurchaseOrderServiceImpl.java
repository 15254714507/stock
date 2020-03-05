package com.drug.stock.service.impl;

import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.PurchaseOrderManager;
import com.drug.stock.service.PurchaseOrderService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Resource
    PurchaseOrderManager purchaseOrderManager;

    @Override
    public PurchaseOrder getPurchaseOrder(Long id) throws DaoException {
        return purchaseOrderManager.getPurchaseOrder(id);
    }

    @Override
    public Long insertPurchaseOrder(PurchaseOrder purchaseOrder) throws DaoException {
        return purchaseOrderManager.insertPurchaseOrder(purchaseOrder);
    }

    @Override
    public Long updatePurchaseOrder(PurchaseOrder purchaseOrder) throws DaoException {
        return purchaseOrderManager.updatePurchaseOrder(purchaseOrder);
    }

    @Override
    public Long deletePurchaseOrder(Long id) throws DaoException {
        return purchaseOrderManager.deletePurchaseOrder(id);
    }

    @Override
    public PurchaseOrder getPurchaseOrderByCode(String code) throws DaoException {
        return purchaseOrderManager.getPurchaseOrderByCode(code);
    }

    @Override
    public List<PurchaseOrder> listPurchaseOrder(PurchaseOrderCondition purchaseOrderCondition) throws DaoException {
        return purchaseOrderManager.listPurchaseOrder(purchaseOrderCondition);
    }

    @Override
    public Long countPurchaseOrderByCode(String code) throws DaoException {
        return purchaseOrderManager.countPurchaseOrderByCode(code);
    }

    @Override
    public PageInfo<PurchaseOrder> findPurchaseOrderPage(PurchaseOrderCondition purchaseOrderCondition) throws DaoException {
        return purchaseOrderManager.findPurchaseOrderPage(purchaseOrderCondition);
    }
}

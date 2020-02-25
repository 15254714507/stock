package com.drug.stock.service.impl;

import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.PurchaseOrderDrugManager;
import com.drug.stock.service.PurchaseOrderDrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("purchaseOrderDrugService")
public class PurchaseOrderDrugServiceImpl implements PurchaseOrderDrugService {
    @Resource
    PurchaseOrderDrugManager purchaseOrderDrugManager;

    @Override
    public PurchaseOrderDrug getPurchaseOrderDrug(Long id) throws DaoException {
        return purchaseOrderDrugManager.getPurchaseOrderDrug(id);
    }

    @Override
    public Long insertPurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug) throws DaoException {
        return purchaseOrderDrugManager.insertPurchaseOrderDrug(purchaseOrderDrug);
    }

    @Override
    public Long updatePurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug) throws DaoException {
        return purchaseOrderDrugManager.updatePurchaseOrderDrug(purchaseOrderDrug);
    }

    @Override
    public Long deletePurchaseOrderDrug(Long id) throws DaoException {
        return purchaseOrderDrugManager.deletePurchaseOrderDrug(id);
    }

    @Override
    public PurchaseOrderDrug getPurchaseOrderDrugByCode(String code) throws DaoException {
        return purchaseOrderDrugManager.getPurchaseOrderDrugByCode(code);
    }

    @Override
    public List<PurchaseOrderDrug> listPurchaseOrderDrug(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException {
        return purchaseOrderDrugManager.listPurchaseOrderDrug(purchaseOrderDrugCondition);
    }

    @Override
    public Long countPurchaseOrderDrugByCode(String code) throws DaoException {
        return purchaseOrderDrugManager.countPurchaseOrderDrugByCode(code);
    }
}

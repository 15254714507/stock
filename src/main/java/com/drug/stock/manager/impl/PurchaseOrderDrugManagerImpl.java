package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.PurchaseOrderDrugDao;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.PurchaseOrderDrugManager;
import com.drug.stock.until.TimestampFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Component("purchaseOrderDrugManager")
public class PurchaseOrderDrugManagerImpl implements PurchaseOrderDrugManager {
    @Resource
    PurchaseOrderDrugDao purchaseOrderDrugDao;

    @Override
    public PurchaseOrderDrug getPurchaseOrderDrug(Long id) throws DaoException {
        try {
            return purchaseOrderDrugDao.getPurchaseOrderDrug(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertPurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug) throws DaoException {
        if (countPurchaseOrderDrugByCode(purchaseOrderDrug.getCode()) > 0) {
            throw new DaoException("新添加入库单药品信息已存在 purchaseOrderDrug：" + JSON.toJSONString(purchaseOrderDrug));
        }
        purchaseOrderDrug.setCreateTime(TimestampFactory.getTimestamp());
        purchaseOrderDrug.setUpdateTime(purchaseOrderDrug.getCreateTime());
        try {
            return purchaseOrderDrugDao.insertPurchaseOrderDrug(purchaseOrderDrug);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updatePurchaseOrderDrug(PurchaseOrderDrug purchaseOrderDrug) throws DaoException {
        if (getPurchaseOrderDrug(purchaseOrderDrug.getId()) == null) {
            throw new DaoException("修改的订单药品信息不存在 purchaseOrderDrug:" + JSON.toJSONString(purchaseOrderDrug));
        }
        purchaseOrderDrug.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return purchaseOrderDrugDao.updatePurchaseOrderDrug(purchaseOrderDrug);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deletePurchaseOrderDrug(Long id) throws DaoException {
        if (getPurchaseOrderDrug(id) == null) {
            throw new DaoException("将要删除的订单药品信息不存在 id：" + id);
        }
        try {
            return purchaseOrderDrugDao.deletePurchaseOrderDrug(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseOrderDrug getPurchaseOrderDrugByCode(String code) throws DaoException {
        try {
            return purchaseOrderDrugDao.getPurchaseOrderDrugByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseOrderDrug> listPurchaseOrderDrug(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException {
        try {
            return purchaseOrderDrugDao.listPurchaseOrderDrug(purchaseOrderDrugCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long countPurchaseOrderDrugByCode(String code) throws DaoException {
        try {
            return purchaseOrderDrugDao.countPurchaseOrderDrugByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}

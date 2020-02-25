package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.PurchaseOrderDao;
import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.PurchaseOrderManager;
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
@Component("purchaseOrderManager")
public class PurchaseOrderManagerImpl implements PurchaseOrderManager {
    @Resource
    PurchaseOrderDao purchaseOrderDao;

    @Override
    public PurchaseOrder getPurchaseOrder(Long id) throws DaoException {
        try {
            return purchaseOrderDao.getPurchaseOrder(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertPurchaseOrder(PurchaseOrder purchaseOrder) throws DaoException {
        if (countPurchaseOrderByCode(purchaseOrder.getCode()) > 0) {
            throw new DaoException("新添加订单已存在 purchaseOrder：" + JSON.toJSONString(purchaseOrder));
        }
        purchaseOrder.setCreateTime(TimestampFactory.getTimestamp());
        purchaseOrder.setUpdateTime(purchaseOrder.getCreateTime());
        try {
            return purchaseOrderDao.insertPurchaseOrder(purchaseOrder);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updatePurchaseOrder(PurchaseOrder purchaseOrder) throws DaoException {
        if (getPurchaseOrder(purchaseOrder.getId()) == null) {
            throw new DaoException("修改的订单信息不存在 purchaseOrder:" + JSON.toJSONString(purchaseOrder));
        }
        purchaseOrder.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return purchaseOrderDao.updatePurchaseOrder(purchaseOrder);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deletePurchaseOrder(Long id) throws DaoException {
        if (getPurchaseOrder(id) == null) {
            throw new DaoException("删除的用户不存在 id：" + id);
        }
        try {
            return purchaseOrderDao.deletePurchaseOrder(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseOrder getPurchaseOrderByCode(String code) throws DaoException {
        try {
            return purchaseOrderDao.getPurchaseOrderByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PurchaseOrder> listPurchaseOrder(PurchaseOrderCondition purchaseOrderCondition) throws DaoException {
        try {
            return purchaseOrderDao.listPurchaseOrder(purchaseOrderCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long countPurchaseOrderByCode(String code) throws DaoException {
        try {
            return purchaseOrderDao.countPurchaseOrderByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}

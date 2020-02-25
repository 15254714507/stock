package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.DeliveryOrderDao;
import com.drug.stock.entity.condition.DeliveryOrderCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DeliveryOrderManager;
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
@Component("deliveryOrderManager")
public class DeliveryOrderManagerImpl implements DeliveryOrderManager {
    @Resource
    DeliveryOrderDao deliveryOrderDao;

    @Override
    public DeliveryOrder getDeliveryOrder(Long id) throws DaoException {
        try {
            return deliveryOrderDao.getDeliveryOrder(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertDeliveryOrder(DeliveryOrder deliveryOrder) throws DaoException {
        if (countDeliveryOrderByCode(deliveryOrder.getCode()) > 0) {
            throw new DaoException("新添加出库单已存在 deliveryOrder：" + JSON.toJSONString(deliveryOrder));
        }
        deliveryOrder.setCreateTime(TimestampFactory.getTimestamp());
        deliveryOrder.setUpdateTime(deliveryOrder.getCreateTime());
        try {
            return deliveryOrderDao.insertDeliveryOrder(deliveryOrder);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateDeliveryOrder(DeliveryOrder deliveryOrder) throws DaoException {
        if (getDeliveryOrder(deliveryOrder.getId()) == null) {
            throw new DaoException("修改的出库单信息不存在 deliveryOrder:" + JSON.toJSONString(deliveryOrder));
        }
        deliveryOrder.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return deliveryOrderDao.updateDeliveryOrder(deliveryOrder);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteDeliveryOrder(Long id) throws DaoException {
        if (getDeliveryOrder(id) == null) {
            throw new DaoException("删除的出库单不存在 id：" + id);
        }
        try {
            return deliveryOrderDao.deleteDeliveryOrder(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public DeliveryOrder getDeliveryOrderByCode(String code) throws DaoException {
        try {
            return deliveryOrderDao.getDeliveryOrderByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<DeliveryOrder> listDeliveryOrder(DeliveryOrderCondition deliveryOrderCondition) throws DaoException {
        try {
            return deliveryOrderDao.listDeliveryOrder(deliveryOrderCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long countDeliveryOrderByCode(String code) throws DaoException {
        try {
            return deliveryOrderDao.countDeliveryOrderByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}

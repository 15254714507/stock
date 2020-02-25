package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.DeliveryOrderDrugDao;
import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DeliveryOrderDrugManager;
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
@Component("deliveryOrderDrugManager")
public class DeliveryOrderDrugManagerImpl implements DeliveryOrderDrugManager {
    @Resource
    DeliveryOrderDrugDao deliveryOrderDrugDao;

    @Override
    public DeliveryOrderDrug getDeliveryOrderDrug(Long id) throws DaoException {
        try {
            return deliveryOrderDrugDao.getDeliveryOrderDrug(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug) throws DaoException {
        if (countDeliveryOrderDrugByCode(deliveryOrderDrug.getCode()) > 0) {
            throw new DaoException("新添加出库药品信息已存在 deliveryOrderDrug：" + JSON.toJSONString(deliveryOrderDrug));
        }
        deliveryOrderDrug.setCreateTime(TimestampFactory.getTimestamp());
        deliveryOrderDrug.setUpdateTime(deliveryOrderDrug.getCreateTime());
        try {
            return deliveryOrderDrugDao.insertDeliveryOrderDrug(deliveryOrderDrug);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateDeliveryOrderDrug(DeliveryOrderDrug deliveryOrderDrug) throws DaoException {
        if (getDeliveryOrderDrug(deliveryOrderDrug.getId()) == null) {
            throw new DaoException("修改的出库药品信息不存在 deliveryOrderDrug:" + JSON.toJSONString(deliveryOrderDrug));
        }
        deliveryOrderDrug.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return deliveryOrderDrugDao.updateDeliveryOrderDrug(deliveryOrderDrug);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteDeliveryOrderDrug(Long id) throws DaoException {
        if (getDeliveryOrderDrug(id) == null) {
            throw new DaoException("将要删除的出库药品信息不存在 id：" + id);
        }
        try {
            return deliveryOrderDrugDao.deleteDeliveryOrderDrug(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public DeliveryOrderDrug getDeliveryOrderDrugByCode(String code) throws DaoException {
        try {
            return deliveryOrderDrugDao.getDeliveryOrderDrugByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<DeliveryOrderDrug> listDeliveryOrderDrug(DeliveryOrderDrugCondition deliveryOrderDrugCondition) throws DaoException {
        try {
            return deliveryOrderDrugDao.listDeliveryOrderDrug(deliveryOrderDrugCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long countDeliveryOrderDrugByCode(String code) throws DaoException {
        try {
            return deliveryOrderDrugDao.countDeliveryOrderDrugByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}

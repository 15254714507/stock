package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.DeliveryOrderDao;
import com.drug.stock.entity.condition.DeliveryOrderCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DeliveryOrderManager;
import com.drug.stock.until.TimestampFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
            log.warn("新添加出库单已存在 deliveryOrder：{}", JSON.toJSONString(deliveryOrder));
            return 0L;
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
            log.warn("修改的出库单信息不存在 deliveryOrder:{}", JSON.toJSONString(deliveryOrder));
            return 0L;
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
            log.warn("删除的出库单不存在 id：{}", id);
            return 0L;
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

    @Override
    public PageInfo<DeliveryOrder> findDeliveryOrderPage(DeliveryOrderCondition deliveryOrderCondition) throws DaoException {
        //参数第一个是第几页，第二个是条数，通过凭借SQL的方式,必须加判断
        if (deliveryOrderCondition.getPage() != null && deliveryOrderCondition.getRows() != null) {
            PageHelper.startPage(deliveryOrderCondition.getPage(), deliveryOrderCondition.getRows());
        }
        List<DeliveryOrder> list = null;
        try {
            list = deliveryOrderDao.listDeliveryOrder(deliveryOrderCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            //虽然不推荐使用，但是上面还是会出现特殊情况的
            PageHelper.clearPage();
        }
        PageInfo<DeliveryOrder> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

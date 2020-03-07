package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.DeliveryOrderDrugDao;
import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DeliveryOrderDrugManager;
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
        if (countDeliveryOrderDrugByCodeAndDrugCode(deliveryOrderDrug.getCode(),deliveryOrderDrug.getDrugCode()) > 0) {
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
    public DeliveryOrderDrug getDeliveryOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException {
        try {
            return deliveryOrderDrugDao.getDeliveryOrderDrugByCodeAndDrugCode(code, drugCode);
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
    public Long countDeliveryOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException {
        try {
            return deliveryOrderDrugDao.countDeliveryOrderDrugByCodeAndDrugCode(code, drugCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PageInfo<DeliveryOrderDrug> findDeliveryOrderDrugPage(DeliveryOrderDrugCondition deliveryOrderDrugCondition) throws DaoException {
        //参数第一个是第几页，第二个是条数，通过凭借SQL的方式,必须加判断
        if (deliveryOrderDrugCondition.getPage() != null && deliveryOrderDrugCondition.getRows() != null) {
            PageHelper.startPage(deliveryOrderDrugCondition.getPage(), deliveryOrderDrugCondition.getRows());
        }
        List<DeliveryOrderDrug> list = null;
        try {
            list = deliveryOrderDrugDao.listDeliveryOrderDrug(deliveryOrderDrugCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            //虽然不推荐使用，但是上面还是会出现特殊情况的
            PageHelper.clearPage();
        }
        PageInfo<DeliveryOrderDrug> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

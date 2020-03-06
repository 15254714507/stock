package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.PurchaseOrderDrugDao;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.PurchaseOrderDrugManager;
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
        if (countPurchaseOrderDrugByCodeAndDrugCode(purchaseOrderDrug.getCode(), purchaseOrderDrug.getDrugCode()) > 0) {
            log.warn("新添加入库单药品信息已存在 purchaseOrderDrug：{}", JSON.toJSONString(purchaseOrderDrug));
            return 0L;
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
            log.warn("修改的订单药品信息不存在 purchaseOrderDrug:{}", JSON.toJSONString(purchaseOrderDrug));
            return 0L;
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
            log.warn("将要删除的订单药品信息不存在 id：{}", id);
            return 0L;
        }
        try {
            return purchaseOrderDrugDao.deletePurchaseOrderDrug(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseOrderDrug getPurchaseOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException {
        try {
            return purchaseOrderDrugDao.getPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);
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
    public Long countPurchaseOrderDrugByCodeAndDrugCode(String code, String drugCode) throws DaoException {
        try {
            return purchaseOrderDrugDao.countPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PageInfo<PurchaseOrderDrug> findPurchaseOrderDrugPage(PurchaseOrderDrugCondition purchaseOrderDrugCondition) throws DaoException {
        //参数第一个是第几页，第二个是条数，通过凭借SQL的方式,必须加判断
        if (purchaseOrderDrugCondition.getPage() != null && purchaseOrderDrugCondition.getRows() != null) {
            PageHelper.startPage(purchaseOrderDrugCondition.getPage(), purchaseOrderDrugCondition.getRows());
        }
        List<PurchaseOrderDrug> list = null;
        try {
            list = purchaseOrderDrugDao.listPurchaseOrderDrug(purchaseOrderDrugCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            //虽然不推荐使用，但是上面还是会出现特殊情况的
            PageHelper.clearPage();
        }
        PageInfo<PurchaseOrderDrug> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

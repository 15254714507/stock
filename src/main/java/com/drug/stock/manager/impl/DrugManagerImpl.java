package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.DrugDao;
import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DrugManager;
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
@Component("drugManager")
public class DrugManagerImpl implements DrugManager {
    @Resource
    DrugDao drugDao;

    @Override
    public Drug getDrug(Long id) throws DaoException {
        try {
            return drugDao.getDrug(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertDrug(Drug drug) throws DaoException {
        if (countDrugByCode(drug.getCode()) > 0) {
            throw new DaoException("新添的药品已存在 drug：" + JSON.toJSONString(drug));
        }
        drug.setCreateTime(TimestampFactory.getTimestamp());
        drug.setUpdateTime(drug.getCreateTime());
        try {
            return drugDao.insertDrug(drug);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateDrug(Drug drug) throws DaoException {
        if (getDrug(drug.getId()) == null) {
            throw new DaoException("修改的药品信息不存在 drug:" + JSON.toJSONString(drug));
        }
        drug.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return drugDao.updateDrug(drug);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteDrug(Long id) throws DaoException {
        if (getDrug(id) == null) {
            throw new DaoException("将要删除的药品不存在 id：" + id);
        }
        try {
            return drugDao.deleteDrug(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public Drug getDrugByCode(String code) throws DaoException {
        try {
            return drugDao.getDrugByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Drug> listDrug(DrugCondition drugCondition) throws DaoException {
        try {
            return drugDao.listDrug(drugCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long countDrugByCode(String code) throws DaoException {
        try {
            return drugDao.countDrugByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}

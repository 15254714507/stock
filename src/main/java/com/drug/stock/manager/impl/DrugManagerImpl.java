package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.DrugDao;
import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DrugManager;
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
            log.warn("新添的药品已存在 drug{}", JSON.toJSONString(drug));
            return 0L;
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
            log.warn("修改的药品信息不存在 drug{}", JSON.toJSONString(drug));
            return 0L;
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
            log.warn("将要删除的药品不存在 id {}", id);
            return 0L;
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

    @Override
    public PageInfo<Drug> findDrugPage(DrugCondition drugCondition) throws DaoException {
        //参数第一个是第几页，第二个是条数，通过凭借SQL的方式,必须加判断
        if (drugCondition.getPage() != null && drugCondition.getRows() != null) {
            PageHelper.startPage(drugCondition.getPage(), drugCondition.getRows());
        }
        List<Drug> list = null;
        try {
            list = drugDao.listDrug(drugCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            //虽然不推荐使用，但是上面还是会出现特殊情况的
            PageHelper.clearPage();
        }
        PageInfo<Drug> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

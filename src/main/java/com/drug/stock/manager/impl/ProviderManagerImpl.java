package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.ProviderDao;
import com.drug.stock.entity.condition.ProviderCondition;
import com.drug.stock.entity.domain.Provider;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.ProviderManager;
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
@Component("providerManager")
public class ProviderManagerImpl implements ProviderManager {
    @Resource
    ProviderDao providerDao;

    @Override
    public Provider getProvider(Long id) throws DaoException {
        try {
            return providerDao.getProvider(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertProvider(Provider provider) throws DaoException {
        if (countProviderByCode(provider.getCode()) > 0) {
            throw new DaoException("新添的供应商已存在 provider：" + JSON.toJSONString(provider));
        }
        provider.setCreateTime(TimestampFactory.getTimestamp());
        provider.setUpdateTime(provider.getCreateTime());
        try {
            return providerDao.insertProvider(provider);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateProvider(Provider provider) throws DaoException {
        if (getProvider(provider.getId()) == null) {
            throw new DaoException("修改的供应商信息不存在 provider:" + JSON.toJSONString(provider));
        }
        provider.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return providerDao.updateProvider(provider);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteProvider(Long id) throws DaoException {
        if (getProvider(id) == null) {
            throw new DaoException("将要删除的供应商不存在 id：" + id);
        }
        try {
            return providerDao.deleteProvider(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public Provider getProviderByCode(String code) throws DaoException {
        try {
            return providerDao.getProviderByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Provider> listProvider(ProviderCondition providerCondition) throws DaoException {
        try {
            return providerDao.listProvider(providerCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long countProviderByCode(String code) throws DaoException {
        try {
            return providerDao.countProviderByCode(code);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}

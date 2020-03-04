package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.ProviderDao;
import com.drug.stock.entity.condition.ProviderCondition;
import com.drug.stock.entity.domain.Provider;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.ProviderManager;
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
            log.warn("新添的供应商已存在 provider：{}", JSON.toJSONString(provider));
            return 0L;
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
            log.warn("修改的供应商信息不存在 provider:{}", JSON.toJSONString(provider));
            return 0L;
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
            log.warn("将要删除的供应商不存在 id：{}", id);
            return 0L;
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

    @Override
    public PageInfo<Provider> findProviderPage(ProviderCondition providerCondition) throws DaoException {
        //参数第一个是第几页，第二个是条数，通过凭借SQL的方式,必须加判断
        if (providerCondition.getPage() != null && providerCondition.getRows() != null) {
            PageHelper.startPage(providerCondition.getPage(), providerCondition.getRows());
        }
        List<Provider> list = null;
        try {
            list = providerDao.listProvider(providerCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            //虽然不推荐使用，但是上面还是会出现特殊情况的
            PageHelper.clearPage();
        }
        PageInfo<Provider> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

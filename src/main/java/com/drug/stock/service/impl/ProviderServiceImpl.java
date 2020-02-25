package com.drug.stock.service.impl;

import com.drug.stock.entity.condition.ProviderCondition;
import com.drug.stock.entity.domain.Provider;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.ProviderManager;
import com.drug.stock.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
    @Resource
    ProviderManager providerManager;

    @Override
    public Provider getProvider(Long id) throws DaoException {
        return providerManager.getProvider(id);
    }

    @Override
    public Long insertProvider(Provider provider) throws DaoException {
        return providerManager.insertProvider(provider);
    }

    @Override
    public Long updateProvider(Provider provider) throws DaoException {
        return providerManager.updateProvider(provider);
    }

    @Override
    public Long deleteProvider(Long id) throws DaoException {
        return providerManager.deleteProvider(id);
    }

    @Override
    public Provider getProviderByCode(String code) throws DaoException {
        return providerManager.getProviderByCode(code);
    }

    @Override
    public List<Provider> listProvider(ProviderCondition providerCondition) throws DaoException {
        return providerManager.listProvider(providerCondition);
    }

    @Override
    public Long countProviderByCode(String code) throws DaoException {
        return providerManager.countProviderByCode(code);
    }
}

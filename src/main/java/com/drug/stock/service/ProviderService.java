package com.drug.stock.service;

import com.drug.stock.entity.condition.ProviderCondition;
import com.drug.stock.entity.domain.Provider;
import com.drug.stock.exception.DaoException;

import java.util.List;

/**
 * @author lenovo
 */
public interface ProviderService {
    /**
     * 获得供应商
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Provider getProvider(Long id) throws DaoException;

    /**
     * 添加供应商
     *
     * @param provider
     * @return
     * @throws DaoException
     */
    public Long insertProvider(Provider provider) throws DaoException;

    /**
     * 修改供应商信息
     *
     * @param provider
     * @return
     * @throws DaoException
     */
    public Long updateProvider(Provider provider) throws DaoException;

    /**
     * 删除供应商信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteProvider(Long id) throws DaoException;

    /**
     * 通过编码获得供应商信息
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public Provider getProviderByCode(String code) throws DaoException;

    /**
     * 根据条件返回供应商的集合
     *
     * @param providerCondition
     * @return
     * @throws DaoException
     */
    public List<Provider> listProvider(ProviderCondition providerCondition) throws DaoException;

    /**
     * 根据code查看是否有此供应商
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public Long countProviderByCode(String code) throws DaoException;
}

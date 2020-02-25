package com.drug.stock.dao;

import com.drug.stock.entity.condition.ProviderCondition;
import com.drug.stock.entity.domain.Provider;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface ProviderDao {
    /**
     * 获得供应商
     *
     * @param id
     * @return
     */
    public Provider getProvider(Long id);

    /**
     * 添加供应商
     *
     * @param provider
     * @return
     */
    public Long insertProvider(Provider provider);

    /**
     * 修改供应商信息
     *
     * @param provider
     * @return
     */
    public Long updateProvider(Provider provider);

    /**
     * 删除供应商信息
     *
     * @param id
     * @return
     */
    public Long deleteProvider(Long id);

    /**
     * 通过编码获得供应商信息
     *
     * @param code
     * @return
     */
    public Provider getProviderByCode(String code);

    /**
     * 根据条件返回供应商的集合
     *
     * @param providerCondition
     * @return
     */
    public List<Provider> listProvider(ProviderCondition providerCondition);

    /**
     * 根据code查看是否有此供应商
     *
     * @param code
     * @return
     */
    public Long countProviderByCode(String code);
}

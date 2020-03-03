package com.drug.stock.service;

import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lenovo
 */
public interface DrugService {
    /**
     * 根据Id获得药品信息
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Drug getDrug(Long id) throws DaoException;

    /**
     * 添加药品
     *
     * @param drug
     * @return
     * @throws DaoException
     */

    public Long insertDrug(Drug drug) throws DaoException;

    /***
     * 修改药品信息
     * @param drug
     * @return
     * @throws DaoException
     */
    public Long updateDrug(Drug drug) throws DaoException;

    /**
     * 删除药品
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteDrug(Long id) throws DaoException;

    /**
     * 根据药品编码获得药品信息
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public Drug getDrugByCode(String code) throws DaoException;

    /**
     * 根据条件获得药品的集合
     *
     * @param drugCondition
     * @return
     * @throws DaoException
     */
    public List<Drug> listDrug(DrugCondition drugCondition) throws DaoException;

    /**
     * 根据药品编码获得药品的数量
     *
     * @param code
     * @return
     * @throws DaoException
     */
    public Long countDrugByCode(String code) throws DaoException;

    /**
     * 通过条件获得分页的药品数据
     * @param drugCondition
     * @return
     * @throws DaoException
     */
    public PageInfo<Drug> findDrugPage(DrugCondition drugCondition) throws DaoException;
}

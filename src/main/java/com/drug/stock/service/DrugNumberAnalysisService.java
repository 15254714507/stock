package com.drug.stock.service;

import com.drug.stock.entity.condition.DrugNumberAnalysisCondition;
import com.drug.stock.entity.domain.DrugNumberAnalysis;
import com.drug.stock.exception.DaoException;

import java.util.List;

/**
 * @author lenovo
 */
public interface DrugNumberAnalysisService {
    /**
     * 获得药品库存分析
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public DrugNumberAnalysis getDrugNumberAnalysis(Long id) throws DaoException;

    /**
     * 添加药品库存分析
     *
     * @param drugNumberAnalysis
     * @return
     * @throws DaoException
     */
    public Long insertDrugNumberAnalysis(DrugNumberAnalysis drugNumberAnalysis) throws DaoException;

    /**
     * 修改药品库存分析
     *
     * @param drugNumberAnalysis
     * @return
     * @throws DaoException
     */
    public Long updateDrugNumberAnalysis(DrugNumberAnalysis drugNumberAnalysis) throws DaoException;

    /**
     * 删除药品库存分析
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteDrugNumberAnalysis(Long id) throws DaoException;

    /**
     * 获得药品库存分析的集合
     *
     * @param drugNumberAnalysisCondition
     * @return
     * @throws DaoException
     */
    public List<DrugNumberAnalysis> listDrugNumberAnalysis(DrugNumberAnalysisCondition drugNumberAnalysisCondition) throws DaoException;
}

package com.drug.stock.dao;

import com.drug.stock.entity.condition.DrugNumberAnalysisCondition;
import com.drug.stock.entity.domain.DrugNumberAnalysis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface DrugNumberAnalysisDao {
    /**
     * 获得药品库存分析
     *
     * @param id
     * @return
     */
    public DrugNumberAnalysis getDrugNumberAnalysis(Long id);

    /**
     * 添加药品库存分析
     *
     * @param drugNumberAnalysis
     * @return
     */
    public Long insertDrugNumberAnalysis(DrugNumberAnalysis drugNumberAnalysis);

    /**
     * 修改药品库存分析
     *
     * @param drugNumberAnalysis
     * @return
     */
    public Long updateDrugNumberAnalysis(DrugNumberAnalysis drugNumberAnalysis);

    /**
     * 删除药品库存分析
     *
     * @param id
     * @return
     */
    public Long deleteDrugNumberAnalysis(Long id);

    /**
     * 获得药品库存分析的集合
     *
     * @param drugNumberAnalysisCondition
     * @return
     */
    public List<DrugNumberAnalysis> listDrugNumberAnalysis(DrugNumberAnalysisCondition drugNumberAnalysisCondition);
}

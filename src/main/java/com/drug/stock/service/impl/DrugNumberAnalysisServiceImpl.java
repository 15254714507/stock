package com.drug.stock.service.impl;

import com.drug.stock.entity.condition.DrugNumberAnalysisCondition;
import com.drug.stock.entity.domain.DrugNumberAnalysis;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DrugNumberAnalysisManager;
import com.drug.stock.service.DrugNumberAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("drugNumberAnalysisService")
public class DrugNumberAnalysisServiceImpl implements DrugNumberAnalysisService {
    @Resource
    DrugNumberAnalysisManager drugNumberAnalysisManager;

    @Override
    public DrugNumberAnalysis getDrugNumberAnalysis(Long id) throws DaoException {
        return drugNumberAnalysisManager.getDrugNumberAnalysis(id);
    }

    @Override
    public Long insertDrugNumberAnalysis(DrugNumberAnalysis drugNumberAnalysis) throws DaoException {
        return drugNumberAnalysisManager.insertDrugNumberAnalysis(drugNumberAnalysis);
    }

    @Override
    public Long updateDrugNumberAnalysis(DrugNumberAnalysis drugNumberAnalysis) throws DaoException {
        return drugNumberAnalysisManager.updateDrugNumberAnalysis(drugNumberAnalysis);
    }

    @Override
    public Long deleteDrugNumberAnalysis(Long id) throws DaoException {
        return drugNumberAnalysisManager.deleteDrugNumberAnalysis(id);
    }

    @Override
    public List<DrugNumberAnalysis> listDrugNumberAnalysis(DrugNumberAnalysisCondition drugNumberAnalysisCondition) throws DaoException {
        return drugNumberAnalysisManager.listDrugNumberAnalysis(drugNumberAnalysisCondition);
    }
}

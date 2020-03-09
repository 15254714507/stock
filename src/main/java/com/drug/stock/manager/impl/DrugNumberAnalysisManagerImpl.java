package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.DrugNumberAnalysisDao;
import com.drug.stock.entity.condition.DrugNumberAnalysisCondition;
import com.drug.stock.entity.domain.DrugNumberAnalysis;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.DrugNumberAnalysisManager;
import com.drug.stock.until.TimestampFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Component("drugNumberAnalysisManager")
public class DrugNumberAnalysisManagerImpl implements DrugNumberAnalysisManager {
    @Resource
    DrugNumberAnalysisDao drugNumberAnalysisDao;

    @Override
    public DrugNumberAnalysis getDrugNumberAnalysis(Long id) throws DaoException {
        try {
            return drugNumberAnalysisDao.getDrugNumberAnalysis(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long insertDrugNumberAnalysis(DrugNumberAnalysis drugNumberAnalysis) throws DaoException {
        DrugNumberAnalysisCondition drugNumberAnalysisCondition = new DrugNumberAnalysisCondition();
        drugNumberAnalysisCondition.setDrugCode(drugNumberAnalysis.getDrugCode());
        List<DrugNumberAnalysis> list = listDrugNumberAnalysis(drugNumberAnalysisCondition);
        if (list != null && list.size() > 0) {
            log.warn("添加药品库存分析时查到数据库中已存在此记录，drugNumberAnalysis:{}", drugNumberAnalysis);
            return 0L;
        }
        drugNumberAnalysis.setCreateTime(TimestampFactory.getTimestamp());
        drugNumberAnalysis.setUpdateTime(drugNumberAnalysis.getCreateTime());
        try {
            return drugNumberAnalysisDao.insertDrugNumberAnalysis(drugNumberAnalysis);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long updateDrugNumberAnalysis(DrugNumberAnalysis drugNumberAnalysis) throws DaoException {
        if (getDrugNumberAnalysis(drugNumberAnalysis.getId()) == null) {
            log.warn("药品库存分析表里没有要修改的记录，drugNumberAnalysis：{}", JSON.toJSONString(drugNumberAnalysis));
            return 0L;
        }
        drugNumberAnalysis.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return drugNumberAnalysisDao.updateDrugNumberAnalysis(drugNumberAnalysis);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Long deleteDrugNumberAnalysis(Long id) throws DaoException {
        if (getDrugNumberAnalysis(id) == null) {
            log.warn("药品库存分析表里没有要删除的记录，id：{}", id);
            return 0L;
        }
        try {
            return drugNumberAnalysisDao.deleteDrugNumberAnalysis(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<DrugNumberAnalysis> listDrugNumberAnalysis(DrugNumberAnalysisCondition drugNumberAnalysisCondition) throws DaoException {
        try {
            return drugNumberAnalysisDao.listDrugNumberAnalysis(drugNumberAnalysisCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}

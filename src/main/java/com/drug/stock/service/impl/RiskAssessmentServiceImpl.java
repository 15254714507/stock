package com.drug.stock.service.impl;

import com.drug.stock.entity.condition.RiskAssessmentCondition;
import com.drug.stock.entity.domain.RiskAssessment;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.RiskAssessmentManager;
import com.drug.stock.service.RiskAssessmentService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Service("riskAssessmentService")
public class RiskAssessmentServiceImpl implements RiskAssessmentService {
    @Resource
    RiskAssessmentManager riskAssessmentManager;

    @Override
    public RiskAssessment getRiskAssessment(Long id) throws DaoException {
        return riskAssessmentManager.getRiskAssessment(id);
    }

    @Override
    public Long insertRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
        return riskAssessmentManager.insertRiskAssessment(riskAssessment);
    }

    @Override
    public Long updateRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
        return riskAssessmentManager.updateRiskAssessment(riskAssessment);
    }

    @Override
    public Long deleteRiskAssessment(Long id) throws DaoException {
        return riskAssessmentManager.deleteRiskAssessment(id);
    }

    @Override
    public List<RiskAssessment> listRiskAssessment(RiskAssessmentCondition riskAssessmentCondition) throws DaoException {
        return riskAssessmentManager.listRiskAssessment(riskAssessmentCondition);
    }

    @Override
    public PageInfo<RiskAssessment> findRiskAssessmentPage(RiskAssessmentCondition riskAssessmentCondition) throws DaoException {
        return riskAssessmentManager.findRiskAssessmentPage(riskAssessmentCondition);
    }
}

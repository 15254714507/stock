package com.drug.stock.service;

import com.drug.stock.entity.condition.RiskAssessmentCondition;
import com.drug.stock.entity.domain.RiskAssessment;
import com.drug.stock.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lenovo
 */
public interface RiskAssessmentService {
    /**
     * 获得药品风险评估
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public RiskAssessment getRiskAssessment(Long id) throws DaoException;

    /**
     * 插入药品风险评估
     *
     * @param riskAssessment
     * @return
     * @throws DaoException
     */

    public Long insertRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * 修改药品风险评估
     *
     * @param riskAssessment
     * @return
     * @throws DaoException
     */

    public Long updateRiskAssessment(RiskAssessment riskAssessment) throws DaoException;

    /**
     * 删除药品风险评估
     *
     * @param id
     * @return
     * @throws DaoException
     */
    public Long deleteRiskAssessment(Long id) throws DaoException;

    /**
     * 返回药品风险评估的集合
     *
     * @param riskAssessmentCondition
     * @return
     * @throws DaoException
     */
    public List<RiskAssessment> listRiskAssessment(RiskAssessmentCondition riskAssessmentCondition) throws DaoException;

    /**
     * 获得药品风险评估的分页数据
     *
     * @param riskAssessmentCondition
     * @return
     * @throws DaoException
     */
    public PageInfo<RiskAssessment> findRiskAssessmentPage(RiskAssessmentCondition riskAssessmentCondition) throws DaoException;
}

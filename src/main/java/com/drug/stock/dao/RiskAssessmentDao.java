package com.drug.stock.dao;

import com.drug.stock.entity.condition.RiskAssessmentCondition;
import com.drug.stock.entity.domain.RiskAssessment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
@Mapper
public interface RiskAssessmentDao {
    /**
     * 获得药品风险评估
     *
     * @param id
     * @return
     */
    public RiskAssessment getRiskAssessment(Long id);

    /**
     * 插入药品风险评估
     *
     * @param riskAssessment
     * @return
     */

    public Long insertRiskAssessment(RiskAssessment riskAssessment);

    /**
     * 修改药品风险评估
     *
     * @param riskAssessment
     * @return
     */

    public Long updateRiskAssessment(RiskAssessment riskAssessment);

    /**
     * 删除药品风险评估
     *
     * @param id
     * @return
     */
    public Long deleteRiskAssessment(Long id);

    /**
     * 返回药品风险评估的集合
     *
     * @param riskAssessmentCondition
     * @return
     */
    public List<RiskAssessment> listRiskAssessment(RiskAssessmentCondition riskAssessmentCondition);
}

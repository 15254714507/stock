package com.drug.stock.manager.impl;

import com.alibaba.fastjson.JSON;
import com.drug.stock.dao.RiskAssessmentDao;
import com.drug.stock.entity.condition.RiskAssessmentCondition;
import com.drug.stock.entity.domain.RiskAssessment;
import com.drug.stock.entity.domain.User;
import com.drug.stock.exception.DaoException;
import com.drug.stock.manager.RiskAssessmentManager;
import com.drug.stock.until.TimestampFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Component("riskAssessmentManager")
public class RiskAssessmentManagerImpl implements RiskAssessmentManager {
    @Resource
    RiskAssessmentDao riskAssessmentDao;

    @Override
    public RiskAssessment getRiskAssessment(Long id) throws DaoException {
        try {
            return riskAssessmentDao.getRiskAssessment(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
        RiskAssessmentCondition riskAssessmentCondition = new RiskAssessmentCondition();
        riskAssessmentCondition.setDrugCode(riskAssessment.getDrugCode());
        List<RiskAssessment> list = listRiskAssessment(riskAssessmentCondition);
        if (list != null && list.size() > 0) {
            log.warn("添加药品评估风险时数据库已经有此药品了 riskAssessment：{}", JSON.toJSONString(riskAssessment));
            return 0L;
        }
        riskAssessment.setCreateTime(TimestampFactory.getTimestamp());
        riskAssessment.setUpdateTime(riskAssessment.getCreateTime());
        try {
            return riskAssessmentDao.insertRiskAssessment(riskAssessment);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateRiskAssessment(RiskAssessment riskAssessment) throws DaoException {
        if (getRiskAssessment(riskAssessment.getId()) == null) {
            log.warn("修改药品风险评估时数据库中没有此条数据，riskAssessment：{}", JSON.toJSONString(riskAssessment));
            return 0L;
        }
        riskAssessment.setUpdateTime(TimestampFactory.getTimestamp());
        try {
            return riskAssessmentDao.updateRiskAssessment(riskAssessment);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteRiskAssessment(Long id) throws DaoException {
        if (getRiskAssessment(id) == null) {
            log.warn("删除药品风险评估时数据库中没有此条数据，id：{}", id);
            return 0L;
        }
        try {
            return riskAssessmentDao.deleteRiskAssessment(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<RiskAssessment> listRiskAssessment(RiskAssessmentCondition riskAssessmentCondition) throws DaoException {
        try {
            return riskAssessmentDao.listRiskAssessment(riskAssessmentCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PageInfo<RiskAssessment> findRiskAssessmentPage(RiskAssessmentCondition riskAssessmentCondition) throws DaoException {
        //参数第一个是第几页，第二个是条数，通过凭借SQL的方式,必须加判断
        if (riskAssessmentCondition.getPage() != null && riskAssessmentCondition.getRows() != null) {
            PageHelper.startPage(riskAssessmentCondition.getPage(), riskAssessmentCondition.getRows());
        }
        List<RiskAssessment> list = null;
        try {
            list = riskAssessmentDao.listRiskAssessment(riskAssessmentCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            //虽然不推荐使用，但是上面还是会出现特殊情况的
            PageHelper.clearPage();
        }
        PageInfo<RiskAssessment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

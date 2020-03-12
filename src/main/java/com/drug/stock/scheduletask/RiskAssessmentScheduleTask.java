package com.drug.stock.scheduletask;

import com.alibaba.fastjson.JSON;
import com.drug.stock.entity.condition.DrugNumberAnalysisCondition;
import com.drug.stock.entity.condition.RiskAssessmentCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.entity.domain.DrugNumberAnalysis;
import com.drug.stock.entity.domain.RiskAssessment;
import com.drug.stock.service.DrugNumberAnalysisService;
import com.drug.stock.service.DrugService;
import com.drug.stock.service.RiskAssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 库存异常的定时任务
 *
 * @author lenovo
 */
@Slf4j
@Configuration
@EnableScheduling
public class RiskAssessmentScheduleTask {
    /**
     * 滞料等级
     */
    private static final int GRADE_0 = 0;
    private static final int GRADE_1 = 1;
    private static final int GRADE_2 = 2;
    private static final int GRADE_3 = 3;
    private static final int GRADE_4 = 4;
    /**
     * 滞料标准的判断条件
     */
    private static final double CV_1 = 0.6;
    private static final double CV_2 = 1;
    private static final int MONTH_1 = 6;
    private static final int MONTH_2 = 12;
    /**
     * 创建者
     */
    private static final String CREATE_USER = "system";
    @Resource
    DrugNumberAnalysisService drugNumberAnalysisService;
    @Resource
    RiskAssessmentService riskAssessmentService;
    @Resource
    DrugService drugService;

    /**
     * 库存异常的定时任务，每天凌晨0点执行一次
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void riskAssessmentTasks() {
        DrugNumberAnalysisCondition drugNumberAnalysisCondition = new DrugNumberAnalysisCondition();
        List<DrugNumberAnalysis> list = drugNumberAnalysisService.listDrugNumberAnalysis(drugNumberAnalysisCondition);
        for (DrugNumberAnalysis drugNumberAnalysis : list) {
            drugNumberAnalysisOperation(drugNumberAnalysis);
        }

    }

    /**
     * 库存异常的执行逻辑
     *
     * @param drugNumberAnalysis
     */
    @Transactional(rollbackFor = Exception.class)
    void drugNumberAnalysisOperation(DrugNumberAnalysis drugNumberAnalysis) {
        RiskAssessmentCondition riskAssessmentCondition = new RiskAssessmentCondition();
        riskAssessmentCondition.setDrugCode(drugNumberAnalysis.getDrugCode());
        List<RiskAssessment> list = riskAssessmentService.listRiskAssessment(riskAssessmentCondition);
        if (list == null || list.size() < 1) {
            //没有就需要新插入
            RiskAssessment riskAssessment = createRiskAssessment(drugNumberAnalysis);
            try {
                Long isSuc = riskAssessmentService.insertRiskAssessment(riskAssessment);
                if (isSuc != 1) {
                    log.error("添加RiskAssessment时发现数据库中已经存在，RiskAssessment：{}", JSON.toJSONString(riskAssessment));
                }
            } catch (Exception e) {
                log.error("添加RiskAssessment时发生系统异常 RiskAssessment：{}", JSON.toJSONString(riskAssessment), e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        } else {
            //里面原来有这个药品的，只需要修改就行
            RiskAssessment riskAssessment = list.get(0);
            fillRiskAssessment(riskAssessment, drugNumberAnalysis);
            try {
                Long isSuc = riskAssessmentService.updateRiskAssessment(riskAssessment);
                if (isSuc != 1) {
                    log.error("修改RiskAssessment发现数据库中已经有了 RiskAssessment{}", JSON.toJSONString(riskAssessment));
                }
            } catch (Exception e) {
                log.error("修改RiskAssessment发生系统异常 RiskAssessment{}", JSON.toJSONString(riskAssessment), e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            }
        }
    }

    /**
     * 创建RiskAssessment
     *
     * @param drugNumberAnalysis
     * @return
     */
    private RiskAssessment createRiskAssessment(DrugNumberAnalysis drugNumberAnalysis) {
        RiskAssessment riskAssessment = new RiskAssessment();
        Drug drug = drugService.getDrugByCode(drugNumberAnalysis.getDrugCode());
        riskAssessment.setDrugCode(drug.getCode());
        riskAssessment.setDrugName(drug.getName());
        riskAssessment.setDrugStorage(drug.getStorage());
        riskAssessment.setDrugWarehouseNumber(drug.getWareHouse());
        fillRiskAssessment(riskAssessment, drugNumberAnalysis);
        return riskAssessment;
    }

    /**
     * 填充 RiskAssessment需要单独计算的
     *
     * @param riskAssessment
     * @param drugNumberAnalysis
     */
    private void fillRiskAssessment(RiskAssessment riskAssessment, DrugNumberAnalysis drugNumberAnalysis) {
        List<Integer> list = new ArrayList<>(6);
        list.add(drugNumberAnalysis.getOneAgoMonthTotal());
        list.add(drugNumberAnalysis.getTwoAgoMonthTotal());
        list.add(drugNumberAnalysis.getThreeAgoMonthTotal());
        list.add(drugNumberAnalysis.getFourAgoMonthTotal());
        list.add(drugNumberAnalysis.getFiveAgoMonthTotal());
        list.add(drugNumberAnalysis.getSixAgoMonthTotal());
        fillDelayedMaterialRisk(list, riskAssessment, drugNumberAnalysis);
        riskAssessment.setCreateUser(CREATE_USER);
        riskAssessment.setUpdateUser(CREATE_USER);
    }

    /**
     * 填充DelayedMaterialRisk
     *
     * @param list
     * @param riskAssessment
     * @param drugNumberAnalysis
     */
    private void fillDelayedMaterialRisk(List<Integer> list, RiskAssessment riskAssessment, DrugNumberAnalysis drugNumberAnalysis) {
        riskAssessment.setDelayedMaterialRisk(getDelayedMaterialRisk(list, drugNumberAnalysis));
    }


    /**
     * 获得滞销等级
     *
     * @param list
     * @param drugNumberAnalysis
     * @return
     */
    private int getDelayedMaterialRisk(List<Integer> list, DrugNumberAnalysis drugNumberAnalysis) {
        //半年没有此药品的出库记录和此药品的库存大于0，等级为4
        if (drugNumberAnalysis.getAvgDosage() == 0 && drugNumberAnalysis.getNumber() > 0) {
            return GRADE_4;
        }
        double standardDeviation = getStandardDeviation(list, drugNumberAnalysis.getAvgDosage());
        double cv = standardDeviation / drugNumberAnalysis.getAvgDosage();
        if (cv > CV_1 && cv < CV_2) {
            if (drugNumberAnalysis.getEstimationMonth() > MONTH_1 && drugNumberAnalysis.getEstimationMonth() < MONTH_2) {
                return GRADE_1;
            }
        }
        if (cv <= CV_2) {
            if (drugNumberAnalysis.getEstimationMonth() > MONTH_1 && drugNumberAnalysis.getEstimationMonth() < MONTH_2) {
                return GRADE_2;
            }
            if (drugNumberAnalysis.getEstimationMonth() >= MONTH_2) {
                return GRADE_3;
            }
        }
        return GRADE_0;
    }

    /**
     * 获得标准差
     *
     * @param list
     * @return
     */
    private double getStandardDeviation(List<Integer> list, int avg) {
        int sum = 0;
        for (Integer dosage : list) {
            sum += Math.pow(dosage - avg, 2);
        }
        double fenMu = list.size() - 1;
        return Math.sqrt(sum / fenMu);
    }
}

package com.drug.stock.scheduleTask;

import com.alibaba.fastjson.JSON;
import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.condition.DrugNumberAnalysisCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.entity.domain.DrugNumberAnalysis;
import com.drug.stock.entity.dto.BetweenTime;
import com.drug.stock.service.DeliveryOrderDrugService;
import com.drug.stock.service.DeliveryOrderService;
import com.drug.stock.service.DrugNumberAnalysisService;
import com.drug.stock.service.DrugService;
import com.drug.stock.until.TimestampFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * 库存异常的定时任务
 *
 * @author lenovo
 */
@Slf4j
@Configuration
@EnableScheduling
public class DrugNumberAnalysisScheduleTask {
    @Resource
    DrugService drugService;
    @Resource
    DeliveryOrderDrugService deliveryOrderDrugService;
    @Resource
    DeliveryOrderService deliveryOrderService;
    @Resource
    DrugNumberAnalysisService drugNumberAnalysisService;
    /**
     * 统计6个月药品库存分析
     */
    private static final Integer MONTH = 6;
    /**
     * 创建者为的名字
     */
    private static final String CREATE_USER = "system";

    /**
     * 库存异常的定时任务，每天凌晨1点执行一次
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void drugNumberAnalysisTasks() {
        //从药品表里获得所有的药品
        DrugCondition drugCondition = new DrugCondition();
        List<Drug> list = drugService.listDrug(drugCondition);
        for (Drug drug : list) {
            drugNumberAnalysisOperation(drug);

        }
    }

    /**
     * 库存异常记录的进行操作的逻辑记录
     *
     * @param drug
     */
    @Transactional(rollbackFor = Exception.class)
    void drugNumberAnalysisOperation(Drug drug) {
        //drug药品半年的出货量，顺序是一个月前，2个月前，一直到半年前总共6个月
        List<Integer> halfPurchase = getHalfPurchase(drug.getCode());
        DrugNumberAnalysisCondition drugNumberAnalysisCondition = new DrugNumberAnalysisCondition();
        drugNumberAnalysisCondition.setDrugCode(drug.getCode());
        List<DrugNumberAnalysis> drugNumberAnalysisList = drugNumberAnalysisService.listDrugNumberAnalysis(drugNumberAnalysisCondition);
        DrugNumberAnalysis drugNumberAnalysis = null;
        try {
            if (drugNumberAnalysisList == null || drugNumberAnalysisList.size() < 1) {
                //没有则插入
                drugNumberAnalysis = createDrugNumberAnalysis(drug, halfPurchase);
                Long isSuc = drugNumberAnalysisService.insertDrugNumberAnalysis(drugNumberAnalysis);
                if (isSuc != 1) {
                    log.error("定时任务添加药品库存分析记录时发现里面已经有了 drugNumberAnalysis:{}", JSON.toJSONString(drugNumberAnalysis));
                }
            } else {
                //有则修改里面的数据
                drugNumberAnalysis = fillDrugNumberAnalysis(drugNumberAnalysisList.get(0), halfPurchase, drug.getNumber());
                Long isSuc = drugNumberAnalysisService.updateDrugNumberAnalysis(drugNumberAnalysis);
                if (isSuc != 1) {
                    log.error("定时任务修改药品库存分析记录时发现里面没有 drugNumberAnalysis:{}", JSON.toJSONString(drugNumberAnalysis));
                }
            }
        } catch (Exception e) {
            log.error("定时任务中库存异常添加或修改时出现系统异常drugNumberAnalysis:{}", JSON.toJSONString(drugNumberAnalysis), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    /**
     * 根据药品编码获得此药品半年的进货量，
     *
     * @param drugCode
     * @return 返回的集合中有6个，正好对应半年的6个月
     */
    private List<Integer> getHalfPurchase(String drugCode) {
        Date endTime = TimestampFactory.getTimestamp();
        BetweenTime betweenTime = new BetweenTime();
        betweenTime.setEndTime(endTime);
        List<Integer> list = new ArrayList<>(6);
        for (int i = 1; i <= MONTH; i++) {
            Date startTime = getMonthDate(i);
            betweenTime.setStartTime(startTime);
            List<DeliveryOrder> deliveryOrderList = deliveryOrderService.listStartTimeToEndTime(betweenTime);
            int sumDrugNumber = getPurchaseDrugNumber(deliveryOrderList, drugCode);
            //顺序一个月前，2个月前，3个月前直到6个月前
            list.add(sumDrugNumber);
            //再往前一个月
            betweenTime.setEndTime(startTime);
        }
        return list;
    }

    /**
     * 获取前month*30的日期
     *
     * @param month
     * @return
     */
    private Date getMonthDate(int month) {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, -30 * month);
        return new Timestamp(calender.getTime().getTime());
    }

    /**
     * 获得出库单中某药品的数量
     *
     * @param deliveryOrderList
     * @param drugCode
     * @return
     */
    private int getPurchaseDrugNumber(List<DeliveryOrder> deliveryOrderList, String drugCode) {
        int sumDrugNumber = 0;
        for (DeliveryOrder deliveryOrder : deliveryOrderList) {
            //根据出库单的编码和药品编码为条件搜索
            DeliveryOrderDrugCondition deliveryOrderDrugCondition = new DeliveryOrderDrugCondition();
            deliveryOrderDrugCondition.setCode(deliveryOrder.getCode());
            deliveryOrderDrugCondition.setDrugCode(drugCode);
            List<DeliveryOrderDrug> deliveryOrderDrugList = deliveryOrderDrugService.listDeliveryOrderDrug(deliveryOrderDrugCondition);
            //下一步就是在这个出库单里这个药品的数量。有可能此出库单没有这个药品
            if (deliveryOrderDrugList != null && deliveryOrderDrugList.size() > 0) {
                sumDrugNumber += deliveryOrderDrugList.get(0).getNumber();
            }
        }
        return sumDrugNumber;

    }

    /**
     * 创建新的药品库存分析记录
     *
     * @param drug 药品信息
     * @param list 此药品6个月前的出库量集合
     * @return
     */
    private DrugNumberAnalysis createDrugNumberAnalysis(Drug drug, List<Integer> list) {
        DrugNumberAnalysis drugNumberAnalysis = new DrugNumberAnalysis();
        drugNumberAnalysis.setDrugCode(drug.getCode());
        drugNumberAnalysis.setDrugName(drug.getName());
        fillDrugNumberAnalysis(drugNumberAnalysis, list, drug.getNumber());
        return drugNumberAnalysis;
    }

    /**
     * 填充药品库存分析
     *
     * @param drugNumberAnalysis 库存新的
     * @param list               前6个月的药品出库量集合
     * @param drugNumber         药品库存
     * @return
     */
    private DrugNumberAnalysis fillDrugNumberAnalysis(DrugNumberAnalysis drugNumberAnalysis, List<Integer> list, int drugNumber) {
        drugNumberAnalysis.setNumber(drugNumber);
        drugNumberAnalysis.setOneAgoMonthTotal(list.get(0));
        drugNumberAnalysis.setTwoAgoMonthTotal(list.get(1));
        drugNumberAnalysis.setThreeAgoMonthTotal(list.get(2));
        drugNumberAnalysis.setFourAgoMonthTotal(list.get(3));
        drugNumberAnalysis.setFiveAgoMonthTotal(list.get(4));
        drugNumberAnalysis.setSixAgoMonthTotal(list.get(5));
        int halfTotal = 0;
        for (int number : list) {
            halfTotal += number;
        }
        drugNumberAnalysis.setHalfTotal(halfTotal);
        int avgDosage = halfTotal / MONTH;
        drugNumberAnalysis.setAvgDosage(avgDosage);
        //分母为0时得出的结果是错误的，所以要先判断分母是否为0
        double estimationMonth = 0;
        if (avgDosage != 0) {
            estimationMonth = (double) drugNumber / (double) avgDosage;
        }
        drugNumberAnalysis.setEstimationMonth(estimationMonth);
        //设定使用量是平均的1.2倍
        int estimationDosage = (int) (1.2 * avgDosage);
        drugNumberAnalysis.setEstimationDosage(estimationDosage);
        //采购量看是否小于设定量
        if (drugNumber < estimationDosage) {
            drugNumberAnalysis.setRequisitionQuantity(estimationDosage - drugNumber);
        } else {
            drugNumberAnalysis.setRequisitionQuantity(0);
        }
        drugNumberAnalysis.setCreateUser(CREATE_USER);
        drugNumberAnalysis.setUpdateUser(CREATE_USER);
        return drugNumberAnalysis;
    }
}


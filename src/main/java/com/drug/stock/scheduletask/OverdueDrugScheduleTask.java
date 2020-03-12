package com.drug.stock.scheduletask;

import com.alibaba.fastjson.JSON;
import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.condition.OverdueDrugCondition;
import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.Drug;
import com.drug.stock.entity.domain.OverdueDrug;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.service.DrugService;
import com.drug.stock.service.OverdueDrugService;
import com.drug.stock.service.PurchaseOrderDrugService;
import com.drug.stock.until.TimestampFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * 过期药品的定时任务
 *
 * @author lenovo
 */
@Slf4j
@Configuration
@EnableScheduling
public class OverdueDrugScheduleTask {
    private static final String PROCESS_MODE_1 = "返厂";
    private static final String PROCESS_MODE_2 = "销毁";
    private static final String CREATE_USER = "system";
    private static final String DRUG_DOSAGE_FORM = "固";
    @Resource
    DrugService drugService;
    @Resource
    PurchaseOrderDrugService purchaseOrderDrugService;
    @Resource
    OverdueDrugService overdueDrugService;

    /**
     * 过期药品的定时任务，每天凌晨1点执行一次
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void overdueDrugTasks() {
        //从药品表里获得所有的药品
        DrugCondition drugCondition = new DrugCondition();
        List<Drug> list = drugService.listDrug(drugCondition);
        for (Drug drug : list) {
            int notOverdueDrugNumber = getNotOverdueDrugList(drug.getCode());
            //说明现有的库存大于没过期的库存，说明有过期的，就需要往过期药品表里添加数据
            if (drug.getNumber() > notOverdueDrugNumber) {
                overdueDrugOperation(drug, notOverdueDrugNumber);
            }
        }
    }

    /**
     * 下面是确定有新的过期药品时逻辑过程
     *
     * @param drug
     * @param notOverdueDrugNumber
     */
    @Transactional(rollbackFor = Exception.class)
    void overdueDrugOperation(Drug drug, int notOverdueDrugNumber) {
        int initDrugNumber = drug.getNumber();
        try {
            //往过期药品表添加
            Long isSuc = addOverdueDrug(drug, initDrugNumber - notOverdueDrugNumber);
            //只有往过期药品表里添加或者修改成功后才可以修改药品表里的库存
            if (isSuc == 1) {
                //修改药品表的库存
                drug.setNumber(notOverdueDrugNumber);
                drugService.updateDrug(drug);
            } else {
                log.error("往药品过期表添加记录或修改记录没有成功，可能库里已经有了 drug:{} ,notOverdueDrugNumber:{}", JSON.toJSONString(drug), notOverdueDrugNumber);
            }
        } catch (Exception e) {
            drug.setNumber(initDrugNumber);
            log.error("往药品过期表添加记录和修改药品库存发生异常 drug:{} ,notOverdueDrugNumber:{}", JSON.toJSONString(drug), notOverdueDrugNumber, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    /**
     * 根据药品编码获得没有过期的药品的集合
     *
     * @param drugCode
     * @return
     */
    private int getNotOverdueDrugList(String drugCode) {
        PurchaseOrderDrugCondition purchaseOrderDrugCondition = new PurchaseOrderDrugCondition();
        purchaseOrderDrugCondition.setDrugCode(drugCode);
        List<PurchaseOrderDrug> purchaseOrderDrugList = purchaseOrderDrugService.listNotOverdueDrug(purchaseOrderDrugCondition);
        //没过期药品的总数量
        int number = 0;
        for (PurchaseOrderDrug purchaseOrderDrug : purchaseOrderDrugList) {
            number += purchaseOrderDrug.getNumber();
        }
        return number;
    }

    /**
     * 往过期药品表里添加记录
     *
     * @param drug
     * @param overdueDrugNumber
     */
    private Long addOverdueDrug(Drug drug, int overdueDrugNumber) {
        OverdueDrugCondition overdueDrugCondition = new OverdueDrugCondition();
        overdueDrugCondition.setDrugCode(drug.getCode());
        List<OverdueDrug> list = overdueDrugService.listOverdueDrug(overdueDrugCondition);
        Long isSuc = 0L;
        if (list == null || list.size() < 1) {
            //说明过期药品表里没有此药品
            OverdueDrug overdueDrug = createOverdueDrug(drug);
            overdueDrug.setNumber(overdueDrugNumber);
            isSuc = overdueDrugService.insertOverdueDrug(overdueDrug);
        } else {
            //过期药品表里有此药品
            OverdueDrug overdueDrug = list.get(0);
            fillOverdueDrug(overdueDrug, overdueDrugNumber);
            isSuc = overdueDrugService.updateOverdueDrug(overdueDrug);
        }
        return isSuc;
    }

    /**
     * 根据drug创建OverdueDrug
     *
     * @param drug
     * @return
     */
    private OverdueDrug createOverdueDrug(Drug drug) {
        OverdueDrug overdueDrug = new OverdueDrug();
        overdueDrug.setDrugCode(drug.getCode());
        overdueDrug.setDrugName(drug.getName());
        overdueDrug.setDrugSpecs(drug.getSpecs());
        //因为每天都定时执行任务，所以如果新添加的过期药品，过期日就是当天
        overdueDrug.setExpireDate(TimestampFactory.getTimestamp());
        if (DRUG_DOSAGE_FORM.equals(drug.getDosageForm())) {
            overdueDrug.setProcessMode(PROCESS_MODE_2);
        } else {
            overdueDrug.setProcessMode(PROCESS_MODE_1);
        }
        overdueDrug.setCreateUser(CREATE_USER);
        overdueDrug.setUpdateUser(CREATE_USER);
        return overdueDrug;
    }

    /**
     * 填充需要修改的 OverdueDrug
     * @param overdueDrug
     * @param overdueDrugNumber
     */
    private void fillOverdueDrug(OverdueDrug overdueDrug, int overdueDrugNumber) {
        overdueDrug.setExpireDate(TimestampFactory.getTimestamp());
        if (overdueDrug.getStatus()) {
            overdueDrug.setStatus(false);
            overdueDrug.setNumber(overdueDrugNumber);
        } else {
            overdueDrug.setNumber(overdueDrug.getNumber() + overdueDrugNumber);
        }
    }


}

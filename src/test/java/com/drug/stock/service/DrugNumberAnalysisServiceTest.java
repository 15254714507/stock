package com.drug.stock.service;

import com.drug.stock.entity.condition.DrugNumberAnalysisCondition;
import com.drug.stock.entity.domain.DrugNumberAnalysis;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugNumberAnalysisServiceTest {
    @Resource
    DrugNumberAnalysisService drugNumberAnalysisService;

    @Test
    @Transactional
    public void insertDrugNumberAnalysisTest() {
        DrugNumberAnalysis drugNumberAnalysis = insertDrugNumberAnalysis();
        DrugNumberAnalysisCondition drugNumberAnalysisCondition = new DrugNumberAnalysisCondition();
        drugNumberAnalysisCondition.setDrugCode(drugNumberAnalysis.getDrugCode());
        drugNumberAnalysisCondition.setDrugName(drugNumberAnalysis.getDrugName());
        drugNumberAnalysisCondition.setAvgDosage(drugNumberAnalysis.getAvgDosage());
        drugNumberAnalysisCondition.setHalfTotal(drugNumberAnalysis.getHalfTotal());
        drugNumberAnalysisCondition.setEstimationDosage(drugNumberAnalysis.getEstimationDosage());
        drugNumberAnalysisCondition.setEstimationMonth(drugNumberAnalysis.getEstimationMonth());
        drugNumberAnalysisCondition.setNumber(drugNumberAnalysis.getNumber());
        drugNumberAnalysisCondition.setRequisitionQuantity(drugNumberAnalysis.getRequisitionQuantity());
        List<DrugNumberAnalysis> list = drugNumberAnalysisService.listDrugNumberAnalysis(drugNumberAnalysisCondition);
        Assert.assertEquals(1, list.size());

    }

    private DrugNumberAnalysis insertDrugNumberAnalysis() {
        DrugNumberAnalysis drugNumberAnalysis = new DrugNumberAnalysis();
        drugNumberAnalysis.setDrugCode(UUID.randomUUID().toString());
        drugNumberAnalysis.setDrugName(UUID.randomUUID().toString());
        drugNumberAnalysis.setAvgDosage(111);
        drugNumberAnalysis.setHalfTotal(111);
        drugNumberAnalysis.setEstimationDosage(111);
        drugNumberAnalysis.setEstimationMonth(2.2);
        drugNumberAnalysis.setNumber(111);
        drugNumberAnalysis.setRequisitionQuantity(111);
        drugNumberAnalysis.setCreateUser("zhengwenju");
        drugNumberAnalysis.setUpdateUser("zhengwenju");
        Long isSuc = drugNumberAnalysisService.insertDrugNumberAnalysis(drugNumberAnalysis);
        Assert.assertEquals(1, isSuc.intValue());
        return drugNumberAnalysis;
    }

    @Test
    @Transactional
    public void getDrugNumberAnalysis() {
        DrugNumberAnalysis drugNumberAnalysis = insertDrugNumberAnalysis();
        DrugNumberAnalysisCondition drugNumberAnalysisCondition = new DrugNumberAnalysisCondition();
        drugNumberAnalysisCondition.setDrugCode(drugNumberAnalysis.getDrugCode());
        List<DrugNumberAnalysis> list = drugNumberAnalysisService.listDrugNumberAnalysis(drugNumberAnalysisCondition);
        Assert.assertEquals(1, list.size());
        DrugNumberAnalysis drugNumberAnalysis1 = drugNumberAnalysisService.getDrugNumberAnalysis(list.get(0).getId());
        Assert.assertNotNull(drugNumberAnalysis1);
    }

    @Test
    @Transactional
    public void deleteDrugNumberAnalysis() {
        DrugNumberAnalysis drugNumberAnalysis = insertDrugNumberAnalysis();
        DrugNumberAnalysisCondition drugNumberAnalysisCondition = new DrugNumberAnalysisCondition();
        drugNumberAnalysisCondition.setDrugCode(drugNumberAnalysis.getDrugCode());
        List<DrugNumberAnalysis> list = drugNumberAnalysisService.listDrugNumberAnalysis(drugNumberAnalysisCondition);
        Assert.assertEquals(1, list.size());

        Long isSuc = drugNumberAnalysisService.deleteDrugNumberAnalysis(list.get(0).getId());
        Assert.assertEquals(1, isSuc.intValue());

        DrugNumberAnalysis drugNumberAnalysis1 = drugNumberAnalysisService.getDrugNumberAnalysis(list.get(0).getId());
        Assert.assertNull(drugNumberAnalysis1);
    }

    @Test
    @Transactional
    public void updateDrugNumberAnalysis() {
        DrugNumberAnalysis drugNumberAnalysis = insertDrugNumberAnalysis();
        DrugNumberAnalysisCondition drugNumberAnalysisCondition = new DrugNumberAnalysisCondition();
        drugNumberAnalysisCondition.setDrugCode(drugNumberAnalysis.getDrugCode());
        List<DrugNumberAnalysis> list = drugNumberAnalysisService.listDrugNumberAnalysis(drugNumberAnalysisCondition);
        Assert.assertEquals(1, list.size());

        drugNumberAnalysis.setId(list.get(0).getId());
        drugNumberAnalysis.setDrugCode(UUID.randomUUID().toString());
        drugNumberAnalysis.setDrugName(UUID.randomUUID().toString());
        drugNumberAnalysis.setAvgDosage(222);
        drugNumberAnalysis.setHalfTotal(222);
        drugNumberAnalysis.setEstimationDosage(222);
        drugNumberAnalysis.setEstimationMonth(1.1);
        drugNumberAnalysis.setNumber(222);
        drugNumberAnalysis.setRequisitionQuantity(222);

        Long isSuc = drugNumberAnalysisService.updateDrugNumberAnalysis(drugNumberAnalysis);
        Assert.assertEquals(1, isSuc.intValue());

        DrugNumberAnalysis drugNumberAnalysis1 = drugNumberAnalysisService.getDrugNumberAnalysis(list.get(0).getId());
        Assert.assertEquals(drugNumberAnalysis.getDrugCode(),drugNumberAnalysis1.getDrugCode());
        Assert.assertEquals(drugNumberAnalysis.getDrugName(),drugNumberAnalysis1.getDrugName());
        Assert.assertEquals(drugNumberAnalysis.getAvgDosage(),drugNumberAnalysis1.getAvgDosage());
        Assert.assertEquals(drugNumberAnalysis.getHalfTotal(),drugNumberAnalysis1.getHalfTotal());
        Assert.assertEquals(drugNumberAnalysis.getEstimationDosage(),drugNumberAnalysis1.getEstimationDosage());
        Assert.assertEquals(drugNumberAnalysis.getEstimationMonth(),drugNumberAnalysis1.getEstimationMonth());

    }

}

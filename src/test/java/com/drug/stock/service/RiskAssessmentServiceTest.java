package com.drug.stock.service;

import com.drug.stock.entity.condition.RiskAssessmentCondition;
import com.drug.stock.entity.domain.RiskAssessment;
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
public class RiskAssessmentServiceTest {
    @Resource
    RiskAssessmentService riskAssessmentService;

    @Test
    @Transactional
    public void insertRiskAssessmentTest() {
        RiskAssessment riskAssessment = insertRiskAssessment();
        RiskAssessmentCondition riskAssessmentCondition = new RiskAssessmentCondition();
        riskAssessmentCondition.setDrugCode(riskAssessment.getDrugCode());
        List<RiskAssessment> list =riskAssessmentService.listRiskAssessment(riskAssessmentCondition);
        Assert.assertEquals(1,list.size());
    }

    private RiskAssessment insertRiskAssessment() {
        RiskAssessment riskAssessment = new RiskAssessment();
        riskAssessment.setDrugCode(UUID.randomUUID().toString());
        riskAssessment.setDrugName(UUID.randomUUID().toString());
        riskAssessment.setDrugStorage(UUID.randomUUID().toString());
        riskAssessment.setDelayedMaterialRisk(1);
        riskAssessment.setDrugWarehouseNumber(2);
        riskAssessment.setCreateUser("kongchao");
        riskAssessment.setUpdateUser("kongchao");
        Long isSuc = riskAssessmentService.insertRiskAssessment(riskAssessment);
        Assert.assertEquals(1, isSuc.intValue());
        return riskAssessment;
    }
    @Test
    @Transactional
    public void getRiskAssessmentTest(){
        RiskAssessment riskAssessment = insertRiskAssessment();
        RiskAssessmentCondition riskAssessmentCondition = new RiskAssessmentCondition();
        riskAssessmentCondition.setDrugCode(riskAssessment.getDrugCode());
        List<RiskAssessment> list =riskAssessmentService.listRiskAssessment(riskAssessmentCondition);
        Assert.assertEquals(1,list.size());
        riskAssessment = riskAssessmentService.getRiskAssessment(list.get(0).getId());
        Assert.assertNotNull(riskAssessment);
    }
    @Test
    @Transactional
    public void deleteRiskAssessmentTest(){
        RiskAssessment riskAssessment = insertRiskAssessment();
        RiskAssessmentCondition riskAssessmentCondition = new RiskAssessmentCondition();
        riskAssessmentCondition.setDrugCode(riskAssessment.getDrugCode());
        List<RiskAssessment> list =riskAssessmentService.listRiskAssessment(riskAssessmentCondition);
        Assert.assertEquals(1,list.size());
        Long isSuc = riskAssessmentService.deleteRiskAssessment(list.get(0).getId());
        Assert.assertEquals(1,isSuc.intValue());

        riskAssessment = riskAssessmentService.getRiskAssessment(list.get(0).getId());
        Assert.assertNull(riskAssessment);
    }
    @Test
    @Transactional
    public void updateRiskAssessmentTest(){
        RiskAssessment riskAssessment = insertRiskAssessment();
        RiskAssessmentCondition riskAssessmentCondition = new RiskAssessmentCondition();
        riskAssessmentCondition.setDrugCode(riskAssessment.getDrugCode());
        List<RiskAssessment> list =riskAssessmentService.listRiskAssessment(riskAssessmentCondition);
        riskAssessment.setId(list.get(0).getId());
        riskAssessment.setDrugCode(UUID.randomUUID().toString());
        riskAssessment.setDrugName(UUID.randomUUID().toString());
        riskAssessment.setDrugWarehouseNumber(2);
        riskAssessment.setDelayedMaterialRisk(2);
        riskAssessment.setDrugStorage(UUID.randomUUID().toString());

        Long isSuc = riskAssessmentService.insertRiskAssessment(riskAssessment);
        Assert.assertEquals(1,isSuc.intValue());

    }
}

package com.drug.stock.service;

import com.drug.stock.entity.condition.DrugCondition;
import com.drug.stock.entity.domain.Drug;
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
public class DrugServiceTest {
    @Resource
    DrugService drugService;

    private Drug createDrug() {
        Drug drug = new Drug();
        drug.setCode(UUID.randomUUID().toString());
        drug.setApprovalNumber(UUID.randomUUID().toString());
        drug.setDosageForm(UUID.randomUUID().toString());
        drug.setName(UUID.randomUUID().toString());
        drug.setPackaging(UUID.randomUUID().toString());
        drug.setNumber(111);
        drug.setSpecs(UUID.randomUUID().toString());
        drug.setStorage(UUID.randomUUID().toString());
        drug.setCreateUser("zhengwenju");
        drug.setUpdateUser("zhengwenju");
        return drug;
    }

    @Test
    @Transactional
    public void insertDrug() {
        Drug drug = createDrug();
        String code = drug.getCode();
        Long isSuc = drugService.insertDrug(drug);
        Assert.assertEquals(1, isSuc.intValue());
        drug = drugService.getDrugByCode(code);
        Assert.assertNotNull(drug);
        Assert.assertNotNull(drug.getApprovalNumber());
        Assert.assertNotNull(drug.getCode());
        Assert.assertNotNull(drug.getCreateTime());
        Assert.assertNotNull(drug.getCreateUser());
        Assert.assertNotNull(drug.getDelete());
        Assert.assertNotNull(drug.getDosageForm());
        Assert.assertNotNull(drug.getName());
        Assert.assertNotNull(drug.getNumber());
        Assert.assertNotNull(drug.getPackaging());
        Assert.assertNotNull(drug.getSpecs());
        Assert.assertNotNull(drug.getStorage());
        Assert.assertNotNull(drug.getUpdateTime());
        Assert.assertNotNull(drug.getUpdateUser());


    }

    @Test
    @Transactional
    public void getDrugTest() {
        Drug drug = createDrug();
        String code = drug.getCode();
        Long isSuc = drugService.insertDrug(drug);
        Assert.assertEquals(1, isSuc.intValue());
        drug = drugService.getDrugByCode(code);
        drug = drugService.getDrug(drug.getId());
        Assert.assertNotNull(drug);
    }

    @Test
    @Transactional
    public void updateDrugTest() throws InterruptedException {
        Drug drug = createDrug();
        String code = drug.getCode();
        Long isSuc = drugService.insertDrug(drug);
        Assert.assertEquals(1, isSuc.intValue());
        drug = drugService.getDrugByCode(code);

        drug.setCode(UUID.randomUUID().toString());
        drug.setApprovalNumber(UUID.randomUUID().toString());
        drug.setDosageForm(UUID.randomUUID().toString());
        drug.setName(UUID.randomUUID().toString());
        drug.setPackaging(UUID.randomUUID().toString());
        drug.setNumber(222);
        drug.setSpecs(UUID.randomUUID().toString());
        drug.setStorage(UUID.randomUUID().toString());

        Thread.sleep(1000);
        isSuc = drugService.updateDrug(drug);
        Assert.assertEquals(1, isSuc.intValue());

        Drug drug1 = drugService.getDrug(drug.getId());
        Assert.assertEquals(drug.getCode(), drug1.getCode());
        Assert.assertEquals(drug.getApprovalNumber(), drug1.getApprovalNumber());
        Assert.assertEquals(drug.getDosageForm(), drug1.getDosageForm());
        Assert.assertEquals(drug.getName(), drug1.getName());
        Assert.assertEquals(drug.getPackaging(), drug1.getPackaging());
        Assert.assertEquals(drug.getNumber(), drug1.getNumber());
        Assert.assertEquals(drug.getSpecs(), drug1.getSpecs());
        Assert.assertEquals(drug.getStorage(), drug1.getStorage());
        Assert.assertTrue(drug1.getCreateTime().before(drug1.getUpdateTime()));


    }

    @Test
    @Transactional
    public void deleteDrugTest() {
        Drug drug = createDrug();
        String code = drug.getCode();
        Long isSuc = drugService.insertDrug(drug);
        Assert.assertEquals(1, isSuc.intValue());
        drug = drugService.getDrugByCode(code);

        isSuc = drugService.deleteDrug(drug.getId());
        Assert.assertEquals(1, isSuc.intValue());

        drug = drugService.getDrug(drug.getId());
        Assert.assertNull(drug);
    }

    @Test
    @Transactional
    public void listDrugTest() {
        Drug drug = createDrug();
        String code = drug.getCode();
        Long isSuc = drugService.insertDrug(drug);
        Assert.assertEquals(1, isSuc.intValue());
        drug = drugService.getDrugByCode(code);

        DrugCondition drugCondition = new DrugCondition();
        drugCondition.setCode(drug.getCode());
        drugCondition.setApprovalNumber(drug.getApprovalNumber());
        drugCondition.setDosageForm(drug.getDosageForm());
        drugCondition.setName(drug.getName());
        drugCondition.setPackaging(drug.getPackaging());
        drugCondition.setNumber(drug.getNumber());
        drugCondition.setSpecs(drug.getSpecs());
        drugCondition.setStorage(drug.getStorage());

        List<Drug> drugList = drugService.listDrug(drugCondition);
        Assert.assertEquals(1,drugList.size());


    }

    @Test
    @Transactional
    public void countDrugByCodeTest() {
        Drug drug = createDrug();
        String code = drug.getCode();
        Long isSuc = drugService.insertDrug(drug);
        Assert.assertEquals(1, isSuc.intValue());
        Long num = drugService.countDrugByCode(code);
        Assert.assertEquals(1,num.intValue());
    }
}

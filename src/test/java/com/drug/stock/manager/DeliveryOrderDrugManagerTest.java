package com.drug.stock.manager;

import com.drug.stock.entity.condition.DeliveryOrderDrugCondition;
import com.drug.stock.entity.domain.DeliveryOrderDrug;
import com.drug.stock.manager.DeliveryOrderDrugManager;
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
public class DeliveryOrderDrugManagerTest {
    @Resource
    DeliveryOrderDrugManager deliveryOrderDrugManager;

    private DeliveryOrderDrug createDeliveryOrderDrug() {
        DeliveryOrderDrug deliveryOrderDrug = new DeliveryOrderDrug();
        deliveryOrderDrug.setCode(UUID.randomUUID().toString());
        deliveryOrderDrug.setDrugName(UUID.randomUUID().toString());
        deliveryOrderDrug.setDrugCode(UUID.randomUUID().toString());

        deliveryOrderDrug.setNumber(111);
        deliveryOrderDrug.setPrice(11.11);
        deliveryOrderDrug.setCreateUser("zhengwenju");
        deliveryOrderDrug.setUpdateUser("zhengwenju");
        return deliveryOrderDrug;
    }

    @Test
    @Transactional
    public void insertDeliveryOrderDrugTest() {
        DeliveryOrderDrug deliveryOrderDrug = createDeliveryOrderDrug();
        String code = deliveryOrderDrug.getCode();
        String drugCode = deliveryOrderDrug.getDrugCode();
        Long isSuc = deliveryOrderDrugManager.insertDeliveryOrderDrug(deliveryOrderDrug);
        Assert.assertTrue(isSuc > 0);
        deliveryOrderDrug = deliveryOrderDrugManager.getDeliveryOrderDrugByCodeAndDrugCode(code, drugCode);
        Assert.assertNotNull(deliveryOrderDrug);
        Assert.assertNotNull(deliveryOrderDrug.getDrugCode());
        Assert.assertNotNull(deliveryOrderDrug.getCreateUser());
        Assert.assertNotNull(deliveryOrderDrug.getUpdateUser());

        Assert.assertEquals(111, deliveryOrderDrug.getNumber().intValue());
        Assert.assertEquals(11.11, deliveryOrderDrug.getPrice().doubleValue(), 2.0);
        Assert.assertNotNull(deliveryOrderDrug.getCreateTime());
        Assert.assertNotNull(deliveryOrderDrug.getUpdateTime());
        Assert.assertNotNull(deliveryOrderDrug.getDrugName());
    }

    @Test
    @Transactional
    public void getDeliveryOrderDrugTest() {
        DeliveryOrderDrug deliveryOrderDrug = createDeliveryOrderDrug();
        String code = deliveryOrderDrug.getCode();
        String drugCode = deliveryOrderDrug.getDrugCode();
        Long isSuc = deliveryOrderDrugManager.insertDeliveryOrderDrug(deliveryOrderDrug);
        Assert.assertTrue(isSuc > 0);
        deliveryOrderDrug = deliveryOrderDrugManager.getDeliveryOrderDrugByCodeAndDrugCode(code, drugCode);
        deliveryOrderDrug = deliveryOrderDrugManager.getDeliveryOrderDrug(deliveryOrderDrug.getId());
        Assert.assertNotNull(deliveryOrderDrug);
    }

    @Test
    @Transactional
    public void updateDeliveryOrderDrugTest() throws InterruptedException {
        DeliveryOrderDrug deliveryOrderDrug = createDeliveryOrderDrug();
        String code = deliveryOrderDrug.getCode();
        String drugCode = deliveryOrderDrug.getDrugCode();
        Long isSuc = deliveryOrderDrugManager.insertDeliveryOrderDrug(deliveryOrderDrug);
        Assert.assertTrue(isSuc > 0);
        deliveryOrderDrug = deliveryOrderDrugManager.getDeliveryOrderDrugByCodeAndDrugCode(code, drugCode);

        Assert.assertNotNull(deliveryOrderDrug);
        deliveryOrderDrug.setCode(UUID.randomUUID().toString());
        deliveryOrderDrug.setDrugCode(UUID.randomUUID().toString());
        deliveryOrderDrug.setDrugName(UUID.randomUUID().toString());

        deliveryOrderDrug.setNumber(222);
        deliveryOrderDrug.setPrice(22.11);
        String newCode = deliveryOrderDrug.getCode();
        String newDrugCode = deliveryOrderDrug.getDrugCode();
        Thread.sleep(1000);
        isSuc = deliveryOrderDrugManager.updateDeliveryOrderDrug(deliveryOrderDrug);
        Assert.assertTrue(isSuc > 0);

        DeliveryOrderDrug deliveryOrderDrug1 = deliveryOrderDrugManager.getDeliveryOrderDrugByCodeAndDrugCode(newCode, newDrugCode);
        Assert.assertNotNull(deliveryOrderDrug);
        Assert.assertEquals(deliveryOrderDrug.getCode(), deliveryOrderDrug1.getCode());
        Assert.assertEquals(deliveryOrderDrug.getDrugCode(), deliveryOrderDrug1.getDrugCode());
        Assert.assertEquals(deliveryOrderDrug.getNumber(), deliveryOrderDrug1.getNumber());
        Assert.assertEquals(deliveryOrderDrug.getPrice(), deliveryOrderDrug1.getPrice(), 1.0);

        Assert.assertTrue(deliveryOrderDrug1.getUpdateTime().after(deliveryOrderDrug1.getCreateTime()));
        Assert.assertEquals(deliveryOrderDrug.getDrugName(), deliveryOrderDrug1.getDrugName());
    }

    @Test
    @Transactional
    public void deleteDeliveryOrderDrugTest() {
        DeliveryOrderDrug deliveryOrderDrug = createDeliveryOrderDrug();
        String code = deliveryOrderDrug.getCode();
        String drugCode = deliveryOrderDrug.getDrugCode();
        Long isSuc = deliveryOrderDrugManager.insertDeliveryOrderDrug(deliveryOrderDrug);
        Assert.assertTrue(isSuc > 0);
        deliveryOrderDrug = deliveryOrderDrugManager.getDeliveryOrderDrugByCodeAndDrugCode(code, drugCode);
        isSuc = deliveryOrderDrugManager.deleteDeliveryOrderDrug(deliveryOrderDrug.getId());
        Assert.assertTrue(isSuc > 0);
        deliveryOrderDrug = deliveryOrderDrugManager.getDeliveryOrderDrug(deliveryOrderDrug.getId());
        Assert.assertNull(deliveryOrderDrug);
    }

    @Test
    @Transactional
    public void countDeliveryOrderDrugByCode() {
        DeliveryOrderDrug deliveryOrderDrug = createDeliveryOrderDrug();
        String code = deliveryOrderDrug.getCode();
        String drugCode = deliveryOrderDrug.getDrugCode();
        Long isSuc = deliveryOrderDrugManager.insertDeliveryOrderDrug(deliveryOrderDrug);
        Assert.assertTrue(isSuc > 0);
        Long num = deliveryOrderDrugManager.countDeliveryOrderDrugByCodeAndDrugCode(code,drugCode);
        Assert.assertTrue(num == 1);
    }

    @Test
    @Transactional
    public void listDeliveryOrderDrugTest() {
        DeliveryOrderDrug deliveryOrderDrug = createDeliveryOrderDrug();
        String code = deliveryOrderDrug.getCode();
        Long isSuc = deliveryOrderDrugManager.insertDeliveryOrderDrug(deliveryOrderDrug);
        Assert.assertTrue(isSuc > 0);
        DeliveryOrderDrugCondition deliveryOrderDrugCondition = new DeliveryOrderDrugCondition();
        deliveryOrderDrugCondition.setCode(code);
        deliveryOrderDrugCondition.setDrugCode(deliveryOrderDrug.getDrugCode());
        deliveryOrderDrugCondition.setPrice(deliveryOrderDrug.getPrice());
        List<DeliveryOrderDrug> purchaseOrderDrugList = deliveryOrderDrugManager.listDeliveryOrderDrug(deliveryOrderDrugCondition);
        Assert.assertTrue(purchaseOrderDrugList.size() == 1);

    }
}

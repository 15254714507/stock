package com.drug.stock.manager;

import com.drug.stock.entity.condition.PurchaseOrderDrugCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
import com.drug.stock.entity.domain.PurchaseOrderDrug;
import com.drug.stock.manager.PurchaseOrderDrugManager;
import com.drug.stock.manager.PurchaseOrderManager;
import com.drug.stock.until.TimestampFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseOrderDrugManagerTest {
    @Resource
    PurchaseOrderDrugManager purchaseOrderDrugManager;
    @Resource
    PurchaseOrderManager purchaseOrderManager;

    private PurchaseOrderDrug createPurchaseOrderDrug() {
        PurchaseOrderDrug purchaseOrderDrug = new PurchaseOrderDrug();
        purchaseOrderDrug.setCode(UUID.randomUUID().toString());
        purchaseOrderDrug.setDrugCode(UUID.randomUUID().toString());
        purchaseOrderDrug.setExpireDate(TimestampFactory.getTimestamp());
        purchaseOrderDrug.setNumber(111);
        purchaseOrderDrug.setPrice(11.11);
        purchaseOrderDrug.setProductionLotNumber(UUID.randomUUID().toString());
        purchaseOrderDrug.setProviderId(111L);
        purchaseOrderDrug.setProviderName(UUID.randomUUID().toString());
        purchaseOrderDrug.setCreateUser("zhengwenju");
        purchaseOrderDrug.setUpdateUser("zhengwenju");
        purchaseOrderDrug.setDrugName("药名");
        return purchaseOrderDrug;
    }

    @Test
    @Transactional
    public void insertPurchaseOrderDrugTest() {
        PurchaseOrderDrug purchaseOrderDrug = createPurchaseOrderDrug();
        String code = purchaseOrderDrug.getCode();
        String drugCode = purchaseOrderDrug.getDrugCode();
        Long isSuc = purchaseOrderDrugManager.insertPurchaseOrderDrug(purchaseOrderDrug);
        Assert.assertTrue(isSuc > 0);
        purchaseOrderDrug = purchaseOrderDrugManager.getPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);
        Assert.assertNotNull(purchaseOrderDrug);
        Assert.assertNotNull(purchaseOrderDrug.getDrugCode());
        Assert.assertNotNull(purchaseOrderDrug.getExpireDate());
        Assert.assertNotNull(purchaseOrderDrug.getProductionLotNumber());
        Assert.assertNotNull(purchaseOrderDrug.getProviderName());
        Assert.assertNotNull(purchaseOrderDrug.getCreateUser());
        Assert.assertNotNull(purchaseOrderDrug.getUpdateUser());

        Assert.assertEquals(111, purchaseOrderDrug.getNumber().intValue());
        Assert.assertEquals(111L, purchaseOrderDrug.getProviderId().intValue());
        Assert.assertEquals(11.11, purchaseOrderDrug.getPrice().doubleValue(), 2.0);
        Assert.assertNotNull(purchaseOrderDrug.getCreateTime());
        Assert.assertNotNull(purchaseOrderDrug.getUpdateTime());
        Assert.assertNotNull(purchaseOrderDrug.getDrugName());
    }

    @Test
    @Transactional
    public void getPurchaseOrderDrugTest() {
        PurchaseOrderDrug purchaseOrderDrug = createPurchaseOrderDrug();
        String code = purchaseOrderDrug.getCode();
        String drugCode = purchaseOrderDrug.getDrugCode();
        Long isSuc = purchaseOrderDrugManager.insertPurchaseOrderDrug(purchaseOrderDrug);
        Assert.assertTrue(isSuc > 0);
        purchaseOrderDrug = purchaseOrderDrugManager.getPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);
        purchaseOrderDrug = purchaseOrderDrugManager.getPurchaseOrderDrug(purchaseOrderDrug.getId());
        Assert.assertNotNull(purchaseOrderDrug);
    }

    @Test
    @Transactional
    public void update() throws InterruptedException {
        PurchaseOrderDrug purchaseOrderDrug = createPurchaseOrderDrug();
        String code = purchaseOrderDrug.getCode();
        String drugCode = purchaseOrderDrug.getDrugCode();
        Long isSuc = purchaseOrderDrugManager.insertPurchaseOrderDrug(purchaseOrderDrug);
        Assert.assertTrue(isSuc > 0);
        purchaseOrderDrug = purchaseOrderDrugManager.getPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);

        Assert.assertNotNull(purchaseOrderDrug);
        purchaseOrderDrug.setCode(UUID.randomUUID().toString());
        purchaseOrderDrug.setDrugCode(UUID.randomUUID().toString());
        purchaseOrderDrug.setExpireDate(TimestampFactory.getTimestamp());
        purchaseOrderDrug.setNumber(222);
        purchaseOrderDrug.setPrice(22.11);
        purchaseOrderDrug.setProductionLotNumber(UUID.randomUUID().toString());
        purchaseOrderDrug.setProviderId(222L);
        purchaseOrderDrug.setProviderName(UUID.randomUUID().toString());
        purchaseOrderDrug.setDrugName(UUID.randomUUID().toString());

        String newCode = purchaseOrderDrug.getCode();
        String newDrugCode = purchaseOrderDrug.getDrugCode();
        Thread.sleep(1000);
        isSuc = purchaseOrderDrugManager.updatePurchaseOrderDrug(purchaseOrderDrug);
        Assert.assertTrue(isSuc > 0);

        PurchaseOrderDrug purchaseOrderDrug1 = purchaseOrderDrugManager.getPurchaseOrderDrugByCodeAndDrugCode(newCode, newDrugCode);
        Assert.assertNotNull(purchaseOrderDrug);
        Assert.assertEquals(purchaseOrderDrug.getCode(), purchaseOrderDrug1.getCode());
        Assert.assertEquals(purchaseOrderDrug.getDrugCode(), purchaseOrderDrug1.getDrugCode());
        Assert.assertEquals(1, purchaseOrderDrug.getExpireDate().compareTo(purchaseOrderDrug1.getExpireDate()));
        Assert.assertEquals(purchaseOrderDrug.getNumber(), purchaseOrderDrug1.getNumber());
        Assert.assertEquals(purchaseOrderDrug.getPrice(), purchaseOrderDrug1.getPrice(), 1.0);
        Assert.assertEquals(purchaseOrderDrug.getProductionLotNumber(), purchaseOrderDrug1.getProductionLotNumber());
        Assert.assertEquals(purchaseOrderDrug.getProviderId(), purchaseOrderDrug1.getProviderId());
        Assert.assertEquals(purchaseOrderDrug.getProviderName(), purchaseOrderDrug1.getProviderName());
        Assert.assertEquals(purchaseOrderDrug.getDrugName(), purchaseOrderDrug1.getDrugName());
        Assert.assertTrue(purchaseOrderDrug1.getUpdateTime().after(purchaseOrderDrug1.getCreateTime()));

    }

    @Test
    @Transactional
    public void deletePurchaseOrderDrugTest() {
        PurchaseOrderDrug purchaseOrderDrug = createPurchaseOrderDrug();
        String code = purchaseOrderDrug.getCode();
        String drugCode = purchaseOrderDrug.getDrugCode();
        Long isSuc = purchaseOrderDrugManager.insertPurchaseOrderDrug(purchaseOrderDrug);
        Assert.assertTrue(isSuc > 0);
        purchaseOrderDrug = purchaseOrderDrugManager.getPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);
        isSuc = purchaseOrderDrugManager.deletePurchaseOrderDrug(purchaseOrderDrug.getId());
        Assert.assertTrue(isSuc > 0);
        purchaseOrderDrug = purchaseOrderDrugManager.getPurchaseOrderDrug(purchaseOrderDrug.getId());
        Assert.assertNull(purchaseOrderDrug);
    }

    @Test
    @Transactional
    public void countPurchaseOrderDrugByCode() {
        PurchaseOrderDrug purchaseOrderDrug = createPurchaseOrderDrug();
        String code = purchaseOrderDrug.getCode();
        String drugCode = purchaseOrderDrug.getDrugCode();
        Long isSuc = purchaseOrderDrugManager.insertPurchaseOrderDrug(purchaseOrderDrug);
        Assert.assertTrue(isSuc > 0);
        Long num = purchaseOrderDrugManager.countPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);
        Assert.assertTrue(num == 1);
    }

    @Test
    @Transactional
    public void listPurchaseOrderDrug() {
        PurchaseOrderDrug purchaseOrderDrug = createPurchaseOrderDrug();
        String code = purchaseOrderDrug.getCode();
        Long isSuc = purchaseOrderDrugManager.insertPurchaseOrderDrug(purchaseOrderDrug);
        Assert.assertTrue(isSuc > 0);
        PurchaseOrderDrugCondition purchaseOrderDrugCondition = new PurchaseOrderDrugCondition();
        purchaseOrderDrugCondition.setCode(code);
        purchaseOrderDrugCondition.setDrugCode(purchaseOrderDrug.getDrugCode());
        purchaseOrderDrugCondition.setProviderId(purchaseOrderDrug.getProviderId());
        purchaseOrderDrugCondition.setProviderName(purchaseOrderDrug.getProviderName());
        purchaseOrderDrugCondition.setPrice(purchaseOrderDrug.getPrice());
        purchaseOrderDrugCondition.setExpireDate(purchaseOrderDrug.getExpireDate());
        purchaseOrderDrugCondition.setDrugName(purchaseOrderDrug.getDrugName());
        List<PurchaseOrderDrug> purchaseOrderDrugList = purchaseOrderDrugManager.listPurchaseOrderDrug(purchaseOrderDrugCondition);
        Assert.assertTrue(purchaseOrderDrugList.size() == 1);

    }

    @Test
    @Transactional
    public void listNotOverdueDrug() {
        //通过入库药品信息表中没过期的药品，他们的入库单应该是已发布的状态
        PurchaseOrderDrugCondition purchaseOrderDrugCondition = new PurchaseOrderDrugCondition();
        List<PurchaseOrderDrug> list1 = purchaseOrderDrugManager.listNotOverdueDrug(purchaseOrderDrugCondition);
        HashMap<String, Object> map = new HashMap<>();
        for (PurchaseOrderDrug purchaseOrderDrug : list1) {
            map.put(purchaseOrderDrug.getCode(), new Object());
        }

        for (String code : map.keySet()) {
            PurchaseOrder purchaseOrder = purchaseOrderManager.getPurchaseOrderByCode(code);
            Assert.assertNotNull(purchaseOrder);
            Assert.assertTrue(purchaseOrder.getStatus());
        }
    }

    @Test
    @Transactional
    public void deleteBatchPurchaseOrderDrugByCode() {
        PurchaseOrderDrug purchaseOrderDrug = createPurchaseOrderDrug();
        String code = purchaseOrderDrug.getCode();
        String drugCode = purchaseOrderDrug.getDrugCode();
        Long isSuc = purchaseOrderDrugManager.insertPurchaseOrderDrug(purchaseOrderDrug);
        Assert.assertTrue(isSuc > 0);
        isSuc = purchaseOrderDrugManager.deleteBatchPurchaseOrderDrugByCode(code);
        Assert.assertEquals(1, (long) isSuc);
        purchaseOrderDrug = purchaseOrderDrugManager.getPurchaseOrderDrugByCodeAndDrugCode(code, drugCode);
        Assert.assertNull(purchaseOrderDrug);
    }
}

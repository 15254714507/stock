package com.drug.stock.service;

import com.drug.stock.entity.condition.PurchaseOrderCondition;
import com.drug.stock.entity.domain.PurchaseOrder;
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
public class PurchaseOrderTest {
    @Resource
    PurchaseOrderService purchaseOrderService;

    private PurchaseOrder createPurchaseOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCode(UUID.randomUUID().toString());
        purchaseOrder.setDescription(UUID.randomUUID().toString());
        purchaseOrder.setUserAccount(UUID.randomUUID().toString());
        purchaseOrder.setUserName(UUID.randomUUID().toString());
        purchaseOrder.setStatus(true);
        purchaseOrder.setCreateUser("kongchao");
        purchaseOrder.setUpdateUser("kongchao");
        return purchaseOrder;
    }


    @Test
    @Transactional
    public void insertPurchaseOrder() {
        PurchaseOrder purchaseOrder = createPurchaseOrder();
        String code = purchaseOrder.getCode();
        Long num = purchaseOrderService.insertPurchaseOrder(purchaseOrder);
        Assert.assertTrue(num.intValue() > 0);
        purchaseOrder = purchaseOrderService.getPurchaseOrderByCode(code);
        Assert.assertNotNull(purchaseOrder);
        Assert.assertNotNull(purchaseOrder.getCode());
        Assert.assertNotNull(purchaseOrder.getDescription());
        Assert.assertNotNull(purchaseOrder.getUserAccount());
        Assert.assertNotNull(purchaseOrder.getUserName());
        Assert.assertNotNull(purchaseOrder.getCreateUser());
        Assert.assertNotNull(purchaseOrder.getUpdateUser());
        Assert.assertNotNull(purchaseOrder.getCreateTime());
        Assert.assertNotNull(purchaseOrder.getCreateTime());
        Assert.assertEquals(false, purchaseOrder.getDelete());
        Assert.assertEquals(1, purchaseOrder.getVersion().intValue());
        Assert.assertFalse(purchaseOrder.getStatus());
    }

    @Test
    @Transactional
    public void getPurchaseOrderTest() {
        PurchaseOrder purchaseOrder = createPurchaseOrder();
        String code = purchaseOrder.getCode();
        Long num = purchaseOrderService.insertPurchaseOrder(purchaseOrder);
        Assert.assertTrue(num.intValue() > 0);
        purchaseOrder = purchaseOrderService.getPurchaseOrderByCode(code);
        purchaseOrder = purchaseOrderService.getPurchaseOrder(purchaseOrder.getId());
        Assert.assertNotNull(purchaseOrder);
    }

    @Test
    @Transactional
    public void updatePurchaseOrderTest() throws InterruptedException {
        PurchaseOrder purchaseOrder = createPurchaseOrder();
        String code = purchaseOrder.getCode();
        Long num = purchaseOrderService.insertPurchaseOrder(purchaseOrder);
        Assert.assertTrue(num.intValue() > 0);
        purchaseOrder = purchaseOrderService.getPurchaseOrderByCode(code);
        purchaseOrder.setCode(UUID.randomUUID().toString());
        purchaseOrder.setUserAccount(UUID.randomUUID().toString());
        purchaseOrder.setUserName(UUID.randomUUID().toString());
        purchaseOrder.setDescription(UUID.randomUUID().toString());
        purchaseOrder.setStatus(true);
        //休眠一秒，要不修改后的时间点和创建的时间点一致了
        Thread.sleep(1000);
        num = purchaseOrderService.updatePurchaseOrder(purchaseOrder);
        Assert.assertTrue(num.intValue() > 0);

        PurchaseOrder purchaseOrder1 = purchaseOrderService.getPurchaseOrderByCode(purchaseOrder.getCode());
        Assert.assertEquals(purchaseOrder.getCode(), purchaseOrder1.getCode());
        Assert.assertEquals(purchaseOrder.getUserAccount(), purchaseOrder1.getUserAccount());
        Assert.assertEquals(purchaseOrder.getUserName(), purchaseOrder1.getUserName());
        Assert.assertEquals(purchaseOrder.getDescription(), purchaseOrder1.getDescription());
        Assert.assertTrue(purchaseOrder1.getCreateTime().before(purchaseOrder1.getUpdateTime()));
        Assert.assertEquals(2, purchaseOrder1.getVersion().intValue());
        Assert.assertTrue(purchaseOrder1.getStatus());

    }

    @Test
    @Transactional
    public void deletePurchaseOrderTest() {
        PurchaseOrder purchaseOrder = createPurchaseOrder();
        String code = purchaseOrder.getCode();
        Long num = purchaseOrderService.insertPurchaseOrder(purchaseOrder);
        Assert.assertTrue(num.intValue() > 0);
        purchaseOrder = purchaseOrderService.getPurchaseOrderByCode(code);
        num = purchaseOrderService.deletePurchaseOrder(purchaseOrder.getId());
        Assert.assertTrue(num > 0);
        purchaseOrder = purchaseOrderService.getPurchaseOrderByCode(code);
        Assert.assertNull(purchaseOrder);
    }

    @Test
    @Transactional
    public void countPurchaseOrderByCodeTest() {
        PurchaseOrder purchaseOrder = createPurchaseOrder();
        String code = purchaseOrder.getCode();
        Long num = purchaseOrderService.insertPurchaseOrder(purchaseOrder);
        Assert.assertTrue(num.intValue() > 0);
        purchaseOrder = purchaseOrderService.getPurchaseOrderByCode(code);
        num = purchaseOrderService.deletePurchaseOrder(purchaseOrder.getId());
        Assert.assertTrue(num > 0);
    }

    @Test
    @Transactional
    public void listPurchaseOrderTest() {
        PurchaseOrder purchaseOrder = createPurchaseOrder();
        PurchaseOrderCondition purchaseOrderCondition = new PurchaseOrderCondition();
        purchaseOrderCondition.setCode(purchaseOrder.getCode());
        purchaseOrderCondition.setUserAccount(purchaseOrder.getUserAccount());
        purchaseOrderCondition.setUserName(purchaseOrder.getUserName());

        Long num = purchaseOrderService.insertPurchaseOrder(purchaseOrder);
        Assert.assertTrue(num.intValue() > 0);


        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.listPurchaseOrder(purchaseOrderCondition);
        Assert.assertTrue(purchaseOrderList.size() == 1);
    }

}

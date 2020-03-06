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
import java.util.Objects;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseOrderServiceTest {
    @Resource
    PurchaseOrderService purchaseOrderService;

    @Test
    @Transactional
    public void insertPurchaseOrderTest() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        String des = UUID.randomUUID().toString();
        purchaseOrder.setDescription(des);
        purchaseOrder.setCreateUser("zhengwen1");
        purchaseOrder.setUpdateUser("zhengwen1");
        purchaseOrder.setUserAccount(purchaseOrder.getCreateUser());
        purchaseOrderService.insertPurchaseOrder(purchaseOrder);

        PurchaseOrderCondition purchaseOrderCondition = new PurchaseOrderCondition();
        List<PurchaseOrder> list = purchaseOrderService.listPurchaseOrder(purchaseOrderCondition);
        boolean flag = false;
        for(PurchaseOrder purchaseOrder1 :list){
            if(Objects.equals(purchaseOrder1.getDescription(),des)){
                Assert.assertEquals(17,purchaseOrder1.getCode().length());
                Assert.assertNotNull(purchaseOrder1.getUserName());
                Assert.assertEquals(1,purchaseOrder1.getVersion().intValue());
                flag=true;
            }
        }
        Assert.assertTrue(flag);
    }
}

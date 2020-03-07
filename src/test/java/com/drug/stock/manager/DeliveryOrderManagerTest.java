package com.drug.stock.manager;

import com.drug.stock.entity.condition.DeliveryOrderCondition;
import com.drug.stock.entity.domain.DeliveryOrder;
import com.github.pagehelper.PageInfo;
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
public class DeliveryOrderManagerTest {
    @Resource
    DeliveryOrderManager deliveryOrderManager;

    private DeliveryOrder createDeliveryOrder() {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setCode(UUID.randomUUID().toString());
        deliveryOrder.setDescription(UUID.randomUUID().toString());
        deliveryOrder.setUserAccount(UUID.randomUUID().toString());
        deliveryOrder.setUserName(UUID.randomUUID().toString());
        deliveryOrder.setCreateUser("kongchao");
        deliveryOrder.setUpdateUser("kongchao");
        return deliveryOrder;
    }


    @Test
    @Transactional
    public void insertDeliveryOrderTest() {
        DeliveryOrder deliveryOrder = createDeliveryOrder();
        String code = deliveryOrder.getCode();
        Long num = deliveryOrderManager.insertDeliveryOrder(deliveryOrder);
        Assert.assertTrue(num.intValue() > 0);
        deliveryOrder = deliveryOrderManager.getDeliveryOrderByCode(code);
        Assert.assertNotNull(deliveryOrder);
        Assert.assertNotNull(deliveryOrder.getCode());
        Assert.assertNotNull(deliveryOrder.getDescription());
        Assert.assertNotNull(deliveryOrder.getUserAccount());
        Assert.assertNotNull(deliveryOrder.getUserName());
        Assert.assertNotNull(deliveryOrder.getCreateUser());
        Assert.assertNotNull(deliveryOrder.getUpdateUser());
        Assert.assertNotNull(deliveryOrder.getCreateTime());
        Assert.assertNotNull(deliveryOrder.getCreateTime());
        Assert.assertEquals(false, deliveryOrder.getDelete());
        Assert.assertEquals(1, deliveryOrder.getVersion().intValue());
        Assert.assertFalse(deliveryOrder.getStatus());
    }

    @Test
    @Transactional
    public void getDeliveryOrderTest() {
        DeliveryOrder deliveryOrder = createDeliveryOrder();
        String code = deliveryOrder.getCode();
        Long num = deliveryOrderManager.insertDeliveryOrder(deliveryOrder);
        Assert.assertTrue(num.intValue() > 0);
        deliveryOrder = deliveryOrderManager.getDeliveryOrderByCode(code);
        deliveryOrder = deliveryOrderManager.getDeliveryOrder(deliveryOrder.getId());
        Assert.assertNotNull(deliveryOrder);
    }

    @Test
    @Transactional
    public void updateDeliveryOrderTest() throws InterruptedException {
        DeliveryOrder deliveryOrder = createDeliveryOrder();
        String code = deliveryOrder.getCode();
        Long num = deliveryOrderManager.insertDeliveryOrder(deliveryOrder);
        Assert.assertTrue(num.intValue() > 0);
        deliveryOrder = deliveryOrderManager.getDeliveryOrderByCode(code);
        deliveryOrder.setCode(UUID.randomUUID().toString());
        deliveryOrder.setUserAccount(UUID.randomUUID().toString());
        deliveryOrder.setUserName(UUID.randomUUID().toString());
        deliveryOrder.setDescription(UUID.randomUUID().toString());
        deliveryOrder.setStatus(true);
        //休眠一秒，要不修改后的时间点和创建的时间点一致了
        Thread.sleep(1000);
        num = deliveryOrderManager.updateDeliveryOrder(deliveryOrder);
        Assert.assertTrue(num.intValue() > 0);

        DeliveryOrder deliverOrder1 = deliveryOrderManager.getDeliveryOrderByCode(deliveryOrder.getCode());
        Assert.assertEquals(deliveryOrder.getCode(), deliverOrder1.getCode());
        Assert.assertEquals(deliveryOrder.getUserAccount(), deliverOrder1.getUserAccount());
        Assert.assertEquals(deliveryOrder.getUserName(), deliverOrder1.getUserName());
        Assert.assertEquals(deliveryOrder.getDescription(), deliverOrder1.getDescription());
        Assert.assertTrue(deliverOrder1.getCreateTime().before(deliverOrder1.getUpdateTime()));
        Assert.assertEquals(2, deliverOrder1.getVersion().intValue());
        Assert.assertTrue(deliverOrder1.getStatus());

    }

    @Test
    @Transactional
    public void deleteDeliveryOrderTest() {
        DeliveryOrder deliveryOrder = createDeliveryOrder();
        String code = deliveryOrder.getCode();
        Long num = deliveryOrderManager.insertDeliveryOrder(deliveryOrder);
        Assert.assertTrue(num.intValue() > 0);
        deliveryOrder = deliveryOrderManager.getDeliveryOrderByCode(code);
        num = deliveryOrderManager.deleteDeliveryOrder(deliveryOrder.getId());
        Assert.assertTrue(num > 0);
        deliveryOrder = deliveryOrderManager.getDeliveryOrderByCode(code);
        Assert.assertNull(deliveryOrder);
    }

    @Test
    @Transactional
    public void countDeliveryOrderByCodeTest() {
        DeliveryOrder deliveryOrder = createDeliveryOrder();
        String code = deliveryOrder.getCode();
        Long num = deliveryOrderManager.insertDeliveryOrder(deliveryOrder);
        Assert.assertTrue(num.intValue() > 0);
        deliveryOrder = deliveryOrderManager.getDeliveryOrderByCode(code);
        num = deliveryOrderManager.deleteDeliveryOrder(deliveryOrder.getId());
        Assert.assertTrue(num > 0);
    }

    @Test
    @Transactional
    public void listDeliveryOrderTest() {
        DeliveryOrder deliveryOrder = createDeliveryOrder();
        DeliveryOrderCondition deliveryOrderCondition = new DeliveryOrderCondition();
        deliveryOrderCondition.setCode(deliveryOrder.getCode());
        deliveryOrderCondition.setUserAccount(deliveryOrder.getUserAccount());
        deliveryOrderCondition.setUserName(deliveryOrder.getUserName());

        Long num = deliveryOrderManager.insertDeliveryOrder(deliveryOrder);
        Assert.assertTrue(num.intValue() > 0);


        List<DeliveryOrder> purchaseOrderList = deliveryOrderManager.listDeliveryOrder(deliveryOrderCondition);
        Assert.assertTrue(purchaseOrderList.size() == 1);
    }
    @Test
    @Transactional
    public void findDeliveryOrderPageTest(){
        DeliveryOrder deliveryOrder = createDeliveryOrder();
        String code = deliveryOrder.getCode();
        Long num = deliveryOrderManager.insertDeliveryOrder(deliveryOrder);
        Assert.assertTrue(num.intValue() > 0);
        DeliveryOrderCondition deliveryOrderCondition = new DeliveryOrderCondition();
        PageInfo<DeliveryOrder> pageInfo = deliveryOrderManager.findDeliveryOrderPage(deliveryOrderCondition);
        Assert.assertTrue(pageInfo.getList().size()>0);
    }

}

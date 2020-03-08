package com.drug.stock.service;

import com.drug.stock.entity.condition.OverdueDrugCondition;
import com.drug.stock.entity.domain.OverdueDrug;
import com.drug.stock.until.TimestampFactory;
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
public class OverdueDrugServiceTest {
    @Resource
    OverdueDrugService overdueDrugService;

    private OverdueDrug insertOverdueDrug() {
        OverdueDrug overdueDrug = new OverdueDrug();
        overdueDrug.setDrugCode(UUID.randomUUID().toString());
        overdueDrug.setDrugName(UUID.randomUUID().toString());
        overdueDrug.setDrugSpecs(UUID.randomUUID().toString());
        overdueDrug.setProcessMode(UUID.randomUUID().toString());
        overdueDrug.setExpireDate(TimestampFactory.getTimestamp());
        overdueDrug.setUpdateTime(TimestampFactory.getTimestamp());
        overdueDrug.setCreateTime(TimestampFactory.getTimestamp());
        overdueDrug.setNumber(222);
        Long isSuc = overdueDrugService.insertOverdueDrug(overdueDrug);
        Assert.assertEquals(1, isSuc.intValue());
        return overdueDrug;
    }

    @Test
    @Transactional
    public void insertOverdueDrugTest() {
        OverdueDrug overdueDrug = insertOverdueDrug();
        OverdueDrugCondition overdueDrugCondition = new OverdueDrugCondition();
        overdueDrugCondition.setDrugCode(overdueDrug.getDrugCode());
        overdueDrugCondition.setDrugName(overdueDrug.getDrugName());
        List<OverdueDrug> list = overdueDrugService.listOverdueDrug(overdueDrugCondition);
        Assert.assertEquals(1, list.size());
    }

    @Test
    @Transactional
    public void getOverdueDrug() {
        OverdueDrug overdueDrug = insertOverdueDrug();
        OverdueDrugCondition overdueDrugCondition = new OverdueDrugCondition();
        overdueDrugCondition.setDrugCode(overdueDrug.getDrugCode());
        overdueDrugCondition.setDrugName(overdueDrug.getDrugName());
        List<OverdueDrug> list = overdueDrugService.listOverdueDrug(overdueDrugCondition);
        Assert.assertEquals(1, list.size());
        overdueDrug = overdueDrugService.getOverdueDrug(list.get(0).getId());
        Assert.assertNotNull(overdueDrug);
    }

    @Test
    @Transactional
    public void deleteOverdueDrug() {
        OverdueDrug overdueDrug = insertOverdueDrug();
        OverdueDrugCondition overdueDrugCondition = new OverdueDrugCondition();
        overdueDrugCondition.setDrugCode(overdueDrug.getDrugCode());
        overdueDrugCondition.setDrugName(overdueDrug.getDrugName());
        List<OverdueDrug> list = overdueDrugService.listOverdueDrug(overdueDrugCondition);
        Assert.assertEquals(1, list.size());

        Long isSuc = overdueDrugService.deleteOverdueDrug(list.get(0).getId());
        Assert.assertEquals(1,isSuc.intValue());
        overdueDrug = overdueDrugService.getOverdueDrug(list.get(0).getId());
        Assert.assertNull(overdueDrug);
    }
    @Test
    @Transactional
    public void updateOverdueDrug() {
        OverdueDrug overdueDrug = insertOverdueDrug();
        OverdueDrugCondition overdueDrugCondition = new OverdueDrugCondition();
        overdueDrugCondition.setDrugCode(overdueDrug.getDrugCode());
        overdueDrugCondition.setDrugName(overdueDrug.getDrugName());
        List<OverdueDrug> list = overdueDrugService.listOverdueDrug(overdueDrugCondition);
        Assert.assertEquals(1, list.size());
        OverdueDrug overdueDrug1 = new OverdueDrug();
        overdueDrug1.setId(list.get(0).getId());
        overdueDrug1.setDrugCode(UUID.randomUUID().toString());
        overdueDrug1.setDrugName(UUID.randomUUID().toString());
        Long isSuc = overdueDrugService.updateOverdueDrug(overdueDrug1);
        Assert.assertEquals(1,isSuc.intValue());
    }
}

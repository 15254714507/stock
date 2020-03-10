package com.drug.stock.scheduleTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OverdueDrugScheduleTaskTest {
    @Resource
    OverdueDrugScheduleTask overdueDrugScheduleTask;
    @Test
//    @Transactional
    public void Test(){
        overdueDrugScheduleTask.overdueDrugTasks();
    }
}

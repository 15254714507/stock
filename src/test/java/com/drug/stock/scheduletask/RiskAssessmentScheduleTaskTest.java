package com.drug.stock.scheduletask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskAssessmentScheduleTaskTest {

    @Resource
    RiskAssessmentScheduleTask riskAssessmentScheduleTask;

    @Test
    public void riskAssessmentTasksTest() {
        riskAssessmentScheduleTask.riskAssessmentTasks();
    }
}

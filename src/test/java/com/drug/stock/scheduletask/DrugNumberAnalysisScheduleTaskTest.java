package com.drug.stock.scheduletask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugNumberAnalysisScheduleTaskTest {
    @Resource
    DrugNumberAnalysisScheduleTask drugNumberAnalysisScheduleTask;

    @Test
    public void CalendarTest() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.DATE, -30);
        System.out.println(new Timestamp(cale.getTime().getTime()));
    }


    @Test
    public void drugNumberAnalysisTasksTest() {
        drugNumberAnalysisScheduleTask.drugNumberAnalysisTasks();
    }


}

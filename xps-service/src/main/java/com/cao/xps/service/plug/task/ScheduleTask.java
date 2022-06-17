package com.cao.xps.service.plug.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 定时任务类
 */
@Service
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduleTask {


    /*//3.添加定时任务
    //直接指定时间间隔，例如：5秒
    @Scheduled(cron = "0/5 * * * * ?")
    //@Scheduled(fixedRate=5000)

    //每天定时执行（0 1 1 * * ? ==> 秒 分 时 日 月 年）
    @Scheduled(cron="${ffa.callback.batch:0 1 1 * * ?}")//每天凌晨1：1点执行
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }*/
}

package com.otoomo.starter.delayqueue.demo;

import com.otoomo.starter.delayqueue.DelayQueueHolder;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class TestAddPkFinishDelayMessage {
    @Resource
    private DelayQueueHolder redisDelayQueueHolder;

    public void testAddJob() {
        PkFinishDelayMessage message = new PkFinishDelayMessage();
        message.setPkId(124356L);
        //30分钟后，会通过PKFinishDelayQueueExecutor.execute()执行
        redisDelayQueueHolder.addJob(message, 30 * 60, TimeUnit.SECONDS, "PkFinishQueue");
    }
}

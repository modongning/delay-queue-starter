package com.otoomo.starter.delayqueue.demo;

import com.otoomo.starter.delayqueue.DelayQueueHolder;
import com.otoomo.starter.delayqueue.executor.DelayQueueExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 该执行器实现类会在服务启动后，会添加到`DelayQueueExecutorFactory`中进行管理。
 *
 * 不需要在单独调用，只需要实现execute()的业务即可
 */
@Component
public class PkFinishDelayQueueExecutor implements DelayQueueExecutor<PkFinishDelayMessage> {
    //注入延迟队列持有者
    @Resource
    private DelayQueueHolder redisDelayQueueHolder;

    @Override
    public String queueName() {
        return "PkFinishQueue";
    }

    @Override
    public void execute(PkFinishDelayMessage delayMessage) {
        //TODO 处理业务逻辑

        Long pkId = delayMessage.getPkId();
    }

    @Override
    public PkFinishDelayMessage task() throws InterruptedException {
        return redisDelayQueueHolder.task(this.queueName());
    }
}

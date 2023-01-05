package com.otoomo.starter.delayqueue.executor;

import com.otoomo.starter.delayqueue.DelayMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 延迟队列顶层接口
 *
 * @Description
 * @Author modongning
 * @Version
 */
public interface DelayQueueExecutor<T extends DelayMessage> {
    Logger log = LoggerFactory.getLogger(DelayQueueExecutor.class);
    /**
     * 延迟队列名称
     *
     * @return
     */
    String queueName();

    /**
     * 执行业务
     *
     * @param t
     */
    void execute(T t);

    /**
     * 获取延迟消息
     *
     * @return
     * @throws InterruptedException
     */
    T take() throws InterruptedException;

}

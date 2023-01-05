# delay-queue-starter

业务中经常有一些场景是要延迟执行的，所以需要有延迟的消息触发到程序，然后程序自动执行。

这个项目是为了简化项目中用到延迟场景的使用。

使用者不再需要关心延迟消息的实现和触达，只需要关注自己业务实现。

- redisson实现延迟队列 (已实现✅)

- rocketmq实现延迟队列 (未实现)


## 使用方式

所有实现了`DelayQueueExecutor`的实现类都会在服务启动后，会添加到`DelayQueueExecutorFactory`中进行管理。

再由执行器启动器`DelayQueueExecutorRunner`统一执行。

## 0. 添加redis配置
```yaml
spring:
  redis:
    cluster:
      nodes: ${redis.nodes}
    database: 1
    timeout: 30000
    password: ${redis.password}
    enableTransactionSupport: false
```

## 1. 继承 DelayQueueExecutor 基类实现延迟队列执行器

```java
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
```

## 2. 往延迟队列里面添加延迟任务

```java
public class Test {
    @Autowired
    private DelayQueueHolder redisDelayQueueHolder;

    @Test
    public void testAddJob() {
        PkFinishDelayMessage message = new PkFinishDelayMessage();
        //30分钟后，会通过PKFinishDelayQueueExecutor.execute()执行
        redisDelayQueueHolder.addJob(message, 30 * 60, TimeUnit.SECONDS, "PK_TIMEOUT");
    }
}
```
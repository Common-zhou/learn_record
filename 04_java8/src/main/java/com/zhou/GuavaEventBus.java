package com.zhou;

import com.google.common.eventbus.EventBus;
import java.util.concurrent.TimeUnit;

/**
 * guava 的eventbus使用方法
 * @author zhoubing
 * @date 2022-04-13 22:51
 */
public class GuavaEventBus {
    private static final EventBus bus = new EventBus();

    static {
        bus.register(new GuavaSubscriber());
    }

    public static void main(String[] args) throws InterruptedException {
        bus.post(new GuavaSubscriber.StudentEvent(new GuavaSubscriber.Student("zhangsan", "hubei")));

        TimeUnit.SECONDS.sleep(2);

        bus.post(new GuavaSubscriber.TeacherEvent("teacher name."));
    }


}

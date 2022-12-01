package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.common.event.CustomEvent;
import org.springframework.test.common.event.MyCustomEvent;

import java.time.LocalDateTime;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public class EventAndEventListenerTest {

    @Test
    public void testEventListener() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext));
        applicationContext.publishEvent(new MyCustomEvent(applicationContext, "now time is " + LocalDateTime.now()));

        applicationContext.registerShutdownHook();//或者applicationContext.close()主动关闭容器;
    }
}

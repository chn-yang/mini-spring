package org.springframework.test.common.event;

import org.springframework.context.ApplicationListener;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public class MyCustomEventListener implements ApplicationListener<MyCustomEvent> {

    @Override
    public void onApplicationEvent(MyCustomEvent event) {
        System.out.println(this.getClass().getName() + " data is : " + event.getData());
    }
}

package org.springframework.test.common.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public class MyCustomEvent extends ApplicationContextEvent {

    private final String data;

    public MyCustomEvent(ApplicationContext source, String data) {
        super(source);
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

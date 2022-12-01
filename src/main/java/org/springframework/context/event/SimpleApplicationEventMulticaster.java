package org.springframework.context.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

	public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
		setBeanFactory(beanFactory);
	}

	@Override
	public void multicastEvent(ApplicationEvent event) {
		for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
			if (supportsEvent(applicationListener, event)) {
				applicationListener.onApplicationEvent(event);
			}
		}
	}

	/**
	 * 监听器是否对该事件感兴趣
	 *
	 * @param applicationListener
	 * @param event
	 * @return
	 */
	protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
		Type type = applicationListener.getClass().getGenericInterfaces()[0];   //获取监听器所属类的泛型接口
		Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];   //获取泛型接口的实际类型
		String className = actualTypeArgument.getTypeName();    //获取泛型接口的实际类型的类名
		Class<?> eventClassName;
		try {
			eventClassName = Class.forName(className);  //用事件的类名获取类
		} catch (ClassNotFoundException e) {
			throw new BeansException("wrong event class name: " + className);
		}
		return eventClassName.isAssignableFrom(event.getClass());   //判断事件对象是否来自该类, 是则说明 该监听器监听当前事件
	}
}

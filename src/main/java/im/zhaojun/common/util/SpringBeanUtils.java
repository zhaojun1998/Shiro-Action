package im.zhaojun.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Spring Bean 工具类
 */
@Component
public final class SpringBeanUtils implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringBeanUtils.beanFactory = beanFactory;
    }

    public static Object getBean(String name) throws BeansException {
        return beanFactory.getBean(name);
    }

    public static <T> T getBean(Class<T> clz) throws BeansException {
        return beanFactory.getBean(clz);
    }

    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }
}

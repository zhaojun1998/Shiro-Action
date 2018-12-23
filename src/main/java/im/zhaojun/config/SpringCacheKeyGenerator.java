package im.zhaojun.config;

import cn.hutool.json.JSONUtil;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

@Component //标记为组件
public class SpringCacheKeyGenerator implements KeyGenerator {

    private final static int NO_PARAM_KEY = 0;

    @Override
    public Object generate(Object target, Method method, Object... params) {

        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getName());
        sb.append(method.getName());
        sb.append("[");
        for (int i = 0; i < params.length; i++) {
            Object object = params[i];
            if (isSimpleValueType(object.getClass())) {
                sb.append(object);
                if (i != params.length - 1) {
                    sb.append(", ");
                }
            } else {
                // 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
                sb.append(JSONUtil.toJsonStr(object).hashCode());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 判断是否是简单值类型.包括：基础数据类型、CharSequence、Number、Date、URL、URI、Locale、Class;
     */
    public static boolean isSimpleValueType(Class<?> clazz) {
        return (ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz)
                || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || URI.class == clazz
                || URL.class == clazz || Locale.class == clazz || Class.class == clazz);
    }
}


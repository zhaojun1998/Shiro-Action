package im.zhaojun.common.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = new HashMap<>();
        Object code = webRequest.getAttribute("code", RequestAttributes.SCOPE_REQUEST);
        Object message = webRequest.getAttribute("msg", RequestAttributes.SCOPE_REQUEST);
        map.put("code", code);
        map.put("msg", message);
        return map;
    }
}

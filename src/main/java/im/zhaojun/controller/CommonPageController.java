package im.zhaojun.controller;

import im.zhaojun.model.vo.UrlVO;
import im.zhaojun.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class CommonPageController {

    @Resource
    private WebApplicationContext applicationContext;

    @GetMapping("/403")
    public String forbidden() {
        return "error/403";
    }

    @GetMapping("/404")
    public String unauthorizedPage() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error() {
        return "error/500";
    }

    /**
     * 获取 @RequestMapping 中配置的所有 URL.
     * @param keyword   关键字: 过滤条件
     * @return          URL 列表.
     */
    @GetMapping("/system/urls")
    @ResponseBody
    public ResultBean getUrl(@RequestParam(defaultValue = "") String keyword) {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        Set<UrlVO> urlSet = new HashSet<>();

        for (RequestMappingInfo info : map.keySet()) {
            PatternsRequestCondition p = info.getPatternsCondition();
            // 一个 @RequestMapping, 可能有多个 URL.
            for (String url : p.getPatterns()) {
                // 根据 keyword 过滤 URL
                if (url.contains(keyword)) {

                    // 获取这个 URL 支持的所有 http method, 多个以逗号分隔, 未配置返回 ALL.
                    Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
                    String type = "ALL";
                    if (methods.size() != 0) {
                        type = StringUtils.collectionToDelimitedString(methods, ",");
                    }
                    urlSet.add(new UrlVO(url, type));
                }
            }
        }
        return ResultBean.success(urlSet);
    }
}
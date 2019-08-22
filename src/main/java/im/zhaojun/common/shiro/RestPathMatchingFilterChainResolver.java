package im.zhaojun.common.shiro;

import im.zhaojun.common.util.WebHelper;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RestPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {

    private static final Logger log = LoggerFactory.getLogger(RestPathMatchingFilterChainResolver.class);

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (!filterChainManager.hasChains()) {
            return null;
        }

        String requestURI = getPathWithinApplication(request);

        // the 'chain names' in this implementation are actually path patterns defined by the user.  We just use them
        // as the chain name for the FilterChainManager's requirements
        for (String pathPattern : filterChainManager.getChainNames()) {

            String[] pathPatternArray = pathPattern.split("==");

            boolean httpMethodMatchFlag = true;

            if (pathPatternArray.length > 1) {
                httpMethodMatchFlag = pathPatternArray[1].equals(WebHelper.getRequestHTTPMethod());
            }

            // 只用过滤器链的 URL 部分与请求的 URL 进行匹配
            if (pathMatches(pathPatternArray[0], requestURI) && httpMethodMatchFlag) {
                if (log.isTraceEnabled()) {
                    log.trace("Matched path pattern [" + pathPattern + "] for requestURI [" + requestURI + "].  " +
                            "Utilizing corresponding filter chain...");
                }
                return filterChainManager.proxy(originalChain, pathPattern);
            }
        }

        return null;
    }
}
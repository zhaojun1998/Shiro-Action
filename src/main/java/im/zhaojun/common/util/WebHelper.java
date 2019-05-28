package im.zhaojun.common.util;

import cn.hutool.json.JSONUtil;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class WebHelper {

    /**
     * 是否是Ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        return requestedWith != null && "XMLHttpRequest".equalsIgnoreCase(requestedWith);
    }

    /**
     * 输出JSON
     */
    public static void writeJson(Object object, ServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(JSONUtil.toJsonStr(object));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
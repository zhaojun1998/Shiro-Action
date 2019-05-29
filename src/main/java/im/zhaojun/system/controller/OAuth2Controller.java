package im.zhaojun.system.controller;

import im.zhaojun.common.constants.AuthcTypeEnum;
import im.zhaojun.common.shiro.OAuth2ClientProperties;
import im.zhaojun.common.shiro.OAuth2Helper;
import im.zhaojun.common.util.ResultBean;
import im.zhaojun.common.util.ShiroUtil;
import im.zhaojun.system.model.UserAuths;
import im.zhaojun.system.model.vo.OAuth2VO;
import im.zhaojun.system.service.UserAuthsService;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("oauth2")
public class OAuth2Controller {

    @Resource
    private OAuth2Helper oAuth2Helper;

    @Resource
    private UserAuthsService userAuthsService;

    /**
     * 生成 Github 授权地址
     */
    @GetMapping("/render/github")
    @ResponseBody
    public ResultBean renderGithubAuth(HttpServletResponse response) {
        AuthRequest authRequest = oAuth2Helper.getAuthRequest(AuthcTypeEnum.GITHUB);
        return ResultBean.successData(authRequest.authorize());
    }

    /**
     * 生成 Gitee 授权地址
     */
    @GetMapping("/render/gitee")
    @ResponseBody
    public ResultBean renderGiteeAuth(HttpServletResponse response) {
        AuthRequest authRequest = oAuth2Helper.getAuthRequest(AuthcTypeEnum.GITEE);
        return ResultBean.successData(authRequest.authorize());
    }

    @GetMapping("/index")
    public String index() {
        return "oauth2/oauth2-list";
    }


    @GetMapping("/bind/{provider}")
    public String add(@PathVariable("provider") AuthcTypeEnum authcTypeEnum) {
        OAuth2ClientProperties.Provider provider = oAuth2Helper.getProvider(authcTypeEnum);

        return "";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultBean list() {

        List<OAuth2VO> authsList = new ArrayList<>();

        for (AuthcTypeEnum type : AuthcTypeEnum.values()) {
            UserAuths auth = userAuthsService.selectOneByIdentityTypeAndUserId(type, ShiroUtil.getCurrentUser().getUserId());

            OAuth2VO oAuth2VO = new OAuth2VO();

            oAuth2VO.setType(type.getDescription());
            oAuth2VO.setStatus(auth == null ?  "unbind" : "bind");
            oAuth2VO.setUsername(auth == null ? "" : auth.getIdentifier());
            authsList.add(oAuth2VO);
        }

        return ResultBean.success(authsList);
    }

    /**
     * 取消授权
     */
    @PostMapping("/revoke/github")
    @ResponseBody
    public Object revokeAuth() {
        UserAuths userAuths = userAuthsService.selectOneByIdentityTypeAndUserId(AuthcTypeEnum.GITHUB, ShiroUtil.getCurrentUser().getUserId());

        if (userAuths == null) {
            return ResultBean.error("已经是未绑定状态!");
        }

        userAuthsService.deleteByPrimaryKey(userAuths.getId());
        return ResultBean.success();
    }

    @GetMapping("/success")
    public String success() {
        return "oauth2/authorize-success";
    }

    @GetMapping("/error")
    public String error() {
        return "oauth2/authorize-error";
    }

}

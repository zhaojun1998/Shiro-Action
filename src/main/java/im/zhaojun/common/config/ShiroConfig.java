package im.zhaojun.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import im.zhaojun.common.shiro.EnhanceModularRealmAuthenticator;
import im.zhaojun.common.shiro.OAuth2Helper;
import im.zhaojun.common.shiro.RestShiroFilterFactoryBean;
import im.zhaojun.common.shiro.ShiroActionProperties;
import im.zhaojun.common.shiro.credential.RetryLimitHashedCredentialsMatcher;
import im.zhaojun.common.shiro.filter.OAuth2AuthenticationFilter;
import im.zhaojun.common.shiro.filter.RestAuthorizationFilter;
import im.zhaojun.common.shiro.filter.RestFormAuthenticationFilter;
import im.zhaojun.common.shiro.realm.OAuth2GiteeRealm;
import im.zhaojun.common.shiro.realm.OAuth2GithubRealm;
import im.zhaojun.common.shiro.realm.UserNameRealm;
import im.zhaojun.system.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.Arrays;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Resource
    private OAuth2Helper oAuth2Helper;

    @Lazy
    @Resource
    private ShiroService shiroService;

    @Resource
    private ShiroActionProperties shiroActionProperties;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Bean
    public RestShiroFilterFactoryBean restShiroFilterFactoryBean(SecurityManager securityManager) {
        RestShiroFilterFactoryBean shiroFilterFactoryBean = new RestShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new RestFormAuthenticationFilter());
        filters.put("perms", new RestAuthorizationFilter());
        filters.put("oauth2Authc", new OAuth2AuthenticationFilter(oAuth2Helper));

        Map<String, String> urlPermsMap = shiroService.getUrlPermsMap();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(urlPermsMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealms(Arrays.asList(userNameRealm(), oAuth2GithubRealm(), oAuth2GiteeRealm()));
        ModularRealmAuthenticator authenticator = new EnhanceModularRealmAuthenticator();
        securityManager.setAuthenticator(authenticator);
        authenticator.setRealms(Arrays.asList(userNameRealm(), oAuth2GithubRealm(), oAuth2GiteeRealm()));
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    /**
     * Github 登录 Realm
     */
    @Bean
    public OAuth2GithubRealm oAuth2GithubRealm() {
        return new OAuth2GithubRealm();
    }

    /**
     * Gitee 登录 Realm
     */
    @Bean
    public OAuth2GiteeRealm oAuth2GiteeRealm() {
        return new OAuth2GiteeRealm();
    }

    /**
     * 用户名密码登录 Realm
     */
    @Bean
    public UserNameRealm userNameRealm() {
        UserNameRealm userNameRealm = new UserNameRealm();
        userNameRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userNameRealm.setCacheManager(redisCacheManager());
        return userNameRealm;
    }

    /**
     * 用户名密码登录密码匹配器
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        return new RetryLimitHashedCredentialsMatcher("md5");
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setExpire(shiroActionProperties.getPermsCacheTimeout() == null ? 3600 : shiroActionProperties.getPermsCacheTimeout());
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost + ":" + redisPort);
        return redisManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setExpire(shiroActionProperties.getSessionTimeout() == null ? 1800 : shiroActionProperties.getSessionTimeout());
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionInMemoryEnabled(false);
        return redisSessionDAO;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
}
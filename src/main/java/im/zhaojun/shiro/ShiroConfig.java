package im.zhaojun.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import im.zhaojun.service.MenuService;
import im.zhaojun.shiro.realm.UserNameRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ShiroConfig {

    @Resource
    private MenuService menuService;

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(menuService.loadFilterChainDefinitions());
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userNameRealm());
        return securityManager;
    }

    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public UserNameRealm userNameRealm() {
        UserNameRealm userNameRealm = new UserNameRealm();
        userNameRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userNameRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        return new HashedCredentialsMatcher("md5");
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
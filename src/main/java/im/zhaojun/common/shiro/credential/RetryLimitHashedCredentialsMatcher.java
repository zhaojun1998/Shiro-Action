package im.zhaojun.common.shiro.credential;

import im.zhaojun.common.shiro.ShiroActionProperties;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MD5 密码匹配器
 *
 * 密码校验失败后计数, 当超出 ${shiro-action.retry-count} 次后, 禁止登录 ${shiro-action.retry-timeout} 秒.
 */
public class RetryLimitHashedCredentialsMatcher extends
		HashedCredentialsMatcher {
 
	private Cache<String, AtomicInteger> passwordRetryCache;

	@Resource(name = "redisCacheManager")
	private CacheManager cacheManager;

	@Resource
	private ShiroActionProperties shiroActionProperties;

	public RetryLimitHashedCredentialsMatcher(String hashAlgorithmName) {
		super(hashAlgorithmName);
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {

		if (passwordRetryCache == null) {
			passwordRetryCache = cacheManager.getCache("passwordRetryCache");
		}

		String username = (String) token.getPrincipal();

		// 超级管理员不进行登录次数校验.
		if (!shiroActionProperties.getSuperAdminUsername().equals(username)) {

			AtomicInteger retryCount = passwordRetryCache.get(username);
			if (retryCount == null) {
				retryCount = new AtomicInteger(0);
			}

			if (retryCount.incrementAndGet() > shiroActionProperties.getRetryCount()) {
				throw new ExcessiveAttemptsException();
			}

			passwordRetryCache.put(username, retryCount);
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			passwordRetryCache.remove(username);
		}
		return matches;
	}

}
package im.zhaojun.shiro.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;
 
public class RetryLimitHashedCredentialsMatcher extends
		HashedCredentialsMatcher {
 
	private Cache<String, AtomicInteger> passwordRetryCache;

	@Resource(name = "redisCacheManager")
	public CacheManager cacheManager;

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
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
		}
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		passwordRetryCache.put(username, retryCount);

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			passwordRetryCache.remove(username);
		}
		return matches;
	}

}
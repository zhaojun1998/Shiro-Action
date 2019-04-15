package im.zhaojun.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShiroUtil {


    public static final String USER_LOCK = "0";

    private static String superAdminUsername;

    @Value("${security.retry.count}")
    private static Integer passwordRetryCount;

    public static String getSuperAdminUsername() {
        return superAdminUsername;
    }

    public static Integer getPasswordRetryCount() {
        return passwordRetryCount;
    }

    @Value("${security.super-admin.username}")
    public void setSuperAdminUsername(String superAdminUsername) {
        this.superAdminUsername = superAdminUsername;
    }

    @Value("${security.retry.count}")
    public void setPasswordRetryCount(Integer passwordRetryCount) {
        this.passwordRetryCount = passwordRetryCount;
    }
}

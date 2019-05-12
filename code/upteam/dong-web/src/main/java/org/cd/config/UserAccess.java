package org.cd.config;

import org.springframework.stereotype.Component;

/**
 * @classname: UserAccess
 * @description:
 * @author: Danny Chen
 * @create: 2019-05-12 19:05
 */
@Component
public class UserAccess {

    public boolean hasRole(String role) {
        if ("Read".equalsIgnoreCase(role)) {
            return true;
        }
        return false;
    }
}

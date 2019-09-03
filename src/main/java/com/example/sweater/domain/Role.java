
package com.example.sweater.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role.
 *
 * @author Taras
 */
public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
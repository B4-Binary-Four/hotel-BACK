package com.codingpals.hotel.security.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CLIENT ,
    ADMIN ;

    public String getRole(){
        return name();
    }
    @Override
    public String getAuthority() {
        return "ROLE_" + getRole();
    }

    @Override
    public String toString() {
        return this.name();
    }
}

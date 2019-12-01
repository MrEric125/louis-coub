package com.oauth2.provider.token;

/**
 * @author John·Louis
 * @date create in 2019/4/14
 */
public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }

}

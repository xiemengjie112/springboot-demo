package com.xmj.springbootdemo.weChat.entity;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2019/3/3 16:21
 */
public class AccessToken {


    private String token;
    //有效时间
    private int expiresIn;

    public AccessToken() {
    }

    public String getToken() {
        return this.token;
    }

    public int getExpiresIn() {
        return this.expiresIn;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AccessToken)) return false;
        final AccessToken other = (AccessToken) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$token = this.token;
        final Object other$token = other.token;
        if (this$token == null ? other$token != null : !this$token.equals(other$token)) return false;
        if (this.expiresIn != other.expiresIn) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AccessToken;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $token = this.token;
        result = result * PRIME + ($token == null ? 43 : $token.hashCode());
        result = result * PRIME + this.expiresIn;
        return result;
    }

    public String toString() {
        return "AccessToken(token=" + this.token + ", expiresIn=" + this.expiresIn + ")";
    }
}

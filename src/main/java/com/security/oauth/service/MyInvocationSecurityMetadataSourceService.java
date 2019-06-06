package com.security.oauth.service;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;

/**
 * @ClassName MyInvocationSecurityMetadataSourceService
 * @Description 用来储存请求与权限的对应关系
 * @Author zzm
 * @Data 2019/6/6 12:13
 * @Version 1.0
 */
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    /**
     * 返回请求的资源需要的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

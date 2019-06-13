package com.security.oauth.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName MyAccessDecisionManager
 * @Description 决策器-----AccessDecisionManager是由AbstractSecurityInterceptor调用的，它负责鉴定用户是否有访问对应资源（方法或URL）的权限
 * @Author zzm
 * @Data 2019/6/12 19:40
 * @Version 1.0
 */
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * 通过传递的参数来决定用户是否有访问对应受保护对象的权限
     *
     * @param authentication 包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities。
     * @param object  就是FilterInvocation对象，可以得到request等web资源
     * @param configAttributes configAttributes是本次访问需要的权限
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(configAttributes)) {
            return;
        } else {
            List<String> needRoles = configAttributes.stream().map(configAttribute -> StringUtils.trim(configAttribute.getAttribute())).collect(Collectors.toList());
            log.info("【需要访问的权限】 = {}", needRoles);
            log.info("【authentication】 = {}", authentication.getAuthorities());
            //是否有权限
            boolean isMatch = authentication.getAuthorities().stream().anyMatch(ga -> needRoles.contains(StringUtils.trim(ga.getAuthority())));
            if (isMatch) {
                log.info("【有权访问】");
                return;
            } else {
                log.info("【没有权限访问】");
                throw new AccessDeniedException("当前访问没有权限");
            }
/*            authentication.getAuthorities().stream().forEach(ga -> {
                log.info("【遍历权限】 = {}", ga.getAuthority());
                if (needRoles.contains(StringUtils.trim(ga.getAuthority()))) {
                    log.info("【有权访问】");
                    return;
                }
            });*/

        }
    }

    /**
     * 表示此AccessDecisionManager是否能够处理传递的ConfigAttribute呈现的授权请求
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    /**
     * 表示当前AccessDecisionManager实现是否能够为指定的安全对象（方法调用或Web请求）提供访问控制决策
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

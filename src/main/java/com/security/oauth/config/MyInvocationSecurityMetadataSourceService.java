package com.security.oauth.config;

import com.security.oauth.domain.RolePermissonData;
import com.security.oauth.repository.RolePermissonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName MyInvocationSecurityMetadataSourceService
 * @Description 用来储存请求与权限的对应关系
 * @Author zzm
 * @Data 2019/6/6 12:13
 * @Version 1.0
 */
@Slf4j
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private RolePermissonRepository rolePermissonRepository;

    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map =null;

    /**
     * 当接收到一个http请求时, filterSecurityInterceptor会调用的方法. 参数object是一个包含url信息的HttpServletRequest实例. 这个方法要返回请求该url所需要的所有权限集合。
     * 返回请求的资源需要的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(map)) {
            loadResourceDefine();
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        //使用URL匹配器AntPathRequestMatcher来匹配对应url的权限信息
        Set<String> urls = map.keySet().stream().filter(url -> new AntPathRequestMatcher(url).matches(request)).collect(Collectors.toSet());
        if (!CollectionUtils.isEmpty(urls)) {
            return map.get(urls.iterator().next());
        }
        return null;
    }

    /**
     * 初始化所有资源对应的角色
     */
    private void loadResourceDefine() {
        map = new HashMap<>();
        List<RolePermissonData> rolePermissons = rolePermissonRepository.getRolePermissions();

        //遍历角色权限信息，并存进map里。
        rolePermissons.stream().forEach(rolePermisson -> {
            String url = rolePermisson.getUrl();
            String roleName = rolePermisson.getRoleName();
            log.info("【roleName】 = {}", roleName);
            ConfigAttribute role = new SecurityConfig(roleName);
            //如果map里已存在该url，则往里添加，如果没有，则创建一个新的List<ConfigAttribute>放进map里
            if (map.containsKey(url)) {
                map.get(url).add(role);
            } else {
                List<ConfigAttribute> list = new ArrayList<>();
                list.add(role);
                map.put(url, list);
            }
        });
        log.info("【map】 = {}", map);

    }

    /**
     * Spring容器启动时自动调用, 一般把所有请求与权限的对应关系也要在这个方法里初始化, 保存在一个属性变量里
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 该类是否能够为指定的方法调用或Web请求提供ConfigAttributes
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

package com.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName MyFilterSecurityInterceptor
 * @Description 每种受支持的安全对象类型（方法调用或Web请求）都有自己的拦截器类。AbstractSecurityInterceptor是一个实现了对受保护对象的访问进行拦截的抽象类
 * AbstractSecurityInterceptor的机制：1. 查找与当前请求关联的配置属性（简单的理解就是权限）
 * 2. 将 安全对象（方法调用或Web请求）、当前身份验证、配置属性 提交给决策器（AccessDecisionManager）  3. （可选）更改调用所根据的身份验证
 * 4. 允许继续进行安全对象调用(假设授予了访问权)  5. 在调用返回之后，如果配置了AfterInvocationManager。如果调用引发异常，则不会调用AfterInvocationManager。
 * @Author zzm
 * @Data 2019/6/12 19:51
 * @Version 1.0
 */
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    /**
     * 注入决策器
     * 将安全对象（方法调用或Web请求）、当前身份验证、配置属性 提交给决策器
     * @param myAccessDecisionManager
     */
    @Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }


    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

}

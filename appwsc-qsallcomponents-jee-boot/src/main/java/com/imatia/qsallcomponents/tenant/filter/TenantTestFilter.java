package com.imatia.qsallcomponents.tenant.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.imatia.qsallcomponents.tenant.TenantContextHolder;

@Component
@Order(1)
public class TenantTestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        TenantContextHolder.setTenant("tenant_2");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

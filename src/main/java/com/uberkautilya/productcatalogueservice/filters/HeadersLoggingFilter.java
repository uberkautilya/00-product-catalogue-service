package com.uberkautilya.productcatalogueservice.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Order(0)
@Component
public class HeadersLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        for (String headerName : httpResponse.getHeaderNames()) {
            String headerValue = httpRequest.getHeader(headerName);
            log.info("{}: {}", headerName, headerValue);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

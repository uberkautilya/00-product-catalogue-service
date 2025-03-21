package com.uberkautilya.productcatalogueservice.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class AddHeaderFilter extends OncePerRequestFilter implements Ordered {

    private final ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(request) {
            private final String customHeaderName = "Header_added_from_AddHeaderFilter";
            private final String customHeaderValue = "Value_for_Header_added_from_AddHeaderFilter";

            @Override
            public Enumeration<String> getHeaderNames() {
                List<String> headerNameList = Collections.list(super.getHeaderNames());
                headerNameList.add(customHeaderName);
                return Collections.enumeration(headerNameList);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
                if (customHeaderName.equalsIgnoreCase(name)) {
                    return Collections.enumeration(Collections.singletonList(customHeaderValue));
                }
                return super.getHeaders(name);
            }

            @Override
            public String getHeader(String name) {
                if (customHeaderName.equalsIgnoreCase(name)) {
                    return customHeaderValue;
                }
                return super.getHeader(name);
            }
        };
        filterChain.doFilter(httpServletRequestWrapper, response);
    }

    @Override
    public int getOrder() {
        if (applicationContext.containsBean("headersLoggingFilter")) {
            //HeadersLoggingFilter has an order of 0. This filter would thus be placed before that filter
            return -1;
        }
        return LOWEST_PRECEDENCE;
    }
}

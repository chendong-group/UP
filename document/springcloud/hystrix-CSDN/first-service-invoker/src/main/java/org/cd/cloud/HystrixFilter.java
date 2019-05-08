package org.cd.cloud;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @classname: HystrixFilter
 * @description:
 * @author: Danny Chen
 * @create: 2019-04-03 23:40
 */
@WebFilter(urlPatterns = "/*", filterName = "hystrixFilter")
public class HystrixFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            context.shutdown();
        }
    }

    @Override
    public void destroy() {

    }
}

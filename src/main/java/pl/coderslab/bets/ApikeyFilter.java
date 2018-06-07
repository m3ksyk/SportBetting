package pl.coderslab.bets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import pl.coderslab.bets.entity.Apikey;
import pl.coderslab.bets.service.ApikeyService;
import pl.coderslab.bets.serviceImpl.ApikeyServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filters requests sent to api. Only requests containing vali api key are allowed
 */

@WebFilter(urlPatterns = "/api/*")
public class ApikeyFilter implements Filter {

    @Autowired
    ApikeyService apikeyService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        //forces injection of autowired service
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String value = String.valueOf(req.getParameter("apikey"));

        if (this.apikeyService.findApiKeyByValue(value) == null) {
            res.sendError(400, "Bad request: apikey not found or wrong apikey");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
